package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class ChangeUsernameAndPassword {

    @FXML
    private AnchorPane root;

    @FXML
    private PasswordField txtNewPassword;

    @FXML
    private TextField txtNewUsername;

    @FXML
    private PasswordField txtOldPassword;

    @FXML
    private TextField txtOldUsername;

    @FXML
    private PasswordField txtReEnterPassword;

    @FXML
    void btnConfirmOnAction(ActionEvent event) {

    }

}
