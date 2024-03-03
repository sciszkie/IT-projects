package bada_proj.dyscyplina;

public class Dyscyplina {
    private int nrDyscypliny;
    private String nazwaDyscypliny;

    public Dyscyplina(int nrDyscypliny, String nazwaDyscypliny) {
        this.nrDyscypliny = nrDyscypliny;
        this.nazwaDyscypliny = nazwaDyscypliny;
    }

    public Dyscyplina(){}

    public int getNrDyscypliny() {
        return nrDyscypliny;
    }

    public void setNrDyscypliny(int nrDyscypliny) {
        this.nrDyscypliny = nrDyscypliny;
    }

    public String getNazwaDyscypliny() {
        return nazwaDyscypliny;
    }

    public void setNazwaDyscypliny(String nazwaDyscypliny) {
        this.nazwaDyscypliny = nazwaDyscypliny;
    }

    @Override
    public String toString() {
        return "Dyscyplina{" +
                "nrDyscypliny=" + nrDyscypliny +
                ", nazwaDyscypliny='" + nazwaDyscypliny + '\'' +
                '}';
    }
}
