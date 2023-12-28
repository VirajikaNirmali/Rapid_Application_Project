package com.example.library;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class LibrariansEventController {

    @FXML
    private Button btnHome;

    @FXML
    private ImageView librarianEventsImg;

    @FXML
    void HomeClick(ActionEvent event) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("librarian_view.fxml"));
        Stage window = (Stage) btnHome.getScene().getWindow();
        window.setScene(new Scene(root));
    }

}
