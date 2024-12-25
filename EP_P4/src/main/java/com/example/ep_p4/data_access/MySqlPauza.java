package com.example.ep_p4.data_access;

import com.example.ep_p4.Config;
import com.example.ep_p4.model.Masina;
import com.example.ep_p4.model.Obrada;
import com.example.ep_p4.model.Pauza;

import java.sql.*;

public class MySqlPauza {

    public static void dodajPauzu(Pauza pauza) {

        String query = "insert into pauza (id, vrijeme_pocetka, vrijeme_zavrsetka, masina_id) values ("+ pauza.getId()+ ","+ pauza.getVrijemePocetka() + ","+ pauza.getVrijemeZavrsetka() + "," + pauza.getMasina().getMId()+")";
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

    public static int prebrojPauze() {
        String query = "select count(id) as id_ukupno from pauza";
        int brojPauza = 0;
        try {
            Class.forName(Config.getMySQLDriver());
            Connection con = DriverManager.getConnection(Config.getDatabaseUrl(), Config.getDatabaseUsername(), Config.getDatabasePassword());
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            rs.next();
            brojPauza = rs.getInt("id_ukupno");
            System.out.println(brojPauza);
            st.close();
            con.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return brojPauza;
    }

    public static void dodajKrajPauze(Masina masina, long vrijeme) {
        String sql = "{CALL UpdateNullAttribute(?, ?)}";

        try {
            Class.forName(Config.getMySQLDriver());
            Connection con = DriverManager.getConnection(Config.getDatabaseUrl(), Config.getDatabaseUsername(), Config.getDatabasePassword());
            CallableStatement stmt = con.prepareCall(sql);
            stmt.setLong(1, vrijeme);
            stmt.setInt(2, masina.getMId());
            stmt.execute();
            con.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
