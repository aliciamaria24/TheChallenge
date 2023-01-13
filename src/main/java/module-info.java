module com.example.demo233445677 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;
    requires java.sql;


    opens com.example.RPrototype to javafx.fxml;
    exports com.example.RPrototype;
}