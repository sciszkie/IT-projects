import java.util.ArrayList;
import java.util.Scanner;

public class Nauka {

    public static void main(String[] args) {
        int tab []={2,5,3,4,5,2,7};
        int lo=2;
        int hi=5;
        int n=wybor(tab,hi,lo);
        System.out.println(n);
        }
        static int wybor(int tab[],int hi,int lo){
            int z=0;
            int m=0;
        for (int i=lo;i<=hi;i++){
            if(i==lo){
                z=tab[i];
                m=i;
            }
            else
            {
                if(tab[i]<z){
                    m=i;
                    z=tab[i];
                }
            }
        }
        return m;
        }
    }

