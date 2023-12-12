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
import java.util.Optional;
import java.util.ResourceBundle;

public class EraseCtrl implements Initializable {
    //Este controlador maneja la pantalla de borrado, el sistema es simple,
    //El usuario pulsa sobre la lista el registro que quiere borrar y despues
    //Sobre el gran boton en medio de la pantalla, se pedira confirmacion antes
    //De borrar cualquier registro
    @FXML
    private Button CloseBtn;

    @FXML
    private Button EraseBtn;

    @FXML
    private Button VolverBtn;
    @FXML
    private TableView<Mytho> TbMythos;

    @FXML
    private TableColumn<?,?> TbcMythos1;

    @FXML
    private TableColumn<?,?> TbcMythos2;

    //Conexion Hibernate
    MythoDAO dao = new MythoDAOImpl();

    BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.TbcMythos1.setCellValueFactory(new PropertyValueFactory("nombre"));
        this.TbcMythos2.setCellValueFactory(new PropertyValueFactory("tipo"));

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

    //Se recoge el registro que el usuario ha pulsado y se borra tras una confirmacion
    @FXML
    void OnClickErase(ActionEvent event) {
        Mytho mytho = TbMythos.getSelectionModel().getSelectedItem();
        if(mytho == null){
            AlertTemp.mostrarWarning("No has elegido nada");
            return;
        }


        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacion.setTitle("Eliminar mytho");
        confirmacion.setContentText("¿Estas seguro?");
        Optional<ButtonType> respuesta = confirmacion.showAndWait();
        if(respuesta.get().getButtonData() == ButtonBar.ButtonData.CANCEL_CLOSE){
            return;
        }
        int id = mytho.getId();
        dao.deleteMythoById(id);
        reloadTable();


    }
    public void reloadTable(){
        List<Mytho> mythos = null;
        mythos = dao.getAllMythos();
        ObservableList<Mytho> listaMythos = FXCollections.observableArrayList(mythos);
        TbMythos.setItems(listaMythos);
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
