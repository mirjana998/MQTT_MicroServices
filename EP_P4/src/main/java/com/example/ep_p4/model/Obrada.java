package com.example.ep_p4.model;

public class Obrada {
    private Masina masina;
    private Ploca ploca;
    private long vrijemePocetka;
    private long vrijemeZavrsetka;

    public Obrada(Masina masina, Ploca ploca, long vrijemePocetka, long vrijemeZavrsetka) {
        this.masina = masina;
        this.ploca = ploca;
        this.vrijemePocetka = vrijemePocetka;
        this.vrijemeZavrsetka = vrijemeZavrsetka;
    }

    public Obrada(Masina masina, Ploca ploca) {
        this.masina = masina;
        this.ploca = ploca;
    }

    public Masina getMasina() {
        return masina;
    }

    public void setMasina(Masina masina) {
        this.masina = masina;
    }

    public Ploca getPloca() {
        return ploca;
    }

    public void setPloca(Ploca ploca) {
        this.ploca = ploca;
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

    @Override
    public String toString() {
        return "Obrada{" +
                "masina=" + masina +
                ", ploca=" + ploca +
                ", vrijemePocetka=" + vrijemePocetka +
                ", vrijemeZavrsetka=" + vrijemeZavrsetka +
                '}';
    }
}
