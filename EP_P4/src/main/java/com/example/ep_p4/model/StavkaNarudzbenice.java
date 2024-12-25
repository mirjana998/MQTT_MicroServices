package com.example.ep_p4.model;

public class StavkaNarudzbenice {

    private double cijena;

    private int kolicina;

    private int idPloce;

    private int idNarudzbenice;

    public StavkaNarudzbenice(double cijena, int kolicina, int idPloce, int idNarudzbenice) {
        this.cijena = cijena;
        this.kolicina = kolicina;
        this.idPloce = idPloce;
        this.idNarudzbenice = idNarudzbenice;
    }

    public double getCijena() {
        return cijena;
    }

    public void setCijena(double cijena) {
        this.cijena = cijena;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    public int getIdPloce() {
        return idPloce;
    }

    public void setIdPloce(int idPloce) {
        this.idPloce = idPloce;
    }

    public int getIdNarudzbenice() {
        return idNarudzbenice;
    }

    public void setIdNarudzbenice(int idNarudzbenice) {
        this.idNarudzbenice = idNarudzbenice;
    }
}
