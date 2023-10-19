package com.example.student_management_system;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;

public class AdminAddStudent extends Application {

    final String LABEL_STYLE = "-fx-font-size: 14px;";
    private Stage primaryStage;

    private TextField tfFirstName, tfMidName, tfLastName, tfMobileNumber, tfDivision, tfTeacherName, tfTeacherId;
    private TextArea tfAddress;
    private DatePicker dpDateOfBirth;
    private ComboBox<String> cbStandard;
    private String imageUrl;
    private Button submitButton;
    GridPane createContents() throws ClassNotFoundException {
        GridPane root = new GridPane();
        root.setPadding(new Insets(20, 0, 20, 20));
        root.setHgap(7);
        root.setVgap(7);
        GridPane.setMargin(root, new Insets(100, 0, 0, 0));

        Label header = new Label("Add Student Details");
        header.setStyle("-fx-font-size: 20px; -fx-font-weight: bold");
        GridPane.setColumnSpan(header, 3);
        GridPane.setHalignment(header, HPos.CENTER);

        Label lbFirstName = new Label("Enter Firstname:");
        lbFirstName.setStyle(LABEL_STYLE);
        GridPane.setHalignment(lbFirstName, HPos.RIGHT);
        tfFirstName = new TextField();

        Label lbMidName = new Label("Enter Middle-name: ");
        lbMidName.setStyle(LABEL_STYLE);
        GridPane.setHalignment(lbMidName, HPos.RIGHT);
        tfMidName = new TextField();

        Label lbLastName = new Label("Enter Lastname: ");
        lbLastName.setStyle(LABEL_STYLE);
        GridPane.setHalignment(lbLastName, HPos.RIGHT);
        tfLastName = new TextField();

        Label lbDateOfBirth = new Label("Enter DOB: ");
        lbLastName.setStyle(LABEL_STYLE);
        GridPane.setHalignment(lbDateOfBirth, HPos.LEFT);
        dpDateOfBirth = new DatePicker();

        Label lbMobileNumber = new Label("Enter Mobile Number: ");
        lbLastName.setStyle(LABEL_STYLE);
        GridPane.setHalignment(lbMobileNumber, HPos.LEFT);
        tfMobileNumber = new TextField();

        Label lbStandard = new Label("Select Standard:");
        lbStandard.setStyle(LABEL_STYLE);
        GridPane.setHalignment(lbStandard, HPos.RIGHT);
        cbStandard = new ComboBox<>();
        cbStandard.getItems().addAll("1", "2", "3", "4", "5", "6", "7", "8", "9", "10");

        Label lbDivision = new Label("Enter Division: ");
        lbDivision.setStyle(LABEL_STYLE);
        GridPane.setHalignment(lbDivision, HPos.LEFT);
        tfDivision = new TextField();

        Label lbTeacherName = new Label("Enter Teacher Name: ");
        lbTeacherName.setStyle(LABEL_STYLE);
        GridPane.setHalignment(lbTeacherName, HPos.RIGHT);
        tfTeacherName = new TextField();

        Label lbTeacherId = new Label("Enter Teacher ID: ");
        lbTeacherId.setStyle(LABEL_STYLE);
        GridPane.setHalignment(lbTeacherId, HPos.RIGHT);
        tfTeacherId = new TextField();

        Label lbAddress = new Label("Enter Address:");
        lbAddress.setStyle(LABEL_STYLE);
        GridPane.setHalignment(lbAddress, HPos.LEFT);
        tfAddress = new TextArea();
        GridPane.setColumnSpan(tfAddress, 4);
        tfAddress.setPrefRowCount(7); // You can adjust the number of visible rows as needed
        tfAddress.setWrapText(true); // This enables text wrapping within the TextArea

        Label lbImage = new Label("Select an Image:");
        lbImage.setStyle(LABEL_STYLE);
        GridPane.setHalignment(lbImage, HPos.RIGHT);
        Button selectImage = new Button("Browse");
        ImageView imageView = new ImageView();
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(250);
        imageView.setFitHeight(150);
        GridPane.setHalignment(imageView, HPos.LEFT);
        GridPane.setColumnSpan(imageView, 3);
        GridPane.setRowSpan(imageView, 3);

        selectImage.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
            File selectedFile = fileChooser.showOpenDialog(primaryStage);
            if (selectedFile != null) {
                imageUrl = selectedFile.toURI().toString();
                Image selectedImage = new Image(imageUrl);
                imageView.setImage(selectedImage);
            }
        });

        submitButton = new Button("Submit");
        submitButton.setOnAction(e -> {
            if (areFieldsValid()) {
                try {
                    boolean isInserted = insertStudent();
                        if (isInserted) {
                            // Student added successfully
                            // Create an alert dialog
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("Student Added");
                            alert.setHeaderText("Student added successfully.");
                            alert.setContentText("What would you like to do next?");

                            // Create "Add More Students" and "Exit" buttons
                            ButtonType addMoreStudentsButton = new ButtonType("Add More Students");
                            ButtonType exitButton = new ButtonType("Exit", ButtonBar.ButtonData.CANCEL_CLOSE);
                            alert.getButtonTypes().setAll(addMoreStudentsButton, exitButton);

                            // Show the alert and handle the user's choice
                            Optional<ButtonType> result = alert.showAndWait();
                            if (result.isPresent()) {
                                if (result.get() == addMoreStudentsButton) {
                                    // User chose to add more students, so clear the fields
                                    clearStudentFields();
                                } else if (result.get() == exitButton) {
                                    // User chose to exit, so close the window
                                    // You can close the window by getting the window's Stage and calling close()
                                    Stage stage = (Stage) submitButton.getScene().getWindow();
                                    stage.close();
                                }
                            }
                        }

                    } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

//        0th row
        root.add(header, 2, 0);

//        1st row
        root.add(lbFirstName, 0, 1);
        root.add(tfFirstName, 1, 1);
        root.add(lbMidName, 2, 1);
        root.add(tfMidName, 3, 1);
        root.add(lbLastName, 4, 1);
        root.add(tfLastName, 5, 1);

//        2nd row
        root.add(lbDateOfBirth, 0, 2);
        root.add(dpDateOfBirth, 1, 2);
        root.add(lbMobileNumber, 2, 2);
        root.add(tfMobileNumber, 3, 2);
        root.add(lbStandard, 4, 2);
        root.add(cbStandard, 5, 2);

        root.add(lbDivision, 0, 3);
        root.add(tfDivision, 1, 3);
        root.add(lbTeacherName, 2, 3);
        root.add(tfTeacherName, 3, 3);
        root.add(lbTeacherId, 4, 3);
        root.add(tfTeacherId, 5, 3);

        root.add(lbAddress, 0, 4);
        root.add(tfAddress, 0, 5);
        root.add(lbImage, 4, 4);
        root.add(selectImage, 5, 4);
        root.add(imageView, 4, 5);


        root.add(submitButton, 3, 6);

        return root;
    }

    private boolean insertStudent() throws ClassNotFoundException {
        Student student = new Student();
        student.setFirstName(tfFirstName.getText());
        student.setSecondName(tfMidName.getText());
        student.setLastName(tfLastName.getText());
        LocalDate selectedDate = dpDateOfBirth.getValue();
        student.setDateOfBirth(Date.valueOf(selectedDate));
        student.setAddress(tfAddress.getText());
        student.setMobileNumber(tfMobileNumber.getText());
        student.setStandard(cbStandard.getValue());
        student.setDivision(tfDivision.getText());
        student.setTeacherName(tfTeacherName.getText());
        student.setTeacherId(Integer.parseInt(tfTeacherId.getText()));
        student.setPhotoUrl(imageUrl);

        DbHelper dbHelper = new DbHelper();
        dbHelper.createStudentsTable();
        int studentId = dbHelper.getNextStudentId();
        student.setStudentId(studentId);

        String standard = student.getStandard();
        String division = student.getDivision();
        String password = "P"+studentId+"@"+standard+division.toLowerCase();
        student.setPassword(password);

        boolean studentAdded = dbHelper.addStudent(student);
        boolean userAdded = dbHelper.addUser(student.getStudentId(), student.getPassword(), "Student");
        if (studentAdded && userAdded) {
            return true;
        } else {
            return false;
        }
    }

    private void clearStudentFields() {
        tfFirstName.clear();
        tfMidName.clear();
        tfLastName.clear();
        dpDateOfBirth.getEditor().clear();  // Clear the DatePicker field
        tfAddress.clear();
        tfMobileNumber.clear();
        cbStandard.getSelectionModel().clearSelection();  // Clear the ComboBox
        tfDivision.clear();
        tfTeacherName.clear();
        tfTeacherId.clear();
        // Clear any other fields as needed
    }


    private boolean areFieldsValid() {
        return !tfFirstName.getText().isEmpty() &&
                !tfMidName.getText().isEmpty() &&
                !tfLastName.getText().isEmpty() &&
                dpDateOfBirth.getValue() != null &&
                !tfAddress.getText().isEmpty() &&
                !tfMobileNumber.getText().isEmpty() &&
                cbStandard.getValue() != null &&
                !tfDivision.getText().isEmpty() &&
                !tfTeacherName.getText().isEmpty() &&
                !tfTeacherId.getText().isEmpty();
    }



    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage;
        stage.setTitle("Admin Search Student");
        // Create the UI content
        GridPane root = createContents();
        // Create a scene and set it on the stage
        Scene scene = new Scene(root, 1080, 720);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
