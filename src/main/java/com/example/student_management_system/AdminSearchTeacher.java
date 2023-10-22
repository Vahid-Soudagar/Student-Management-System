package com.example.student_management_system;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class AdminSearchTeacher extends Application {
    final String LABEL_STYLE = "-fx-font-size: 14px;";
    private TextField tfPost, tfDateOfBirth, tfJoiningDate, searchField, tfDepartment, tfExperience, tfQualification, tfEmail, tfFirstName, tfLastName, tfMobileNumber;
    private TextArea tfAddress;
    private ImageView imageView;
    private GridPane gridPane;
    private Teacher searchedTeacher;

    BorderPane createContents() {
        BorderPane root = new BorderPane();

        VBox vBox = new VBox(20);
        Label header = new Label("Search Teacher Details");
        header.setStyle("-fx-font-size: 20px; -fx-font-weight: bold");
        vBox.setAlignment(Pos.CENTER);
        HBox hBox = new HBox(20);
        searchField = new TextField();
        searchField.setPromptText("Enter Teacher Id");
        searchField.setPrefWidth(400);
        Button searchButton = new Button("Search");
        searchButton.setOnAction(event -> {
            boolean teacherFound = searchTeacher(Integer.parseInt(searchField.getText()));
            if (teacherFound) {
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
            gridPane.add(button, 3, 9);
            button.setOnAction(event1 -> {
                boolean updated = updateTeacherDetails(Integer.parseInt(searchField.getText()), searchedTeacher);
                if (updated) {
                    System.out.println("Details updated");
                }
            });
        });

        Button deleteStudent = new Button("Delete Teacher");
        deleteStudent.setOnAction(event -> {
            boolean deleted = deleteTeacher(searchedTeacher.getTeacherId());
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
    private boolean updateTeacherDetails(int teacherId, Teacher teacher) {
        DbHelper dbHelper = new DbHelper();
        teacher = dbHelper.searchTeacher(teacherId);
        boolean isUpdated = dbHelper.updateTeacherDetails(teacherId, teacher);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Update Teacher");
        if (isUpdated) {
            alert.setHeaderText("Teacher Details Updated");
            alert.setContentText("Teacher Details Upload Successfully");
            alert.show();
            return true;
        } else {
            alert.setHeaderText("Student details is not updated");
            alert.setContentText("Failed to update student details, try after again after sometime");
            alert.show();
            return false;
        }
    }
    private void editableSetFalse() {
        tfDateOfBirth.setEditable(false);
        tfFirstName.setEditable(false);
        tfLastName.setEditable(false);
        tfMobileNumber.setEditable(false);
        tfDepartment.setEditable(false);
        tfAddress.setEditable(false);
        tfPost.setEditable(false);
        tfExperience.setEditable(false);
        tfQualification.setEditable(false);
        tfJoiningDate.setEditable(false);
        tfEmail.setEditable(false);
    }
    private void editableSetTrue() {
        tfDateOfBirth.setEditable(true);
        tfFirstName.setEditable(true);
        tfLastName.setEditable(true);
        tfMobileNumber.setEditable(true);
        tfDepartment.setEditable(true);
        tfAddress.setEditable(true);
        tfPost.setEditable(true);
        tfExperience.setEditable(true);
        tfQualification.setEditable(true);
        tfJoiningDate.setEditable(true);
        tfEmail.setEditable(true);
    }
    private GridPane fillData() {
        GridPane root = new GridPane();
        root.setPadding(new Insets(20, 0, 20, 20));
        root.setHgap(7);
        root.setVgap(7);
        GridPane.setMargin(root, new Insets(100, 0, 0, 0));

        Label header = new Label("Teacher Details");
        header.setStyle("-fx-font-size: 20px; -fx-font-weight: bold");
        GridPane.setHalignment(header, HPos.CENTER);

        Label lbFirstName = new Label("Enter Firstname:");
        lbFirstName.setStyle(LABEL_STYLE);
        GridPane.setHalignment(lbFirstName, HPos.LEFT);
        tfFirstName = new TextField();
        tfFirstName.setText(searchedTeacher.getFirstName());

        Label lbLastName = new Label("Enter Lastname: ");
        lbLastName.setStyle(LABEL_STYLE);
        GridPane.setHalignment(lbLastName, HPos.LEFT);
        tfLastName = new TextField();
        tfLastName.setText(searchedTeacher.getLastName());

        Label lbDateOfBirth = new Label("Enter DOB: ");
        lbDateOfBirth.setStyle(LABEL_STYLE);
        GridPane.setHalignment(lbDateOfBirth, HPos.LEFT);
        tfDateOfBirth = new TextField();
        tfDateOfBirth.setText(searchedTeacher.getDateOfBirth().toString());

        Label lbEmail = new Label("Email: ");
        lbEmail.setStyle(LABEL_STYLE);
        GridPane.setHalignment(lbDateOfBirth, HPos.LEFT);
        tfEmail = new TextField();
        tfEmail.setText(searchedTeacher.getEmail());

        Label lbMobileNumber = new Label("Mobile Number: ");
        lbLastName.setStyle(LABEL_STYLE);
        GridPane.setHalignment(lbMobileNumber, HPos.LEFT);
        tfMobileNumber = new TextField();
        tfMobileNumber.setText(searchedTeacher.getPhoneNumber());

        Label lbQualification = new Label("Qualification: ");
        lbQualification.setStyle(LABEL_STYLE);
        GridPane.setHalignment(lbQualification, HPos.LEFT);
        tfQualification = new TextField();
        tfQualification.setText(searchedTeacher.getQualification());

        Label lbPost = new Label("Post: ");
        lbPost.setStyle(LABEL_STYLE);
        GridPane.setHalignment(lbPost, HPos.LEFT);
        tfPost = new TextField();
        tfPost.setText(searchedTeacher.getPost());

        Label lbExperience = new Label("Experience: ");
        lbExperience.setStyle(LABEL_STYLE);
        GridPane.setHalignment(lbExperience, HPos.LEFT);
        tfExperience = new TextField();
        tfExperience.setText(searchedTeacher.getExperience());

        Label lbDepartment = new Label("Department: ");
        lbDepartment.setStyle(LABEL_STYLE);
        GridPane.setHalignment(lbDepartment, HPos.LEFT);
        tfDepartment = new TextField();
        tfDepartment.setText(searchedTeacher.getDepartment());

        Label lbJoiningDate = new Label("Joining Date: ");
        lbJoiningDate.setStyle(LABEL_STYLE);
        GridPane.setHalignment(lbJoiningDate, HPos.LEFT);
        tfJoiningDate = new TextField();
        tfJoiningDate.setText(searchedTeacher.getJoiningDate().toString());

        imageView = new ImageView();
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(250);
        imageView.setFitHeight(150);
        GridPane.setHalignment(imageView, HPos.LEFT);
        GridPane.setColumnSpan(imageView, 3);
        GridPane.setRowSpan(imageView, 3);
        String url = searchedTeacher.getPhotoUrl();
        Image image = new Image(url);
        imageView.setImage(image);

        tfAddress = new TextArea();
        tfAddress.setPromptText("Address");
        GridPane.setColumnSpan(tfAddress, 4);
        GridPane.setRowSpan(tfAddress, 3);
        tfAddress.setPrefRowCount(7); // You can adjust the number of visible rows as needed
        tfAddress.setWrapText(true);
        tfAddress.setText(searchedTeacher.getAddress());

        root.add(header, 4, 2);

        root.add(lbFirstName, 1, 4);
        root.add(tfFirstName, 2, 4);
        root.add(lbLastName, 3, 4);;
        root.add(tfLastName, 4, 4);
        root.add(lbDateOfBirth, 5, 4);
        root.add(tfDateOfBirth, 6, 4);
        root.add(lbDepartment, 7, 4);
        root.add(tfDepartment, 8, 4);

        root.add(lbEmail, 1, 5);
        root.add(tfEmail, 2, 5);
        root.add(lbMobileNumber, 3, 5);
        root.add(tfMobileNumber, 4, 5);
        root.add(lbQualification, 5, 5);
        root.add(tfQualification, 6, 5);
        root.add(lbPost, 7, 5);
        root.add(tfPost, 8, 5);

        root.add(lbExperience, 1, 6);
        root.add(tfExperience, 2, 6);
        root.add(lbJoiningDate, 3, 6);
        root.add(tfJoiningDate, 4, 6);

        root.add(tfAddress, 1, 7);
        root.add(imageView, 5, 7);

        return root;
    }
    private boolean deleteTeacher(int teacherId) {
        DbHelper dbHelper = new DbHelper();
        boolean deleted = dbHelper.deleteTeacher(teacherId);
        if (deleted) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Teacher Deleted");
            alert.setContentText("Student with "+searchedTeacher.getTeacherId()+" is deleted");
            alert.show();
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Something went wrong");
            alert.setHeaderText("Teacher can't be Deleted");
            alert.setContentText("Teacher is not Deleted");
            alert.show();
            return false;
        }
    }
    private boolean searchTeacher(int teacherId) {
        DbHelper dbHelper = new DbHelper();
        searchedTeacher = dbHelper.searchTeacher(teacherId);
        if (searchedTeacher != null) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Something went wrong");
            alert.setHeaderText("No Teacher Found");
            alert.setContentText("Check Teacher Id or Enter valid Teacher Id");
            alert.show();
            return false;
        }
    }
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Search Teacher");
        // Create the UI content
        BorderPane root = createContents();
        // Create a scene and set it on the stage
        Scene scene = new Scene(root, 1080, 720);
        stage.setScene(scene);
        stage.show();
    }
}
