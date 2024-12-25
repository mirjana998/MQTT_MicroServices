package com.example.ep_p4.model;

import java.io.Serializable;
import java.util.Random;

public class Ploca implements Serializable {
    private int id;
    private boolean oblikovana;
    private boolean ravna;
    private boolean lakirana;
    double cijena;

    private boolean naStanju;

    public Ploca(int id, boolean oblikovana, boolean ravna, boolean lakirana, double cijena) {
        this.id = id;
        this.oblikovana = oblikovana;
        this.ravna = ravna;
        this.lakirana = lakirana;
        this.cijena = cijena;
    }

    public Ploca(int id) {
        this.id = id;
        this.oblikovana = false;
        this.lakirana = false;
        this.ravna = false;
        this.cijena = new Random().nextDouble() + 2;
        this.naStanju = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isOblikovana() {
        return oblikovana;
    }

    public void setOblikovana(boolean oblikovana) {
        this.oblikovana = oblikovana;
    }

    public boolean isRavna() {
        return ravna;
    }

    public void setRavna(boolean ravna) {
        this.ravna = ravna;
    }

    public boolean isLakirana() {
        return lakirana;
    }

    public void setLakirana(boolean lakirana) {
        this.lakirana = lakirana;
    }

    public double getCijena() {
        return cijena;
    }

    public void setCijena(double cijena) {
        this.cijena = cijena;
    }

    public boolean isNaStanju() {
        return naStanju;
    }

    public void setNaStanju(boolean naStanju) {
        this.naStanju = naStanju;
    }

    @Override
    public String toString() {
        return "Ploca{" +
                "id=" + id +
                ", oblikovana=" + oblikovana +
                ", ravna=" + ravna +
                ", lakirana=" + lakirana +
                ", cijena=" + cijena +
                '}';
    }
}
