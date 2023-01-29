module com.example.RPrototype {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;
    requires java.sql;
    requires com.fazecast.jSerialComm;

    opens com.example.RPrototype to javafx.fxml;
    exports com.example.RPrototype;
}