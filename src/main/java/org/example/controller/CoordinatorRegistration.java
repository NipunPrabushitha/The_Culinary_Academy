package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import org.example.bo.BOFactory;
import org.example.bo.custom.AdminBO;
import org.example.model.AdminDTO;


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

    AdminBO adminBO = (AdminBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ADMIN);


    @FXML
    void btnConfirmOnAction(ActionEvent event) {
        String userId = txtCoordinatorID.getText();
        String username = txtCoordinatorUsername.getText();
        String password = txtCoordinatorPassword.getText();
        String repassword  = txtCoordinatorRePassword.getText();
        String securityQuestion = txtCoordinatorSecurityQuestion.getText();
        String role = "coordinator";

        if(password.equals(repassword)){
            boolean saved = adminBO.saveAdmin(new AdminDTO(userId,username, password, securityQuestion,role));
            clearFields();
            if(saved){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Coordinator Registration Successful");
                alert.setContentText("Coordinator ID: " + userId);
                alert.showAndWait();
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Coordinator Registration Failed");
                alert.setContentText("Please try again!");
                alert.showAndWait();
            }
        }else{
            clearFields();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Password Not Matched");
            alert.setContentText("Please try again!");
            alert.showAndWait();
            txtCoordinatorRePassword.setText("");
            txtCoordinatorPassword.setText("");
        }
    }
    public void clearFields(){
        txtCoordinatorID.setText("");
        txtCoordinatorUsername.setText("");
        txtCoordinatorPassword.setText("");
        txtCoordinatorRePassword.setText("");
        txtCoordinatorSecurityQuestion.setText("");
    }

}
