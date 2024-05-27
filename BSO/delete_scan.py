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
path = './gvm/gvmd/gvmd.sock'
connection = UnixSocketConnection(path=path)
gmp = Gmp(connection)
gmp.connect()
gmp.authenticate('admin','admin')

def choose_scan():
    tasks_names=scanner_func.get_task_names(gmp)
    p=1
    for i, name in enumerate(tasks_names,start=0):
    	print(f"{i+1}. {name}")
    	p=p+1
    seq_num=int(input("Wybierz, ktory skan chcesz usunac ( "+str(p)+" jesli chcesz zakonczyc modyfikacje): "))
    return (seq_num,p)
def delete_task(id,gmp):
    decision=input("Czy na pewno chcesz usunac skan (T lub N)?")
    while(True):
        if(decision=="T"):
            gmp.delete_task(id)
            break
        elif(decision=="N"):
            break
        else:
            decision=input("Bledny wybor, wybierz sposrod T lub N !!!")
def main ():
    while True:
        seq_num, last_i=choose_scan()
        if(seq_num==last_i):
            scanner_app.main()
        task_ids=scanner_func.get_task_ids(gmp) 
        delete_task(task_ids[seq_num-1],gmp) 
if __name__=="__main__":
    main()  
 
