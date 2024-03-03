package bada_proj.wydarzenie;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Wydarzenie {
    private int nrWydarzenia;
    private String nazwa;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime data;
    private int nrKlubu;
    private int nrObiektu;
    private int nrDyscypliny;
    int userSignedUp;
    int liczbaDostepnychMiejsc;
    private String nazwaKlubu;
    private String nazwaObiektu;
    private String nazwaDyscypliny;

    public Wydarzenie(int nrWydarzenia, String nazwa,LocalDateTime data, int nrKlubu, int nrObiektu, int nrDyscypliny, String nazwaKlubu, String nazwaObiektu, String nazwaDyscypliny) {
        this.nrWydarzenia = nrWydarzenia;
        this.nazwa = nazwa;
        this.data = data;
        this.nrKlubu = nrKlubu;
        this.nrObiektu = nrObiektu;
        this.nrDyscypliny = nrDyscypliny;
        this.nazwaKlubu = nazwaKlubu;
        this.nazwaObiektu = nazwaObiektu;
        this.nazwaDyscypliny = nazwaDyscypliny;
    }

    public Wydarzenie() {
    }

    public int getNrWydarzenia() {
        return nrWydarzenia;
    }

    public void setNrWydarzenia(int nrWydarzenia) {
        this.nrWydarzenia = nrWydarzenia;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public LocalDateTime getData() {
        return data;
    }

    public int getUserSignedUp() {
        return userSignedUp;
    }

    public void setUserSignedUp(int userSignedUp) {
        this.userSignedUp = userSignedUp;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public int getNrKlubu() {
        return nrKlubu;
    }

    public void setNrKlubu(int nrKlubu) {
        this.nrKlubu = nrKlubu;
    }

    public int getNrObiektu() {
        return nrObiektu;
    }

    public void setNrObiektu(int nrObiektu) {
        this.nrObiektu = nrObiektu;
    }

    public int getNrDyscypliny() {
        return nrDyscypliny;
    }

    public void setNrDyscypliny(int nrDyscypliny) {
        this.nrDyscypliny = nrDyscypliny;
    }

    public int getLiczbaDostepnychMiejsc() {
        return liczbaDostepnychMiejsc;
    }

    public void setLiczbaDostepnychMiejsc(int liczbaDostepnychMiejsc) {
        this.liczbaDostepnychMiejsc = liczbaDostepnychMiejsc;
    }

    public String getNazwaKlubu() {
        return nazwaKlubu;
    }

    public void setNazwaKlubu(String nazwaKlubu) {
        this.nazwaKlubu = nazwaKlubu;
    }

    public String getNazwaObiektu() {
        return nazwaObiektu;
    }

    public void setNazwaObiektu(String nazwaObiektu) {
        this.nazwaObiektu = nazwaObiektu;
    }

    public String getNazwaDyscypliny() {
        return nazwaDyscypliny;
    }

    public void setNazwaDyscypliny(String nazwaDyscypliny) {
        this.nazwaDyscypliny = nazwaDyscypliny;
    }

    @Override
    public String toString() {
        return "Wydarzenie{" +
                "nrWydarzenia=" + nrWydarzenia +
                ", nazwa='" + nazwa + '\'' +
                ", data=" + data +
                ", nrKlubu=" + nrKlubu +
                ", nrObiektu=" + nrObiektu +
                ", nrDyscypliny=" + nrDyscypliny +
                ", userSignedUp=" + userSignedUp +
                ", liczbaDostepnychMiejsc=" + liczbaDostepnychMiejsc +
                ", nazwaKlubu='" + nazwaKlubu + '\'' +
                ", nazwaObiektu='" + nazwaObiektu + '\'' +
                ", nazwaDyscypliny='" + nazwaDyscypliny + '\'' +
                '}';
    }
}
