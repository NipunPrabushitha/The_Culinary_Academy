package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.bo.custom.AdminBO;
import org.example.bo.custom.CoordinatorBO;
import org.example.bo.custom.impl.AdminBOImpl;
import org.example.bo.custom.impl.CoordinatorBOImpl;
import org.example.dao.DAOFactory;


import java.io.IOException;

public class LoginController {

    @FXML
    private AnchorPane rootNode;

    @FXML
    private TextField txtUsername;

    @FXML
    private PasswordField txtPassword;

    AdminBO adminBO = (AdminBO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOType.ADMIN);

    @FXML
    void btnLoginInOnAction(ActionEvent event) {

    }
    @FXML
    void forgetPasswordOnAction(ActionEvent event) {
        navigateToForgotPassword();
    }
    public void navigateToForgotPassword()  {
        AnchorPane rootNode = null;
        try {
            rootNode = FXMLLoader.load(this.getClass().getResource("/view/forgetPassword.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) this.rootNode.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Forget Password Form");
    }

    public String getUserIdByUserName(){
        String username = txtUsername.getText();
        String password = null;


        password = adminBO.getIdByUserName(username);

        if (password.equals("null")){
            CoordinatorBO coordinatorBO = new CoordinatorBOImpl();
            password = coordinatorBO.getIdByUserName(username);
            return password;
        }else {
            return password;
        }
    }


}
