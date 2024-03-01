import java.util.Scanner;

public class skaner {

    public static void main(String[] args) {
        Scanner skaner = new Scanner(System.in);
        System.out.println("Podaj pierwsza liczbe:");
        int a = skaner.nextInt();
        System.out.println("Podaj druga liczbe:");
        int b = skaner.nextInt();
        if(a>b)
            System.out.println("Pierwsza liczba większa od drugiej!!!");
        else if (a<b)
            System.out.println("Druga liczba większa od pierwszej!!!");
        else
            System.out.println("Pierwsza liczba równa drugiej!!!");
    }
}