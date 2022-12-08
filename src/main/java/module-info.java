module com.example.thechallenge {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.thechallenge to javafx.fxml;
    exports com.example.thechallenge;
}