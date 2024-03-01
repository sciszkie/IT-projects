package lab9;


public class Prefix {

    public static String shortest = "";
   public static String prefix(String words []){
       shortest=minimal(words);
       StringBuilder sb = new StringBuilder();
       for (int j=0;j<shortest.length();j++){
           int counter= 0;
           for (int i = 0; i < words.length; i++){
               if(shortest.charAt(j)!=words[i].charAt(j)){
                   break;
               }
               counter++;
               if(counter==words.length){
                   sb.append(shortest.charAt(j));
               }

           }
       }
       System.out.println(sb.toString());
       return sb.toString();
   }



    public static String minimal(String[] words){
        for (int i = 0; i < words.length; i++) {
            if (i == 0) {
                shortest = words[i];
            }
            else if(words[i].length()<shortest.length()){
                shortest=words[i];

            }
        }
        return shortest;
    }

    public static void main(String[] arguments) {
        String[] words = {"arbuz","arbitraz"};
       System.out.println(prefix(words));


    }
}
