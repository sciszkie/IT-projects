package wyklad;

import java.util.function.Predicate;

public class ReferenceExcercise{
static class Reference_class{
    Reference_class(Integer integer){
        System.out.println("hello");
}

    }
    public static void main(String[] args) {
        Predicate<Integer> predicate = ReferenceExcercise::prime;
        //Predicate<Integer>predicate2=Reference_class::new;
        //boolean test=predicate2.test(20);
        boolean result = predicate.test(11);
        System.out.println(result);
    }

    public static boolean prime(int t) {
        int counter = 0;
        boolean a;
        for (int i = 2; i < t; i++) {
            if (t % i==0) {
                counter++;
            }
        }
        if (counter==0){
            a=true;
        }
        else{
           a=false;
        }
return a;
    }
}

//z biblioteki apijavy importujemy interfejs predicate, ktory implementujemy do klasy RefernceExcercise.
// Ma ona za zadanie wywołac prostą metode statyczna prime (sprawdza czy liczba jest pierwsza) za pomoca stworzenia referencji metody prime. (linijka 8).
//Ostatecznie na standardowe wyjscie wypisujemy efekt naszej metody (zwraca ona true jesli liczba jest pierwsza lub false gdy jest złozona).
//Dla liczby 11 nasz program zwraca wartosc true.