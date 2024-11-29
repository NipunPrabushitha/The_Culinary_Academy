package org.example.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import org.example.ViewTm.AdminTm;
import org.example.ViewTm.StudentTM;
import org.example.bo.BOFactory;
import org.example.bo.custom.StudentBo;
import org.example.entity.Registration;
import org.example.entity.Student;
import org.example.model.AdminDTO;


import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StudentDetail {

    @FXML
    private TableView<StudentTM> StudentTable;

    @FXML
    private TextField addresstxt;

    @FXML
    private TableColumn<?, ?> coladdress;

    @FXML
    private TableColumn<?, ?> colemail;

    @FXML
    private TableColumn<?, ?> colfirstname;

    @FXML
    private TableColumn<?, ?> colid;

    @FXML
    private TableColumn<?, ?> collastname;

    @FXML
    private TableColumn<?, ?> colnumber;

    @FXML
    private DatePicker datecombo;

    @FXML
    private TextField emailtxt;

    @FXML
    private TextField firstNametxt;

    @FXML
    private TextField idtxt;

    @FXML
    private TextField lastnametxt;

    @FXML
    private TextField phonenumbertxt;

    @FXML
    private AnchorPane root;

    private String userRole;


    StudentBo studentBo = (StudentBo) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.STUDENT);
    public void initialize() {
        loadAllValues();
        setCellValueFactory();
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        idtxt.setText("");
        firstNametxt.setText("");
        lastnametxt.setText("");
        addresstxt.setText("");
        phonenumbertxt.setText("");
        emailtxt.setText("");
    }
    public void setCellValueFactory(){
        colid.setCellValueFactory(new PropertyValueFactory<>("id"));
        colfirstname.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
        collastname.setCellValueFactory(new PropertyValueFactory<>("LastName"));
        coladdress.setCellValueFactory(new PropertyValueFactory<>("Address"));
        colemail.setCellValueFactory(new PropertyValueFactory<>("Email"));
        colnumber.setCellValueFactory(new PropertyValueFactory<>("PhoneNumber"));
    }
    public void loadAllValues(){
        ObservableList<StudentTM> students = FXCollections.observableArrayList();
        List<Student> studentDTO = null;
        try {
            studentDTO = studentBo.getAllStudent();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for(Student student : studentDTO){
            StudentTM studentTM =new StudentTM(
                        student.getId(),
                        student.getFirstName(),
                        student.getLastName(),
                        student.getPhoneNumber(),
                        student.getAddress(),
                        student.getEmail()

                );

            students.add(studentTM);
        }
        StudentTable.setItems(students);
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        int id = Integer.parseInt(idtxt.getText());

        try {
            boolean b = studentBo.deleteStudent(id);
            loadAllValues();
            setCellValueFactory();
            if (b) {
                new Alert(Alert.AlertType.INFORMATION, "Student Delete Success").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Student Delete Failed").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        clearTextFiled();
        /*loadallvalues();*/
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        int id = Integer.parseInt(idtxt.getText());
        String fn = firstNametxt.getText();
        String ln = lastnametxt.getText();
        String address = addresstxt.getText();
        String email = emailtxt.getText();
        String number = phonenumbertxt.getText();
        LocalDate enrollmentDate = datecombo.getValue();
        List<Registration> list = new ArrayList<>();


        Student student = new Student(id,fn,ln,address,email,number,enrollmentDate,list,userRole);


        boolean s = false;

        try {

            s = studentBo.updateStudent(student);

        } catch (Exception e) {
            e.printStackTrace();
        }
        if (s) {
            new Alert(Alert.AlertType.INFORMATION, "Student Update Success").show();
        }else {
            new Alert(Alert.AlertType.ERROR, "Student Update Failed").show();
        }
        /*loadallvalues();*/
        clearTextFiled();
        loadAllValues();
        setCellValueFactory();
    }

    @FXML
    void saveOnActionStudent(ActionEvent event) {
        int id = 0;
        String fn = firstNametxt.getText();
        String ln = lastnametxt.getText();
        String address = addresstxt.getText();
        String email = emailtxt.getText();
        String number = phonenumbertxt.getText();
        LocalDate enrollmentDate = datecombo.getValue();
        List<Registration> list = new ArrayList<>();




        Student student = new Student(id,fn,ln,address,email,number,enrollmentDate,list,userRole);


        boolean s = false;

        try{

            s = studentBo.saveStudent(student);
            if (s) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Student Saved Successful");
                alert.setContentText("Student Saved");
                alert.showAndWait();
            }else {
                new Alert(Alert.AlertType.ERROR, "Student save UnSuccess").show();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        /*loadallvalues();*/
        clearTextFiled();
        loadAllValues();
        setCellValueFactory();
    }

    @FXML
    void txtStudentIdOnAction(ActionEvent event) {
        String id = String.valueOf(Integer.parseInt(idtxt.getText()));
        ArrayList<Student> students = null;
        try {
            students = (ArrayList<Student>) studentBo.SearchSID(Integer.parseInt(id));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        firstNametxt.setText(students.get(0).getFirstName());
        lastnametxt.setText(students.get(0).getLastName());
        addresstxt.setText(students.get(0).getAddress());
        phonenumbertxt.setText(students.get(0).getPhoneNumber());
        emailtxt.setText(students.get(0).getEmail());
    }
    public void clearTextFiled(){
        idtxt.setText("");
        firstNametxt.setText("");
        lastnametxt.setText("");
        addresstxt.setText("");
        phonenumbertxt.setText("");
        emailtxt.setText("");
    }

}
