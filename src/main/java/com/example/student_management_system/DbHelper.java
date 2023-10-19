package com.example.student_management_system;
import java.sql.*;

public class DbHelper {

    private final String DRIVER = "org.postgresql.Driver";
    private final String URL = "jdbc:postgresql://localhost:5432/";
    private final String DATABASE_NAME = "StudentManagementSystem";
    private final String USER_TABLE = "Users";
    private final String STUDENT_TABLE = "Students";
    private final String USER = "postgres";
    private final String PASSWORD = "90041";


    public void createUserTable() throws ClassNotFoundException {
        Class.forName(DRIVER);
        try {
            Connection connection = DriverManager.getConnection(URL + DATABASE_NAME, USER, PASSWORD);
            String query = "CREATE TABLE IF NOT EXISTS "+ USER_TABLE + " (" +
                    " userid INT PRIMARY KEY NOT NULL," +
                    " password VARCHAR(16) NOT NULL," +
                    " role VARCHAR(10) NOT NULL"+
                    " )";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean addUser(int userId, String password, String role) {
        try {
            Class.forName(DRIVER);
            Connection connection = DriverManager.getConnection(URL+DATABASE_NAME, USER, PASSWORD);
            String query = "INSERT INTO "+USER_TABLE+" (userId, password, role) "+
                    " VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userId);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, role);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                // Data inserted successfully
                System.out.println("Student added successfully.");
                return true;
            } else {
                // Handle error or show a message if needed
                return false;
            }

        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

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
//    public void createStudentsTable() throws ClassNotFoundException {
//        Class.forName(DRIVER);
//        try {
//            Connection connection = DriverManager.getConnection(URL + DATABASE_NAME, USER, PASSWORD);
//            String query = "CREATE TABLE IF NOT EXISTS " + STUDENT_TABLE + " (" +
//                    "studentId INT PRIMARY KEY NOT NULL, " +
//                    "password VARCHAR(16) NOT NULL," +
//                    "firstName VARCHAR(255) NOT NULL, " +
//                    "secondName VARCHAR(255) NOT NULL, " +
//                    "lastName VARCHAR(255) NOT NULL, " +
//                    "dateOfBirth DATE NOT NULL, " +
//                    "address VARCHAR(255) NOT NULL, " +
//                    "mobileNumber VARCHAR(15) NOT NULL, " +
//                    "standard VARCHAR(10) NOT NULL, " +
//                    "division VARCHAR(10) NOT NULL, " +
//                    "teacherName VARCHAR(255) NOT NULL, " +
//                    "teacherId INT NOT NULL, " +
//                    "photoUrl VARCHAR(255) NOT NULL" +
//                    ")";
//            PreparedStatement preparedStatement = connection.prepareStatement(query);
//            preparedStatement.executeUpdate();
//            System.out.println("Student table created");
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//            throw new RuntimeException(e);
//        }
//    }


    public void createStudentsTable() throws ClassNotFoundException {
        Class.forName(DRIVER);
        try {
            Connection connection = DriverManager.getConnection(URL + DATABASE_NAME, USER, PASSWORD);
            String query = "CREATE TABLE IF NOT EXISTS " + STUDENT_TABLE + " (" +
                    "studentId INT DEFAULT nextval('my_seq'::regclass) PRIMARY KEY, " +
                    "password VARCHAR(16) NOT NULL," +
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
            System.out.println(e.getMessage());
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

    public Student searchStudent(int studentId) {
        Student student = null;
        try {
            Class.forName(DRIVER);
            Connection connection = DriverManager.getConnection(URL+DATABASE_NAME, USER, PASSWORD);
            String query = "SELECT * FROM "+STUDENT_TABLE+" WHERE studentid = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, studentId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                student = new Student();
                student.setStudentId(resultSet.getInt("studentId"));
                student.setPassword(resultSet.getString("password"));
                student.setFirstName(resultSet.getString("firstName"));
                student.setSecondName(resultSet.getString("secondName"));
                student.setLastName(resultSet.getString("lastName"));
                student.setDateOfBirth(new Date(resultSet.getDate("dateOfBirth").getDate()));
                student.setAddress(resultSet.getString("address"));
                student.setMobileNumber(resultSet.getString("mobileNumber"));
                student.setStandard(resultSet.getString("standard"));
                student.setDivision(resultSet.getString("division"));
                student.setTeacherName(resultSet.getString("teacherName"));
                student.setTeacherId(resultSet.getInt("teacherId"));
                student.setPhotoUrl(resultSet.getString("photoUrl"));

            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return student;

    }

    public boolean deleteStudent(int studentId) {
        try {
            Class.forName(DRIVER);
            Connection connection = DriverManager.getConnection(URL+DATABASE_NAME, USER, PASSWORD);
            String deleteFromStudentTable = "DELETE FROM "+STUDENT_TABLE+ " WHERE studentId = ?";
//            String deleteFromUserTable = "DELETE FROM "+USER_TABLE+ " WHERE userId = ?";
//            PreparedStatement userTablePS = connection.prepareStatement(deleteFromUserTable);
            PreparedStatement studentTablePS = connection.prepareStatement(deleteFromStudentTable);
            studentTablePS.setInt(1, studentId);
            int rowsEffected = studentTablePS.executeUpdate();
            if (rowsEffected > 0) {
                System.out.println("Student with ID " + studentId + " deleted successfully.");
                return true;
            } else {
                System.out.println("Student with ID " + studentId + " not found.");
                return false;
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int getNextStudentId() throws ClassNotFoundException {
        Class.forName(DRIVER);
        Connection connection = null;
        int nextStudentId = 300000;  // Set the starting value

        try {
            connection = DriverManager.getConnection(URL + DATABASE_NAME, USER, PASSWORD);
            Statement stmt = connection.createStatement();

            // Get the current maximum student ID from the database
            ResultSet rs = stmt.executeQuery("SELECT MAX(studentId) FROM " + STUDENT_TABLE);

            if (rs.next()) {
                int maxStudentId = rs.getInt(1);
                // Increment the maximum student ID to get the next student ID
                nextStudentId = Math.max(maxStudentId + 1, 300000);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle this error appropriately
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return nextStudentId;
    }

}
