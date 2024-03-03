package bada_proj.obiekt;

public class Obiekt {
    private int nrObiektu;
    private String nazwa;
    private int pojemnosc;
    private int nrKlubu;
    private int nrAdresu;

    public Obiekt(int nrObiektu, String nazwa, int pojemnosc, int nrKlubu, int nrAdresu) {
        this.nrObiektu = nrObiektu;
        this.nazwa = nazwa;
        this.pojemnosc = pojemnosc;
        this.nrKlubu = nrKlubu;
        this.nrAdresu = nrAdresu;
    }

    public Obiekt(){}

    public int getNrObiektu() {
        return nrObiektu;
    }

    public void setNrObiektu(int nrObiektu) {
        this.nrObiektu = nrObiektu;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public int getPojemnosc() {
        return pojemnosc;
    }

    public void setPojemnosc(int pojemnosc) {
        this.pojemnosc = pojemnosc;
    }

    public int getNrKlubu() {
        return nrKlubu;
    }

    public void setNrKlubu(int nrKlubu) {
        this.nrKlubu = nrKlubu;
    }

    public int getNrAdresu() {
        return nrAdresu;
    }

    public void setNrAdresu(int nrAdresu) {
        this.nrAdresu = nrAdresu;
    }

    @Override
    public String toString() {
        return "Obiekt{" +
                "nrObiektu=" + nrObiektu +
                ", nazwa='" + nazwa + '\'' +
                ", pojemnosc=" + pojemnosc +
                ", nrKlubu=" + nrKlubu +
                ", nrAdresu=" + nrAdresu +
                '}';
    }
}
