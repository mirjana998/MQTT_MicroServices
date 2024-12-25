package com.example.ep_p4;

import com.example.ep_p4.model.Masina;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;


import java.net.URL;
import java.util.ResourceBundle;

public class KontrolnaTablaController implements Initializable {

    @FXML
    private Button pauseM1Btn;

    @FXML
    private Button pauseM2Btn;

    @FXML
    private Button pauseM3Btn;

    @FXML
    private Button startM1Btn;

    @FXML
    private Button startM2Btn;

    @FXML
    private Button startM3Btn;

    @FXML
    private Button stopM1Btn;

    @FXML
    private Button stopM2Btn;

    @FXML
    private Button stopM3Btn;

    @FXML
    private Circle greenM1Ccl;

    @FXML
    private Circle greenM2Ccl;

    @FXML
    private Circle greenM3Ccl;

    @FXML
    private Circle redM1Ccl;

    @FXML
    private Circle redM2Ccl;

    @FXML
    private Circle redM3Ccl;

    @FXML
    private Circle yellowM1Ccl;

    @FXML
    private Circle yellowM2Ccl;

    @FXML
    private Circle yellowM3Ccl;

    @FXML
    void pauseM1BtnClicked(ActionEvent event) {
        updateCircle("pauza",222);
        Masina m =  NarudzbeniceController.masine.get(0);
        m.setPauzirana(true);
        KontrolerskiMikroservis.azurirajMasinu(m,"PP");
        KontrolerskiMikroservis.pokreniPauzuMasine(m,System.currentTimeMillis());
    }

    @FXML
    void pauseM2BtnClicked(ActionEvent event) {
        updateCircle("pauza",333);
        Masina m =  NarudzbeniceController.masine.get(1);
        m.setPauzirana(true);
        KontrolerskiMikroservis.azurirajMasinu(m,"PP");
        KontrolerskiMikroservis.pokreniPauzuMasine(m,System.currentTimeMillis());
    }

    @FXML
    void pauseM3BtnClicked(ActionEvent event) {
        updateCircle("pauza",444);
        Masina m =  NarudzbeniceController.masine.get(2);
        m.setPauzirana(true);
        KontrolerskiMikroservis.azurirajMasinu(m,"PP");
        KontrolerskiMikroservis.pokreniPauzuMasine(m,System.currentTimeMillis());
    }

    @FXML
    void startM1BtnClicked(ActionEvent event) {
        updateCircle("start",222);
        Masina m = NarudzbeniceController.masine.get(0);
        synchronized(m.lock) {
           m.lock.notify();
           m.setPauzirana(false);
           KontrolerskiMikroservis.azurirajMasinu(m,"KP");
           KontrolerskiMikroservis.zaustaviPauzuMasine(m,System.currentTimeMillis());
        }
    }

    @FXML
    void startM2BtnClicked(ActionEvent event) {
        updateCircle("start",333);
        Masina m = NarudzbeniceController.masine.get(1);
        synchronized(m.lock) {
            m.lock.notify();
            m.setPauzirana(false);
            KontrolerskiMikroservis.azurirajMasinu(m,"KP");
            KontrolerskiMikroservis.zaustaviPauzuMasine(m,System.currentTimeMillis());
        }
    }

    @FXML
    void startM3BtnClicked(ActionEvent event) {
        updateCircle("start",444);
        Masina m = NarudzbeniceController.masine.get(2);
        synchronized(m.lock) {
            m.lock.notify();
            m.setPauzirana(false);
            KontrolerskiMikroservis.azurirajMasinu(m,"KP");
            KontrolerskiMikroservis.zaustaviPauzuMasine(m,System.currentTimeMillis());
        }
    }

    @FXML
    void stopM1BtnClicked(ActionEvent event) {
        updateCircle("stop",222);
        Masina m = NarudzbeniceController.masine.get(0);
        m.setZaustavljena(true);
        KontrolerskiMikroservis.azurirajMasinu(m,"Z");
    }

    @FXML
    void stopM2BtnClicked(ActionEvent event) {
        updateCircle("stop",333);
        Masina m = NarudzbeniceController.masine.get(1);
        m.setZaustavljena(true);
        KontrolerskiMikroservis.azurirajMasinu(m,"Z");
    }

    @FXML
    void stopM3BtnClicked(ActionEvent event) {
        updateCircle("stop",444);
        Masina m = NarudzbeniceController.masine.get(2);
        m.setZaustavljena(true);
        KontrolerskiMikroservis.azurirajMasinu(m,"Z");
    }

