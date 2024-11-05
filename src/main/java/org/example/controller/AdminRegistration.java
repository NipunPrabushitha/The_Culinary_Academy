package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import org.example.bo.custom.AdminBO;
import org.example.bo.custom.impl.AdminBOImpl;
import org.example.entity.Admin;
import org.example.model.AdminDTO;

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
    private TableView<?> tblCoordinatorTable;

    @FXML
    private PasswordField txtAdminPassword;

    @FXML
    private PasswordField txtAdminRePassword;

    @FXML
    private TextField txtAdminSecurityQuestion;

    @FXML
    private TextField txtAdminUsername;

    @FXML
    void btnConfirmOnAction(ActionEvent event) {
        String useId = txtAdminId.getText();
        String username = txtAdminUsername.getText();
        String password = txtAdminPassword.getText();
        String securityQuestion = txtAdminSecurityQuestion.getText();

        String reEnter = txtAdminRePassword.getText();

        if (reEnter.equals(password)){
            AdminDTO admin = new AdminDTO(useId, username, password,securityQuestion);

            boolean isSaved = false;
            try {
                AdminBO adminBO = new AdminBOImpl();
                isSaved = adminBO.saveAdmin(admin);
            }catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                isSaved = false;
            }
            if (isSaved) {
                /*initialize();*/
                new Alert(Alert.AlertType.INFORMATION, "Admin saved successfully").show();
            }else {
                new Alert(Alert.AlertType.INFORMATION, "Admin saved Unsuccessfully").show();
            }
        }else{
            new Alert(Alert.AlertType.INFORMATION, "Password Not Match").show();
            txtAdminPassword.setText("");
            txtAdminRePassword.setText("");
        }
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
