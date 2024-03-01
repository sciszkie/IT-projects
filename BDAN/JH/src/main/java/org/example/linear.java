package org.example;

public class linear {
    public static void main(String[] args) {
        for (int i = 0; i < 16; i++) {
            String a = Integer.toBinaryString(i);
            while (a.length() < 4)
                a = "0" + a;
            for (int j = 0; j < 16; j++ ){
                String b = Integer.toBinaryString(j);
                while (b.length() < 4)
                    b = "0" + b;
                lin(a, b);
            }
        }

    }
    public static String XORing(char x, char y){
        String a = "";
        if (x == y) {
            a = "0";
        }
        else {
            a = "1";
        }
        return a;
    }
public static void lin(String a, String b ){
    String c = "";
    String d = "";
    //this is B(0) XORed with A(1)
    d += XORing(b.charAt(0), a.charAt(1));
    d += XORing(b.charAt(1), a.charAt(2));
    d += XORing((XORing(b.charAt(2), a.charAt(3)).charAt(0)), a.charAt(0));
    d += XORing(b.charAt(3), a.charAt(0));
    c += XORing(a.charAt(0), d.charAt(1));
    c += XORing(a.charAt(1), d.charAt(2));
    c += XORing((XORing(a.charAt(2), d.charAt(3)).charAt(0)), d.charAt(0));
    c += XORing(a.charAt(3), d.charAt(0));
    int tempc = Integer.parseInt(c, 2);
    int tempd = Integer.parseInt(d, 2);
    System.out.println(tempc+ " "+tempd);
}
}
