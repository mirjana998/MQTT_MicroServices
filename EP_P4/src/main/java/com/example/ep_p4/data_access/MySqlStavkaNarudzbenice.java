package com.example.ep_p4.data_access;

import com.example.ep_p4.Config;
import com.example.ep_p4.model.StavkaNarudzbenice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;

public class MySqlStavkaNarudzbenice {

    public static void dodajStavku(StavkaNarudzbenice stavkaNarudzbenice) {
        String query = "insert into stavka_narudzbenice (cijena, kolicina, narudzbenica_id, ploca_id) values (" + stavkaNarudzbenice.getCijena() + ","+stavkaNarudzbenice.getKolicina() + "," + stavkaNarudzbenice.getIdNarudzbenice() +"," + stavkaNarudzbenice.getIdPloce() +")";
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

    public static void dodajListuStavki(ArrayList<StavkaNarudzbenice> listaStavki) {
        for(StavkaNarudzbenice sn : listaStavki) {
            dodajStavku(sn);
        }
    }
}
