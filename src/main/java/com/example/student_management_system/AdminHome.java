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
        searchStudent.setOnAction(e -> {
            openAdminSearchStudent();
        });


        student.getChildren().addAll(addStudent, searchStudent);

        HBox teacher = new HBox(20);
        teacher.setAlignment(Pos.CENTER);
        vBox.setSpacing(20);

        Button addTeacher = new Button();
        addTeacher.setText("Add Teacher");
        addTeacher.setStyle("-fx-font-size: 24px; -fx-min-width: 200px; -fx-min-height: 50px;");
        addTeacher.setOnAction(event -> openAdminAddTeacher());
        Button searchTeacher = new Button();
        searchTeacher.setText("Search Teacher");
        searchTeacher.setStyle("-fx-font-size: 24px; -fx-min-width: 200px; -fx-min-height: 50px;");
        searchTeacher.setOnAction(event -> {
            openAdminSearchTeacher();
        });
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

    private void openAdminSearchStudent() {
        AdminSearchStudent adminSearchStudent = new AdminSearchStudent();
        Stage adminSearchStudentStage = new Stage();
        Scene adminSearchStudentScene = new Scene(adminSearchStudent.createContents(), 1080,720);
        adminSearchStudentStage.setScene(adminSearchStudentScene);
        adminSearchStudentStage.setResizable(false);
        adminSearchStudentStage.setTitle("Search Students");
        adminSearchStudentStage.show();
    }

    private void openAdminAddTeacher() {
        AdminAddTeacher adminAddTeacher = new AdminAddTeacher();
        Stage stage = new Stage();
        Scene scene = new Scene(adminAddTeacher.createContents(), 1080, 720);
        stage.setScene(scene);
        stage.setTitle("Admin Add Teacher");
        stage.setResizable(false);
        stage.show();
    }

    private void openAdminSearchTeacher() {
        AdminSearchTeacher adminSearchTeacher = new AdminSearchTeacher();
        Stage stage = new Stage();
        Scene scene = new Scene(adminSearchTeacher.createContents(), 1080, 720);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Search Teacher");
        stage.show();
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
