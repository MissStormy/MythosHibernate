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
import javafx.scene.control.Label;
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

public class AppCtrl implements Initializable {
    @FXML
    private Button AddBtn;

    @FXML
    private Button CloseBtn;

    @FXML
    private Button EraseBtn;

    @FXML
    private Button ListBtn;

    @FXML
    private Button ModBtn;

    @FXML
    private TableView<Mytho> TbMythos;

    @FXML
    private TableColumn<?, ?> TbcMythos1;

    @FXML
    private TableColumn<?, ?> TbcMythos2;
    //Conexion Hibernate
    MythoDAO dao = new MythoDAOImpl();

    BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

    @FXML
    void OnClickAdd(ActionEvent event) throws IOException {
        //AppCtrl controller = new AppCtrl();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("add.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();

        //controller.cargarTabla();

        stage.initStyle(StageStyle.TRANSPARENT);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.show();

        Stage stagePrincipal = (Stage) AddBtn.getScene().getWindow();
        stagePrincipal.close();
    }
    @FXML
    void OnClickClose(ActionEvent event) {
        Stage stage = (Stage) this.CloseBtn.getScene().getWindow();
        stage.close();
    }
    @FXML
    void OnClickErase(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("erase.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initStyle(StageStyle.TRANSPARENT);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.show();

        Stage stagePrincipal = (Stage) EraseBtn.getScene().getWindow();
        stagePrincipal.close();
    }

    //Con este boton vamos a la pantalla list.fxml
    @FXML
    void OnClickList(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("list.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initStyle(StageStyle.TRANSPARENT);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.show();

        Stage stagePrincipal = (Stage) EraseBtn.getScene().getWindow();
        stagePrincipal.close();
    }

    @FXML
    void OnClickMod(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("modify.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initStyle(StageStyle.TRANSPARENT);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.show();

        Stage stagePrincipal = (Stage) EraseBtn.getScene().getWindow();
        stagePrincipal.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.TbcMythos1.setCellValueFactory(new PropertyValueFactory("nombre"));
        this.TbcMythos2.setCellValueFactory(new PropertyValueFactory("tipo"));

        List<Mytho> mythos = null;
        mythos = dao.getAllMythos();
        ObservableList<Mytho> listaMythos = FXCollections.observableArrayList(mythos);
        TbMythos.setItems(listaMythos);
    }
}