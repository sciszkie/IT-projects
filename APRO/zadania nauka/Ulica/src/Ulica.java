import java.util.Scanner;

public class Ulica {
    public static void main(String[] args) {
        int ulica []={0,1,1,1,0,1,1,0,0,0,0,0,1,0,1,1,1,1,0,0,0,0};
        int mozliwe_domy=0;
        Scanner scan = new Scanner(System.in);
        System.out.println("Podaj, ile domow chcesz wybudowac: ");
        int ile_domow= scan.nextInt();
        for(int i=0;i<ulica.length-2;i++){
            if(ulica[i]==0 && ulica[i+1]==0 && ulica[i+2]==0){
                ulica[i+1]=1;
                mozliwe_domy++;
            }
        }
        System.out.println(mozliwe_domy);
        if(mozliwe_domy>ile_domow){
            System.out.println("Mozesz wybudowac tyle domow!!!");
        }
        else{
            System.out.println("Nie mozesz wybudowac tylu domow!!!");
        }
    }
}