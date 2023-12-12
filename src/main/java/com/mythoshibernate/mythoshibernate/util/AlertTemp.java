package com.mythoshibernate.mythoshibernate.util;

import javafx.scene.control.Alert;

public class AlertTemp {
    //Plantilla para generar una alerta de error
    public static void mostrarError(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setContentText(mensaje);
        alerta.show();
    }
    //Plantilla para generar una alerta de informacion
    public static void mostrarInfo(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setContentText(mensaje);
        alerta.show();
    }
    //Plantilla para generar una alerta de warning
    public static void mostrarWarning(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.WARNING);
        alerta.setContentText(mensaje);
        alerta.show();
    }
}
