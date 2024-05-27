import xml.etree.ElementTree as ET
from base64 import b64decode
from pathlib import Path
from gvm.connections import UnixSocketConnection
import gvm
from gvm.protocols.gmpv208 import CredentialType, AlertCondition, AlertEvent, AlertMethod
from gvm.protocols.gmpv224.entities import scan_configs
from gvm.protocols.latest import Gmp
from gvm.xml import pretty_print
from gvm.protocols.latest import ScannerType
from gvm.protocols.latest import CredentialType
import pytz
import time
from datetime import datetime
from icalendar import Calendar, Event,vRecur
import smtplib
from email.mime.application import MIMEApplication
from email.mime.multipart import MIMEMultipart
from email.mime.text import MIMEText
import os
import sys
script_dir = '/home/ubuntu/greenbone-community-container'
sys.path.append(script_dir)

path = '/home/ubuntu/greenbone-community-container/gvm/gvmd/gvmd.sock'
connection = UnixSocketConnection(path=path)

def connect_gmp():
    if os.path.exists(path):
        gmp = Gmp(connection)
        gmp.connect()
        gmp.authenticate('admin', 'admin')
        return gmp
    else:
        print(f"Socket {path} does not exist")
        return None
        
def download_report(gmp,report_id):
   current_report = gmp.get_report(report_id=report_id, report_format_id='c402cc3e-b531-11e1-9163-406186ea4fc5')
   report_XML_tree = ET.fromstring(current_report)
   report_element = report_XML_tree.find("report")
   content=report_element.find("report_format").tail
   binary_base64_encoded_pdf = content.encode('ascii')
   binary_pdf = b64decode(binary_base64_encoded_pdf)
   pdf_path = Path('report.pdf').expanduser()
   pdf_path.write_bytes(binary_pdf)
   print("PDF report created")
def mail_send():
    subject = "PDF REPORT"
    body = "This is your OpenVAS scan report."
    sender = "bso24lscan@gmail.com"
    recipients = "stas.ciszkiewicz@gmail.com"
    password = "dqbxkrffoyblrbzn"

    msg = MIMEMultipart()
    msg['Subject'] = subject
    msg['From'] = sender
    msg['To'] = recipients

    msg.attach(MIMEText(body, 'plain'))
 
    file = "report.pdf"
    with open(file, "rb") as attachment:
        part = MIMEApplication(attachment.read(), _subtype="pdf")
        part.add_header('Content-Disposition', 'attachment', filename=file)
        msg.attach(part)
    with smtplib.SMTP_SSL('smtp.gmail.com', 465) as smtp_server:
        smtp_server.login(sender, password)
        smtp_server.sendmail(sender, recipients, msg.as_string())

    print("Message sent!")

def get_report_ids(gmp):    
    report_XML = gmp.get_reports()
    print(report_XML)
    report_XML_tree = ET.fromstring(report_XML)
    report_ids = []
    for report in report_XML_tree.findall('report'):
        report_id = report.attrib.get('id')
        if report_id:
            report_ids.append(report_id)
    
    print("Report ids: ", report_ids)
    return report_ids
    
def get_report_scan_status(gmp):    
    report_XML = gmp.get_reports()
    report_XML_tree = ET.fromstring(report_XML)
    
    report_statuses = []
    
    for report in report_XML_tree.findall('.//report'):
        scan_run_status = report.find('scan_run_status')
        if scan_run_status is not None:
            report_statuses.append(scan_run_status.text)
    
    print("Scan run statuses: ", report_statuses)
    return report_statuses

def main():
  gmp = connect_gmp()
  if gmp:
     report_ids = get_report_ids(gmp)
     report_statuses = get_report_scan_status(gmp)
     if report_statuses is not None:
         if report_statuses[-1] == "Done":
             download_report(gmp,report_ids[-1])
             mail_send()
             gmp.delete_report(report_ids[-1])
  else:
     print("Could not connect to GVM server, socket does not exist.")

if __name__=="__main__":
    main()  
