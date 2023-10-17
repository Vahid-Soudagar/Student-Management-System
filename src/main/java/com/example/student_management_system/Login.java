package com.example.student_management_system;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Login extends Application  {

    private TextField userIdField;
    private PasswordField passwordField;

    BorderPane createContents() {
        BorderPane root = new BorderPane();
        VBox content = new VBox(10); // Vertical layout with spacing
        content.setAlignment(Pos.CENTER);
        // Title Label
        Label titleLabel = new Label("Login");
        titleLabel.setStyle("-fx-font-size: 40px; -fx-font-weight: bold;");
        // User ID Input Field
        userIdField = new TextField();
        userIdField.setPromptText("Enter User ID");
        userIdField.setMaxSize(200, 20);
        // Password Input Field
        passwordField = new PasswordField();
        passwordField.setPromptText("Enter Password");
        passwordField.setMaxSize(200, 20);
        // Login Button
        Button loginButton = new Button("Login");

        loginButton.setOnAction(e -> {
            try {
                userExist();
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });
        content.getChildren().addAll(titleLabel, userIdField, passwordField, loginButton);
        root.setCenter(content);



        return root;
    }

    private void userExist() throws ClassNotFoundException {
        DbHelper dbHelper = new DbHelper();
        dbHelper.createTable();
        int userid = Integer.parseInt(userIdField.getText());
        String password = passwordField.getText();
        boolean exist = dbHelper.isUserExist(userid, password);
        if (exist) {
            System.out.println("User Exist");
        } else {
            System.out.println("User not found");
        }
    }


    @Override
    public void start(Stage stage) throws Exception {

    }

    public static void main(String[] args) throws ClassNotFoundException {
        launch(args);
    }
}
