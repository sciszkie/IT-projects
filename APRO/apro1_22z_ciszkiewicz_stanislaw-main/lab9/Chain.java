package lab9;
import java.util.Arrays;

public class Chain {
    public static void main(String[] arguments) {
        String a="adcd";
        String b="cdad";
        StringBuilder sB1p=new StringBuilder();
        StringBuilder sB1n=new StringBuilder();
        StringBuilder sB2p=new StringBuilder();
        StringBuilder sB2n=new StringBuilder();
        for(int i=0;i<b.length();i++){
        if (i%2==0){
            sB1n.append(a.charAt(i));
            sB2n.append(b.charAt(i));
        }
            if (i%2==1){
                sB1p.append(a.charAt(i));
                sB2p.append(b.charAt(i));
            }
        }
        char []ap = sB1p.toString().toCharArray();
        char []bp = sB2p.toString().toCharArray();
        char []an = sB1n.toString().toCharArray();
        char []bn = sB2n.toString().toCharArray();
        Arrays.sort(an);
        System.out.println(an);
        Arrays.sort(bn);
        System.out.println(bn);
        Arrays.sort(ap);
        System.out.println(ap);
        Arrays.sort(bp);
        System.out.println(bp);
        if(Arrays.equals(an,bn)&&Arrays.equals(ap,bp)){
            System.out.println("lancuchy znakow takie same");
        }
        else {
            System.out.println("lancuchy znakow inne");
        }
    }
}
