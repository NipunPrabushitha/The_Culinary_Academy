package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import org.example.bo.custom.AdminBO;
import org.example.bo.custom.impl.AdminBOImpl;
import org.example.dao.DAOFactory;
import org.example.entity.Admin;
import org.example.model.AdminDTO;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

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

    AdminBO adminBO = (AdminBO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOType.ADMIN);

    @FXML
    void btnConfirmOnAction(ActionEvent event) {
        String username = txtAdminUsername.getText();
        String password = txtAdminPassword.getText();
        String rePassword = txtAdminRePassword.getText();
        String securityQuestion = txtAdminSecurityQuestion.getText();


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
