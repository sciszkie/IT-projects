package lab3;

public class Factorial {
    public static void main(String[] args) {
        short N = 4;
        int result = 1;
        while(N>0){
            result=result*N;
            N-=1;
        }
        System.out.println(result);
    }
}