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

public class AdminsLibrarianController implements Initializable {

    @FXML
    private Button btnHome;

    @FXML
    private TableColumn<Librarian, String> colEmail;

    @FXML
    private TableColumn<Librarian, Integer> colID;

    @FXML
    private TableColumn<Librarian, String> colName;

    @FXML
    private TableView<Librarian> tableLibrarian;

    @FXML
    private ImageView adminLibrarianImg;

    @FXML
    void HomeClick(ActionEvent event) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("admin_view.fxml"));
        Stage window = (Stage) btnHome.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    ObservableList<Librarian> obList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resourceBundle) {

        try{
            DatabaseConnector connectNow = new DatabaseConnector();
            Connection connectDB = connectNow.getConnection();

            ResultSet rs = connectDB.createStatement().executeQuery("SELECT * FROM librarian");


            while (rs.next()){
                obList.add(new Librarian(rs.getInt("id"), rs.getString("name"), rs.getString("email")));
            }

        }
        catch (SQLException e){
            Logger.getLogger(AdminsAdminController.class.getName()).log(Level.SEVERE, null, e);
        }

        colID.setCellValueFactory(new PropertyValueFactory<>("LibrarianID"));
        colName.setCellValueFactory(new PropertyValueFactory<>("LibrarianName"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("LibrarianEmail"));

        tableLibrarian.setItems(obList);

    }

}
