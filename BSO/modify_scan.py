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
from datetime import datetime, timedelta
from icalendar import Calendar, Event,vRecur
import scanner_func
import new_scan
import scanner_app
import network
path = './gvm/gvmd/gvmd.sock'
connection = UnixSocketConnection(path=path)
gmp = Gmp(connection)
gmp.connect()
gmp.authenticate('admin','admin')

def change_task_name(task_id,gmp):
    target_id,scanner_id,schedule_id=scanner_func.task_info_ids()
    print("Target id: ", target_id)
    print("Scanner id: ", scanner_id)
    print("Schedule id: ", schedule_id)
    new_name=input("Podaj nowa nazwe skanu: ")
    gmp.modify_task(task_id=task_id,name=new_name,config_id='daba56c8-73ec-11df-a475-002264764cea',target_id=target_id,scanner_id=scanner_id,schedule_id=schedule_id)


def change_schedule (task_id,gmp):
    task_XML=gmp.get_task(task_id)
    task_XML_tree = ET.fromstring(task_XML)
    for task in task_XML_tree.findall('task'):
            schedule=task.find('schedule')
            if schedule!=None:
                schedule_id=schedule.attrib['id']
    for task in task_XML_tree.findall('task'):
            schedule=task.find('schedule')
            if schedule!=None:
                schedule_name=schedule.find('name').text
    schedule_type=input("Podaj rodzaj skanu, jednorazowy (J) lub cykliczny (C): ")
    cal=scanner_func.new_cal(schedule_type)
    gmp.modify_schedule(schedule_id,name=schedule_name,icalendar=cal.to_ical(),timezone='Europe/Warsaw')

def choose_scan():
    tasks_names=scanner_func.get_task_names(gmp)
    p=1
    for i, name in enumerate(tasks_names,start=0):
    	print(f"{i+1}. {name}")
    	p=p+1
    seq_num=int(input("Wybierz, ktory skan chcesz edytowac ( "+str(p)+" jesli chcesz zakonczyc modyfikacje): "))
    return (seq_num,p)

def change_target(task_id,gmp):
    task_XML=gmp.get_task(task_id)
    task_XML_tree = ET.fromstring(task_XML)
    for task in task_XML_tree.findall('task'):
        target = task.find('target')
        if target != None:
            target_id = target.attrib['id']
    target_ports_id=scanner_func.get_target_ports(target_id, gmp)
    for task in task_XML_tree.findall('task'):
        target=task.find('target')
        if target!=None:
            target_name=target.find('name').text
    while (True):
        target_type = input("Wybierz czy chcesz wykonac skan calej sieci (S) czy wybranych hostow (H): ")
        if (target_type == "H"):
            hosts=[]
            host = input("Podaj adres IP  hosta: ")
            id=scanner_func.get_target_id_from_ip(gmp,host)
            hosts.append(host)
            break
        elif(target=="S"):
            hosts=network.scan_network()
            id=scanner_func.get_target_id_from_ip(gmp,hosts)
            break
        else:
            print("Bledny wybor!")
    if (id==None):
        current_datetime=datetime.now()
        current_datetime_str=current_datetime.strftime("%Y-%m-%d %H:%M:%S")
        gmp.create_target(name="target"+current_datetime_str,hosts=hosts, port_range="1-5836")
        target_id, scanner_id, schedule_id = scanner_func.task_info_ids(task_id,gmp)
        target_id=scanner_func.get_target_id(gmp)
        name=scanner_func.get_task_name(task_id,gmp)
        gmp.modify_task(task_id=task_id, name=name, config_id='daba56c8-73ec-11df-a475-002264764cea',target_id=target_id, scanner_id=scanner_id, schedule_id=schedule_id)
    else:
        target_id, scanner_id, schedule_id = scanner_func.task_info_ids(task_id,gmp)
        name=scanner_func.get_task_name(task_id,gmp)
        gmp.modify_task(task_id=task_id, name=name, config_id='daba56c8-73ec-11df-a475-002264764cea',target_id=id, scanner_id=scanner_id, schedule_id=schedule_id)

def change_port_list (task_id,gmp):
    target_id, scanner_id, schedule_id = scanner_func.task_info_ids(task_id,gmp)
    current_datetime=datetime.now()
    current_datetime_str=current_datetime.strftime("%Y-%m-%d %H:%M:%S")
    port_range=input("Podaj zakres portow - wcisnij enter dla listy domyslnej (1-1000), dla niestandardowej wpisz ja w formacie 'x-y'")
    if(port_range==""):
    	port_range="1-1000"
    gmp.create_port_list(name="port_list"+current_datetime_str, port_range=port_range)
    port_list_id=scanner_func.get_port_list_id(gmp)
    hosts=scanner_func.get_target_ip(gmp,target_id)
    gmp.create_target(name="target"+current_datetime_str,hosts=hosts,port_list_id=port_list_id)
    target_id, scanner_id, schedule_id = scanner_func.task_info_ids(task_id,gmp)
    target_id=scanner_func.get_target_id(gmp)
    name=scanner_func.get_task_name(task_id,gmp)
    gmp.modify_task(task_id=task_id, name=name, config_id='daba56c8-73ec-11df-a475-002264764cea',
                                target_id=target_id, scanner_id=scanner_id, schedule_id=schedule_id)
def choose_activity():
    actvities=["Zmien nazwe skanu", "Zmien cyklicznosc skanu","Zmien target","Zmien zakres portow","Cofnij do listy skanow"]
    for i, name in enumerate(actvities,start=0):
        print(f"{i+1}. {name}") 
    activity_number=int(input("Podaj numer aktywnosci, ktora chcesz wykonac: "))
    return(activity_number)
def main ():
    while True:
        seq_num, last_i=choose_scan()
        if(seq_num==last_i):
            scanner_app.main()
        task_ids=scanner_func.get_task_ids(gmp) 
        while True:
            activity_number=choose_activity()
            if(activity_number==1):
                change_task_name(task_ids[seq_num-1],gmp) 
            if(activity_number==2):
                change_schedule(task_ids[seq_num-1], gmp)
            if(activity_number==3):
                change_target(task_ids[seq_num-1],gmp)
            if(activity_number==4):
                change_port_list(task_ids[seq_num-1],gmp)
            if(activity_number==5):
                break
if __name__=="__main__":
    main()  
 
