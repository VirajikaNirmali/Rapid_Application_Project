package com.example.library;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminViewController {

    @FXML
    private Button adminsBtn;

    @FXML
    private Button booksBtn;

    @FXML
    private Button inspectorBtn;

    @FXML
    private Button librarianBtn;

    @FXML
    private Button membersBtn;

    @FXML
    private Button btnLogout;

    @FXML
    private ImageView adminDashboardImg;

    @FXML
    void adminsClick(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("admins_admin_view.fxml"));
        Stage window = (Stage) adminsBtn.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    @FXML
    void booksClick(ActionEvent event) throws Exception {
        //toBooks();
        Parent root = FXMLLoader.load(getClass().getResource("admins_book_view.fxml"));
        Stage window = (Stage) booksBtn.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    @FXML
    void inspectorClick(ActionEvent event) throws IOException {

    }

    @FXML
    void librarianClick(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("admins_librarian_view.fxml"));
        Stage window = (Stage) librarianBtn.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    @FXML
    void membersClick(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("admins_member_view.fxml"));
        Stage window = (Stage) membersBtn.getScene().getWindow();
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
