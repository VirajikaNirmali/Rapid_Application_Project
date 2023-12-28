package com.example.library;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;

public class RegisterController {

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnRegister;

    @FXML
    private Button btnLogin;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtEmail;

    @FXML
    private Label txtMessage;

    @FXML
    private TextField txtName;

    @FXML
    private PasswordField txtConfirmPassword;

    @FXML
    private Label lblPasswordConfirm;

    @FXML
    void cancelClicked(ActionEvent event) {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
        Platform.exit();
    }



    @FXML
    void registerClicked(ActionEvent event) {



        if(txtName.getText().isBlank() == false && txtEmail.getText().isBlank() == false && txtPassword.getText().isBlank() == false && txtConfirmPassword.getText().isBlank() == false){
            //String userName = username.getText();
            //String userPassword = password.getText();
            if(txtPassword.getText().equals(txtConfirmPassword.getText())){
                registerUser();

                lblPasswordConfirm.setText("You are set!");
            }
            else {
                lblPasswordConfirm.setText("Password doesn't match");
            }


        }
        else {
            txtMessage.setText("Please enter username and password");
        }


    }

    @FXML
    void loginPage(ActionEvent event) {
        toLoginForm();
    }

    public void toLoginForm() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
            Stage stage = (Stage) btnLogin.getScene().getWindow();
            Scene scene = new Scene(loader.load());
            stage.setTitle("Login Dashboard");
            stage.setScene(scene);
        }catch (IOException io){
            io.printStackTrace();
        }
    }

    public void registerUser(){

        DatabaseConnector connectNow = new DatabaseConnector();
        Connection connectDB = connectNow.getConnection();

        String Name = txtName.getText();
        String Email = txtEmail.getText();
        String Password = txtPassword.getText();
        String UserRole = "Member";

        String insertFields = "INSERT INTO users(name, email, password, userRole) VALUES ('";
        String insertValues = Name + "','" + Email + "','" + Password + "','" + UserRole + "')";
        String insertToRegister = insertFields + insertValues;

        try{

                Statement statement = connectDB.createStatement();
                statement.executeUpdate(insertToRegister);

                txtMessage.setText("User registered Successfully!");

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("User registration");

                alert.setHeaderText("User Registered");
                alert.setContentText("Registered successfully!");

                alert.showAndWait();

                txtName.clear();
                txtEmail.clear();
                txtPassword.clear();
                txtConfirmPassword.clear();

        }
        catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

}