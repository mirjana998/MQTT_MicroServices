package com.example.ep_p4.model;

import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;

public class M1 extends Masina {


    public M1(int id) {
        super(id, "topic/oblikovanje");
    }

    public void uradi(Obrada obrada) {
        System.out.println("Masina M1 je izvrsila oblikovanje ploce: " + getPloca().getId());
        this.getPloca().setOblikovana(true);
        try {
            this.sleep(1000);
            obrada.setVrijemeZavrsetka(System.currentTimeMillis());
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(this.getPloca());
            byte [] data = bos.toByteArray();
            MqttMessage mqttMessage = new MqttMessage(data);
            System.out.println("M1 " + this.client.isConnected() );
            this.client.publish("topic/ravnanje",mqttMessage);
            oos.flush();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
