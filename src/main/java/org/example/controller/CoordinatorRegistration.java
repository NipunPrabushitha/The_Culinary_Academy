package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import org.example.bo.custom.AdminBO;
import org.example.bo.custom.CoordinatorBO;
import org.example.bo.custom.impl.AdminBOImpl;
import org.example.bo.custom.impl.CoordinatorBOImpl;
import org.example.model.AdminDTO;
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
        String useId = txtCoordinatorID.getText();
        String username = txtCoordinatorUsername.getText();
        String password = txtCoordinatorPassword.getText();
        String securityQuestion = txtCoordinatorSecurityQuestion.getText();

        String reEnter = txtCoordinatorRePassword.getText();

        if (reEnter.equals(password)){
            CoordinatorDTO coordinator = new CoordinatorDTO(useId, username, password, securityQuestion);

            boolean isSaved = false;
            try {
                CoordinatorBO coordinatorBO = new CoordinatorBOImpl();
                isSaved = coordinatorBO.saveCoordinator(coordinator);
            }catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                isSaved = false;
            }
            if (isSaved) {
                /*initialize();*/
                new Alert(Alert.AlertType.INFORMATION, "Coordinator saved successfully").show();
            }else {
                new Alert(Alert.AlertType.INFORMATION, "Coordinator saved Unsuccessfully").show();
            }
        }else{
            new Alert(Alert.AlertType.INFORMATION, "Password Not Matched").show();
            txtCoordinatorPassword.setText("");
            txtCoordinatorRePassword.setText("");
        }
    }

}
