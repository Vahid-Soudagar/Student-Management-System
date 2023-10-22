package com.example.student_management_system;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.Date;

public class AdminSearchStudent extends Application {

    private Student searchedStudent;
    final String LABEL_STYLE = "-fx-font-size: 14px;";
    private TextField tfStandard, tfDateOfBirth, tfFirstName, tfMidName, tfLastName, tfMobileNumber, tfDivision, tfTeacherName, tfTeacherId;
    private TextArea tfAddress;
    private GridPane gridPane;
    private TextField searchField;

    BorderPane createContents() {
        BorderPane root = new BorderPane();

        VBox vBox = new VBox(20);
        Label header = new Label("Search Student Details");
        header.setStyle("-fx-font-size: 20px; -fx-font-weight: bold");
        vBox.setAlignment(Pos.CENTER);
        HBox hBox = new HBox(20);
        searchField = new TextField();
        searchField.setPromptText("Enter Student Id");
        searchField.setPrefWidth(400);
        Button searchButton = new Button("Search");
        searchButton.setOnAction(event -> {
            boolean studentFound = searchStudent(Integer.parseInt(searchField.getText()));
            if (studentFound) {
                gridPane = fillData();
                root.setCenter(gridPane);
                editableSetFalse();
                VBox vBox1 = operations();
                vBox1.setAlignment(Pos.CENTER);
                root.setPadding(new Insets(0, 30, 0, 0));
                root.setRight(vBox1);
            }
        });
        hBox.getChildren().addAll(searchField, searchButton);
        hBox.setAlignment(Pos.CENTER);

        vBox.getChildren().addAll(header, hBox);

        root.setTop(vBox);
        return root;
    }
    private VBox operations() {
        VBox root = new VBox(20);

        Button editDetails = new Button("Edit Details");
        editDetails.setOnAction(event -> {
            editableSetTrue();
            Button button = new Button("Update Details");
            gridPane.add(button, 3,9);
            button.setOnAction(event1 -> updateStudentDetails());
        });

        Button deleteStudent = new Button("Delete Student");
        deleteStudent.setOnAction(event -> {
            boolean deleted = deleteStudent(searchedStudent.getStudentId());
            if (deleted) {
                gridPane.getChildren().clear();
            }
        });
        editDetails.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-alignment: center;");
        deleteStudent.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-alignment: center;");
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(editDetails, deleteStudent);
        return root;
    }
    private boolean deleteStudent(int studentId) {
        DbHelper dbHelper = new DbHelper();
        boolean deleted = dbHelper.deleteStudent(studentId);
        if (deleted) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Student Deleted");
            alert.setContentText("Student with "+searchedStudent.getStudentId()+" is deleted");
            alert.show();
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Something went wrong");
            alert.setHeaderText("Student can't be Deleted");
            alert.setContentText("Student is not Deleted");
            alert.show();
            return false;
        }
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
    private boolean searchStudent(int studentId) {
        DbHelper dbHelper = new DbHelper();
        searchedStudent = dbHelper.searchStudent(studentId);
        if (searchedStudent != null) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Something went wrong");
            alert.setHeaderText("No Student Found");
            alert.setContentText("Check Student Id or Enter valid Ttudent Id");
            alert.show();
            return false;
        }
    }
    private void editableSetTrue() {
        tfStandard.setEditable(true);
        tfDateOfBirth.setEditable(true);
        tfFirstName.setEditable(true);
        tfMidName.setEditable(true);
        tfLastName.setEditable(true);
        tfMobileNumber.setEditable(true);
        tfDivision.setEditable(true);
        tfTeacherName.setEditable(true);
        tfTeacherId.setEditable(true);
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

    private void updateStudentDetails() {
        DbHelper dbHelper = new DbHelper();
        Student student = dbHelper.searchStudent(Integer.parseInt(searchField.getText()));
        boolean isUpdated = dbHelper.updateStudentDetails(student.getStudentId(), student);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Update Student");
        if (isUpdated) {
            alert.setHeaderText("Student details Updated");
            alert.setContentText("Student Details Updated successfully");
        } else {
            alert.setHeaderText("Student details is not updated");
            alert.setContentText("Failed to update student details, try after again after sometime");
        }
        alert.show();
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Admin Search Student");
        // Create the UI content
        BorderPane root = createContents();
        // Create a scene and set it on the stage
        Scene scene = new Scene(root, 1080, 720);
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}
