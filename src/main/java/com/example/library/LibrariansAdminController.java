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

public class LibrariansAdminController implements Initializable {

    @FXML
    private TableView<Admins> adminTable;

    @FXML
    private Button btnHome;

    @FXML
    private Button clearBtn;

    @FXML
    private Button addBtn;

    @FXML
    private Button deleteBtn;

    @FXML
    private Button updateBtn;

    @FXML
    private TextField txtAdminEmail;

    @FXML
    private TextField txtAdminID;

    @FXML
    private TextField txtAdminName;

    @FXML
    private TextField txtAdminUserRole;

    @FXML
    private TableColumn<Admins, String> colEmail;

    @FXML
    private TableColumn<Admins, Integer> colID;

    @FXML
    private TableColumn<Admins, String> colName;

    @FXML
    private TableColumn<Admins, String> colUserRole;

    @FXML
    private ImageView librarianAdminImg;

    //ObservableList<Admins> obList = FXCollections.observableArrayList();


    int myIndex;
    int id;

    public void table()
    {
        DatabaseConnector connectNow = new DatabaseConnector();
        Connection connectDB = connectNow.getConnection();

        ObservableList<Admins> obList = FXCollections.observableArrayList();
        try{

            ResultSet rs = connectDB.createStatement().executeQuery("SELECT * FROM admin");

            while (rs.next()){
                obList.add(new Admins(rs.getInt("id"), rs.getString("name"), rs.getString("email"), rs.getString("userRole")));
            }

        }
        catch (SQLException e){
            Logger.getLogger(MembersBookController.class.getName()).log(Level.SEVERE, null, e);
        }

        colID.setCellValueFactory(new PropertyValueFactory<>("AdminID"));
        colName.setCellValueFactory(new PropertyValueFactory<>("AdminName"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("AdminEmail"));
        colUserRole.setCellValueFactory(new PropertyValueFactory<>("AdminUserRole"));

        adminTable.setItems(obList);

        adminTable.setRowFactory( tv -> {
            TableRow<Admins> myRow = new TableRow<>();
            myRow.setOnMouseClicked (event ->
            {
                if (event.getClickCount() == 1 && (!myRow.isEmpty()))
                {
                    myIndex =  adminTable.getSelectionModel().getSelectedIndex();
                    id = Integer.parseInt(String.valueOf(adminTable.getItems().get(myIndex).getAdminID()));
                    txtAdminID.setText(String.valueOf(id));
                    txtAdminName.setText(adminTable.getItems().get(myIndex).getAdminName());
                    txtAdminEmail.setText(adminTable.getItems().get(myIndex).getAdminEmail());
                    txtAdminUserRole.setText(adminTable.getItems().get(myIndex).getAdminUserRole());

                }
            });
            return myRow;
        });


    }




    @FXML
    void HomeClick(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("librarian_view.fxml"));
        Stage window = (Stage) btnHome.getScene().getWindow();
        window.setScene(new Scene(root));
    }


    @FXML
    void ClearClick(ActionEvent event) {
        txtAdminID.clear();
        txtAdminName.clear();
        txtAdminEmail.clear();
        txtAdminUserRole.clear();
    }

    @FXML
    void DeleteClick(ActionEvent event) {
        DatabaseConnector connectNow = new DatabaseConnector();
        Connection connectDB = connectNow.getConnection();

        String memberid;

        myIndex = adminTable.getSelectionModel().getSelectedIndex();
        id = Integer.parseInt(String.valueOf(adminTable.getItems().get(myIndex).getAdminID()));

        memberid = txtAdminID.getText();

        String query = "DELETE FROM admin WHERE id = '" + memberid +  "'";

        try
        {
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(query);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Admin Delete");

            alert.setHeaderText("Admin Deleted");
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
    void AddClick(ActionEvent event) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("librarians_add_new_admin_view.fxml"));
        Stage window = (Stage) addBtn.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    @FXML
    void UpdateClick(ActionEvent event) {
        DatabaseConnector connectNow = new DatabaseConnector();
        Connection connectDB = connectNow.getConnection();

        String adminid, adminname, adminemail, adminuserrole;

        myIndex = adminTable.getSelectionModel().getSelectedIndex();
        id = Integer.parseInt(String.valueOf(adminTable.getItems().get(myIndex).getAdminID()));

        adminid = txtAdminID.getText();
        adminname = txtAdminName.getText();
        adminemail = txtAdminEmail.getText();
        adminuserrole = txtAdminUserRole.getText();

        //  String query = "UPDATE users SET id = '" + memberid + "' ,name = '" + membername + "' ,email = '" + memberemail + "' ,userRole = '" + memberuserrole +  "' where id = '" + memberid + "'";
        String query = "UPDATE admin SET id = '" + adminid + "' ,name = '" + adminname + "' ,email = '" + adminemail + "' ,userRole = '" + adminuserrole +  "' where id = '" + adminid+ "'";

        try
        {
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(query);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);

            alert.setTitle("Admin Update");
            alert.setHeaderText("Admin Updated");
            alert.setContentText("Updated Successfully!");

            alert.showAndWait();
            txtAdminID.clear();
            txtAdminName.clear();
            txtAdminEmail.clear();
            txtAdminUserRole.clear();
            table();
        }
        catch (SQLException e)
        {
            //Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            Logger.getLogger(MembersBookController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        table();
    }

}
