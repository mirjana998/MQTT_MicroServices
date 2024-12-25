package com.example.ep_p4.data_access;

import com.example.ep_p4.Config;
import com.example.ep_p4.model.Narudzbenica;
import com.example.ep_p4.model.Obrada;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class MySqlObrada {

    public static void dodajObradu(Obrada obrada) {

        String query = "insert into obrada (vrijeme_pocetka, vrijeme_zavrsetka, masina_id, ploca_id) values (" + obrada.getVrijemePocetka() + ","+ obrada.getVrijemeZavrsetka() + "," + obrada.getMasina().getMId() +"," + obrada.getPloca().getId() +")";
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
