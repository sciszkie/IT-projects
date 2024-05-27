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
import scanner_func
import scanner_app
import network

path = './gvm/gvmd/gvmd.sock'
connection = UnixSocketConnection(path=path)
gmp = Gmp(connection)
gmp.connect()
gmp.authenticate('admin','admin')

def new_credential(gmp):
    credentials = CredentialType("up")
    credential=gmp.create_credential("default_credentials", credential_type=credentials,login="admin")
    return(credential)

def new_target(gmp,hosts):
    current_datetime=datetime.now()
    current_datetime_str=current_datetime.strftime("%Y-%m-%d %H:%M:%S")
    port_range=input("Podaj zakres portow - wcisnij enter dla listy domyslnej (1-1000), dla niestandardowej wpisz ja w formacie 'x-y'")
    if(port_range==""):
    	port_range="1-1000"
    target=gmp.create_target("target"+current_datetime_str, hosts=hosts, port_range=port_range)
    return(target)

def new_scanner(credential_id,gmp):
    scanner_type = ScannerType("2")
    scanner=gmp.create_scanner("default_scanner", scanner_type=scanner_type, host='localhost',port=9393,credential_id=credential_id)
    return(scanner)

def new_schedule(cal,gmp):
    current_datetime=datetime.now()
    current_datetime_str=current_datetime.strftime("%Y-%m-%d %H:%M:%S")
    schedule=gmp.create_schedule(name="schedule"+current_datetime_str,icalendar=cal.to_ical(), timezone='Europe/Warsaw')
    return(schedule)

def new_task(target_id,scanner_id,schedule_id,gmp):
    task_name=input("Podaj nazwe skanu: ")
    task=gmp.create_task(task_name, config_id='daba56c8-73ec-11df-a475-002264764cea', target_id=target_id, scanner_id=scanner_id,schedule_id=schedule_id)
    return(task)

def interaction(gmp):
    credential=new_credential(gmp)
    credential_id=scanner_func.get_credential_id(gmp)
    while(True):
        target_type=input("Wybierz czy chcesz wykonac skan calej sieci (S) czy wybranych hostow (H): ")
        if(target_type=="H"):
            hosts=[]
            host=input("Podaj adres IP  hosta: ")
            hosts.append(host)
            print(hosts)
            break
        elif (target_type=="S"):
            hosts=network.network_scan()
            print(hosts)
            break
        else:
            print("Bledny wybor!")
    target=new_target(gmp,hosts)
    target_id=scanner_func.get_target_id(gmp)
    scanner=new_scanner(credential_id,gmp)
    scanner_id=scanner_func.get_scanner_id(gmp) 
    while(True):
        schedule_type=input("Podaj czy chcesz wykonac skan jednorazowy (J) czy zaplanowac skanowanie cykliczne (C): ")
        if(schedule_type=="C"or schedule_type=="J"):
            cal=scanner_func.new_cal(schedule_type)
            break
        else:
            print("Bledny wybor!")
    schedule=new_schedule(cal,gmp)
    schedule_id=scanner_func.get_schedule_id(gmp)
    task=new_task(target_id,scanner_id,schedule_id,gmp)
    task_ids=scanner_func.get_task_ids(gmp)



def main ():
    interaction(gmp)
    scanner_app.main()
if __name__=="__main__":
    main()   


