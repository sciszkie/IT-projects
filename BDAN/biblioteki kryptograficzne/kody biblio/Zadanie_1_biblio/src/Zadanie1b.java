import java.math.BigInteger;
import java.security.SecureRandom;

public class Zadanie1b {
    public static void main(String[] args) {
        SecureRandom random= new SecureRandom();
        SecureRandom how_many_bits=new SecureRandom();
        int bits = how_many_bits.nextInt(2048);
        while (bits<1024){
            bits = how_many_bits.nextInt(2048);
        }
        System.out.println(bits);
        BigInteger p=new BigInteger(bits,random).nextProbablePrime();
        int bitsa = how_many_bits.nextInt(bits)+1;
        System.out.println(bitsa);
        BigInteger a=new BigInteger(bitsa,random);
        while(p.compareTo(a)==-1||p.compareTo(a)==0){
            a=new BigInteger(bitsa,random);
        }
        int bitsb= how_many_bits.nextInt(bits)+1;
        System.out.println(bitsb);
        BigInteger b=new BigInteger(bitsb,random);
        while(p.compareTo(b)==-1||p.compareTo(b)==0){
           b=new BigInteger(bitsb,random);
        }
        BigInteger addmodulo= (a.add(b)).mod(p);
        BigInteger substractionmodulo= (a.subtract(b).add(p)).mod(p);
        BigInteger multiplicationmodulo = (a.multiply(b)).mod(p);
        BigInteger inversea = a.modInverse(p);
        BigInteger inverseb = b.modInverse(p);
        System.out.println("a: " + a);
        System.out.println("b: " + b);
        System.out.println("p: "+ p);
        System.out.println("add: " + addmodulo);
        System.out.println("substraction: " + substractionmodulo);
        System.out.println("multiplictaion: "+ multiplicationmodulo);
        System.out.println("inverse a: "+ inversea);
        System.out.println("inverse b: "+ inverseb);

    }
}
