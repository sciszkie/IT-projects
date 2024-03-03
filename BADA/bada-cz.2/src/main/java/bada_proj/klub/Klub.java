package bada_proj.klub;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class Klub {
    private int nrKlubu;
    private String nazwa;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataZalozenia;
    private int nrAdresu;

    public Klub(int nrKlubu, String nazwa, LocalDate dataZalozenia, int nrAdresu) {
        this.nrKlubu = nrKlubu;
        this.nazwa = nazwa;
        this.dataZalozenia = dataZalozenia;
        this.nrAdresu = nrAdresu;
    }

    public Klub(){}

    public int getNrKlubu() {
        return nrKlubu;
    }

    public void setNrKlubu(int nrKlubu) {
        this.nrKlubu = nrKlubu;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public LocalDate getDataZalozenia() {
        return dataZalozenia;
    }

    public void setDataZalozenia(LocalDate dataZalozenia) {
        this.dataZalozenia = dataZalozenia;
    }

    public int getNrAdresu() {
        return nrAdresu;
    }

    public void setNrAdresu(int nrAdresu) {
        this.nrAdresu = nrAdresu;
    }

    @Override
    public String toString() {
        return "Klub{" +
                "nrKlubu=" + nrKlubu +
                ", nazwa='" + nazwa + '\'' +
                ", dataZalozenia='" + dataZalozenia + '\'' +
                ", nrAdresu=" + nrAdresu +
                '}';
    }
}
