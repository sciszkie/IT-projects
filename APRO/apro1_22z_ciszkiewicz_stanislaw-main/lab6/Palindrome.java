package lab6;
import java.io.Serializable;
import java.util.Hashtable;
public class Palindrome implements Serializable {
    private String palindrome ;
    private String rawPalindrome;
    public Palindrome(String p1){
        palindrome=p1;
        rawPalindrome=makeRawPalindrome(p1);

    }


    public static int index;
    public String getPalindrome(){
        palindrome=findLongestPalindrome(rawPalindrome);
        return palindrome;
    }
    public boolean equals(String p1, String p2) {
        if(isPalindrome(p1)&&isPalindrome(p2)){
            if(equals(makeRawPalindrome(p1),makeRawPalindrome(p2))) {
                return true;
            }
        }
        return false;
    }
    public static String findLongestPalindrome(String word) {
        String longestPalindrome="";
        for (int i = 0; i < word.length(); i++) {
            String subword = word.substring(i,word.length());
            String current=analyzeSubstring(subword);
            if(current.length()>longestPalindrome.length()){
                longestPalindrome=current;
            }
        }
        return longestPalindrome;
    }
    public static String makeRawPalindrome(String sentence){
        String line="";
        StringBuilder stringbuild=new StringBuilder(line);
        for(int i=0;i<sentence.length();i++){
            char s=sentence.charAt(i);
            if(Character.isLetter(s)){
                stringbuild.append(s);
            }
        }
        line=stringbuild.toString();
        return line;
    }
    public static String analyzeSubstring(String word) {
        int index = word.length();
        String substring="";
        while (index > 0) {
            substring= word.substring(0,index);
            if(isPalindrome(substring)==true) {
                break;
            }
            else{
                index=index-1;
            }
        }
        return substring;
    }
    public static boolean isPalindrome(String word) {
        int i1 = 0;
        int i2 = word.length()-1;
        boolean pal = true;
        while (i2>i1){
            if(word.charAt(i1)!=word.charAt(i2)){
                pal=false;
                break;
            }
            i1=i1+1;
            i2=i2-1;
        }
        return pal;
    }
    public static void main (String[] arguments) {
        lab5.Palindrome p1 = new lab5.Palindrome("kobyłamamałybok");
        lab5.Palindrome p2 = new lab5.Palindrome("kobyła ma mały bok!");
        lab5.Palindrome p3 = new lab5.Palindrome("elf układał kufle");
        System.out.println("p1 == p2 : " + (p1 == p2));
        System.out.println("p1.equals(p2) : " + p1.getPalindrome().equals(p2.getPalindrome()));
        Hashtable<lab5.Palindrome, String> ps = new Hashtable<>();
        ps.put(new lab5.Palindrome("a car boso kokos obraca"), "Andrzej Pacierpnik");
        ps.put(p1,"Stanisław");
        ps.put(p2, "autor nieznany");
        ps.put(new lab5.Palindrome("muzo, raz daj jad za rozum"), "Julian Tuwim");
        ps.put(p3, "Tadeusz Morawski");
        System.out.println("Autorem palindromu " + p1.getPalindrome() + " jest " + ps.get(p1));

        String word = "rrrrr abccba kobyłamamałybok";
        String longestPalindrome = findLongestPalindrome(word);
        System.out.println("longestPalindrome: " + longestPalindrome);
    }
}