package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class PaymentController {
    @FXML
    public TableColumn<?,?> colProgramName;
    @FXML
    public TableColumn<?,?> colStudentName;

    @FXML
    private ComboBox<?> cmbRegidtrationId;

    @FXML
    private TableColumn<?, ?> colDueamount;

    @FXML
    private TableColumn<?, ?> colDuration;

    @FXML
    private TableColumn<?, ?> colPaymentAmount;

    @FXML
    private TableColumn<?, ?> colPaymentDate;

    @FXML
    private TableColumn<?, ?> colProgramId;

    @FXML
    private TableColumn<?, ?> colRegistrationDate;

    @FXML
    private TableColumn<?, ?> colRegistrationId;

    @FXML
    private TableColumn<?, ?> colStetus;

    @FXML
    private TableColumn<?, ?> colStudentId;

    @FXML
    private DatePicker dpPaymentDate;

    @FXML
    private Label lblDueAmount;

    @FXML
    private Label lblDuration;

    @FXML
    private Label lblProgramId;

    @FXML
    private Label lblProgramName;

    @FXML
    private Label lblRegistraionDate;

    @FXML
    private Label lblStudentId;

    @FXML
    private Label lblStudentName;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<?> tblPayment;

    @FXML
    private TextField txtPaymentAmount;

    @FXML
    void PayOnAction(ActionEvent event) {

    }

    @FXML
    void cmbRegistrationIdOnAction(ActionEvent event) {

    }

}
