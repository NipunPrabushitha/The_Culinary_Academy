package org.example.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import org.example.ViewTm.CourseTM;
import org.example.bo.BOFactory;
import org.example.bo.custom.CourseBO;
import org.example.entity.Course;
import org.example.entity.Registration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CulinaryPrograms {

    @FXML
    private TableView<CourseTM> CourseTable;

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
    public void initialize() {
        lordAllPrograms();
        setCellValueFactory();
    }
    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearTextFiled();
    }
    private void setCellValueFactory() {
        colProgramID.setCellValueFactory(new PropertyValueFactory<>("ProgramID"));
        colName.setCellValueFactory(new PropertyValueFactory<>("ProgramName"));
        colfee.setCellValueFactory(new PropertyValueFactory<>("fee"));
        colDuration.setCellValueFactory(new PropertyValueFactory<>("Duration"));
    }

    private void lordAllPrograms() {
        ObservableList<CourseTM> courseTMS = FXCollections.observableArrayList();
        List<Course> courses = null;
        try {
            courses = courseBO.getAllCourse();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for(Course course : courses){
            CourseTM studentTM =new CourseTM(
                    course.getProgramId(),
                    course.getProgramName(),
                    course.getFee(),
                    course.getDuration()
            );

            courseTMS.add(studentTM);
        }
        CourseTable.setItems(courseTMS);
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = Programidtxt.getText();

        try {
            boolean c = courseBO.deleteCourse(id);
            lordAllPrograms();
            setCellValueFactory();
            if (c) {
                new Alert(Alert.AlertType.INFORMATION, "Course Delete Successfully").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Course Delete Unsuccessful").show();
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
            lordAllPrograms();
            setCellValueFactory();

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (c) {
            new Alert(Alert.AlertType.CONFIRMATION, "Course saved successfully").show();
        } else {

            new Alert(Alert.AlertType.ERROR, "Course save UnSuccess").show();
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
            lordAllPrograms();
            setCellValueFactory();

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (c) {
            new Alert(Alert.AlertType.CONFIRMATION, "Course update successfully....!!! :)").show();

        } else {
            new Alert(Alert.AlertType.ERROR, "Course update unsuccessfully....!!! :(").show();
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
