public class Kalkulator {
    public static void main(String[] args) {
        double a = 1.2;
        double b = 22.7;
        double c = 33.7;
        System.out.println((a+b)*c);
        System.out.println(a-b/c);
        a++;
        b++;
        c++;
        System.out.println((a+b)*c);
        System.out.println(a-b/c);
        boolean g= (a+b)>c;
        System.out.println(g);
        boolean h =(a==b);
        System.out.println(h);

    }
}