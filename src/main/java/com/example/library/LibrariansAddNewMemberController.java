package com.example.library;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.Statement;

public class LibrariansAddNewMemberController {

    @FXML
    private Button btnHome;

    @FXML
    private Button btnUpdate;

    @FXML
    private Label lblPasswordConfirm;

    @FXML
    private PasswordField txtConfirmPassword;

    @FXML
    private TextField txtEmail;

    @FXML
    private Label txtMessage;

    @FXML
    private TextField txtName;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private ImageView librarianAddNewMemberImg;


    @FXML
    void HomeClick(ActionEvent event) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("librarians_member_view.fxml"));
        Stage window = (Stage) btnHome.getScene().getWindow();
        window.setTitle("Member");
        window.setScene(new Scene(root));
    }

    @FXML
    void UpdateClick(ActionEvent event) {
        if(txtPassword.getText().equals(txtConfirmPassword.getText())){
            registerMember();

            lblPasswordConfirm.setText("");
        }
        else {
            lblPasswordConfirm.setText("Password doesn't match!");
        }
    }



    public void registerMember(){

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

            txtMessage.setText("Member registered Successfully!");

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Member registration");

            alert.setHeaderText("Member Registered");
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
