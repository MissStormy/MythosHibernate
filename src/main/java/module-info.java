module com.mythoshibernate.mythoshibernate {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.mythoshibernate.mythoshibernate to javafx.fxml;
    exports com.mythoshibernate.mythoshibernate;
    exports com.mythoshibernate.mythoshibernate.controller;
    opens com.mythoshibernate.mythoshibernate.controller to javafx.fxml;
}