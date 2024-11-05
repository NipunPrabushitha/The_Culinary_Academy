package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML
    private AnchorPane rootNode;

    @FXML
    private TextField txtAdminId;

    @FXML
    private PasswordField txtPassword;

    @FXML
    void btnLoginInOnAction(ActionEvent event) {
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
        stage.setTitle("Dashboard Form");
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
}
