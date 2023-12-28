package com.example.library;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class MemberViewController {

    @FXML
    private Button btnBooks;

    @FXML
    private Button btnUpdateProfile;

    @FXML
    private Button btnLogout;

    @FXML
    private ImageView MemberDashboardImg;

    @FXML
    void BooksClick(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("members_book_view.fxml"));
        Stage window = (Stage) btnBooks.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    @FXML
    void UpdateProfileClick(ActionEvent event) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("members_update_details_view.fxml"));
        Stage window = (Stage) btnUpdateProfile.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    @FXML
    void LogoutClick(ActionEvent event) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        Stage window = (Stage) btnLogout.getScene().getWindow();
        window.setTitle("Update Details");
        window.setScene(new Scene(root));
    }

}