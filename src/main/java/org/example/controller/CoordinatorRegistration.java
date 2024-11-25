package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import org.example.bo.custom.CoordinatorBO;
import org.example.bo.custom.impl.CoordinatorBOImpl;
import org.example.model.CoordinatorDTO;


public class CoordinatorRegistration {

    @FXML
    private Label lblCoordinatorID;

    @FXML
    private AnchorPane root;

    @FXML
    private TableColumn<?, ?> tblCoordinatorID;

    @FXML
    private TableView<?> tblCoordinatorTable;

    @FXML
    private TableColumn<?, ?> tblCoordinatorUsername;

    @FXML
    private TextField txtCoordinatorID;

    @FXML
    private PasswordField txtCoordinatorPassword;

    @FXML
    private PasswordField txtCoordinatorRePassword;

    @FXML
    private TextField txtCoordinatorSecurityQuestion;

    @FXML
    private TextField txtCoordinatorUsername;



    @FXML
    void btnConfirmOnAction(ActionEvent event) {

    }

}
