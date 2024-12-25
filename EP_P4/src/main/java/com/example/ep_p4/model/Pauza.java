package com.example.ep_p4.model;

public class Pauza {

    private int id;
    private long vrijemePocetka;
    private long vrijemeZavrsetka;
    private Masina masina;

    public Pauza(int id, long vrijemePocetka, long vrijemeZavrsetka, Masina masina) {
        this.id = id;
        this.vrijemePocetka = vrijemePocetka;
        this.vrijemeZavrsetka = vrijemeZavrsetka;
        this.masina = masina;
    }

    public Pauza(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getVrijemePocetka() {
        return vrijemePocetka;
    }

    public void setVrijemePocetka(long vrijemePocetka) {
        this.vrijemePocetka = vrijemePocetka;
    }

    public long getVrijemeZavrsetka() {
        return vrijemeZavrsetka;
    }

    public void setVrijemeZavrsetka(long vrijemeZavrsetka) {
        this.vrijemeZavrsetka = vrijemeZavrsetka;
    }

    public Masina getMasina() {
        return masina;
    }

    public void setMasina(Masina masina) {
        this.masina = masina;
    }
}
