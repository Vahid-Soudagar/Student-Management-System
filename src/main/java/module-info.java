module com.example.student_management_system {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql; // Add this line to require the java.sql module
                            
    opens com.example.student_management_system to javafx.fxml;
    exports com.example.student_management_system;
}