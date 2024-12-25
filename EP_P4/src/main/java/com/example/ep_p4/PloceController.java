package com.example.ep_p4;

import com.example.ep_p4.data_access.MySqlPloca;
import com.example.ep_p4.model.Ploca;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.sql.*;

public class PloceController implements Initializable {

    @FXML
    private TableColumn<Ploca, Double> cijenaCol;

    @FXML
    private TableColumn<Ploca,Integer> idCol;

    @FXML
    private TableColumn<Ploca, Boolean> lakiranaCol;

    @FXML
    private TableColumn<Ploca, Boolean> oblikovanaCol;

    @FXML
    private TableColumn<Ploca, Boolean> ravnaCol;

    @FXML
    private TableColumn<Ploca, Boolean> stanjeCol;

    public ArrayList<Ploca> listaPloca = new ArrayList<>();

    @FXML
    private TableView<Ploca> ploceTW = new TableView<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ucitajPloce();
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        oblikovanaCol.setCellValueFactory(new PropertyValueFactory<>("oblikovana"));
        ravnaCol.setCellValueFactory(new PropertyValueFactory<>("ravna"));
        lakiranaCol.setCellValueFactory(new PropertyValueFactory<>("lakirana"));
        cijenaCol.setCellValueFactory(new PropertyValueFactory<>("cijena"));
        stanjeCol.setCellValueFactory(new PropertyValueFactory<>("naStanju"));

        ObservableList<Ploca> plocaObservableList = FXCollections.observableArrayList();
        plocaObservableList.addAll(listaPloca);
        ploceTW.setItems(plocaObservableList);
        ploceTW.refresh();

    }

    public void ucitajPloce() {
        listaPloca = MySqlPloca.ucitajPloce();
    }
}
