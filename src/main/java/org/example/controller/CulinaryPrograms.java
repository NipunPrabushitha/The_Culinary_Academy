package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.example.bo.BOFactory;
import org.example.bo.custom.CourseBO;
import org.example.entity.Course;
import org.example.entity.Registration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CulinaryPrograms {

    @FXML
    private TableView<?> CourseTable;

    @FXML
    private TextField ProgramNametxt;

    @FXML
    private TextField Programidtxt;

    @FXML
    private TableColumn<?, ?> colDuration;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colProgramID;

    @FXML
    private TableColumn<?, ?> colfee;

    @FXML
    private TextField durationtxt;

    @FXML
    private TextField feetxt;

    @FXML
    private AnchorPane root;

    CourseBO courseBO = (CourseBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.COURSE);

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearTextFiled();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = Programidtxt.getText();

        try {
            boolean c = courseBO.deleteCourse(id);
            if (c) {
                new Alert(Alert.AlertType.INFORMATION, "Course Delete Successfully");
            } else {
                new Alert(Alert.AlertType.ERROR, "Course Delete Unsuccessful");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        /*loadallvalues();*/
        clearTextFiled();
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        int id = 0;
        String pid = Programidtxt.getText();
        String pname = ProgramNametxt.getText();
        String duration = durationtxt.getText();
        double fee = Double.parseDouble(feetxt.getText());
        List<Registration> list = new ArrayList<>();


        Course course = new Course(id,pid, pname, duration, fee,list);

        boolean c = false;

        try {

            c = courseBO.saveCourse(course);

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (c) {
            new Alert(Alert.AlertType.CONFIRMATION, "Customer SAVE Success");
        } else {

            new Alert(Alert.AlertType.ERROR, "Student save UnSuccess");
        }
        /*loadallvalues();*/
        clearTextFiled();
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        int id = 0;
        String pid = Programidtxt.getText();
        String pname = ProgramNametxt.getText();
        String duration = durationtxt.getText();
        double fee = Double.parseDouble(feetxt.getText());
        List<Registration> list = new ArrayList<>();


        Course course = new Course(id,pid, pname, duration, fee,list);

        boolean c = false;

        try {

            c = courseBO.updateCourse(course);

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (c) {
            new Alert(Alert.AlertType.CONFIRMATION, "Customer update successfully....!!! :)").show();

        } else {
            new Alert(Alert.AlertType.ERROR, "Student update unsuccessfully....!!! :(").show();
        }
        /*loadallvalues();*/
        clearTextFiled();
    }

    @FXML
    void txtIdOnAction(ActionEvent event) {
        String id = Programidtxt.getText();
        ArrayList<Course> courses = null;
        try {
            courses = (ArrayList<Course>) courseBO.SearchCID(id);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Programidtxt.setText(courses.get(0).getProgramId());
        ProgramNametxt.setText(courses.get(0).getProgramName());
        durationtxt.setText(courses.get(0).getDuration());
        feetxt.setText(String.valueOf(courses.get(0).getFee()));
    }
    public void clearTextFiled() {
        Programidtxt.clear();
        ProgramNametxt.clear();
        durationtxt.clear();
        feetxt.clear();
    }

}
