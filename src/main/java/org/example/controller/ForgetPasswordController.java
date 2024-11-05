package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.config.FactoryConfiguration;
import org.example.entity.Admin;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;

public class ForgetPasswordController {

    @FXML
    private AnchorPane root;

    @FXML
    private PasswordField txtNewPassword;

    @FXML
    private TextField txtQuestion;

    @FXML
    private PasswordField txtReEnterPassword;

    @FXML
    void btnConfirmOnAction(ActionEvent event) {
        navigateToLogIn();
    }
    public void navigateToLogIn()  {
        AnchorPane rootNode = null;
        try {
            rootNode = FXMLLoader.load(this.getClass().getResource("/view/Login.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Dashboard Form");
    }
}
