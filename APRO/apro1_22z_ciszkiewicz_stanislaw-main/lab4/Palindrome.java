package lab4;

public class Palindrome {
    public static int index;
    public static void main (String[] arguments) {
        String word = "podoko";
        String longestPalindrome = findLongestPalindrome(word);
        System.out.println(longestPalindrome);
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
}