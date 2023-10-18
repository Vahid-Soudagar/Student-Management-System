package com.example.student_management_system;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
/*
    This code defines a JavaFX application called "Student Management System."
    It presents a simple user interface with a title, asks the user to select their role (Student, Teacher, or Admin),
    and provides buttons for each role. When a role is selected,
    it opens a login screen specific to that role.
    The code is organized into methods and well-styled, making it clear and easy to understand.
 */

public class Home extends Application {
    private Stage homeStage;

    // Create the main content of the Home class
    private BorderPane createContent() {
        BorderPane root = new BorderPane();

        // Title label for the application
        Label titleLabel = new Label();
        titleLabel.setText("Student Management System");
        titleLabel.setStyle("-fx-font-size: 40px; -fx-font-weight: bold;");
        BorderPane.setAlignment(titleLabel, Pos.CENTER);
        BorderPane.setMargin(titleLabel, new Insets(200, 0, 0, 0));
        root.setTop(titleLabel);

        // Label asking the user's role
        Label roleLabel = new Label();
        roleLabel.setText("You are a?");
        roleLabel.setStyle("-fx-font-size: 30px; -fx-font-weight: bold;");
        BorderPane.setAlignment(roleLabel, Pos.CENTER);
        root.setCenter(roleLabel);

        // Create buttons for different roles
        HBox hBox = new HBox();
        hBox.setSpacing(20);
        Button studentButton = new Button("Student");
        Button teacherButton = new Button("Teacher");
        Button adminButton = new Button("Admin");

        // Styling for role buttons
        studentButton.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-alignment: center;");
        teacherButton.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-alignment: center;");
        adminButton.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-alignment: center;");

        // Actions for the role buttons
        studentButton.setOnAction(e -> {
            Login login = new Login("Student");
            openLoginScreen(login);
        });

        teacherButton.setOnAction(e -> {
            Login login = new Login("Teacher");
            openLoginScreen(login);
        });

        adminButton.setOnAction(e -> {
            Login login = new Login("Admin");
            openLoginScreen(login);
        });

        hBox.getChildren().addAll(studentButton, teacherButton, adminButton);
        hBox.setAlignment(Pos.CENTER);
        BorderPane.setMargin(hBox, new Insets(0, 0, 300, 0));
        root.setBottom(hBox);

        return root;
    }

    // Open the login screen for the selected role
    private void openLoginScreen(Login login) {
        Stage loginStage = new Stage();
        Scene loginScene = new Scene(login.createContents(), 1080, 720);
        loginStage.setScene(loginScene);
        loginStage.setResizable(false);
        loginStage.setTitle("Login");
        homeStage.close();
        loginStage.show();
    }

    @Override
    public void start(Stage stage) throws IOException {
        homeStage = stage;
        BorderPane root = createContent();
        Scene scene = new Scene(root, 1080, 720);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Student Management System!");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
