package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class DashboardController {

    @FXML
    private AnchorPane root;

    @FXML
    private AnchorPane rootNode;

    @FXML
    private Label lblAdminCount;

    @FXML
    private Label lblClock;

    @FXML
    private Label lblCourseCount;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblStudentCount;

    @FXML
    void btnAdminRegistrationOnAction(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AdminRegistration.fxml"));
        Parent rootNode = null;
        try {
            rootNode = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        root.getChildren().clear();
        root.getChildren().add(rootNode);
    }

    @FXML
    void btnCoordinatorRegistrationOnAction(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/CoordinatorRegistration.fxml"));
        Parent rootNode = null;
        try {
            rootNode = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        root.getChildren().clear();
        root.getChildren().add(rootNode);
    }

    @FXML
    void btnCulinaryProgramsOnAction(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/culinaryPrograms.fxml"));
        Parent rootNode = null;
        try {
            rootNode = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        root.getChildren().clear();
        root.getChildren().add(rootNode);
    }

    @FXML
    void btnLogOutOnAction(ActionEvent event) {
        AnchorPane anchorPane = null;
        try {
            anchorPane = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Stage stage = (Stage) rootNode.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Login Form");
        stage.centerOnScreen();
    }

    @FXML
    void btnPaymentDetailsOnAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Payment.fxml"));
            Parent rootNode = loader.load();

            root.getChildren().clear();
            root.getChildren().add(rootNode);
        } catch (IOException e) {
            e.printStackTrace(); // For debugging
            throw new RuntimeException("Failed to load the FXML file.", e);
        }
    }

    @FXML
    void btnStudentProfileOnAction(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Student.fxml"));
        Parent rootNode = null;
        try {
            rootNode = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        root.getChildren().clear();
        root.getChildren().add(rootNode);
    }

    @FXML
    void btnStudentRegistrationOnAction(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/studentRegistration.fxml"));
        Parent rootNode = null;
        try {
            rootNode = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        root.getChildren().clear();
        root.getChildren().add(rootNode);
    }

}
