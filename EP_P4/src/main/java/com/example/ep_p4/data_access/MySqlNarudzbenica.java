package com.example.ep_p4.data_access;

import com.example.ep_p4.Config;
import com.example.ep_p4.model.Narudzbenica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MySqlNarudzbenica {

    public static int prebrojNarudzbenice() {
        String query = "select count(id) as id_ukupno from narudzbenica";
        int brojNarudzbenica = 0;
        try {
            Class.forName(Config.getMySQLDriver());
            Connection con = DriverManager.getConnection(Config.getDatabaseUrl(), Config.getDatabaseUsername(), Config.getDatabasePassword());
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            rs.next();
            brojNarudzbenica = rs.getInt("id_ukupno");
           // System.out.println(brojNarudzbenica);
            st.close();
            con.close();
            }
        catch (Exception e) {
            e.printStackTrace();
        }
        return brojNarudzbenica;
    }

    public static void dodajNarudzbenicu(Narudzbenica narudzbenica) {
        String query = "";
        if(narudzbenica.getDatumPlacanja()==null) {
           query = "insert into narudzbenica (id, kupac, datum_prodaje, datum_placanja, adresa, ukupno) values (" + narudzbenica.getId() + ",'"+ narudzbenica.getKupac() + "','" + narudzbenica.getDatumProdaje() +"', null,'" + narudzbenica.getAdresa() +"'," + narudzbenica.getUkupno() +")";
        }else {
            query = "insert into narudzbenica (id, kupac, datum_prodaje, datum_placanja, adresa, ukupno) values (" + narudzbenica.getId() + ",'"+ narudzbenica.getKupac() + "','" + narudzbenica.getDatumProdaje() +"','" + narudzbenica.getDatumPlacanja() + "','" + narudzbenica.getAdresa() + "'," + narudzbenica.getUkupno() +")";
        }
       // System.out.println(query);
        try {
            Class.forName(Config.getMySQLDriver());
            Connection con = DriverManager.getConnection(Config.getDatabaseUrl(), Config.getDatabaseUsername(), Config.getDatabasePassword());
            Statement st = con.createStatement();
            st.execute(query);
            st.close();
            con.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
