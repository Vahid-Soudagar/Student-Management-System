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

public class AdminAddTeacher extends Application {

    final String LABEL_STYLE = "-fx-font-size: 14px;";
    private TextField tfDepartment, tfExperience, tfQualification, tfEmail, tfFirstName, tfLastName, tfMobileNumber;
    private TextArea tfAddress;
    private DatePicker dpDateOfBirth, dpJoiningDate;
    private String imageUrl;
    private Stage primaryStage;
    private Button submitButton;
    private ImageView imageView;
    private ComboBox<String> cbPost;

    GridPane createContents() {
        GridPane root = new GridPane();
        root.setPadding(new Insets(20, 0, 20, 20));
        root.setHgap(7);
        root.setVgap(7);
        GridPane.setMargin(root, new Insets(100, 0, 0, 0));

        Label header = new Label("Add Teacher Details");
        header.setStyle("-fx-font-size: 20px; -fx-font-weight: bold");
        GridPane.setHalignment(header, HPos.CENTER);

        Label lbFirstName = new Label("Enter Firstname:");
        lbFirstName.setStyle(LABEL_STYLE);
        GridPane.setHalignment(lbFirstName, HPos.LEFT);
        tfFirstName = new TextField();

        Label lbLastName = new Label("Enter Lastname: ");
        lbLastName.setStyle(LABEL_STYLE);
        GridPane.setHalignment(lbLastName, HPos.LEFT);
        tfLastName = new TextField();

        Label lbDateOfBirth = new Label("Enter DOB: ");
        lbDateOfBirth.setStyle(LABEL_STYLE);
        GridPane.setHalignment(lbDateOfBirth, HPos.LEFT);
        dpDateOfBirth = new DatePicker();


        Label lbEmail = new Label("Email: ");
        lbEmail.setStyle(LABEL_STYLE);
        GridPane.setHalignment(lbDateOfBirth, HPos.LEFT);
        tfEmail = new TextField();

        Label lbMobileNumber = new Label("Mobile Number: ");
        lbLastName.setStyle(LABEL_STYLE);
        GridPane.setHalignment(lbMobileNumber, HPos.LEFT);
        tfMobileNumber = new TextField();

        Label lbQualification = new Label("Qualification: ");
        lbQualification.setStyle(LABEL_STYLE);
        GridPane.setHalignment(lbQualification, HPos.LEFT);
        tfQualification = new TextField();

        Label lbPost = new Label("Post: ");
        lbPost.setStyle(LABEL_STYLE);
        GridPane.setHalignment(lbPost, HPos.LEFT);
        cbPost = new ComboBox<>();
        cbPost.getItems().addAll("Junior Teacher", "Teacher", "HOD", "VP", "Principal");

        Label lbExperience = new Label("Experience: ");
        lbExperience.setStyle(LABEL_STYLE);
        GridPane.setHalignment(lbExperience, HPos.LEFT);
        tfExperience = new TextField();

        Label lbDepartment = new Label("Department: ");
        lbDepartment.setStyle(LABEL_STYLE);
        GridPane.setHalignment(lbDepartment, HPos.LEFT);
        tfDepartment = new TextField();

        Label lbJoiningDate = new Label("Joining Date: ");
        lbJoiningDate.setStyle(LABEL_STYLE);
        GridPane.setHalignment(lbJoiningDate, HPos.LEFT);
        dpJoiningDate = new DatePicker();

        Label lbImage = new Label("Select Image");
        lbImage.setStyle(LABEL_STYLE);
        GridPane.setHalignment(lbImage, HPos.CENTER);
        imageView = new ImageView();
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(250);
        imageView.setFitHeight(150);
        GridPane.setHalignment(imageView, HPos.LEFT);
        GridPane.setColumnSpan(imageView, 3);
        GridPane.setRowSpan(imageView, 3);
        Button selectImage = new Button("Browse");
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


        tfAddress = new TextArea();
        tfAddress.setPromptText("Address");
        GridPane.setColumnSpan(tfAddress, 4);
        GridPane.setRowSpan(tfAddress, 3);
        tfAddress.setPrefRowCount(7); // You can adjust the number of visible rows as needed
        tfAddress.setWrapText(true);

        submitButton = new Button("Submit");
        submitButton.setOnAction(event -> {
            if (areFieldsValid()) {
                boolean addTeacher = insertTeacher();
                if (addTeacher) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Teacher Added");
                    alert.setHeaderText("Teacher added successfully.");
                    alert.setContentText("What would you like to do next?");

                    ButtonType addMoreStudentsButton = new ButtonType("Add More Teacher");
                    ButtonType exitButton = new ButtonType("Exit", ButtonBar.ButtonData.CANCEL_CLOSE);
                    alert.getButtonTypes().setAll(addMoreStudentsButton, exitButton);

                    // Show the alert and handle the user's choice
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.isPresent()) {
                        if (result.get() == addMoreStudentsButton) {
                            // User chose to add more students, so clear the fields
                            clearFields();
                        } else if (result.get() == exitButton) {
                            // User chose to exit, so close the window
                            // You can close the window by getting the window's Stage and calling close()
                            Stage stage = (Stage) submitButton.getScene().getWindow();
                            stage.close();
                        }
                    }
                }
            }
        });


        root.add(header, 4, 2);

        root.add(lbFirstName, 1, 4);
        root.add(tfFirstName, 2, 4);
        root.add(lbLastName, 3, 4);;
        root.add(tfLastName, 4, 4);
        root.add(lbDateOfBirth, 5, 4);
        root.add(dpDateOfBirth, 6, 4);
        root.add(lbDepartment, 7, 4);
        root.add(tfDepartment, 8, 4);

        root.add(lbEmail, 1, 5);
        root.add(tfEmail, 2, 5);
        root.add(lbMobileNumber, 3, 5);
        root.add(tfMobileNumber, 4, 5);
        root.add(lbQualification, 5, 5);
        root.add(tfQualification, 6, 5);
        root.add(lbPost, 7, 5);
        root.add(cbPost, 8, 5);

        root.add(lbExperience, 1, 6);
        root.add(tfExperience, 2, 6);
        root.add(lbJoiningDate, 3, 6);
        root.add(dpJoiningDate, 4, 6);
        root.add(lbImage, 5, 6);
        root.add(selectImage, 6, 6);

        root.add(tfAddress, 1, 7);
        root.add(imageView, 5, 7);

        root.add(submitButton, 4, 12);
        return root;
    }

    private void clearFields() {
        tfFirstName.clear();
        tfLastName.clear();
        dpDateOfBirth.getEditor().clear();  // Clear the DatePicker field
        tfAddress.clear();
        tfMobileNumber.clear();
        tfEmail.clear();
        tfQualification.clear();
        tfExperience.clear();
        dpDateOfBirth.getEditor().clear();
        dpJoiningDate.getEditor().clear();
        imageView.getImage().cancel();
        cbPost.getSelectionModel().clearSelection();
    }

    private boolean areFieldsValid() {
        return !tfFirstName.getText().isEmpty() &&
                !tfLastName.getText().isEmpty() &&
                !tfAddress.getText().isEmpty() &&
                !tfEmail.getText().isEmpty() &&
                !tfQualification.getText().isEmpty() &&
                !tfDepartment.getText().isEmpty() &&
                !tfExperience.getText().isEmpty() &&
                !tfMobileNumber.getText().isEmpty() &&
                dpDateOfBirth.getValue() != null &&
                dpJoiningDate.getValue() != null &&
                !imageUrl.isEmpty() &&
                cbPost.getValue() != null;

    }

    private boolean insertTeacher() {
        Teacher teacher = new Teacher();
        teacher.setFirstName(tfFirstName.getText());
        teacher.setLastName(tfLastName.getText());
        LocalDate dob = dpDateOfBirth.getValue();
        teacher.setDateOfBirth(Date.valueOf(dob));
        teacher.setAddress(tfAddress.getText());
        teacher.setPhoneNumber(tfMobileNumber.getText());
        teacher.setDepartment(tfDepartment.getText());
        teacher.setPost(cbPost.getValue());
        teacher.setEmail(tfEmail.getText());
        teacher.setExperience(tfExperience.getText());
        teacher.setPhotoUrl(imageUrl);
        teacher.setQualification(tfQualification.getText());
        LocalDate jod = dpJoiningDate.getValue();
        teacher.setJoiningDate(Date.valueOf(jod));

        DbHelper dbHelper = new DbHelper();
        dbHelper.createTeacherTable();
        int teacherId = dbHelper.getNextTeacherId();
        teacher.setTeacherId(teacherId);

        String password = "Pass"+"@"+teacherId;
        teacher.setPassword(password);

        boolean teacherAdded = dbHelper.addTeacher(teacher);
        boolean userAdded = dbHelper.addUser(teacherId, password, "Teacher");
        if (teacherAdded && userAdded) {
            return true;
        } else {
            return false;
        }
    }
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Admin Search teacher");
        // Create the UI content
        GridPane root = createContents();
        // Create a scene and set it on the stage
        Scene scene = new Scene(root, 1080, 720);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
