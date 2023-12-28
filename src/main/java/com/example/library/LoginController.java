package com.example.library;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class LoginController {

    @FXML
    private Button cancelBtn;

    @FXML
    private Button loginBtn;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    @FXML
    private Label msgLabel;

    @FXML
    private Button btnReg;


    @FXML
    void cancelClicked(ActionEvent event) {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    void loginClicked(ActionEvent event) throws IOException {

        if(username.getText().isBlank() == false && password.getText().isBlank() == false){
            //String userName = username.getText();
            //String userPassword = password.getText();
            userLogin();
            adminLogin();
            librarianLogin();
            Stage stage = (Stage) cancelBtn.getScene().getWindow();
            stage.close();


        }
        else {
            msgLabel.setText("Please enter username and password");
        }
    }

    //String userName = username.getText();
    //String userPassword = password.getText();


    @FXML
    void registrationPage(ActionEvent event) {
        toRegistrationForm();
    }

    public void userLogin() {

        DatabaseConnector connectNow = new DatabaseConnector();
        Connection connectDB = connectNow.getConnection();

        try{
            String userRole1 = "Member";

            String query = "SELECT count(1) FROM users WHERE email = '" + username.getText() + "' AND password = '" + password.getText() + "' AND userRole = '" + userRole1 + "'";


            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(query);


            while(queryResult.next()){

                if(queryResult.getInt(1) == 1){
                    // msgLabel.setText("Congratulations");

                    Stage primaryStage = new Stage();
                    Parent root = FXMLLoader.load(getClass().getResource("member_view.fxml"));
                    Scene scene = new Scene(root);
                    primaryStage.setScene(scene);
                    primaryStage.setTitle("Member Dashboard");
                    primaryStage.show();

                }

                else {
                  //msgLabel.setText("Login Failed");
                 }
            }

        }
        catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }


    }


    public void adminLogin() {

        DatabaseConnector connectNow = new DatabaseConnector();
        Connection connectDB = connectNow.getConnection();

        try{
            String userRole2 = "Admin";

            String query = "SELECT count(1) FROM admin WHERE email = '" + username.getText() + "' AND password = '" + password.getText() + "' AND userRole = '" + userRole2 + "'";

            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(query);


            while(queryResult.next()){

                if(queryResult.getInt(1) == 1){
                    // msgLabel.setText("Congratulations");

                    Stage primaryStage = new Stage();
                    Parent root = FXMLLoader.load(getClass().getResource("admin_view.fxml"));
                    Scene scene = new Scene(root);
                    primaryStage.setScene(scene);
                    primaryStage.setTitle("Admin Dashboard");
                    primaryStage.show();

                }

                else {
                    //msgLabel.setText("Login Failed");
                }
            }

        }
        catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }


    }



    public void librarianLogin() {

        DatabaseConnector connectNow = new DatabaseConnector();
        Connection connectDB = connectNow.getConnection();

        try{
            String userRole3 = "Librarian";

            String query = "SELECT count(1) FROM librarian WHERE email = '" + username.getText() + "' AND password = '" + password.getText() + "' AND userRole = '" + userRole3 + "'";

            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(query);


            while(queryResult.next()){

                if(queryResult.getInt(1) == 1){
                    // msgLabel.setText("Congratulations");


                    FXMLLoader loader = new FXMLLoader(getClass().getResource("librarian_view.fxml"));
                    Parent root = loader.load();

                    LibrarianViewController librarianViewController = loader.getController();
                    librarianViewController.transferMessage(username.getText());

                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.setTitle("Librarian Dashboard");
                    stage.show();
                }

                else {
                    // msgLabel.setText("Login Failed");
                }
            }

        }
        catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }


    }


    public void toRegistrationForm(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("register.fxml"));
            Stage stage = (Stage) btnReg.getScene().getWindow();
            Scene scene = new Scene(loader.load());
            stage.setTitle("Registration Form");
            stage.setScene(scene);
        }catch (IOException io){
            io.printStackTrace();
        }
    }

}
