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
import javafx.scene.control.TextField;
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

public class MembersBookController implements Initializable {

    @FXML
    private TableView<Books> bookTable;

    @FXML
    private TableColumn<Books, Integer> bookIDColumn;

    @FXML
    private TableColumn<Books, String> bookNameColumn;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnHome;

    @FXML
    private TableColumn<Books, String> isAvailableColumn;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPassword;

    @FXML
    private ImageView MemberBookImg;

    ObservableList<Books> obList = FXCollections.observableArrayList();

    // PreparedStatement preparedStatement = null;
    // ResultSet rs = null;

    @Override
    public void initialize(URL location, ResourceBundle resourceBundle) {

        try{
            DatabaseConnector connectNow = new DatabaseConnector();
            Connection connectDB = connectNow.getConnection();

            //String query = "Select * from `books`";
            ResultSet rs = connectDB.createStatement().executeQuery("SELECT * FROM books");
            // preparedStatement = connectDB.prepareStatement(rs);
            // preparedStatement.executeQuery();

            while (rs.next()){
                obList.add(new Books(rs.getInt("bookID"), rs.getString("bookName"), rs.getString("isAvailable")));
            }

        }
        catch (SQLException e){
            Logger.getLogger(MembersBookController.class.getName()).log(Level.SEVERE, null, e);
        }

        bookIDColumn.setCellValueFactory(new PropertyValueFactory<>("bookID"));
        bookNameColumn.setCellValueFactory(new PropertyValueFactory<>("bookName"));
        isAvailableColumn.setCellValueFactory(new PropertyValueFactory<>("isBookAvailable"));

        bookTable.setItems(obList);

    }

    @FXML
    void HomeClick(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("member_view.fxml"));
        Stage window = (Stage) btnHome.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    @FXML
    void updateDetails(ActionEvent event) {

    }

}