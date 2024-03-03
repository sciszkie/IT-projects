package bada_proj.kibic;

import bada_proj.address.Adress;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;


public class Kibic {

    private int nrKibica;
    private String imie;
    private String nazwisko;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataUrodzenia;
    private String pesel;
    private String nrTelefonu;
    private String email;
    private String plec;
    private int nrAdresu;
    private String login;
    private String haslo;


    public Kibic(int nrKibica, String imie, String nazwisko, LocalDate dataUrodzenia, String pesel, String nrTelefonu, String plec, String email, int nrAdresu, String login, String haslo,String nowehaslo) {
        this.nrKibica = nrKibica;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.dataUrodzenia = dataUrodzenia;
        this.pesel = pesel;
        this.nrTelefonu = nrTelefonu;
        this.email = email;
        this.plec = plec;
        this.nrAdresu = nrAdresu;
        this.login = login;
        this.haslo = haslo;
    }

    public Kibic() {
    }

    public int getNrKibica() {
        return nrKibica;
    }

    public void setNrKibica(int nrKibica) {
        this.nrKibica = nrKibica;
    }

    public String getImie() {
        return imie;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }

    public String getLogin() {
        return login;
    }

    public String getHaslo() {
        return haslo;
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

    public LocalDate getDataUrodzenia() {
        return dataUrodzenia;
    }

    public void setDataUrodzenia(LocalDate dataUrodzenia) {
        this.dataUrodzenia = dataUrodzenia;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getNrTelefonu() {
        return nrTelefonu;
    }

    public void setNrTelefonu(String numerTelefonu) {
        this.nrTelefonu = numerTelefonu;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPlec() {
        return plec;
    }

    public void setPlec(String plec) {
        this.plec = plec;
    }

    public int getNrAdresu() {
        return nrAdresu;
    }

    public void setNrAdresu(int nrAdresu) {
        this.nrAdresu = nrAdresu;
    }
    @Override
    public String toString() {
        return "Kibic{" +
                "nrKibica=" + nrKibica +
                ", imie='" + imie + '\'' +
                ", nazwisko='" + nazwisko + '\'' +
                ", dataUrodzenia='" + dataUrodzenia + '\'' +
                ", pesel='" + pesel + '\'' +
                ", nrTelefonu='" + nrTelefonu + '\'' +
                ", plec='" + plec + '\'' +
                ", nrAdresu=" + nrAdresu +
                '}';
    }
}
