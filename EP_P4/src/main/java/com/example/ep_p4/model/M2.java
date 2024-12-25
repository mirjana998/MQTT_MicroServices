package com.example.ep_p4.model;

import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;

public class M2 extends Masina {

    public M2(int id) {
        super(id,"topic/ravnanje");
    }

    public void uradi(Obrada obrada) {
        System.out.println("Masina M2 je izvrsila ravnanje ploce: " + getPloca().getId());
        this.getPloca().setRavna(true);
        try {
            this.sleep(1000);
            obrada.setVrijemeZavrsetka(System.currentTimeMillis());
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(this.getPloca());
            byte [] data = bos.toByteArray();
            MqttMessage mqttMessage = new MqttMessage(data);
            this.client.publish("topic/lakiranje",mqttMessage);
            oos.flush();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}