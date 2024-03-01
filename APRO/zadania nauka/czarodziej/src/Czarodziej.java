import java.util.Scanner;
public class Czarodziej {
    int sila;
    int zycie;
    String woreczek;
    Scanner skan=new Scanner(System.in);
    Czarodziej(){
        System.out.println("Podaj sile czarodzieja: ");
        sila=skan.nextInt();
        this.sila=sila;
        System.out.println("Podaj zycie czarodzieja: ");
        zycie=skan.nextInt();
        this.zycie=zycie;
        System.out.println("Podaj czar czarodzieja: ");
        woreczek=skan.next();
        this.woreczek=woreczek;

    }
    void zwieksz_sile(){
        int o_ile_zwieksz=skan.nextInt();
        this.sila+=o_ile_zwieksz;
    }

    public static void main(String[] args) {
        Czarodziej Merlin=new Czarodziej();
        System.out.println(Merlin.sila);
        Merlin.zwieksz_sile();
        System.out.println(Merlin.sila);
    }
}