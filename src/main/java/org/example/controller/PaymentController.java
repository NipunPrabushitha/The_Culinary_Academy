package org.example.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import org.example.ViewTm.PaymentTM;
import org.example.ViewTm.RegistrationTM;
import org.example.bo.BOFactory;
import org.example.bo.custom.CourseBO;
import org.example.bo.custom.RegistrationBO;
import org.example.bo.custom.StudentBo;
import org.example.entity.Course;
import org.example.entity.Registration;
import org.example.entity.Student;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class PaymentController {
    @FXML
    public TableColumn<?,?> colProgramName;
    @FXML
    public TableColumn<?,?> colStudentName;

    @FXML
    private ComboBox<Long> cmbRegidtrationId;

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
    private TableView<PaymentTM> tblPayment;

    @FXML
    private TextField txtPaymentAmount;
    RegistrationBO registrationBO = (RegistrationBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.REGISTRATION);
    StudentBo studentBO = (StudentBo) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.STUDENT);
    CourseBO courseBO = (CourseBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.COURSE);
    public void initialize(){
        getRegistrationId();
        setCellValueFactory();
        loadAllPayments();
    }

    private void setCellValueFactory() {
        colRegistrationId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colRegistrationDate.setCellValueFactory(new PropertyValueFactory<>("regDate"));
        colDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        colStudentId.setCellValueFactory(new PropertyValueFactory<>("sid"));
        colStudentName.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        colProgramId.setCellValueFactory(new PropertyValueFactory<>("courseId"));
        colProgramName.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        colPaymentAmount.setCellValueFactory(new PropertyValueFactory<>("payment"));
        colDueamount.setCellValueFactory(new PropertyValueFactory<>("dueAmount"));
        colPaymentDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colStetus.setCellValueFactory(new PropertyValueFactory<>("status"));
    }

    private void loadAllPayments() {
        ObservableList<PaymentTM> students = FXCollections.observableArrayList();
        List<Registration> registrations = null;
        try {
            registrations = registrationBO.getAllRegistrationDetails();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (int i = 0; i < registrations.size(); i++) {
            PaymentTM registrationTM = new PaymentTM(
                    registrations.get(i).getId(),
                    registrations.get(i).getDuration(),
                    registrations.get(i).getPaymentDate(),
                    registrations.get(i).getRegistrationDate(),
                    registrations.get(i).getStudent().getId(),
                    registrations.get(i).getStudentName(),
                    registrations.get(i).getCourse().getProgramId(),
                    registrations.get(i).getProgramName(),
                    registrations.get(i).getPayment(),
                    registrations.get(i).getDueAmount(),
                    registrations.get(i).getStatus()

            );

            students.add(registrationTM);
        }
        tblPayment.setItems(students);
    }

    @FXML
    void PayOnAction(ActionEvent event) {
        Long id = (Long) cmbRegidtrationId.getValue();
        LocalDate regDate = LocalDate.parse(lblRegistraionDate.getText());
        String studentId = lblStudentId.getText();
        String studentName = lblStudentName.getText();
        String programId = lblProgramId.getText();
        String programName = lblProgramName.getText();
        String duration = lblDuration.getText();
        LocalDate paymentDate = dpPaymentDate.getValue();
        double payment = Double.parseDouble(txtPaymentAmount.getText());
        double dueAmount = Double.parseDouble(lblDueAmount.getText());
        String status = null;

        if ((dueAmount<=payment)){
            status="paid";
            dueAmount=0;
        }else{
            status="pending";
            dueAmount -= payment;
        }
        Student student = null;
        try {
            student = studentBO.serachbyIDS(Integer.parseInt(studentId));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        Course course = null;
        try {
            course = courseBO.serachbyCIDs(programId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        Registration registration = new Registration(id,duration,regDate,paymentDate,payment,dueAmount,studentName,programName,status,student,course);
        boolean isUpdate;
        try {
            isUpdate = registrationBO.updateRegistration(registration);
            setCellValueFactory();
            loadAllPayments();
        } catch (IOException e) {
            isUpdate = false;
            throw new RuntimeException(e);
        }
        if (isUpdate) {
            new Alert(Alert.AlertType.INFORMATION, "Payment Successful!").show();
        }else {
            new Alert(Alert.AlertType.ERROR, "Payment Failed!").show();
        }


    }

    @FXML
    void cmbRegistrationIdOnAction(ActionEvent event) {
        Long id = (Long) cmbRegidtrationId.getValue();
        try {
            Registration registration = null;
            try {
                registration = registrationBO.serachbyRID(id);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            if (registration!= null) {
                lblDueAmount.setText(String.valueOf(registration.getDueAmount()));
                lblDuration.setText(registration.getDuration());
                lblProgramId.setText(String.valueOf(registration.getCourse().getProgramId()));
                lblProgramName.setText(registration.getCourse().getProgramName());
                lblRegistraionDate.setText(registration.getRegistrationDate().toString());
                lblStudentId.setText(String.valueOf(registration.getStudent().getId()));
                lblStudentName.setText(registration.getStudentName());
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public void getRegistrationId(){
        try {
            List<Registration> allStudents = registrationBO.getAllRegistrationDetails();
            for (Registration s : allStudents) {
                cmbRegidtrationId.getItems().add(s.getId());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
