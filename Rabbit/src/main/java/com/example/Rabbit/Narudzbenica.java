package com.example.Rabbit;

import java.io.Serializable;
import java.time.LocalDate;

public class Narudzbenica implements Serializable{

        private int id;

        private String kupac;

        private LocalDate datumProdaje;

        private LocalDate datumPlacanja;

        private String adresa;

        private double ukupno;

        public Narudzbenica(int id, String kupac, LocalDate datumProdaje, LocalDate datumPlacanja, String adresa, double ukupno) {
            this.id = id;
            this.kupac = kupac;
            this.datumProdaje = datumProdaje;
            this.datumPlacanja = datumPlacanja;
            this.adresa = adresa;
            this.ukupno = ukupno;
        }

        public Narudzbenica(int id) {
            this.id = id;
            this.kupac = "default-kupac";
            this.datumProdaje = null;
            this.datumPlacanja = null;
            this.adresa = "default-adresa";
            this.ukupno = 0.0;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getKupac() {
            return kupac;
        }

        public void setKupac(String kupac) {
            this.kupac = kupac;
        }

        public LocalDate getDatumProdaje() {
            return datumProdaje;
        }

        public void setDatumProdaje(LocalDate datumProdaje) {
            this.datumProdaje = datumProdaje;
        }

        public LocalDate getDatumPlacanja() {
            return datumPlacanja;
        }

        public void setDatumPlacanja(LocalDate datumPlacanja) {
            this.datumPlacanja = datumPlacanja;
        }

        public String getAdresa() {
            return adresa;
        }

        public void setAdresa(String adresa) {
            this.adresa = adresa;
        }

        public double getUkupno() {
            return ukupno;
        }

        public void setUkupno(double ukupno) {
            this.ukupno = ukupno;
        }

        @Override
        public String toString() {
            return "Naruzbenica{" +
                    "id=" + id +
                    ", kupac='" + kupac + '\'' +
                    ", datumProdaje=" + datumProdaje +
                    ", datumPlacanja=" + datumPlacanja +
                    ", adresa='" + adresa + '\'' +
                    '}';
        }
}

