package com.example.library;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LibrariansBookController implements Initializable{

    @FXML
    private TableColumn<Books, Integer> bookIDColumn;

    @FXML
    private TableColumn<Books, String> bookNameColumn;

    @FXML
    private TableView<Books> bookTable;

    @FXML
    private Button btnHome;

    @FXML
    private TableColumn<Books, String> isAvailableColumn;

    @FXML
    private TextField txtBookID;

    @FXML
    private TextField txtBookName;

    @FXML
    private TextField txtIsBookAvailable;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnClear;

    @FXML
    private ImageView librarianBookImg;

    @FXML
    void HomeClick(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("librarian_view.fxml"));
        Stage window = (Stage) btnHome.getScene().getWindow();
        window.setScene(new Scene(root));
    }



    //ObservableList<Books> obList = FXCollections.observableArrayList();


    int myIndex;
    int id;


    public void table()
    {
        DatabaseConnector connectNow = new DatabaseConnector();
        Connection connectDB = connectNow.getConnection();

        ObservableList<Books> obList = FXCollections.observableArrayList();
        try{

            ResultSet rs = connectDB.createStatement().executeQuery("SELECT * FROM books");

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

        bookTable.setRowFactory( tv -> {
            TableRow<Books> myRow = new TableRow<>();
            myRow.setOnMouseClicked (event ->
            {
                if (event.getClickCount() == 1 && (!myRow.isEmpty()))
                {
                    myIndex =  bookTable.getSelectionModel().getSelectedIndex();
                    id = Integer.parseInt(String.valueOf(bookTable.getItems().get(myIndex).getBookID()));
                    txtBookID.setText(String.valueOf(id));
                    txtBookName.setText(bookTable.getItems().get(myIndex).getBookName());
                    txtIsBookAvailable.setText(bookTable.getItems().get(myIndex).getIsBookAvailable());

                }
            });
            return myRow;
        });


    }


    @FXML
    void AddClick(ActionEvent event) {

        DatabaseConnector connectNow = new DatabaseConnector();
        Connection connectDB = connectNow.getConnection();

        String bookid,bookname,isavailable;
        bookid = txtBookID.getText();
        bookname = txtBookName.getText();
        isavailable = txtIsBookAvailable.getText();

        String insertFields = "INSERT INTO books(bookName, isAvailable) VALUES ('";
        String insertValues = bookname + "','" + isavailable + "')";
        String query = insertFields + insertValues;

        try
        {
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(query);

            //txtMessage.setText("User registered Successfully!");

            Alert alert = new Alert(Alert.AlertType.INFORMATION);

            alert.setTitle("Book Registration");
            alert.setHeaderText("Book Registered");
            alert.setContentText("Registered successfully!");

            alert.showAndWait();

            txtBookID.clear();
            txtBookName.clear();
            txtIsBookAvailable.clear();

            table();
        }
        catch (SQLException e)
        {
            //Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            Logger.getLogger(MembersBookController.class.getName()).log(Level.SEVERE, null, e);
        }
    }



    @FXML
    void UpdateClick(ActionEvent event) {

        DatabaseConnector connectNow = new DatabaseConnector();
        Connection connectDB = connectNow.getConnection();

        String bookid,bookname,bookisavailable;

        myIndex = bookTable.getSelectionModel().getSelectedIndex();
        id = Integer.parseInt(String.valueOf(bookTable.getItems().get(myIndex).getBookID()));

        bookid = txtBookID.getText();
        bookname = txtBookName.getText();
        bookisavailable = txtIsBookAvailable.getText();

        String query = "UPDATE books SET bookID = '" + bookid + "' ,bookName = '" + bookname + "' ,isAvailable = '" + bookisavailable + "' where bookID = '" + bookid + "'";

        try
        {
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(query);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);

            alert.setTitle("Book Update");
            alert.setHeaderText("Book Updated");
            alert.setContentText("Updated Successfully!");

            alert.showAndWait();
            txtBookID.clear();
            txtBookName.clear();
            txtIsBookAvailable.clear();
            table();
        }
        catch (SQLException e)
        {
            //Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            Logger.getLogger(MembersBookController.class.getName()).log(Level.SEVERE, null, e);
        }
    }


    @FXML
    void DeleteClick(ActionEvent event) {

        DatabaseConnector connectNow = new DatabaseConnector();
        Connection connectDB = connectNow.getConnection();

        String bookid;

        myIndex = bookTable.getSelectionModel().getSelectedIndex();
        id = Integer.parseInt(String.valueOf(bookTable.getItems().get(myIndex).getBookID()));

        bookid = txtBookID.getText();

        String query = "DELETE FROM books WHERE bookID = '" + bookid +  "'";

        try
        {
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(query);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Book Delete");

            alert.setHeaderText("Book Deleted");
            alert.setContentText("Deleted successfully!");

            alert.showAndWait();
            table();
        }
        catch (SQLException ex)
        {
            //Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            Logger.getLogger(MembersBookController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void ClearClick(ActionEvent event) {
        txtBookID.clear();
        txtBookName.clear();
        txtIsBookAvailable.clear();
        table();
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        table();
    }

}

