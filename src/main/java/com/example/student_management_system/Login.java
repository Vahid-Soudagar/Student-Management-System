package com.example.student_management_system;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

/*

 */

public class Login extends Application {

    private TextField userIdField;
    private PasswordField passwordField;
    private Label userTypeLabel;
    private String userType;
    private Stage loginStage;

    // Constructor to set the user type
    public Login(String userType) {
        this.userType = userType;
    }

    // Create the contents of the login screen
    BorderPane createContents() {
        BorderPane root = new BorderPane();
        VBox content = new VBox(10); // Vertical layout with spacing
        content.setAlignment(Pos.CENTER);

        // Title Label
        Label titleLabel = new Label("Login");
        titleLabel.setStyle("-fx-font-size: 40px; -fx-font-weight: bold;");

        userTypeLabel = new Label("You are logging in as " + userType);
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

        // Action for the login button
        loginButton.setOnAction(e -> {
            try {
                boolean exist = userExist();
                if (exist && userType.equals("Admin")) {
                    openAdminHomeScreen(new AdminHome());
                    Stage stage = (Stage) loginButton.getScene().getWindow();
                    stage.close();
                }
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });

        content.getChildren().addAll(titleLabel, userTypeLabel, userIdField, passwordField, loginButton);
        root.setCenter(content);

        return root;
    }

    // Check if the user exists and validate credentials
    private boolean userExist() throws ClassNotFoundException {
        // Create a database helper
        DbHelper dbHelper = new DbHelper();
        dbHelper.createUserTable();

        // Get user ID and password from input fields
        String userIdText = userIdField.getText();
        String password = passwordField.getText();

        if (userIdText.isEmpty()) {
            // Display an error if User ID is empty
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("User ID Error");
            alert.setContentText("Please enter a User ID.");
            alert.showAndWait();
            return false;
        }

        int userId;
        try {
            userId = Integer.parseInt(userIdText);
        } catch (NumberFormatException e) {
            // Display an error if parsing the User ID fails
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("User ID Error");
            alert.setContentText("User ID must be a valid integer.");
            alert.showAndWait();
            return false;
        }

        boolean validCredentials = Util.validCredentials(userId, password);
        if (validCredentials) {
            if (dbHelper.isUserExist(userId, password, userType)) {
                System.out.println("User Exists");
                return true;
            } else {
                System.out.println("User not found");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Login");
                alert.setHeaderText("User ID");
                alert.setContentText("User not found");
                alert.showAndWait();
                return false;
            }
        } else {
            System.out.println("Invalid Credentials");
            return false;
        }
    }

    // Open the Admin Home screen
    private void openAdminHomeScreen(AdminHome adminHome) {
        Stage adminHomeStage = new Stage();
        Scene adminHomeScene = new Scene(adminHome.createContents(), 1080, 720);
        adminHomeStage.setScene(adminHomeScene);
        adminHomeStage.setResizable(false);
        adminHomeStage.setTitle("Admin Home");
        if (loginStage != null) {
            loginStage.close();
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
