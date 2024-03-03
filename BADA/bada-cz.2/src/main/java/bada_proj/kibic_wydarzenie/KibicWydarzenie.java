package bada_proj.kibic_wydarzenie;

import java.time.LocalDate;

public class KibicWydarzenie {

    private int nrKibica;
    private String imie;
    private String nazwisko;
    private String pesel;
    private int nrWydarzenia;
    private String nazwa;
    private LocalDate data;


    public KibicWydarzenie(int nrWydarzenia, int nrKibica) {
        this.nrWydarzenia = nrWydarzenia;
        this.nrKibica = nrKibica;
    }


    public KibicWydarzenie(int nrKibica, String imie, String nazwisko, String pesel, int nrWydarzenia, String nazwa, LocalDate data) {
        this.nrKibica = nrKibica;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.pesel = pesel;
        this.nrWydarzenia = nrWydarzenia;
        this.nazwa = nazwa;
        this.data = data;
    }

    public KibicWydarzenie() {
    }

    public int getNrWydarzenia() {
        return nrWydarzenia;
    }

    public void setNrWydarzenia(int nrWydarzenia) {
        this.nrWydarzenia = nrWydarzenia;
    }

    public int getNrKibica() {
        return nrKibica;
    }

    public void setNrKibica(int nrKibica) {
        this.nrKibica = nrKibica;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

//    @Override
//    public String toString() {
//        return "KibicWydarzenie{" +
//                "nrWydarzenia=" + nrWydarzenia +
//                ", nrKibica=" + nrKibica +
//                '}';
//    }


    @Override
    public String toString() {
        return "KibicWydarzenie{" +
                "nrKibica=" + nrKibica +
                ", imie='" + imie + '\'' +
                ", nazwisko='" + nazwisko + '\'' +
                ", pesel='" + pesel + '\'' +
                ", nrWydarzenia=" + nrWydarzenia +
                ", nazwa='" + nazwa + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}
