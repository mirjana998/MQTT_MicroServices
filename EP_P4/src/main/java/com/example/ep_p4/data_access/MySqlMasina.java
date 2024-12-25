package com.example.ep_p4.data_access;

import com.example.ep_p4.Config;
import com.example.ep_p4.model.Masina;
import com.example.ep_p4.model.Obrada;
import com.example.ep_p4.model.Ploca;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class MySqlMasina {

    public static void azurirajMasinu(Masina masina, String tipAzuriranja) {
        String query = "";

        switch (tipAzuriranja) {
            case "P" :
                query = "update masina set vrijeme_pokretanja=" + masina.getVrijemePokretanja() + ", pokrenuta = 1 where id=" + masina.getMId();
                break;
            case "Z" :
                query = "update masina set vrijeme_gasenja=" + masina.getVrijemeGasenja() + ", zaustavljena = 1 where id=" + masina.getMId();
                break;
            case "PP":
                query = "update masina set pauzirana= 1 where id=" + masina.getMId();
                break;
            default: //case "PZ"
                query = "update masina set pauzirana= 0 where id=" + masina.getMId();

        }
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

    /*
    public static ArrayList<Ploca> ucitajMasine() {
        String query = "select * from masina";
        ArrayList<Masina> listaMasina = new ArrayList<>();
        try {
            Class.forName(Config.getMySQLDriver());
            Connection con = DriverManager.getConnection(Config.getDatabaseUrl(), Config.getDatabaseUsername(), Config.getDatabasePassword());
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()) {
                //Masina m = new Masina(rs.getInt("id"));
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
    }*/


}
