package com.example.student_management_system;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.Date;

public class StudentHomeScreen extends Application {

    private Student searchedStudent;
    final String LABEL_STYLE = "-fx-font-size: 14px;";
    private TextField tfStandard, tfDateOfBirth, tfFirstName, tfMidName, tfLastName, tfMobileNumber, tfDivision, tfTeacherName, tfTeacherId;
    private TextArea tfAddress;
    private TextField searchField;
    private int studentId;

    public StudentHomeScreen(int studentId) {
        this.studentId = studentId;
    }

    BorderPane createContents(){
        BorderPane root = new BorderPane();
        searchStudent();
        GridPane gridPane = fillData();
        root.setCenter(gridPane);
        editableSetFalse();
        return root;
    }

    private void searchStudent(){
        DbHelper dbHelper = new DbHelper();
        searchedStudent = dbHelper.searchStudent(studentId);
    }

    private GridPane fillData() {
        GridPane root = new GridPane();
        root.setPadding(new Insets(20, 0, 20, 20));
        root.setHgap(7);
        root.setVgap(7);
        GridPane.setMargin(root, new Insets(100, 0, 0, 0));

        Label header = new Label("Student Details");
        header.setStyle("-fx-font-size: 20px; -fx-font-weight: bold");
        GridPane.setColumnSpan(header, 3);
        GridPane.setHalignment(header, HPos.CENTER);

        Label lbStudentId = new Label("Student Id:");
        lbStudentId.setStyle(LABEL_STYLE);
        GridPane.setHalignment(lbStudentId, HPos.LEFT);
        TextField tfStudentId = new TextField();
        tfStudentId.setText(String.valueOf(searchedStudent.getStudentId()));
        tfStudentId.setEditable(false);

        Label lbFirstName = new Label("Firstname:");
        lbFirstName.setStyle(LABEL_STYLE);
        GridPane.setHalignment(lbFirstName, HPos.LEFT);
        tfFirstName = new TextField();
        tfFirstName.setText(searchedStudent.getFirstName());
//        tfFirstName.setEditable(false);

        Label lbMidName = new Label("Middle-name: ");
        lbMidName.setStyle(LABEL_STYLE);
        GridPane.setHalignment(lbMidName, HPos.LEFT);
        tfMidName = new TextField();
        tfMidName.setText(searchedStudent.getSecondName());
//        tfMidName.setEditable(false);

        Label lbLastName = new Label("Lastname: ");
        lbLastName.setStyle(LABEL_STYLE);
        GridPane.setHalignment(lbLastName, HPos.LEFT);
        tfLastName = new TextField();
        tfLastName.setText(searchedStudent.getLastName());
//        tfLastName.setEditable(false);

        Label lbDateOfBirth = new Label("Date Of Birth: ");
        lbLastName.setStyle(LABEL_STYLE);
        GridPane.setHalignment(lbDateOfBirth, HPos.LEFT);
        tfDateOfBirth = new TextField();
        Date date = searchedStudent.getDateOfBirth();
        tfDateOfBirth.setText(date.toString());
//        tfDateOfBirth.setEditable(false);

        Label lbMobileNumber = new Label("Mobile Number: ");
        lbLastName.setStyle(LABEL_STYLE);
        GridPane.setHalignment(lbMobileNumber, HPos.LEFT);
        tfMobileNumber = new TextField();
        tfMobileNumber.setText(searchedStudent.getMobileNumber());
//        tfMobileNumber.setEditable(false);

        Label lbStandard = new Label("Standard:");
        lbStandard.setStyle(LABEL_STYLE);
        GridPane.setHalignment(lbStandard, HPos.LEFT);
        tfStandard = new TextField();
        tfStandard.setText(searchedStudent.getStandard());
//        tfStandard.setEditable(false);

        Label lbDivision = new Label("Division: ");
        lbDivision.setStyle(LABEL_STYLE);
        GridPane.setHalignment(lbDivision, HPos.LEFT);
        tfDivision = new TextField();
        tfDivision.setText(searchedStudent.getDivision());
//        tfDivision.setEditable(false);

        Label lbTeacherName = new Label("Teacher Name: ");
        lbTeacherName.setStyle(LABEL_STYLE);
        GridPane.setHalignment(lbTeacherName, HPos.LEFT);
        tfTeacherName = new TextField();
        tfTeacherName.setText(searchedStudent.getTeacherName());
//        tfTeacherName.setEditable(false);

        Label lbTeacherId = new Label("Teacher ID: ");
        lbTeacherId.setStyle(LABEL_STYLE);
        GridPane.setHalignment(lbTeacherId, HPos.LEFT);
        tfTeacherId = new TextField();
        tfTeacherId.setText(String.valueOf(searchedStudent.getTeacherId()));
//        tfTeacherId.setEditable(false);

        Label lbAddress = new Label("Address:");
        lbAddress.setStyle(LABEL_STYLE);
        GridPane.setHalignment(lbAddress, HPos.LEFT);
        tfAddress = new TextArea();
        GridPane.setColumnSpan(tfAddress, 4);
        tfAddress.setText(searchedStudent.getAddress());
//        tfAddress.setEditable(false);

        Label lbImage = new Label("Student Photo:");
        lbImage.setStyle(LABEL_STYLE);
        GridPane.setHalignment(lbImage, HPos.LEFT);
        String url = searchedStudent.getPhotoUrl();
        Image image = new Image(url);
        ImageView imageView = new ImageView();
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(250);
        imageView.setFitHeight(150);
        imageView.setImage(image);

        root.add(header, 3, 0);

        root.add(lbStudentId, 0, 2);
        root.add(tfStudentId, 1, 2);

        root.add(lbFirstName, 0, 3);
        root.add(tfFirstName, 1, 3);
        root.add(lbMidName, 2, 3);
        root.add(tfMidName, 3, 3);
        root.add(lbLastName, 4, 3);
        root.add(tfLastName, 5, 3);

        root.add(lbDateOfBirth, 0, 4);
        root.add(tfDateOfBirth, 1, 4);
        root.add(lbMobileNumber, 2, 4);
        root.add(tfMobileNumber, 3, 4);
        root.add(lbStandard, 4, 4);
        root.add(tfStandard, 5, 4);

        root.add(lbDivision, 0, 5);
        root.add(tfDivision, 1, 5);
        root.add(lbTeacherName, 2, 5);
        root.add(tfTeacherName, 3, 5);
        root.add(lbTeacherId, 4, 5);
        root.add(tfTeacherId, 5, 5);

        root.add(lbAddress, 0, 6);
        root.add(tfAddress, 0, 7);
        root.add(lbImage, 5, 6);
        root.add(imageView, 5, 7);
        return root;
    }

    private void editableSetFalse() {
        tfStandard.setEditable(false);
        tfDateOfBirth.setEditable(false);
        tfFirstName.setEditable(false);
        tfMidName.setEditable(false);
        tfLastName.setEditable(false);
        tfMobileNumber.setEditable(false);
        tfDivision.setEditable(false);
        tfTeacherName.setEditable(false);
        tfTeacherId.setEditable(false);
    }

//    TODO - write functionality of the student to change its password see result

    @Override
    public void start(Stage stage) throws Exception {

    }

    public static void main(String[] args) {
        launch();
    }
}
