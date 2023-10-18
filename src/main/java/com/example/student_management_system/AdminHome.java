package com.example.student_management_system;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AdminHome extends Application {

    BorderPane createContents() {
        BorderPane root = new BorderPane();

        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(20);

        HBox student = new HBox(20);
        student.setAlignment(Pos.CENTER);

        Button addStudent = new Button();
        addStudent.setStyle("-fx-font-size: 24px; -fx-min-width: 200px; -fx-min-height: 50px;");
        addStudent.setText("Add Student");
        addStudent.setOnAction(e -> {
            try {
                openAdminAddStudent();
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });

        Button searchStudent = new Button();
        searchStudent.setText("Search Student");
        searchStudent.setStyle("-fx-font-size: 24px; -fx-min-width: 200px; -fx-min-height: 50px;");

        student.getChildren().addAll(addStudent, searchStudent);

        HBox teacher = new HBox(20);
        teacher.setAlignment(Pos.CENTER);
        vBox.setSpacing(20);

        Button addTeacher = new Button();
        addTeacher.setText("Add Teacher");
        addTeacher.setStyle("-fx-font-size: 24px; -fx-min-width: 200px; -fx-min-height: 50px;");

        Button searchTeacher = new Button();
        searchTeacher.setText("Search Teacher");
        searchTeacher.setStyle("-fx-font-size: 24px; -fx-min-width: 200px; -fx-min-height: 50px;");

        teacher.getChildren().addAll(addTeacher, searchTeacher);

        vBox.getChildren().addAll(student, teacher);
        root.setCenter(vBox);

        return root;
    }

    private void openAdminAddStudent() throws ClassNotFoundException {
        AdminAddStudent adminAddStudent = new AdminAddStudent();
        Stage adminAddStudentStage = new Stage();
        Scene adminAddStudentScene = new Scene(adminAddStudent.createContents(), 1080, 720);
        adminAddStudentStage.setScene(adminAddStudentScene);
        adminAddStudentStage.setResizable(false);
        adminAddStudentStage.setTitle("Add Students");
        adminAddStudentStage.show();

    }
    @Override
    public void start(Stage stage) throws Exception {
        BorderPane root = createContents();
        Scene scene = new Scene(root, 1080, 720);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Student Management System!");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
