package com.example.student_management_system;
import java.sql.*;

public class DbHelper {

    private final String DRIVER = "org.postgresql.Driver";
    private final String URL = "jdbc:postgresql://localhost:5432/";
    private final String DATABASE_NAME = "StudentManagement";
    private final String USER_TABLE = "Users";
    private final String STUDENT_TABLE = "Students";
    private final String USER = "postgres";
    private final String PASSWORD = "90041";


    public void createUserTable() throws ClassNotFoundException {
        Class.forName(DRIVER);
        try {
            Connection connection = DriverManager.getConnection(URL + DATABASE_NAME, USER, PASSWORD);
            String query = "CREATE TABLE IF NOT EXISTS "+ USER_TABLE + " (" +
                    " userid serial PRIMARY KEY," +
                    " password VARCHAR(16) NOT NULL," +
                    " role VARCHAR(10) NOT NULL"+
                    " )";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void createStudentsTable() throws ClassNotFoundException {
         Class.forName(DRIVER);
         try {
             Connection connection = DriverManager.getConnection(URL+DATABASE_NAME, USER, PASSWORD);
             String query = "CREATE TABLE IF NOT EXISTS " + STUDENT_TABLE + " (" +
                     "studentId INT PRIMARY KEY NOT NULL, " +
                     "password VARCHAR(16) NOT NULL,"+
                     "firstName VARCHAR(255) NOT NULL, " +
                     "secondName VARCHAR(255) NOT NULL, " +
                     "lastName VARCHAR(255) NOT NULL, " +
                     "dateOfBirth DATE NOT NULL, " +
                     "address VARCHAR(255) NOT NULL, " +
                     "mobileNumber VARCHAR(15) NOT NULL, " +
                     "standard VARCHAR(10) NOT NULL, " +
                     "division VARCHAR(10) NOT NULL, " +
                     "teacherName VARCHAR(255) NOT NULL, " +
                     "teacherId INT NOT NULL, " +
                     "photoUrl VARCHAR(255) NOT NULL" +
                     ")";

             PreparedStatement preparedStatement = connection.prepareStatement(query);
             preparedStatement.executeUpdate();
             System.out.println("Student table created");
         } catch (SQLException e) {
             throw new RuntimeException(e);
         }
    }

    public boolean addStudent(Student student) throws ClassNotFoundException {
        Class.forName(DRIVER);
        try {
            Connection connection = DriverManager.getConnection(URL+DATABASE_NAME, USER, PASSWORD);
            String insertQuery = "INSERT INTO " + STUDENT_TABLE + " " +
                    "(studentId, password, firstName, secondName, lastName, dateOfBirth, address, " +
                    "mobileNumber, standard, division, teacherName, teacherId, photoUrl) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);

            // Set values for the placeholders in the prepared statement
            preparedStatement.setInt(1, student.getStudentId());
            preparedStatement.setString(2, student.getPassword());
            preparedStatement.setString(3, student.getFirstName());
            preparedStatement.setString(4, student.getSecondName());
            preparedStatement.setString(5, student.getLastName());
            preparedStatement.setDate(6, student.getDateOfBirth());
            preparedStatement.setString(7, student.getAddress());
            preparedStatement.setString(8, student.getMobileNumber());
            preparedStatement.setString(9, student.getStandard());
            preparedStatement.setString(10, student.getDivision());
            preparedStatement.setString(11, student.getTeacherName());
            preparedStatement.setInt(12, student.getTeacherId());
            preparedStatement.setString(13, student.getPhotoUrl());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                // Data inserted successfully
                System.out.println("Student added successfully.");
                return true;
            } else {
                // Handle error or show a message if needed
                return false;
            }
            } catch (SQLException e) {
                // Handle database errors
                e.printStackTrace();
            }

        return false;
    }

    public boolean isUserExist(int userid, String password, String role) {
        try {
            String query = "SELECT COUNT(*) FROM "+ USER_TABLE +" WHERE userid = ? AND password = ? AND role = ?";
            Connection connection = DriverManager.getConnection(URL + DATABASE_NAME, USER, PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userid);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, role);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
