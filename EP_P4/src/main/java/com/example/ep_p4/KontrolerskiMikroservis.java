package com.example.ep_p4;

import com.example.ep_p4.data_access.MySqlMasina;
import com.example.ep_p4.data_access.MySqlObrada;
import com.example.ep_p4.data_access.MySqlPauza;
import com.example.ep_p4.model.Masina;
import com.example.ep_p4.model.Obrada;
import com.example.ep_p4.model.Pauza;

import java.util.ArrayList;
import java.util.List;

public class KontrolerskiMikroservis {

    public static List<Obrada> obrade = new ArrayList<>();

    public static void azurirajMasinu(Masina masina, String tipAzuriranja) {
        MySqlMasina.azurirajMasinu(masina,tipAzuriranja);
    }

    public static void pokreniPauzuMasine(Masina masina, long vrijeme) {
        Pauza p = new Pauza(MySqlPauza.prebrojPauze()+1);
        p.setMasina(masina);
        p.setVrijemePocetka(vrijeme);
        MySqlPauza.dodajPauzu(p);
    }

    public static void zaustaviPauzuMasine(Masina masina, long vrijeme) {
        MySqlPauza.dodajKrajPauze(masina,vrijeme);
    }

    public static void dodajObrade(int idPloce) {
        List<Obrada> temp = obrade.stream().filter(obrada -> obrada.getPloca().getId()==idPloce).toList();
        temp.forEach(obrada ->  MySqlObrada.dodajObradu(obrada));
    }
}
