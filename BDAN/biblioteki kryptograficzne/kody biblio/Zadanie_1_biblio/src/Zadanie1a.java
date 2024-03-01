
import java.security.SecureRandom;
import java.util.Scanner;
public class Zadanie1a{


    public static boolean checkIfPrime(int p) {
        int j = 0;
        for (int i = p/2; i> 1; i--) {

            if (p % i == 0) {
                j++;
            }
        }
        if (j > 0) {
            return false;
        }
        return true;
    }
    public static int addModulo(int a, int b, int p)
    {
        int result;
        result=(a+b)%p;
        return result;
    }
    public static int substractModulo(int a, int b, int p){
        int result;
        result=(a-b+p)%p;
        return result;
    }
    public static int multiplicationModulo(int a, int b, int p){
        int result;
        result=(a*b)%p;
        return result;
    }
    public static void inverse(int a, int b, int p){
        int result=p;
        for (int i = 0; i < p; i++) {
            if((a*i)%p==1){
                result=i;
            }
            }
        if(result!=p){
            System.out.println("Element odwrotny dla "+a+ " wynosi "+ result);
        }
        else{
            System.out.println("Brak elementu odwrotnego.");
        }
    }
    public static void main(String[] args) {
        int p;
        int a;
        int b;
        SecureRandom random = new SecureRandom();
        p = random.nextInt(100);
        while (checkIfPrime(p)==false||p<10){
            p= random.nextInt(100);
        }
        System.out.println("Podaj pierwsza liczbe z zakresu od 0 do " + p +": ");
        Scanner skan=new Scanner(System.in);
        a=skan.nextInt();
        while(a>=p||a<=0){
            System.out.println("Liczba spoza zakresu. Podaj pierwsza liczbę z zakresu od 0 do " + p +": ");
            a=skan.nextInt();
        }
        System.out.println("Podaj druga liczbe z zakresu od 0 do " + p +": ");
        b=skan.nextInt();
        while(b>=p){
            System.out.println("Liczba spoza zakresu. Podaj druga liczbę z zakresu od 0 do " + p +": ");
            b=skan.nextInt();
        }
        System.out.println("a: "+a);
        System.out.println("b: "+ b);
        System.out.println("p: "+p);
        System.out.println("add: " + addModulo(a,b,p));
        System.out.println("substract: "+ substractModulo(a,b,p));
        System.out.println("multiplication: "+ multiplicationModulo(a,b,p));
        inverse(a,b,p);
    }

}