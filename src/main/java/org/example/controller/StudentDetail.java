package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import org.example.bo.BOFactory;
import org.example.bo.custom.StudentBo;
import org.example.entity.Registration;
import org.example.entity.Student;


import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StudentDetail {

    @FXML
    private TableView<?> StudentTable;

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

    @FXML
    void btnClearOnAction(ActionEvent event) {
        idtxt.setText("");
        firstNametxt.setText("");
        lastnametxt.setText("");
        addresstxt.setText("");
        phonenumbertxt.setText("");
        emailtxt.setText("");
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        int id = Integer.parseInt(idtxt.getText());

        try {
            boolean b = studentBo.deleteStudent(id);
            if (b) {
                new Alert(Alert.AlertType.INFORMATION, "Student Delete Success");
            } else {
                new Alert(Alert.AlertType.ERROR, "Student Delete Failed");
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
            new Alert(Alert.AlertType.INFORMATION, "Student Update Success");
        }else {
            new Alert(Alert.AlertType.ERROR, "Student Update Failed");
        }
        /*loadallvalues();*/
        clearTextFiled();
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
                new Alert(Alert.AlertType.ERROR, "Student save UnSuccess");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        /*loadallvalues();*/
        clearTextFiled();
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
