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
from datetime import datetime, timedelta
from icalendar import Calendar, Event,vRecur
import smtplib 
from email.mime.application import MIMEApplication
from email.mime.multipart import MIMEMultipart
from email.mime.text import MIMEText 

path = './gvm/gvmd/gvmd.sock'
connection = UnixSocketConnection(path=path)
gmp = Gmp(connection)


def get_credential_id(gmp):
    credential_XML=gmp.get_credentials()
    credential_XML_tree = ET.fromstring(credential_XML)
    last_created_time=None
    last_created_credential_id=None
    for credential in credential_XML_tree.findall('credential'):
        mod_time=credential.find('modification_time').text
        if(last_created_time is None or last_created_time<mod_time):
            last_created_time=mod_time
            last_created_credential_id=credential.attrib['id']
    #print("Credential IDs:", credential_ids)
    return(last_created_credential_id)

def get_scanner_id(gmp):
    scanner_XML=gmp.get_scanners()
    scanner_XML_tree = ET.fromstring(scanner_XML)
    scanner_ids=[]
    for scanner in scanner_XML_tree.findall('scanner'):
        scanner_ids.append(scanner.attrib['id'])
    scanner_id=scanner_ids[len(scanner_ids)-1]
    #print("Scanner IDs:", scanner_ids)
    return(scanner_id)

def get_target_id(gmp):
    target_XML=gmp.get_targets()
    target_XML_tree = ET.fromstring(target_XML)
    last_created_time=None
    last_created_target_id=None
    for target in target_XML_tree.findall('target'):
        mod_time=target.find('modification_time').text
        if(last_created_time is None or last_created_time<mod_time):
            last_created_time=mod_time
            last_created_target_id=target.attrib['id']
    #print("Target IDs:", target_ids)
    return(last_created_target_id)

def get_port_list_id(gmp):
    port_list_XML=gmp.get_port_lists()
    port_list_XML_tree = ET.fromstring(port_list_XML)
    last_created_time=None
    last_created_port_list_id=None
    for port_list in port_list_XML_tree.findall('port_list'):
        mod_time=port_list.find('modification_time').text
        if(last_created_time is None or last_created_time<mod_time):
            last_created_time=mod_time
            last_created_port_list_id=port_list.attrib['id']
    return(last_created_port_list_id)
def get_task_names(gmp):
    task_XML=gmp.get_tasks()

    task_XML_tree = ET.fromstring(task_XML)
    task_names=[]
    for task in task_XML_tree.findall('task'):
        task_name=task.find('name').text
        task_names.append(task_name)
    print("Task names:", task_names)
    return(task_names)

def get_task_name(task_id,gmp):
    task_XML=gmp.get_task(task_id)
    task_XML_tree = ET.fromstring(task_XML)
    for task in task_XML_tree.findall('task'):
        task_name=task.find('name').text
    return(task_name)
    
def get_target_ports(target_id,gmp):
    target_XML=gmp.get_target(target_id)
    target_XML_tree = ET.fromstring(target_XML)
    for target in target_XML_tree.findall('target'):
        ports=target.find('port_list')
        target_ports_id=ports.attrib['id']
    print("Target ports id:", target_ports_id)
    return(target_ports_id)

def get_target_id_from_ip(gmp,ip):
    target_XML=gmp.get_targets()
    target_XML_tree = ET.fromstring(target_XML)
    id=None
    for target in target_XML_tree.findall('target'):
        ip_to_check=target.find('hosts').text
        if(ip_to_check==ip):
            id=target.attrib['id']
    return(id)
 
def get_target_ip(gmp,target_id):
    target_XML=gmp.get_target(target_id)
    target_XML_tree = ET.fromstring(target_XML)
    ip=[]
    for target in target_XML_tree.findall('target'):
        if(target!=None):
            ip.append(target.find('hosts').text)
    return(ip)

def task_info_ids(task_id,gmp):
    task_XML=gmp.get_task(task_id)
    task_XML_tree = ET.fromstring(task_XML)
    for task in task_XML_tree.findall('task'):
        target=task.find('target')
        if target!=None:
            target_id=target.attrib['id']

    for task in task_XML_tree.findall('task'):
        scanner=task.find('scanner')
        if scanner!=None:
            scanner_id=scanner.attrib['id']

    for task in task_XML_tree.findall('task'):
        schedule=task.find('schedule')
        if schedule!=None:
            schedule_id=schedule.attrib['id']
    return(target_id,scanner_id,schedule_id)
