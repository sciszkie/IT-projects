package wyklad;


public class Asercje {
    public static int hour=-5;
    public static int get_number_of_seconds(int hour){
        assert hour>0:"hour must be greater than 0";

        return hour*60*60;
    }
    public static void main(String[] arguments) {
        System.out.println(get_number_of_seconds(hour));

    }
}

