package org.example.controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

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
    void initialize() {
        setDate();
        setLocalTime();
    }

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

    }

    @FXML
    void btnLogOutOnAction(ActionEvent event) {

    }

    @FXML
    void btnPaymentDetailsOnAction(ActionEvent event) {

    }

    @FXML
    void btnStudentProfileOnAction(ActionEvent event) {

    }

    @FXML
    void btnStudentRegistrationOnAction(ActionEvent event) {

    }


    private void setDate() {
        LocalDate now = LocalDate.now();
        lblDate.setText(String.valueOf(now));
    }

    private void setLocalTime() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss"); // Define format
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), e -> {
                    LocalTime timeInSriLanka = LocalTime.now(); // Get current time
                    String formattedTime = timeInSriLanka.format(formatter); // Format time
                    lblClock.setText(formattedTime); // Set formatted time to label
                })
        );
        timeline.setCycleCount(Animation.INDEFINITE); // Repeat indefinitely
        timeline.play(); // Start the timeline


    }

}