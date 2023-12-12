package com.mythoshibernate.mythoshibernate.controller;

import com.mythoshibernate.mythoshibernate.App;
import com.mythoshibernate.mythoshibernate.dao.MythoDAO;
import com.mythoshibernate.mythoshibernate.dao.MythoDAOImpl;
import com.mythoshibernate.mythoshibernate.domain.Mytho;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class ListCtrl implements Initializable {
    //Este controlador maneja la vista de lista de los registros al completo
    @FXML
    private Button CloseBtn;
    @FXML
    private Button VolverBtn;
    @FXML
    private TableView<Mytho> TbMythos;

    @FXML
    private TableColumn<?, ?> TbcMythos1;

    @FXML
    private TableColumn<?, ?> TbcMythos2;

    @FXML
    private TableColumn<?, ?> TbcMythos3;

    @FXML
    private TableColumn<?, ?> TbcMythos4;

    @FXML
    private TableColumn<?, ?> TbcMythos5;

    //Conexion Hibernate
    MythoDAO dao = new MythoDAOImpl();

    BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

    //Simplemente mostramos todos los registros, no hace nada mas
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        this.TbcMythos1.setCellValueFactory(new PropertyValueFactory("nombre"));
        this.TbcMythos2.setCellValueFactory(new PropertyValueFactory("tipo"));
        this.TbcMythos3.setCellValueFactory(new PropertyValueFactory("genero"));
        this.TbcMythos4.setCellValueFactory(new PropertyValueFactory("origen"));
        this.TbcMythos5.setCellValueFactory(new PropertyValueFactory("bio"));

        List<Mytho> mythos = null;
        mythos = dao.getAllMythos();
        ObservableList<Mytho> listaMythos = FXCollections.observableArrayList(mythos);
        TbMythos.setItems(listaMythos);
    }
    @FXML
    void OnClickClose(ActionEvent event) {
        Stage stage = (Stage) this.CloseBtn.getScene().getWindow();
        stage.close();
    }
    @FXML
    void OnClickVolver(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("main.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initStyle(StageStyle.TRANSPARENT);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.show();

        Stage stagePrincipal = (Stage) VolverBtn.getScene().getWindow();
        stagePrincipal.close();
    }
}
