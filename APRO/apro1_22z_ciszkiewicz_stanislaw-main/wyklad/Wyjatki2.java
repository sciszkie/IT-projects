package wyklad;

public class Wyjatki2 {
    public static int hour=-5;
    public static int get_number_of_seconds(int hour){
        if (hour<0){
            throw new IllegalArgumentException("Hour must be greater than 0");
        }
        return hour*60*60;
    }
    public static void main(String[] arguments) {
        int numberOfSeconds = 0;
        Wyjatki2 instance=new Wyjatki2();
        try {
            numberOfSeconds = instance.get_number_of_seconds(hour);
        }
        catch (IllegalArgumentException exception) {
            numberOfSeconds = instance.get_number_of_seconds(hour * -1);
        }
        System.out.println(numberOfSeconds);

    }
}
