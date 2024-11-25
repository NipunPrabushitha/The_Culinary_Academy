package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.bo.BOFactory;
import org.example.bo.custom.AdminBO;
import org.mindrot.jbcrypt.BCrypt;


import java.io.IOException;

public class LoginController {

    @FXML
    private AnchorPane rootNode;

    @FXML
    private TextField txtUsername;

    @FXML
    private PasswordField txtPassword;

    AdminBO adminBO = (AdminBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ADMIN);

    @FXML
    void btnLoginInOnAction(ActionEvent event) {
        String password = txtPassword.getText();

        String Adminpassword = getUserIdByUserName();

        boolean isPasswordCorrect = BCrypt.checkpw(password,Adminpassword);

        if (isPasswordCorrect) {
            AnchorPane rootNode = null;
            try {
                rootNode = FXMLLoader.load(this.getClass().getResource("/view/Dashboard.fxml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            Scene scene = new Scene(rootNode);

            Stage stage = (Stage) this.rootNode.getScene().getWindow();
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.setTitle("Forget Password Form");
        } else {
            System.out.println("Invalid password. Access denied.");
        }

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
        return password;
    }


}
