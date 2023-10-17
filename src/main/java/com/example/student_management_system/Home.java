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

public class Home extends Application {
    private Stage homeStage;

    private BorderPane createContent() {
        BorderPane root = new BorderPane();

//      title
        Label titleLabel = new Label();
        titleLabel.setText("Student Management System");
        titleLabel.setStyle("-fx-font-size: 40px; -fx-font-weight: bold;");
        BorderPane.setAlignment(titleLabel, Pos.CENTER);
        BorderPane.setMargin(titleLabel, new Insets(200, 0, 0, 0));
        root.setTop(titleLabel);

//        role title
        Label roleLabel = new Label();
        roleLabel.setText("You are a?");
        roleLabel.setStyle("-fx-font-size: 30px; -fx-font-weight: bold;");
        BorderPane.setAlignment(roleLabel, Pos.CENTER);
        root.setCenter(roleLabel);

//        buttons
        HBox hBox = new HBox();
        hBox.setSpacing(20);
        Button studentButton = new Button("Student");  // Set the text here
        Button teacherButton = new Button("Teacher");  // Set the text here
        Button adminButton = new Button("Admin");      // Set the text here

        studentButton.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-alignment: center;");
        teacherButton.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-alignment: center;");
        adminButton.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-alignment: center;");

        studentButton.setOnAction(e -> openLoginScreen());
        teacherButton.setOnAction(e -> openLoginScreen());
        adminButton.setOnAction(e -> openLoginScreen());

        hBox.getChildren().addAll(studentButton, teacherButton, adminButton);
        hBox.setAlignment(Pos.CENTER);
        BorderPane.setMargin(hBox, new Insets(0, 0, 300, 0));
        root.setBottom(hBox);  // Add the HBox to the bottom region of the BorderPane


        return root;
    }

    private void openLoginScreen() {
        Login login = new Login();
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