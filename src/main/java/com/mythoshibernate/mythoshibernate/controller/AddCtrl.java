package com.mythoshibernate.mythoshibernate.controller;

import com.mythoshibernate.mythoshibernate.App;
import com.mythoshibernate.mythoshibernate.dao.MythoDAO;
import com.mythoshibernate.mythoshibernate.dao.MythoDAOImpl;
import com.mythoshibernate.mythoshibernate.domain.Mytho;
import com.mythoshibernate.mythoshibernate.util.AlertTemp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
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

public class AddCtrl implements Initializable {
    @FXML
    private TextField Biotxt;

    @FXML
    private Button CancelBtn;

    @FXML
    private ComboBox<String> ClassCmb;
    ObservableList<String> classes = FXCollections.observableArrayList("Primigenio", "Dios Exterior", "Monstruo");

    @FXML
    private Button CleanBtn;

    @FXML
    private Button CloseBtn;

    @FXML
    private ComboBox<String> GenderCmb;
    ObservableList<String> genders = FXCollections.observableArrayList("Femenino", "Masculino", "Otro");

    @FXML
    private ListView<?> MythList;

    @FXML
    private TextField Nametxt;

    @FXML
    private TextField Origintxt;

    @FXML
    private Button SaveBtn;
    @FXML
    private Button VolverBtn;
    @FXML
    private TableView<Mytho> TbMythos;

    @FXML
    private TableColumn<?, ?> TbcMythos1;

    @FXML
    private TableColumn<?, ?> TbcMythos2;

    //Conexion Hibernate
    MythoDAO dao = new MythoDAOImpl();

    BufferedReader input = new BufferedReader(new InputStreamReader(System.in));


    //El initialize nos permite mostrar los datos en la TableView nada mas arrancar la app
    //Tambien nos permite ajustar los valores de los ComboBox "Genero" y "Tipo"
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        GenderCmb.setItems(genders);
        GenderCmb.getSelectionModel().selectFirst();
        ClassCmb.setItems(classes);
        ClassCmb.getSelectionModel().selectFirst();

        this.TbcMythos1.setCellValueFactory(new PropertyValueFactory("nombre"));
        this.TbcMythos2.setCellValueFactory(new PropertyValueFactory("tipo"));

        List<Mytho> mythos = null;
        mythos = dao.getAllMythos();
        ObservableList<Mytho> listaMythos = FXCollections.observableArrayList(mythos);
        TbMythos.setItems(listaMythos);
    }
    //ReloadTable(): nos permite refrescar la tabla cada vez que hacemos algun cambio
    public void reloadTable(){
        List<Mytho> mythos = null;
        mythos = dao.getAllMythos();
        ObservableList<Mytho> listaMythos = FXCollections.observableArrayList(mythos);
        TbMythos.setItems(listaMythos);
    }
    //Si cancelamos, simplemente nos aparecera un aviso de que se van a borrar los datos
    //Es lo mismo que borrar, pero con mas clase
    @FXML
    void OnClickCancel(ActionEvent event) {
        AlertTemp.mostrarWarning("Se van a borrar todos los datos");
        limpiarTexto();
    }

    @FXML
    void OnClickClean(ActionEvent event) {
        limpiarTexto();
    }

    private void limpiarTexto(){
        Nametxt.setText("");
        Origintxt.setText("");
        Biotxt.setText("");
    }
    //Como no he puesto una barra superior, el botón de cerrar esta en la propia app
    @FXML
    void OnClickClose(ActionEvent event) {
        Stage stage = (Stage) this.CloseBtn.getScene().getWindow();
        stage.close();
    }
    //Al hacer click en guardar, recogemos los datos de los TextField y ComboBox
    //Y creamos una instancia para guardar los datos del nuevo mito
    //Tras ello, refrescamos la tabla
    @FXML
    void OnClickSave(ActionEvent event) throws SQLException {
        String nombre = Nametxt.getText();
        if(nombre.equals("")){
            AlertTemp.mostrarError("El nombre es obligatorio");
            return;
        }
        String tipo = ClassCmb.getSelectionModel().getSelectedItem();
        String genero = GenderCmb.getSelectionModel().getSelectedItem();

        String origen = Origintxt.getText();
        String bio = Biotxt.getText();

        Mytho mytho = new Mytho(nombre, tipo, genero, origen, bio);
        dao.saveMytho(mytho);
        reloadTable();

    }
    //Un boton simple para volver a la pantalla principal
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
