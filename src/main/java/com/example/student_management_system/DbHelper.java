package com.example.student_management_system;
import java.sql.*;

public class DbHelper {

    private final String DRIVER = "org.postgresql.Driver";
    private final String URL = "jdbc:postgresql://localhost:5432/";
    private final String DATABASE_NAME = "StudentManagementSystem";
    private final String USER_TABLE = "Users";
    private final String STUDENT_TABLE = "Students";
    private final String TEACHER_TABLE = "Teachers";
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

        } catch (ClassNotFoundException | SQLException e) {
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
                student.setDateOfBirth(resultSet.getDate("dateOfBirth"));
                student.setAddress(resultSet.getString("address"));
                student.setMobileNumber(resultSet.getString("mobileNumber"));
                student.setStandard(resultSet.getString("standard"));
                student.setDivision(resultSet.getString("division"));
                student.setTeacherName(resultSet.getString("teacherName"));
                student.setTeacherId(resultSet.getInt("teacherId"));
                student.setPhotoUrl(resultSet.getString("photoUrl"));

            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        return student;

    }

    public boolean deleteStudent(int studentId) {
        try {
            Class.forName(DRIVER);
            Connection connection = DriverManager.getConnection(URL+DATABASE_NAME, USER, PASSWORD);
            String deleteFromStudentTable = "DELETE FROM "+STUDENT_TABLE+ " WHERE studentId = ?";
            String deleteFromUserTable = "DELETE FROM "+USER_TABLE+ " WHERE userId = ?";
            PreparedStatement userTablePS = connection.prepareStatement(deleteFromUserTable);
            PreparedStatement studentTablePS = connection.prepareStatement(deleteFromStudentTable);
            PreparedStatement userTablePs = connection.prepareStatement(deleteFromUserTable);
            studentTablePS.setInt(1, studentId);
            int studentRow = studentTablePS.executeUpdate();
            int userRow = userTablePS.executeUpdate();
            if (studentRow > 0 && userRow > 0) {
                System.out.println("Student with ID " + studentId + " deleted successfully.");
                System.out.println("User with ID " + studentId + " deleted successfully.");
                return true;
            } else {
                System.out.println("Student with ID " + studentId + " not found.");
                return false;
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

//    TODO - Check what is happening while updating details it is not working
    public boolean updateStudentDetails(int studentId, Student updatedStudent) {
        try {
            Class.forName(DRIVER);
            Connection connection = DriverManager.getConnection(URL + DATABASE_NAME, USER, PASSWORD);
            String updateQuery = "UPDATE " + STUDENT_TABLE + " SET " +
                    "firstName = ?, " +
                    "secondName = ?, " +
                    "lastName = ?, " +
                    "dateOfBirth = ?, " +
                    "address = ?, " +
                    "mobileNumber = ?, " +
                    "standard = ?, " +
                    "division = ?, " +
                    "teacherName = ?, " +
                    "teacherId = ?, " +
                    "photoUrl = ? " +
                    "WHERE studentId = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);

            preparedStatement.setString(1, updatedStudent.getFirstName());
            preparedStatement.setString(2, updatedStudent.getSecondName());
            preparedStatement.setString(3, updatedStudent.getLastName());
            preparedStatement.setDate(4, updatedStudent.getDateOfBirth());
            preparedStatement.setString(5, updatedStudent.getAddress());
            preparedStatement.setString(6, updatedStudent.getMobileNumber());
            preparedStatement.setString(7, updatedStudent.getStandard());
            preparedStatement.setString(8, updatedStudent.getDivision());
            preparedStatement.setString(9, updatedStudent.getTeacherName());
            preparedStatement.setInt(10, updatedStudent.getTeacherId());
            preparedStatement.setString(11, updatedStudent.getPhotoUrl());
            preparedStatement.setInt(12, studentId);

            int rowsUpdated = preparedStatement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Updated");
                return true;
            } else {
                System.out.println("Not updated");
                return false;
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
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
        }

        return nextStudentId;
    }

    public int getNextTeacherId() {
        int nextTeacherId = 200000;
        try {
            Class.forName(DRIVER);
            Connection connection = DriverManager.getConnection(URL+DATABASE_NAME, USER, PASSWORD);
            Statement statement = connection.createStatement();
            String query = " SELECT MAX(teacherId) FROM "+TEACHER_TABLE;
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                int maxTeacherId = resultSet.getInt(1);
                nextTeacherId = Math.max(maxTeacherId+1, 200000);
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

        return nextTeacherId;
    }

    public void createTeacherTable() {
        try {
            Class.forName(DRIVER);
            Connection connection = DriverManager.getConnection(URL+DATABASE_NAME, USER, PASSWORD);
            String query = "CREATE TABLE IF NOT EXISTS " + TEACHER_TABLE + " (" +
                    "teacherId INT PRIMARY KEY NOT NULL , " +
                    "password VARCHAR(16) NOT NULL, "+
                    "firstName VARCHAR(255) NOT NULL, " +
                    "lastName VARCHAR(255) NOT NULL, " +
                    "dateOfBirth DATE NOT NULL, " +
                    "address VARCHAR(1000) NOT NULL, " +
                    "email VARCHAR(255) NOT NULL, " +
                    "phoneNumber VARCHAR(10) NOT NULL, " +
                    "qualification VARCHAR(255), " +
                    "experience VARCHAR(255), " +
                    "department VARCHAR(255), " +
                    "joiningDate DATE NOT NULL, " +
                    "photoUrl VARCHAR(255) NOT NULL, " +
                    "post VARCHAR(255) NOT NULL" +
                    ")";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.executeUpdate();
            System.out.println("Teacher Table Created");
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean addTeacher(Teacher teacher) {
        try {
            Class.forName(DRIVER);
            Connection connection = DriverManager.getConnection(URL+DATABASE_NAME, USER, PASSWORD);

            // Define your INSERT query for the teacher table
            String insertQuery = "INSERT INTO " + TEACHER_TABLE + " " +
                    "(teacherId, password, firstName, lastName, dateOfBirth, address, email, " +
                    "phoneNumber, qualification, experience, department, joiningDate, photoUrl, post) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            // Prepare the SQL statement
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);

            // Set values for the placeholders in the prepared statement
            preparedStatement.setInt(1, teacher.getTeacherId());
            preparedStatement.setString(2, teacher.getPassword());
            preparedStatement.setString(3, teacher.getFirstName());
            preparedStatement.setString(4, teacher.getLastName());
            preparedStatement.setDate(5, (Date) teacher.getDateOfBirth());
            preparedStatement.setString(6, teacher.getAddress());
            preparedStatement.setString(7, teacher.getEmail());
            preparedStatement.setString(8, teacher.getPhoneNumber());
            preparedStatement.setString(9, teacher.getQualification());
            preparedStatement.setString(10, teacher.getExperience());
            preparedStatement.setString(11, teacher.getDepartment());
            preparedStatement.setDate(12, (Date) teacher.getJoiningDate());
            preparedStatement.setString(13, teacher.getPhotoUrl());
            preparedStatement.setString(14, teacher.getPost());

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                // Data inserted successfully
                System.out.println("Teacher added successfully.");
                return true;
            } else {
                // Handle error or show a message if needed
                return false;
            }
        } catch (ClassNotFoundException | SQLException e) {
            // Handle database errors
            e.printStackTrace();
            throw new RuntimeException(e); // You may want to handle this exception more gracefully
        }
    }

    public Teacher searchTeacher(int teacherId) {
        Teacher teacher = null;
        try {
            Class.forName(DRIVER);
            Connection connection = DriverManager.getConnection(URL+DATABASE_NAME, USER, PASSWORD);
            String query = "SELECT * FROM "+TEACHER_TABLE+" WHERE teacherId = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, teacherId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                teacher = new Teacher();
                teacher.setTeacherId(resultSet.getInt("teacherId"));
                teacher.setPassword(resultSet.getString("password"));
                teacher.setFirstName(resultSet.getString("firstName"));
                teacher.setLastName(resultSet.getString("lastName"));
                teacher.setDateOfBirth(resultSet.getDate("dateOfBirth"));
                teacher.setAddress(resultSet.getString("address"));
                teacher.setEmail(resultSet.getString("email"));
                teacher.setPhoneNumber(resultSet.getString("phoneNumber"));
                teacher.setQualification(resultSet.getString("qualification"));
                teacher.setExperience(resultSet.getString("experience"));
                teacher.setDepartment(resultSet.getString("department"));
                teacher.setJoiningDate(resultSet.getDate("joiningDate"));
                teacher.setPhotoUrl(resultSet.getString("photoUrl"));
                teacher.setPost(resultSet.getString("post"));

            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        return teacher;
    }

    public boolean deleteTeacher(int teacherId) {
        try {
            Class.forName(DRIVER);
            Connection connection = DriverManager.getConnection(URL+DATABASE_NAME, USER, PASSWORD);
            String deleteFromTeacherTable = "DELETE FROM "+TEACHER_TABLE+" WHERE teacherId = ?";
            String deleteFromUserTable = "DELETE FROM "+USER_TABLE+" WHERE userId = ?";
            PreparedStatement p1 = connection.prepareStatement(deleteFromTeacherTable);
            PreparedStatement p2 = connection.prepareStatement(deleteFromUserTable);
            p1.setInt(1, teacherId);
            p2.setInt(1, teacherId);
            int teacherRow = p1.executeUpdate();
            int userRow = p2.executeUpdate();
            if (teacherRow > 0 && userRow > 0) {
                System.out.println("Teacher with ID " + teacherId + " deleted successfully.");
                System.out.println("User with ID " + teacherId + " deleted successfully.");
                return true;
            } else {
                System.out.println("Teacher with ID " + teacherId + " not found.");
                return false;
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

//    TODO - Check what is happening while updating details it is not working
    public boolean updateTeacherDetails(int teacherId, Teacher updatedTeacher) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL + DATABASE_NAME, USER, PASSWORD);

            String updateQuery = "UPDATE " + TEACHER_TABLE + " SET "
                    + "firstName = ?,"
                    + "lastName = ?,"
                    + "dateOfBirth = ?,"
                    + "address = ?,"
                    + "email = ?,"
                    + "phoneNumber = ?,"
                    + "qualification = ?,"
                    + "experience = ?,"
                    + "department = ?,"
                    + "joiningDate = ?,"
                    + "photoUrl = ?,"
                    + "post = ?"
                    + " WHERE teacherId = ?";

            preparedStatement = connection.prepareStatement(updateQuery);

            // Set the parameters for the update query based on the updatedTeacher object.
            preparedStatement.setString(1, updatedTeacher.getFirstName());
            preparedStatement.setString(2, updatedTeacher.getLastName());
            preparedStatement.setDate(3, (Date) updatedTeacher.getDateOfBirth());
            preparedStatement.setString(4, updatedTeacher.getAddress());
            preparedStatement.setString(5, updatedTeacher.getEmail());
            preparedStatement.setString(6, updatedTeacher.getPhoneNumber());
            preparedStatement.setString(7, updatedTeacher.getQualification());
            preparedStatement.setString(8, updatedTeacher.getExperience());
            preparedStatement.setString(9, updatedTeacher.getDepartment());
            preparedStatement.setDate(10, new java.sql.Date(updatedTeacher.getJoiningDate().getTime()));
            preparedStatement.setString(11, updatedTeacher.getPhotoUrl());
            preparedStatement.setString(12, updatedTeacher.getPost());
            preparedStatement.setInt(13, teacherId);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
