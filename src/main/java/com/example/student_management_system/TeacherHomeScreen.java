package com.example.student_management_system;

import javafx.application.Application;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class TeacherHomeScreen extends Application {

    private int teacherId;

    public TeacherHomeScreen(int teacherId) {
        this.teacherId = teacherId;
    }

    BorderPane createContents() {
        return new BorderPane();
    }

    @Override
    public void start(Stage stage) throws Exception {

    }
}
