package ru.geekbrains.lesson_3.homework.db;

import ru.geekbrains.lesson_3.homework.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientService implements Service<User> {


    @Override
    public User getUserByEmailAndPassword(String email, String password){
        Connection connection = DBService.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE email = ? AND password = ?");
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                return new User(
                        resultSet.getInt("id"),
                        resultSet.getString("nickname"),
                        resultSet.getString("email"),
                        resultSet.getString("password")
                );
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("SWW during getUserByEmailAndPassword", e);
        } finally {
            DBService.close(connection);
        }
    }


    @Override
    public List<User> findAll() {
        Connection connection = DBService.getConnection();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
            List<User> user = new ArrayList<>();
            while (resultSet.next()){
                user.add(
                        new User(
                                resultSet.getInt("id"),
                                resultSet.getString("nickname"),
                                resultSet.getString("email"),
                                resultSet.getString("password")
                        )
                );
            }
            return user;
        } catch (SQLException e) {
            throw new RuntimeException("SWW during findAll", e);
        } finally {
            DBService.close(connection);
        }
    }

    @Override
    public int saveInDB(User user) {
        Connection connection = DBService.getConnection();

        try {
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO users (nickname, email, password) VALUES (?, ?, ?)"
            );
            statement.setString(1, user.getNickname());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            int val = statement.executeUpdate();
            connection.commit();
            return val;
        } catch (SQLException e) {
            DBService.rollback(connection);
            throw new RuntimeException("SWW during saveInDB", e);
        } finally {
            DBService.close(connection);
        }
    }

    @Override
    public int doChangeNickname(int id, String newName) {
        Connection connection = DBService.getConnection();

        try {
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE users SET nickname = ? WHERE id = ?"
            );
            statement.setString(1, newName);
            statement.setInt(2, id);
            int val = statement.executeUpdate();
            connection.commit();
            return val;
        } catch (SQLException e) {
            DBService.rollback(connection);
            throw new RuntimeException("SWW during changing nickname", e);
        } finally {
            DBService.close(connection);
        }
    }
}
