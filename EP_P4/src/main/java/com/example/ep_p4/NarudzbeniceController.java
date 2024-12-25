package com.example.ep_p4;

import com.example.ep_p4.data_access.MySqlNarudzbenica;
import com.example.ep_p4.data_access.MySqlPloca;
import com.example.ep_p4.data_access.MySqlStavkaNarudzbenice;
import com.example.ep_p4.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConnectionFactory;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Stack;


public class NarudzbeniceController implements Initializable {

    @FXML
    private Button addBtn;

    @FXML
    private TextField addressTxt;

    @FXML
    private TextField customerTxt;

    @FXML
    private TableColumn<StavkaNarudzbenice, Integer> idCol;

    @FXML
    private TableView<StavkaNarudzbenice> itemTW = new TableView<>();

    @FXML
    private TextField idTxt;

    @FXML
    private Button openBtn;

    @FXML
    private DatePicker paymentDate;

    @FXML
    private TableColumn<StavkaNarudzbenice, Double> priceCol;

    @FXML
    private TableColumn<StavkaNarudzbenice, Integer> quantityCol;

    @FXML
    private TextField quantityTxt;

    @FXML
    private TextField totalTxt;

    @FXML
    private DatePicker saleDate;

    @FXML
    private Button saveBtn;

    @FXML
    private Button showBtn;

    @FXML
    private Button removeBtn;

    public Narudzbenica tekucaNarudzbenica = new Narudzbenica(MySqlNarudzbenica.prebrojNarudzbenice()+1);
    @FXML
    private Label warningLbl;

    public ArrayList<Ploca> listaPloca = new ArrayList<>();

    public ArrayList<StavkaNarudzbenice> listaStavki = new ArrayList<>();

    public static Stack<Ploca> ploce = new Stack<>();

    public static List<Obrada> obrade = new ArrayList<>();
    public static int brojPloca;
    public static List<Masina> masine = new ArrayList<>();

    public static String broker = "tcp://broker.emqx.io:1883";

    public static MqttConnectOptions options = new MqttConnectOptions();
    @FXML
    void addBtnClicked(ActionEvent event) {
        warningLbl.setText("");
        listaPloca = MySqlPloca.ucitajPloce();
        if("".equals(idTxt.getText())) {
            warningLbl.setText("Unesite id ploce!");
        }
        else {
            int id = Integer.parseInt(idTxt.getText());
            if(listaPloca.stream().anyMatch(p->p.getId()==id)){
                Ploca p = listaPloca.stream().filter(ploca -> ploca.getId() == id).toList().get(0);
                if(p.isNaStanju()) {
                    StavkaNarudzbenice sn = new StavkaNarudzbenice(p.getCijena(), 1, p.getId(), tekucaNarudzbenica.getId());
                    if(listaStavki.stream().anyMatch(st->st.getIdPloce()==id)) {
                        warningLbl.setText("Data ploca u procesu obrade.");
                    }else {
                        listaStavki.add(sn);
                        osvjeziTabeluStavki();
                        osvjeziUkupnuCijenu();
                    }
                }else {
                    warningLbl.setText("Data ploca nije dostupna!");
                }
            }else {
                warningLbl.setText("Neuspjesno dodavanje! Neispravan id ploce!");
            }
        }
    }

