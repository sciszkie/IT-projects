public class Tablica {
    public static void main(String[] args) {
        int tablica[]={0,2,3,4,0,7,1,0,0,0,5,5,6,1,2,3};
        int[] tablica_posortowana=new int[tablica.length];
        int z=0;
        String Z="Zosia";
        char p=Z.charAt(2);
        String b="";
        b=p+Z;
        System.out.println(b);
        int liczba_zer=0;
              for(int i=0;i<tablica.length;i++){
                  if(tablica[i]!=0){
                      tablica_posortowana[z]=tablica[i];
                      System.out.print(tablica_posortowana[z]+" ");
                      z++;
                  }
                  else{
                      liczba_zer++;
                  }
              }
              for(int i=tablica.length-liczba_zer;i< tablica.length;i++){
                  tablica_posortowana[i]=0;
                  System.out.print(tablica_posortowana[i]+" ");
              }
    }
}