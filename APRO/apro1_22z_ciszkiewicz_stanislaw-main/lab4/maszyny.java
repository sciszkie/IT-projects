package lab4;
import java.util.Scanner;

public class maszyny {

    public static void main(String[] args) {
        Scanner skan = new Scanner(System.in);
        System.out.println("Podaj liczbe produktow, ktore chcesz wyprodukowac:");
        int x= skan.nextInt();
        System.out.println("Podaj liczbe maszyn, jakimi dysponujesz: ");
        int y =skan.nextInt();
        int days=1;
        int wyprodukowane=0;
        int [] productionSpeed = new int[y];
        for(int i=0;i<y;i++) {
            System.out.println("Podaj ile produktow wytwarza maszyna nr. " + (i + 1) + ": ");
            productionSpeed[i] = skan.nextInt();
        }
        while(wyprodukowane<x){
            for(int i=0;i<y;i++){
                if (days%productionSpeed[i] == 0) {
                    wyprodukowane++;
                }
            }
            days++;
        }
        days--;
        System.out.println("Liczba potrzebnych dni do wyprodukowania " + x + " produktow: " + days);
    }
}
