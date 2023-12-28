package com.example.library;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AdminsMemberController implements Initializable {

    @FXML
    private Button HomeBtn;

    @FXML
    private TableColumn<Members, String> colEmail;

    @FXML
    private TableColumn<Members, Integer> colID;

    @FXML
    private TableColumn<Members, String> colName;

    @FXML
    private TableColumn<Members, String> colUserRole;

    @FXML
    private TableView<Members> memberTable;

    @FXML
    private ImageView adminMemberImg;

    @FXML
    void HomeClick(ActionEvent event) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("admin_view.fxml"));
        Stage window = (Stage) HomeBtn.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    ObservableList<Members> obList = FXCollections.observableArrayList();


    @Override
    public void initialize(URL location, ResourceBundle resourceBundle) {

        try{
            DatabaseConnector connectNow = new DatabaseConnector();
            Connection connectDB = connectNow.getConnection();

            ResultSet rs = connectDB.createStatement().executeQuery("SELECT * FROM users");


            while (rs.next()){
                obList.add(new Members(rs.getInt("id"), rs.getString("name"), rs.getString("email"), rs.getString("userRole")));
            }

        }
        catch (SQLException e){
            Logger.getLogger(AdminsAdminController.class.getName()).log(Level.SEVERE, null, e);
        }

        colID.setCellValueFactory(new PropertyValueFactory<>("MemberID"));
        colName.setCellValueFactory(new PropertyValueFactory<>("MemberName"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("MemberEmail"));
        colUserRole.setCellValueFactory(new PropertyValueFactory<>("MemberUserRole"));
        memberTable.setItems(obList);

    }

}

