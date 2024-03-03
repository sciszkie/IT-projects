package bada_proj.address;

import javax.persistence.*;


public class Adress {
    private int nrAdresu;
    private String nrBudynku;
    private String nrLokalu;
    private String ulica;
    private String miasto;
    private String kodPocztowy;

    public Adress(int nrAdresu, String nrBudynku, String nrLokalu, String ulica, String miasto, String kodPocztowy) {
        this.nrAdresu = nrAdresu;
        this.nrBudynku = nrBudynku;
        this.nrLokalu = nrLokalu;
        this.ulica = ulica;
        this.miasto = miasto;
        this.kodPocztowy = kodPocztowy;
    }

    public Adress(){}

    public int getNrAdresu() {
        return nrAdresu;
    }

    public void setNrAdresu(int nrAdresu) {
        this.nrAdresu = nrAdresu;
    }

    public String getNrBudynku() {
        return nrBudynku;
    }

    public void setNrBudynku(String nrBudynku) {
        this.nrBudynku = nrBudynku;
    }

    public String getNrLokalu() {
        return nrLokalu;
    }

    public void setNrLokalu(String lokalu) {
        this.nrLokalu = lokalu;
    }

    public String getUlica() {
        return ulica;
    }

    public void setUlica(String ulica) {
        this.ulica = ulica;
    }

    public String getMiasto() {
        return miasto;
    }

    public void setMiasto(String miasto) {
        this.miasto = miasto;
    }

    public String getKodPocztowy() {
        return kodPocztowy;
    }

    public void setKodPocztowy(String kodPocztowy) {
        this.kodPocztowy = kodPocztowy;
    }
    @Override
    public String toString() {
        return "Adress{" +
                "nrAdresu=" + nrAdresu +
                ", nrBudynku=" + nrBudynku +
                ", lokalu=" + nrLokalu +
                ", ulica='" + ulica + '\'' +
                ", miasto='" + miasto + '\'' +
                ", kodPocztowy='" + kodPocztowy + '\'' +
                '}';
    }

}
