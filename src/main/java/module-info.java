module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.library to javafx.fxml;
    exports com.example.library;
}