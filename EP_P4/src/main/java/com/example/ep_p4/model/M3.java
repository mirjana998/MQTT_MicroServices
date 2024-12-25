package com.example.ep_p4.model;

import com.example.ep_p4.KontrolerskiMikroservis;
import org.eclipse.paho.client.mqttv3.IMqttClient;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.DecimalFormat;


public class M3 extends Masina {

    public M3(int id) {
        super(id, "topic/lakiranje");
    }

    public void uradi(Obrada obrada) {
        System.out.println("Masina M3 je izvrsila lakiranje ploce: " + getPloca().getId());
        this.getPloca().setLakirana(true);
        this.getPloca().setNaStanju(true);
        smjesti();
        try {
            this.sleep(1000);
            obrada.setVrijemeZavrsetka(System.currentTimeMillis());
            KontrolerskiMikroservis.obrade.add(obrada);
            KontrolerskiMikroservis.dodajObrade(this.getPloca().getId());
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void smjesti(){
        int i,j,k,l;
        DecimalFormat decfor = new DecimalFormat("0.00");
        i = getPloca().isOblikovana() ? 1:0;
        j = getPloca().isRavna() ? 1:0;
        k = getPloca().isLakirana() ? 1:0;
        l = getPloca().isNaStanju()? 1:0;
        String plocaJson = "{\n" +
                "\"id\": " + getPloca().getId() +",\n"+
                "\"oblikovana\": " + i + ",\n"+
                "\"ravna\": " + j +",\n"+
                "\"lakirana\": " + k +",\n"+
                "\"cijena\": " + decfor.format(getPloca().getCijena())+",\n"+
                "\"na_stanju\": " + l +"\n"+
                "}";
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(new URI("http://localhost:8080/ploca")).headers("Content-Type", "application/json;charset=UTF-8").POST(HttpRequest.BodyPublishers.ofString(plocaJson)).build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.statusCode()+"\n"+response.body()+"\n"+request.bodyPublisher().toString()+"\n"+response.headers());
            System.out.println(plocaJson);
        }catch (Exception e ) {
            e.printStackTrace();
        }
    }

    /*
    @Override
    public void run() {

    }*/
}