    public void pokreni() {

        try {
            options.setAutomaticReconnect(true);
            options.setCleanSession(true);
            options.setConnectionTimeout(10);
            options.setUserName("username");
            options.setPassword("password".toCharArray());

            for (int i = 0; i < 5; i++) {
                ploce.push(new Ploca(i + 1));
            }
            brojPloca = ploce.size();

            masine.add(new M1(222));
            masine.add(new M2(333));
            masine.add(new M3(444));

            for (Masina m : masine) {

                m.getClient().connect(options);
                System.out.println("Masina  konekcija  "+m.getMId() + " --> "+m.getClient().isConnected());
                if (m.getMId() != 222) {
                    System.out.println("Masina " + m.getMId()+" subscribe na topic" + m.topic);

                    m.getClient().subscribe(m.topic, (topic,msg) ->{
                        m.payload = msg.getPayload();
                        System.out.println("Message received "+m.getMId()+" : topic={"+topic+"}, payload="+ new String(m.payload));
                    });
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }


        for(Masina m : masine) {
            m.start();
        }

    }

    @FXML
    void removeBtnClicked(ActionEvent event) {
        StavkaNarudzbenice selectedItem = itemTW.getSelectionModel().getSelectedItem();
        listaStavki.remove(selectedItem);
        itemTW.getItems().remove(selectedItem);
        osvjeziUkupnuCijenu();
    }

    @FXML
    void saveBtn(ActionEvent event) {
        warningLbl.setText("");
        //"".equals(customerTxt.getText())
        if("".equals(customerTxt.getText()) || saleDate.getValue()==null || "".equals(addressTxt.getText()) || listaStavki.isEmpty()) {
            warningLbl.setText("Nepopunjena polja!");
        }
        else {
            tekucaNarudzbenica.setKupac(customerTxt.getText());
            tekucaNarudzbenica.setAdresa(addressTxt.getText());
            tekucaNarudzbenica.setDatumProdaje(saleDate.getValue());
            if(!(paymentDate.getValue()==null)) {
                tekucaNarudzbenica.setDatumPlacanja(paymentDate.getValue());
            }
            tekucaNarudzbenica.setUkupno(Double.parseDouble(totalTxt.getText()));
            MySqlNarudzbenica.dodajNarudzbenicu(tekucaNarudzbenica);
            MySqlStavkaNarudzbenice.dodajListuStavki(listaStavki);
           // kreirajTxtNarudzbenicu(tekucaNarudzbenica);
            konektuj(tekucaNarudzbenica);
            ukloniUnose();
            osvjeziTabeluStavki();
            azurirajTekucuNarudzbenicu();
            warningLbl.setText("Narudzbenica uspjesno kreirana!");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        pokreni();
        listaPloca = MySqlPloca.ucitajPloce();
        idCol.setCellValueFactory(new PropertyValueFactory<StavkaNarudzbenice,Integer>("idPloce"));
        quantityCol.setCellValueFactory(new PropertyValueFactory<StavkaNarudzbenice,Integer>("kolicina"));
        priceCol.setCellValueFactory(new PropertyValueFactory<StavkaNarudzbenice,Double>("cijena"));

        osvjeziTabeluStavki();

        openBtn.setOnAction((event) -> {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("KontrolnaTabla.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 450, 400);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        showBtn.setOnAction((event) -> {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("Ploce.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 605, 400);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });


    }

    public void azurirajTekucuNarudzbenicu() {
        tekucaNarudzbenica = new Narudzbenica(MySqlNarudzbenica.prebrojNarudzbenice()+1);
    }

    public void osvjeziTabeluStavki(){
        ObservableList<StavkaNarudzbenice> stavkaObservableList = FXCollections.observableArrayList();
        stavkaObservableList.addAll(listaStavki);
        itemTW.setItems(stavkaObservableList);
        itemTW.refresh();
    }

    public void osvjeziUkupnuCijenu() {
        double ukupno = listaStavki.stream().mapToDouble(StavkaNarudzbenice::getCijena).reduce(0,(a,b)->a+b);
        totalTxt.setText(""+ukupno);
    }

    public void ukloniUnose() {
        listaStavki.clear();
        totalTxt.setText("0.0");
        customerTxt.setText("");
        paymentDate.setValue(null);
        saleDate.setValue(null);
        addressTxt.setText("");
        idTxt.setText("");
    }

   public void konektuj(Narudzbenica narudzbenica){
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("user");
        factory.setPassword("secret");
        factory.setHost("localhost");
        factory.setPort(5672);

        try {
            byte [] message = kreirajTxtNarudzbenicu(narudzbenica).getBytes();

            Connection conn = factory.newConnection();
            Channel channel = conn.createChannel();
            channel.queueDeclare("spring-boot", false, false, false, null);
            channel.basicPublish("", "spring-boot", null, message);

        }catch (Exception e) {
            System.out.println("IOException");
        }
    }

    public String kreirajTxtNarudzbenicu(Narudzbenica narudzbenica) {
           return "id:" + narudzbenica.getId() + "\nkupac:" + narudzbenica.getKupac() + "\ndatum-prodaje:" + narudzbenica.getDatumProdaje() + "\ndatum-placanja:" + narudzbenica.getDatumPlacanja() + "\nadresa:" + narudzbenica.getAdresa() + "\nukupno:" + narudzbenica.getUkupno();
    }
}
