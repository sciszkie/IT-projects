import java.util.Scanner;
public class Equation {
    public static void main(String[] args) {
    Scanner skan = new Scanner(System.in);
    System.out.println ("Podaj współczynnik a funkcji kwadratowej: ");
    float a = skan.nextFloat();
    System.out.println ("Podaj współczynnik b funkcji kwadratowej: ");
    float b = skan.nextFloat();
    System.out.println ("Podaj współczynnik c funkcji kwadratowej: ");
    float c = skan.nextFloat();
    if(a!=0){
        funkcjakwadratowa(a,b,c);
        }
        else{
            funkcjaliniowa(b,c);

        }
    }

    public static void funkcjakwadratowa(float a, float b, float c) {
    float delta=b*b-(4*a*c);
    double pierwiastek = Math.sqrt(delta);
    double x1 =(-b+pierwiastek)/(2*a);
    double x2 =(-b-pierwiastek)/(2*a);

    if(delta<0){
        System.out.println("Funkcja kwadratowa nie ma miejsc zerowych");
    }
    else if (delta==0){
        System.out.println("Miejscem zerowym tego równania wynosi: "+ x1);
    }
    else{
        System.out.println("Miejscami zerowymi tego równania są: "+ x1 +" i "+x2);
    }
    }
    public static void funkcjaliniowa(float b, float c) {
    float x =(-c)/b;
    System.out.println("Miejsce zerowe fukncji: " + x);
    }
}