def get_task_ids(gmp):
    task_XML=gmp.get_tasks()
    task_XML_tree = ET.fromstring(task_XML)
    task_ids=[]
    for task in task_XML_tree.findall('task'):
        task_ids.append(task.attrib['id'])
   #print("Task IDs:", task_ids)
    return(task_ids)

def get_schedule_id(gmp):
    schedule_XML=gmp.get_schedules()
    schedule_XML_tree = ET.fromstring(schedule_XML)
    last_created_time=None
    last_created_schedule_id=None
    for schedule in schedule_XML_tree.findall('schedule'):
        mod_time=schedule.find('modification_time').text
        if(last_created_time is None or last_created_time<mod_time):
            last_created_time=mod_time
            last_created_schedule_id=schedule.attrib['id']
    return(last_created_schedule_id)

def get_task_status(gmp):
    task_id=get_task_id()[len(get_task_id(task))-1]    
    task_XML=gmp.get_task(task_id)
    task_XML_tree = ET.fromstring(task_XML)
    task_status=""
    for task in task_XML_tree.findall('task'):
        task_status=task.find('status').text
    print("Task status: ", task_status)
    return(task_status)

def new_schedule(cal,gmp):
    schedule=gmp.create_schedule(name="My Schedule",icalendar=cal.to_ical(), timezone='Europe/Warsaw')
    return(schedule)

def new_cal(schedule_type):
    cal = Calendar()
    cal.add('prodid', '-//Foo Bar//')
    cal.add('version', '2.0')
    rule=vRecur()
    if(schedule_type=="C"):
        cyclical_type=int(input("Wybierz cyklicznosc: co godzine (1), codzienne(2), co tydzien(3)"))
        while(True): 
            if(cyclical_type==1):
                rule['freq']='hourly'
                hour=int(input("Podaj godzine pierwszego skanu (sama godzina): "))
                minute=int(input("Podaj godzine pierwszego skanu (same minuty): "))
                event = Event()
                now = datetime.now(pytz.timezone('Europe/Warsaw'))
                start_time = now.replace(hour=hour, minute=minute, second=0, microsecond=0)
                event.add('dtstart', start_time)
                event.add('rrule',rule)
                break
            elif(cyclical_type==2):
                rule = vRecur()
                rule['freq'] = 'daily'
                hour = int(input("Podaj godzine pierwszego skanu (sama godzina): "))
                minute = int(input("Podaj godzine pierwszego skanu (same minuty): "))
                event = Event()
                now = datetime.now(pytz.timezone('Europe/Warsaw'))
                start_time = now.replace(hour=hour, minute=minute, second=0, microsecond=0)
                event.add('dtstart', start_time)
                event.add('rrule', rule)
                break
            elif(cyclical_type==3):
                rule = vRecur()
                rule['freq'] = 'weekly'
                day = input("Podaj dzien tygodnia (wedlug schematu): MO, TU, WE, TH, FR, SA, SU: ")
                hour = int(input("Podaj godzine pierwszego skanu (sama godzina): "))
                minute = int(input("Podaj godzine pierwszego skanu (same minuty): "))
                event = Event()
                now = datetime.now(pytz.timezone('Europe/Warsaw'))
                start_time = now.replace(hour=hour, minute=minute, second=0, microsecond=0)
                rule['byday'] = day
                event.add('dtstart', start_time)
                event.add('rrule', rule)
                break
            else:
                print("Bledny wybor!")
    elif(schedule_type=="J"):
        year=int(input("Podaj date skanu (sam rok): "))
        month=int(input("Podaj date skanu (sam miesiac, format 1,2,3 ...): "))
        day=int(input("Podaj date skanu (sam dzien): "))
        hour=int(input("Podaj date skanu (sama godzina): "))
        minute=int(input("Podaj date skanu (same minuty): "))
        event = Event()
        event.add('dtstart',datetime(year,month,day,hour,minute,tzinfo=pytz.timezone('Europe/Warsaw')))
        event.add('dtstamp', datetime.now(tz=pytz.timezone('Europe/Warsaw')))
    cal.add_component(event)
    return(cal)

