package com.mythoshibernate.mythoshibernate.controller;

import com.mythoshibernate.mythoshibernate.App;
import com.mythoshibernate.mythoshibernate.dao.MythoDAO;
import com.mythoshibernate.mythoshibernate.dao.MythoDAOImpl;
import com.mythoshibernate.mythoshibernate.domain.Mytho;
import com.mythoshibernate.mythoshibernate.util.AlertTemp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
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

public class ModifyCtrl implements Initializable {
    //Este controlador maneja las modificaciones, el usuario elige el registro
    //Y cambia los datos que desee
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
    private TextField Nametxt;

    @FXML
    private TextField Origintxt;

    @FXML
    private Button SaveBtn;

    @FXML
    private TableView<Mytho> TbMythos;

    @FXML
    private TableColumn<?, ?> TbcMythos1;

    @FXML
    private TableColumn<?, ?> TbcMythos2;

    @FXML
    private Button VolverBtn;

    private Mytho mythoSelec;
    //Conexion Hibernate
    MythoDAO dao = new MythoDAOImpl();

    BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

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
    public void reloadTable(){
        List<Mytho> mythos = null;
        mythos = dao.getAllMythos();
        ObservableList<Mytho> listaMythos = FXCollections.observableArrayList(mythos);
        TbMythos.setItems(listaMythos);
    }
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

    @FXML
    void OnClickClose(ActionEvent event) {
        Stage stage = (Stage) this.CloseBtn.getScene().getWindow();
        stage.close();
    }
    //Es parecido a add.fxml, pero aqui usamos modificarMytho()
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

        System.out.println(tipo);
        System.out.println(genero);

        Mytho mytho = new Mytho(nombre, tipo, genero, origen, bio);
        dao.updateMytho(mytho);
        reloadTable();
    }
    //Recogemos los valores que el usuario ha seleccionado y los mostramos en las
    //Cajas para que sea mas sencillo cambiarlos
    public void cargarMytho(Mytho mytho){
        Nametxt.setText(mytho.getNombre());
        ClassCmb.setValue(mytho.getTipo());
        GenderCmb.setValue(mytho.getGenero());
        Origintxt.setText(mytho.getOrigen());
        Biotxt.setText(mytho.getBio());
    }
    @FXML
    public void seleccionarMytho(Event event){
        mythoSelec = TbMythos.getSelectionModel().getSelectedItem();
        cargarMytho(mythoSelec);
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
