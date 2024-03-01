package lab6;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
/**
 * Simple program to serialize palindrome and write it (serialize it) into the file.
 */
public class WritePalindrome {
    /**
     * Writes the given palindrome into the file with the given name.
     */
    public static void write(Palindrome p, String fileName) {
        try (ObjectOutputStream oos =
                     new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(p);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
    /**
     * Starts the program.
     */
    public static void main(String[] args) {
        WritePalindrome.write(new Palindrome("Kobyła ma mały bok."), "palindrome.plr");
    }
}