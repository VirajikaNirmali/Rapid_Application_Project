package com.example.library;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class LibrarianViewController {

    @FXML
    private Button adminsBtn;

    @FXML
    private Button bookBtn;

    @FXML
    private Button eventsBtn;

    @FXML
    private Button membersBtn;

    @FXML
    private Button updataBtn;

    @FXML
    private Button btnLogout;

    @FXML
    private ImageView librarianDashboardImg;


    @FXML
    void AdminsClick(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("librarians_admin_view.fxml"));
        Stage window = (Stage) adminsBtn.getScene().getWindow();
        window.setTitle("Admins");
        window.setScene(new Scene(root));
    }

    @FXML
    void BooksClick(ActionEvent event) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("librarians_book_view.fxml"));
        Stage window = (Stage) bookBtn.getScene().getWindow();
        window.setTitle("Books");
        window.setScene(new Scene(root));
    }

    @FXML
    void EventsClick(ActionEvent event) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("librarians_event_view.fxml"));
        Stage window = (Stage) eventsBtn.getScene().getWindow();
        window.setTitle("Events");
        window.setScene(new Scene(root));
    }

    @FXML
    void MembersClick(ActionEvent event) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("librarians_member_view.fxml"));
        Stage window = (Stage) membersBtn.getScene().getWindow();
        window.setTitle("Members");
        window.setScene(new Scene(root));
    }

    @FXML
    void UpdateClick(ActionEvent event) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("librarians_update_details_view.fxml"));
        Stage window = (Stage) updataBtn.getScene().getWindow();
        window.setTitle("Update Details");
        window.setScene(new Scene(root));
    }

    @FXML
    void LogoutClick(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        Stage window = (Stage) btnLogout.getScene().getWindow();
        window.setTitle("Update Details");
        window.setScene(new Scene(root));
    }

    @FXML
    private Label name;

    public String UserEmail = null;


    public void transferMessage(String message) {
        //Display the message
        //UserEmail = message;
        name.setText(message);
    }




}
