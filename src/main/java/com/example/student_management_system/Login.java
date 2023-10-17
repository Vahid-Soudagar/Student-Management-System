package com.example.student_management_system;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.Scanner;

public class Login extends Application  {

    private TextField userIdField;
    private PasswordField passwordField;
    private Label userTypeLabel;
    private String userType;
    private Stage loginStage;

    public Login (String userType) {
        this.userType = userType;
    }

    BorderPane createContents() {
        BorderPane root = new BorderPane();
        VBox content = new VBox(10); // Vertical layout with spacing
        content.setAlignment(Pos.CENTER);
        // Title Label
        Label titleLabel = new Label("Login");
        titleLabel.setStyle("-fx-font-size: 40px; -fx-font-weight: bold;");

        userTypeLabel = new Label("You are logging as "+userType);
        titleLabel.setStyle("-fx-font-size: 32px; -fx-font-weight: bold;");

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
                boolean exist = userExist();
                if (exist && userType.equals("Admin")) {
                    if (loginStage != null) {
                        loginStage.close();
                    }
                    openAdminHomeScreen(new AdminHome());
                }

            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });
        content.getChildren().addAll(titleLabel, userTypeLabel, userIdField, passwordField, loginButton);
        root.setCenter(content);



        return root;
    }

    private boolean userExist() throws ClassNotFoundException {
        DbHelper dbHelper = new DbHelper();
        dbHelper.createTable();
        String userIdText = userIdField.getText();
        String password = passwordField.getText();

        if (userIdText.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("User ID Error");
            alert.setContentText("Please enter a User ID.");
            alert.showAndWait();
            return false; // Stop execution if User ID is empty
        }

        int userId;
        try {
            userId = Integer.parseInt(userIdText);
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("User ID Error");
            alert.setContentText("User ID must be a valid integer.");
            alert.showAndWait();
            return false; // Stop execution if parsing fails
        }

        boolean validCredentials = Util.validCredentials(userId, password);
        if (validCredentials) {
            if (dbHelper.isUserExist(userId, password)) {
                System.out.println("User Exist");
                return true;
            } else {
                System.out.println("User not found");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Login");
                alert.setContentText("User not found");
                alert.showAndWait();
                return false;
            }
        } else {
            System.out.println("Invalid Credentials");
            return false;
        }
    }

    private void openAdminHomeScreen(AdminHome adminHome) {
        Stage adminHomeStage = new Stage();
        Scene adminHomeScene = new Scene(adminHome.createContents(), 1080, 720);
        adminHomeStage.setScene(adminHomeScene);
        adminHomeStage.setResizable(false);
        adminHomeStage.setTitle("Admin Home");
        if (loginStage != null) {
            loginStage.close();
        } else {

        }
        adminHomeStage.show();
    }

    @Override
    public void start(Stage stage) throws Exception {
        loginStage = stage;
        BorderPane root = createContents();
        stage.setTitle("Login");
        Scene loginScene = new Scene(root, 1080, 720);
        stage.setScene(loginScene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) throws ClassNotFoundException {
        launch(args);
    }


}
