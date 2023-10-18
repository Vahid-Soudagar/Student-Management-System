package com.example.student_management_system;

import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class Util extends Application {

    private static boolean isValidUserId(int id) {
        String userId = String.valueOf(id);

        if (userId.length() != 6) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("User Id");
            alert.setContentText("User ID must be exactly 6 characters in length.");
            alert.showAndWait();
            return false;
        }

        return true;
    }


    private static boolean isValidPassword(String password) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        if (password.length() < 6 && password.length() > 14) {
            alert.setTitle("Error");
            alert.setHeaderText("Password Error");
            alert.setContentText("Password length must be greater than 6 and less than 14");
            alert.showAndWait();
            return false;
        }
        // Check if the password contains at least one uppercase, one lowercase, one digit, and one special character
        if (!password.matches(".*[A-Z].*") || !password.matches(".*[a-z].*") ||
                !password.matches(".*\\d.*") || !password.matches(".*[^A-Za-z0-9].*")) {
            alert.setTitle("Error");
            alert.setHeaderText("Password Error");
            alert.setContentText("Password must contain at least one uppercase letter, one lowercase letter, one digit, and one special character.");
            alert.showAndWait();
            return false;
        }

        return true;
    }



    public static boolean validCredentials(int userid, String password) {
        if (isValidUserId(userid) && isValidPassword(password)){
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Password Error");
            alert.setContentText("Password must contain at least one uppercase letter, one lowercase letter, one digit, and one special character.");
            alert.showAndWait();
            return false;
        }
    }

    @Override
    public void start(Stage stage) throws Exception {

    }
}
