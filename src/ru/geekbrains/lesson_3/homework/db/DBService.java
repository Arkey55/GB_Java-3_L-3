package ru.geekbrains.lesson_3.homework.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBService {
    private DBService(){}

    public static Connection getConnection(){
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost/java_chat?useSSL=false", "root", "");
        } catch (SQLException e) {
            throw new RuntimeException("SWW during DB connection", e);
        }
    }
    public static void close(Connection connection){
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException("SWW during close save operation", e);
        }
    }

    public static void rollback(Connection connection){
        try {
            connection.rollback();
        } catch (SQLException e) {
            throw new RuntimeException("SWW during rollback", e);
        }
    }
}
