package com.example.ep_p4.data_access;

import com.example.ep_p4.Config;
import com.example.ep_p4.model.Ploca;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class MySqlPloca {

    public static ArrayList<Ploca> ucitajPloce() {
        String query = "select * from ploca";
        ArrayList<Ploca> listaPloca = new ArrayList<>();
        try {
            Class.forName(Config.getMySQLDriver());
            Connection con = DriverManager.getConnection(Config.getDatabaseUrl(), Config.getDatabaseUsername(), Config.getDatabasePassword());
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()) {
                Ploca p = new Ploca(rs.getInt("id"));
                if (rs.getInt("oblikovana") == 1) {
                    p.setOblikovana(true);
                } else {
                    p.setOblikovana(false);
                }
                if(rs.getInt("ravna") == 1) {
                    p.setRavna(true);
                } else {
                    p.setOblikovana(false);
                }
                if( rs.getInt("lakirana")==1) {
                    p.setLakirana(true);
                } else {
                    p.setLakirana(false);
                }
                if( rs.getInt("na_stanju")==1) {
                    p.setNaStanju(true);
                } else {
                    p.setNaStanju(false);
                }
                p.setCijena(rs.getDouble("cijena"));
                listaPloca.add(p);
            }
            st.close();
            con.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return listaPloca;
    }
}
