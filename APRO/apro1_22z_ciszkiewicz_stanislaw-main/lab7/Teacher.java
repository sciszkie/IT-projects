package lab7;

public class Teacher extends Person {

        private  String przedmiot;
        Teacher(String name, String surname, String przedmiot){
            super(name, surname);
            this.przedmiot=przedmiot;
        }
        public String getPrzedmiot(){
            return przedmiot;
        }

        public void setPrzedmiot(String przedmiot) {
            this.przedmiot=przedmiot;
        }


    public static void main(String[] args) {
        Teacher teacher = new Teacher("anna", "majewska", "fizyka");
        System.out.println(teacher.getSurname());
        teacher.setPrzedmiot("matematyka");
        System.out.println(teacher.getPrzedmiot());
    }
    }

