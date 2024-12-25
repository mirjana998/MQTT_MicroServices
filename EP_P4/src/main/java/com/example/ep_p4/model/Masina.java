package com.example.ep_p4.model;

import com.example.ep_p4.KontrolerskiMikroservis;
import com.example.ep_p4.NarudzbeniceController;
import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttClient;


import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;

public abstract class Masina extends Thread {
    private int mId;
    public byte[] payload;
    private long vrijemePokretanja;
    private long vrijemeGasenja;
    private boolean pauzirana;
    private boolean zaustavljena;
    private Ploca ploca;

    IMqttClient client;

    public  String topic;

    public Object lock = new Object();

    public static int brojPloca = 0;
    public Masina(int id, String topic) {
        this.mId = id;
        this.topic = topic;
        this.zaustavljena = false;
        this.pauzirana = false;
        try {
            client = new MqttClient(NarudzbeniceController.broker, String.valueOf(mId));
        }catch (Exception e) {
            System.out.println("Kreiranje klijenta");
        }

    }

    public Masina(int id) {
        this.mId=id;
    }

    public Ploca getPloca() {
        return ploca;
    }

    public void setPloca(Ploca ploca) {
        this.ploca = ploca;
    }

    public int getMId() {
        return mId;
    }

    public void setMId(int id) {
        this.mId = id;
    }

    public long getVrijemeGasenja() {
        return vrijemeGasenja;
    }

    public void setVrijemeGasenja(long vrijemeGasenja) {
        this.vrijemeGasenja = vrijemeGasenja;
    }


    public boolean isPauzirana() {
        return pauzirana;
    }

    public void setPauzirana(boolean pauzirana) {
        this.pauzirana = pauzirana;
    }

    public boolean isZaustavljena() {
        return zaustavljena;
    }

    public void setZaustavljena(boolean zaustavljena) {
        this.zaustavljena = zaustavljena;
    }

    public long getVrijemePokretanja() {
        return vrijemePokretanja;
    }

    public void setVrijemePokretanja(long vrijemePokretanja) {
        this.vrijemePokretanja = vrijemePokretanja;
    }

    public IMqttClient getClient() {
        return client;
    }

    public void setClient(MqttClient client) {
        this.client = client;
    }

    public abstract void uradi(Obrada obrada);

    @Override
    public String toString() {
        return "Masina{" +
                "id=" + mId +
                ", vrijemePokretanja=" + vrijemePokretanja +
                ", vrijemeGasenja=" + vrijemeGasenja +
                ", pauzirana=" + pauzirana +
                ", zaustavljena=" + zaustavljena +
                '}';
    }

    @Override
        public void run() {
            setVrijemePokretanja(System.currentTimeMillis());
            KontrolerskiMikroservis.azurirajMasinu(this,"P");
            while(true) {
                try {
                    this.sleep(1000);
                }catch (Exception e){

                }
                if(zaustavljena) break;
                while (this.mId != 222 && this.payload != null) {
                   if(zaustavljena) {
                        System.out.println("Masina: M" + mId + "je prinudno zaustavljena.");
                        this.setVrijemeGasenja(System.currentTimeMillis());
                        KontrolerskiMikroservis.azurirajMasinu(this,"Z");
                        break;
                    }
                    if(pauzirana) {
                        try {
                            synchronized (this.lock) {
                                this.lock.wait();
                            }
                        }catch (Exception e) {

                        }
                    }
                    try {
                        ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(payload));
                        Ploca p = (Ploca) in.readObject();
                        this.ploca = p;
                        Obrada obrada = new Obrada(this,this.ploca);
                        obrada.setVrijemePocetka(System.currentTimeMillis());
                        this.uradi(obrada);
                        KontrolerskiMikroservis.obrade.add(obrada);
                        this.payload = null;
                        in.close();
                    }catch (Exception e) {
                        System.out.println("Unutar while m2 m3");
                    }
                }
                while(this.mId==222 && brojPloca<5) {
                    if(zaustavljena) {
                        System.out.println("Masina: M" + mId + "je prinudno zaustavljena.");
                        this.setVrijemeGasenja(System.currentTimeMillis());
                        KontrolerskiMikroservis.azurirajMasinu(this,"Z");
                        break;
                    }
                    if(pauzirana) {
                        try {
                            synchronized (this.lock) {
                                this.lock.wait();
                            }
                        }catch (Exception e) {

                        }
                    }
                    this.ploca= new Ploca(++brojPloca);
                    Obrada obrada = new Obrada(this,this.ploca);
                    obrada.setVrijemePocetka(System.currentTimeMillis());
                    this.uradi(obrada);
                    KontrolerskiMikroservis.obrade.add(obrada);
                    try {
                        this.sleep(10000);
                    }catch (Exception e) {
                        e.printStackTrace();
                    }
                }
        }
    }
}
