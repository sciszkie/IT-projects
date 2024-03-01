import java.util.Scanner;

public class Kolokwium {
    static int n = 2;
    static int m = 3;
    static int[][] tablica1 = new int[n][m];
    static int tab1[]=new int[n+m];
    int sortowanie_tab1(int tablica1){
        for(int i=0;i<n;i++){
            for (int j=0;j<m;j++){
                int z=0
                tab1[z]=tablica1[i][j];
            }
        }
    }
    static int[][] tablica2 = new int[n][m];
    static int[][] tablica3 = new int[2 * n][m];

    void laczenie(int tablica1[][],int tablica2[][]){
        tablica1[0][0] = 3;
        tablica1[0][1] = 1;
        tablica1[0][2] = 5;
        tablica1[1][0] = 8;
        tablica1[1][1] = 5;
        tablica1[1][2] = 0;
        tablica2[0][0] = 14;
        tablica2[0][1] = 15;
        tablica2[0][2] = 17;
        tablica2[1][0] = 18;
        tablica2[1][1] = 19;
        tablica2[1][2] = 10;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                tablica3[i][j] = tablica1[i][j];
                System.out.print(tablica3[i][j]);
            }
            System.out.println();
        }
        for (int i = n; i < 2 * n; i++) {
            for (int j = 0; j < m; j++) {
                tablica3[i][j] = tablica2[i - n][j];
                System.out.print(tablica3[i][j]);
            }
            System.out.println();

        }
    }
    public static void main(String[] args) {
        tablica3.laczenie(tablica1,tablica2);
    }
}