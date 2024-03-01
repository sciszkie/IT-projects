package wyklad;
import java.util.LinkedList;

public class Teams {
    private  String name;
    Teams(String name){
        this.name=name;
    }
    public static void main(String[] args) {

            //creating new list with different football teams
            LinkedList<Teams> teams = new LinkedList<Teams>();

            //adding new element to the list
            teams.add(new Teams("Barcelona"));
            System.out.println(teams.size());
            System.out.println(teams.get(0).name);


            //Removing element of chosen index
            teams.remove(0);
            System.out.println(teams.size());

            teams.add(new Teams("Barcelona"));
            teams.add(new Teams("Liverpool"));


            //Replaces the element at the specified position in this list with the specified element.
            //Shows the values in the list
            for (int i = 0; i < teams.size(); i++) {
                System.out.print(teams.get(i).name + " ");
            }
            System.out.println();
            //Adding specified element in the specified position
            Teams team1 = new Teams("PSV");
            teams.set(0, team1);

            //Shows the values in the list (with the change)
            for (int i = 0; i < teams.size(); i++) {
                System.out.print(teams.get(i).name + " ");
            }
            System.out.println();

            //Replaces each element of this list with the result of applying the operator to that element.
            LinkedList<Teams> teams2 = new LinkedList<Teams>();
            Teams legia = new Teams ("Legia");
            teams2.add(legia);
            teams2.add(new Teams("PSV"));
            teams2.add(legia);
            teams2.add(legia);

            for (int i = 0; i < teams2.size(); i++) {
                System.out.print(teams2.get(i).name + " ");
            }
            System.out.println();

            //removes occurence of the first object "Legia" of class Teams
            teams2.removeFirstOccurrence(legia);
            for (int i = 0; i < teams2.size(); i++) {
                System.out.print(teams2.get(i).name + " ");
            }
            System.out.println();

            //Taking the first element from the list
            System.out.println(teams.peekFirst().name);
            System.out.println(teams.size());


            //Removing all elements from list
            System.out.println(teams.size());
            teams.removeAll(teams);
            System.out.println(teams.size());
        }
}
/*komentarz:
- kolekcja LinkedList pozwala na szybki dostęp do swoich elementów,
- w przeciwieństwie do ArrayListy jest to lista podwójnie wiązana (posiada referncję do poprzedniego i kolejnegoobiektu z listy)
 */

