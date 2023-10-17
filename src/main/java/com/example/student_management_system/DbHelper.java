package com.example.student_management_system;
import java.sql.*;

public class DbHelper {

    private final String DRIVER = "org.postgresql.Driver";
    private final String URL = "jdbc:postgresql://localhost:5432/";
    private final String DATABASE_NAME = "Test";
    private final String TABLE_NAME = "Users";
    private final String USER = "postgres";
    private final String PASSWORD = "90041";


    public void createTable() throws ClassNotFoundException {
        Class.forName(DRIVER);
        try {
            Connection connection = DriverManager.getConnection(URL + DATABASE_NAME, USER, PASSWORD);
            String query = "CREATE TABLE IF NOT EXISTS "+ TABLE_NAME + " (" +
                    " userid serial PRIMARY KEY," +
                    " password VARCHAR(16) NOT NULL" +
                    " )";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean isUserExist(int userid, String password) {
        try {
            String query = "SELECT COUNT(*) FROM "+ TABLE_NAME +" WHERE userid = ? AND password = ?";
            Connection connection = DriverManager.getConnection(URL + DATABASE_NAME, USER, PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userid);
            preparedStatement.setString(2, password);

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
