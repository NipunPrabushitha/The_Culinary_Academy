package org.example.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
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
import java.util.ArrayList;
import java.util.List;

public class StudentRegistration {

    @FXML
    private Label Amountduetxt;

    @FXML
    private Label CourseDuration;

    @FXML
    private TextField Paymenttxt;

    @FXML
    private TableView<RegistrationTM> RegisterTable;

    @FXML
    private ComboBox<Integer> StudentIDComboBox;

    @FXML
    private ComboBox<String> StudentIDComboCourseComboBox;

    @FXML
    private ComboBox<String> cmbSelectStatus;

    @FXML
    private TableColumn<?, ?> colPayment;

    @FXML
    private TableColumn<?, ?> colProgram;

    @FXML
    private TableColumn<?, ?> colStatus;

    @FXML
    private TableColumn<?, ?> colcid;

    @FXML
    private TableColumn<?, ?> coldate;

    @FXML
    private TableColumn<?, ?> coldueAmonut;

    @FXML
    private TableColumn<?, ?> colduration;

    @FXML
    private TableColumn<?, ?> colsid;

    @FXML
    private TableColumn<?, ?> colsname;

    @FXML
    private Label courseName;

    @FXML
    private DatePicker datecombo;

    @FXML
    private Label fee;

    @FXML
    private Label registrationNum;

    @FXML
    private AnchorPane root;

    @FXML
    private Label studentMobile;

    @FXML
    private Label studentName;

    RegistrationBO registrationBO = (RegistrationBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.REGISTRATION);
    StudentBo studentBO = (StudentBo) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.STUDENT);
    CourseBO courseBO = (CourseBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.COURSE);

    public void initialize(){
        getStudentId();
        getCourseId();
        getStatus();
        loadAllStudents();
        setCellValueFactory();

    }
    private void setCellValueFactory() {
        colsid.setCellValueFactory(new PropertyValueFactory<>("Sid"));
        colsname.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        colcid.setCellValueFactory(new PropertyValueFactory<>("courseId"));
        colProgram.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        coldate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colduration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        colPayment.setCellValueFactory(new PropertyValueFactory<>("payment"));
        coldueAmonut.setCellValueFactory(new PropertyValueFactory<>("dueAmount"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
    }

    private void loadAllStudents() {
        ObservableList<RegistrationTM> students = FXCollections.observableArrayList();
        List<Registration> registrations = null;
        try {
            registrations = registrationBO.getAllRegistrationDetails();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (int i = 0; i < registrations.size(); i++) {
            RegistrationTM registrationTM = new RegistrationTM(
                    registrations.get(i).getId(),
                    registrations.get(i).getDuration(),
                    registrations.get(i).getPaymentDate(),
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
        RegisterTable.setItems(students);
    }

    @FXML
    void RegisterComfirmOnAction(ActionEvent event) {
        Long id = 0L;
        Integer studentId = StudentIDComboBox.getValue();
        String courseId = StudentIDComboCourseComboBox.getValue();
        String studentFName = studentName.getText();
        String courseFullName = courseName.getText();
        String courseDuration = CourseDuration.getText();
        LocalDate RegDate = datecombo.getValue();
        LocalDate PayDate = datecombo.getValue();
        double payment = Double.parseDouble(Paymenttxt.getText());
        double dueAmount = ((Double.parseDouble(fee.getText()))-payment);
        String status = cmbSelectStatus.getValue();


        Student student = null;
        try {
            student = studentBO.serachbyIDS(studentId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        Course course = null;
        try {
            course = courseBO.serachbyCIDs(courseId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


        if (student == null) {
            new Alert(Alert.AlertType.ERROR, "Student not found!").show();
            return;
        }
        if (course == null) {
            new Alert(Alert.AlertType.ERROR, "Course not found!").show();
            return;
        }

        Registration registration = new Registration(id,courseDuration,RegDate,PayDate,payment,dueAmount,studentFName,courseFullName,status,student,course);
        boolean isSaved = false;
        try {
             isSaved = registrationBO.saveRegistration(registration);
            loadAllStudents();
            setCellValueFactory();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if(isSaved){
            new Alert(Alert.AlertType.INFORMATION, "Registration successful!").show();
        }else{
            new Alert(Alert.AlertType.ERROR, "Registration failed!").show();
        }

    }

    @FXML
    void cmbProgramNameOnAction(ActionEvent event) {
        String cid = StudentIDComboCourseComboBox.getValue();
        try{
            Course course = courseBO.serachbyCIDs(cid);
            courseName.setText(course.getProgramName());
            fee.setText(String.valueOf(course.getFee()));
            CourseDuration.setText(course.getDuration());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void cmbSelectStatusOnAction(ActionEvent event) {

    }

    @FXML
    void cmbStudentIdOnAction(ActionEvent event) {
        Integer sid = StudentIDComboBox.getValue();
        try{
            Student student = studentBO.serachbyIDS(sid);
            studentName.setText(student.getFirstName());
            System.out.printf(student.getFirstName());
            studentMobile.setText(student.getPhoneNumber());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void getStudentId(){
        try {
            List<Student> allStudents = studentBO.getAllStudent();
            for (Student s : allStudents) {
                StudentIDComboBox.getItems().add(s.getId());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void getCourseId(){
        try {
            List<Course> allCourses = courseBO.getAllCourse();
            for (Course c : allCourses) {
                StudentIDComboCourseComboBox.getItems().add(c.getProgramId());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void getStatus(){
        List<String> statusList = new ArrayList<>();

        // Add strings to the list
        statusList.add("Paid");
        statusList.add("Pending");

        for (String status : statusList){
            cmbSelectStatus.getItems().add(status);
        }
    }

}