    public void updateCircle(String stanje,int brojMasine) {
        switch (stanje) {
            case "start" :
                switch (brojMasine) {
                    case 222:
                        greenM1Ccl.setFill(Paint.valueOf("#66FF00"));
                        redM1Ccl.setFill(Paint.valueOf("#ecbeb9"));
                        yellowM1Ccl.setFill(Paint.valueOf("#ebe2a4"));
                        break;
                    case 333:
                        greenM2Ccl.setFill(Paint.valueOf("#66FF00"));
                        redM2Ccl.setFill(Paint.valueOf("#ecbeb9"));
                        yellowM2Ccl.setFill(Paint.valueOf("#ebe2a4"));
                        break;
                    default:
                        greenM3Ccl.setFill(Paint.valueOf("#66FF00"));
                        redM3Ccl.setFill(Paint.valueOf("#ecbeb9"));
                        yellowM3Ccl.setFill(Paint.valueOf("#ebe2a4"));
                }
                break;
            case "pauza" :
                switch (brojMasine) {
                    case 222:
                        yellowM1Ccl.setFill(Paint.valueOf("#FFEA00"));
                        greenM1Ccl.setFill(Paint.valueOf("#9ae493"));
                        redM1Ccl.setFill(Paint.valueOf("#ecbeb9"));
                        break;
                    case 333:
                        yellowM2Ccl.setFill(Paint.valueOf("#FFEA00"));
                        greenM2Ccl.setFill(Paint.valueOf("#9ae493"));
                        redM2Ccl.setFill(Paint.valueOf("#ecbeb9"));
                        break;
                    default:
                        yellowM3Ccl.setFill(Paint.valueOf("#FFEA00"));
                        greenM3Ccl.setFill(Paint.valueOf("#9ae493"));
                        redM3Ccl.setFill(Paint.valueOf("#ecbeb9"));
                }
                break;
            case "stop" :
                switch (brojMasine) {
                    case 222:
                        redM1Ccl.setFill(Paint.valueOf("#EE4B2B"));
                        greenM1Ccl.setFill(Paint.valueOf("#9ae493"));
                        yellowM1Ccl.setFill(Paint.valueOf("#ebe2a4"));
                        break;
                    case 333:
                        redM2Ccl.setFill(Paint.valueOf("#EE4B2B"));
                        greenM2Ccl.setFill(Paint.valueOf("#9ae493"));
                        yellowM2Ccl.setFill(Paint.valueOf("#ebe2a4"));
                        break;
                    default:
                        redM3Ccl.setFill(Paint.valueOf("#EE4B2B"));
                        greenM3Ccl.setFill(Paint.valueOf("#9ae493"));
                        yellowM3Ccl.setFill(Paint.valueOf("#ebe2a4"));
                }
                break;
            default:
                switch (brojMasine) {
                    case 222:
                        greenM1Ccl.setFill(Paint.valueOf("#9ae493"));
                        redM1Ccl.setFill(Paint.valueOf("#ecbeb9"));
                        yellowM1Ccl.setFill(Paint.valueOf("#ebe2a4"));
                        break;
                    case 333:
                        greenM2Ccl.setFill(Paint.valueOf("#9ae493"));
                        redM2Ccl.setFill(Paint.valueOf("#ecbeb9"));
                        yellowM2Ccl.setFill(Paint.valueOf("#ebe2a4"));
                        break;
                    default:
                        greenM3Ccl.setFill(Paint.valueOf("#9ae493"));
                        redM3Ccl.setFill(Paint.valueOf("#ecbeb9"));
                        yellowM3Ccl.setFill(Paint.valueOf("#ebe2a4"));
                }
        }

    }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Masina m1 = NarudzbeniceController.masine.get(0);
        Masina m2 = NarudzbeniceController.masine.get(1);
        Masina m3 = NarudzbeniceController.masine.get(2);
        inicijalizuj(m1);
        inicijalizuj(m2);
        inicijalizuj(m3);
    }

    private void inicijalizuj(Masina m) {
        if(!m.isZaustavljena()) {
            updateCircle("start",m.getMId());
        }
        else if(m.isPauzirana()) {
            updateCircle("pauza",m.getMId());
        }
        else if(m.isZaustavljena()) {
            updateCircle("stop",m.getMId());
        }
    }
}
