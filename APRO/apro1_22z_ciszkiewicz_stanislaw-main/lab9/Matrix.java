package lab9;

public class Matrix {
    public static int firstRowSum = 4;
    public static int secondRowSum = 7;

    public static int[] columnSum = {1, 2, 1, 1, 2,2,2};
    public static int [][] rozw= new int[columnSum.length][2];


        public static void tworzenie_macierzy(int firstRowSum, int secondRowSum, int[]columnSum,int[][] rozw) {
            int m = columnSum.length;

                for (int j = 0; j < m; j++) {
                    if ( columnSum[j] == 2) {
                        rozw[j][0] = 1;
                        rozw[j][1] = 1;
                        columnSum[j] = columnSum[j] - 2;
                        secondRowSum--;
                        firstRowSum--;
                    }
                }
            int sum = firstRowSum;
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < m; j++) {
                    if (sum > 0 && columnSum[j] > 0&&rozw[j][i]!=1) {
                        rozw[j][i] = 1;
                        columnSum[j]--;
                        sum--;
                    }
                    else if(rozw[j][i]==1){

                    }
                    else {
                        rozw[j][i] = 0;
                    }

                }
                sum = secondRowSum;

            }
        }
    public static void wypisywanie_macierzy(int [][]rozw,int suma,int firstRowSumPlussecondRowSum){


            if(firstRowSum> columnSum.length||secondRowSum> columnSum.length||suma!=firstRowSumPlussecondRowSum) {
                System.out.println("Nie da się wypisać takiej macierzy!!!");
            }
            else{

            for(int i=0;i<2;i++){
                for(int j=0;j< columnSum.length;j++){
                    System.out.print(rozw[j][i]+" ");
                }
                System.out.println("");
            }
        }

    }
    public static void main(String[] arguments) {
        int suma=0;
        for(int k=0;k< columnSum.length;k++){
            suma=suma+columnSum[k];
        }
        int firstRowSumPlussecondRowSum=secondRowSum+firstRowSum;
        Matrix matrix=new Matrix();
        matrix.tworzenie_macierzy( firstRowSum,secondRowSum,columnSum,rozw);
        matrix.wypisywanie_macierzy(rozw,suma,firstRowSumPlussecondRowSum);
    }
        }




