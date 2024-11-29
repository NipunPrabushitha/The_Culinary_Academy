package org.example.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import org.example.ViewTm.AdminTm;
import org.example.bo.BOFactory;
import org.example.bo.custom.AdminBO;
import org.example.model.AdminDTO;

import java.util.List;

public class AdminRegistration {

    @FXML
    private Label lblAdminID;

    @FXML
    private TextField txtAdminId;

    @FXML
    private AnchorPane root;

    @FXML
    private TableColumn<?, ?> tblAdminID;

    @FXML
    private TableColumn<?, ?> tblAdminUsername;

    @FXML
    private TableView<AdminTm> tblAdminTable;

    @FXML
    private PasswordField txtAdminPassword;

    @FXML
    private PasswordField txtAdminRePassword;

    @FXML
    private TextField txtAdminSecurityQuestion;

    @FXML
    private TextField txtAdminUsername;

    AdminBO adminBO = (AdminBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ADMIN);

    public void initialize() {
        lordAllAdmins();
        setCellValueFactory();
    }

    private void setCellValueFactory() {
        tblAdminID.setCellValueFactory(new PropertyValueFactory<>("UserId"));
        tblAdminUsername.setCellValueFactory(new PropertyValueFactory<>("userName"));
    }

    private void lordAllAdmins() {
        ObservableList<AdminTm> admins = FXCollections.observableArrayList();
        List<AdminDTO> adminDTOS = adminBO.getAllAdmins();

        for(AdminDTO admin : adminDTOS){
            AdminTm adminTm = null;
            if(admin.getRole().equals("admin")){
                 adminTm = new AdminTm(
                        admin.getUserId(),
                        admin.getUserName()
                );
            }
            admins.add(adminTm);
        }
        tblAdminTable.setItems(admins);
    }

    @FXML
    void btnConfirmOnAction(ActionEvent event) {
        String userId = txtAdminId.getText();
        String username = txtAdminUsername.getText();
        String password = txtAdminPassword.getText();
        String repassword  = txtAdminRePassword.getText();
        String securityQuestion = txtAdminSecurityQuestion.getText();
        String role = "admin";

        if(password.equals(repassword)){
            boolean saved = adminBO.saveAdmin(new AdminDTO(userId,username, password, securityQuestion,role));
            clearFields();
            lordAllAdmins();
            setCellValueFactory();
            if(saved){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Admin Registration Successful");
                alert.setContentText("Admin ID: " + userId);
                alert.showAndWait();
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Admin Registration Failed");
                alert.setContentText("Please try again!");
                alert.showAndWait();
            }
        }else{
            clearFields();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Password Not Matched");
            alert.setContentText("Please try again!");
            alert.showAndWait();
            txtAdminRePassword.setText("");
            txtAdminPassword.setText("");
        }

    }

    public void clearFields(){
        txtAdminId.setText("");
        txtAdminUsername.setText("");
        txtAdminPassword.setText("");
        txtAdminRePassword.setText("");
        txtAdminSecurityQuestion.setText("");
    }


    /*public void getCurrentOrderId(){
        *//*String nextOrderId = orderBO.getId();
        lblOrderId.setText(nextOrderId);*//*
        AdminBO adminBO = new AdminBOImpl();
        String newAdminId = adminBO.getAdminId();
        lblAdminID.setText(newAdminId);
    }*/
   /* public static String splitAdminId(String string) {
        if(string != null) {
            String[] strings = string.split("A0");
            int id = Integer.parseInt(strings[1]);
            id++;
            String ID = String.valueOf(id);
            int length = ID.length();
            if (length < 2){
                return "A00"+id;
            }else {
                if (length < 3){
                    return "A0"+id;
                }else {
                    return "A"+id;
                }
            }
        }
        return "A001";
    }*/
}
