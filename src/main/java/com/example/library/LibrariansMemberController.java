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

public class LibrariansMemberController implements Initializable {

    @FXML
    private Button HomeBtn;

    @FXML
    private Button addBtn;

    @FXML
    private TextField txtMemberEmail;

    @FXML
    private TextField txtMemberID;

    @FXML
    private TextField txtMemberName;

    @FXML
    private TextField txtMemberUserRole;

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
    private ImageView librarianMemberImg;

    @FXML
    void HomeClick(ActionEvent event) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("librarian_view.fxml"));
        Stage window = (Stage) HomeBtn.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    @FXML
    void AddClick(ActionEvent event) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("librarians_add_new_member_view.fxml"));
        Stage window = (Stage) addBtn.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    //ObservableList<Members> obList = FXCollections.observableArrayList();


    int myIndex;
    int id;


    public void table()
    {
        DatabaseConnector connectNow = new DatabaseConnector();
        Connection connectDB = connectNow.getConnection();

        ObservableList<Members> obList = FXCollections.observableArrayList();
        try{

            ResultSet rs = connectDB.createStatement().executeQuery("SELECT * FROM users");

            while (rs.next()){
                obList.add(new Members(rs.getInt("id"), rs.getString("name"), rs.getString("email"), rs.getString("userRole")));
            }

        }
        catch (SQLException e){
            Logger.getLogger(MembersBookController.class.getName()).log(Level.SEVERE, null, e);
        }

        colID.setCellValueFactory(new PropertyValueFactory<>("MemberID"));
        colName.setCellValueFactory(new PropertyValueFactory<>("MemberName"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("MemberEmail"));
        colUserRole.setCellValueFactory(new PropertyValueFactory<>("MemberUserRole"));

        memberTable.setItems(obList);

        memberTable.setRowFactory( tv -> {
            TableRow<Members> myRow = new TableRow<>();
            myRow.setOnMouseClicked (event ->
            {
                if (event.getClickCount() == 1 && (!myRow.isEmpty()))
                {
                    myIndex =  memberTable.getSelectionModel().getSelectedIndex();
                    id = Integer.parseInt(String.valueOf(memberTable.getItems().get(myIndex).getMemberID()));
                    txtMemberID.setText(String.valueOf(id));
                    txtMemberName.setText(memberTable.getItems().get(myIndex).getMemberName());
                    txtMemberEmail.setText(memberTable.getItems().get(myIndex).getMemberEmail());
                    txtMemberUserRole.setText(memberTable.getItems().get(myIndex).getMemberUserRole());

                }
            });
            return myRow;
        });


    }


    @FXML
    void ClearClick(ActionEvent event) {
        txtMemberID.clear();
        txtMemberName.clear();
        txtMemberEmail.clear();
        txtMemberUserRole.clear();
        table();
    }

    @FXML
    void DeleteClick(ActionEvent event) {
        DatabaseConnector connectNow = new DatabaseConnector();
        Connection connectDB = connectNow.getConnection();

        String memberid;

        myIndex = memberTable.getSelectionModel().getSelectedIndex();
        id = Integer.parseInt(String.valueOf(memberTable.getItems().get(myIndex).getMemberID()));

        memberid = txtMemberID.getText();

        String query = "DELETE FROM users WHERE id = '" + memberid +  "'";

        try
        {
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(query);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Member Delete");

            alert.setHeaderText("Member Deleted");
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
    void UpdateClick(ActionEvent event) {
        DatabaseConnector connectNow = new DatabaseConnector();
        Connection connectDB = connectNow.getConnection();

        String memberid, membername, memberemail, memberuserrole;

        myIndex = memberTable.getSelectionModel().getSelectedIndex();
        id = Integer.parseInt(String.valueOf(memberTable.getItems().get(myIndex).getMemberID()));

        memberid = txtMemberID.getText();
        membername = txtMemberName.getText();
        memberemail = txtMemberEmail.getText();
        memberuserrole = txtMemberUserRole.getText();

        //  String query = "UPDATE users SET id = '" + memberid + "' ,name = '" + membername + "' ,email = '" + memberemail + "' ,userRole = '" + memberuserrole +  "' where id = '" + memberid + "'";
        String query = "UPDATE users SET id = '" + memberid + "' ,name = '" + membername + "' ,email = '" + memberemail + "' ,userRole = '" + memberuserrole +  "' where id = '" + memberid + "'";

        try
        {
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(query);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);

            alert.setTitle("Member Update");
            alert.setHeaderText("Member Updated");
            alert.setContentText("Updated Successfully!");

            alert.showAndWait();
            txtMemberID.clear();
            txtMemberName.clear();
            txtMemberEmail.clear();
            txtMemberUserRole.clear();
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
