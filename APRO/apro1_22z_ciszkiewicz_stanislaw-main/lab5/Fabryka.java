package lab5;
import java.util.Scanner;

public class Fabryka {

    int liczbaDni() {
        Scanner skan = new Scanner(System.in);
        System.out.println("Podaj liczbe produktow, ktore chcesz wyprodukowac:");
        int x = skan.nextInt();
        System.out.println("Podaj liczbe maszyn, jakimi dysponujesz: ");
        int y =skan.nextInt();
        int days=1;
        int wyprodukowane=0;
        Maszyna [] maszyny = new Maszyna[y];

        for(int i=0;i<y;i++){
            System.out.println("Podaj ile produktow wytwarza maszyna nr. " + (i+1) +": ");
            int a = skan.nextInt();
            maszyny[i] = new Maszyna(a);
        }

        skan.close();
        while(wyprodukowane<x){
            for(int i = 0; i < y; i++){
                wyprodukowane = wyprodukowane + maszyny[i].produce(days);
            }
            days++;
        }
        days--;
        return days;
    }


    public static void main(String[] args) {
        Fabryka fabryka = new Fabryka();
        System.out.println("Liczba potrzebnych dni do wyprodukowania tych produktow: " + fabryka.liczbaDni());
    }
}


class Maszyna {
    int szybkoscProdukcji;

    Maszyna(int a) {
        this.szybkoscProdukcji = a;
    }

    int produce(int days){
        if(days%this.szybkoscProdukcji == 0) {
            return 1;
        }
        return 0;
    }
}
