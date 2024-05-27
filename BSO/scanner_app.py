import delete_scan
import scanner_func
import modify_scan
import new_scan

def main ():
    activity=int(input("Witaj w naszym skanerze !!! Oto co mozesz zrobic: dodac nowy skan (1), zmodyfikowac istniejacy skan (2) oraz usunac skan (3). Jesli skonczyles prace ze skanerem wybierz 4."))
    while True:
        if(activity==1):
            new_scan.main()
        elif (activity==2):
            modify_scan.main()
        elif(activity==3):
            delete_scan.main()
        elif(activity==4):
            exit()
        else:
            activity=int(input("Podales bledny znak, oto co mozesz zrobic:dodac nowy skan (1), zmodyfikowac istniejacy skan (2) oraz usunac skan (3). Jesli skonczyles prace ze skanerem wybierz 4.")) 
if __name__=="__main__":
    main()  
 
