package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private static Connection connection;

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try {
            Statement statement = Util.getConnection().createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS User (" +
                    "id INTEGER AUTO_INCREMENT NOT NULL, " +
                    "name VARCHAR(255), " +
                    "lastname VARCHAR(255), " +
                    "age INTEGER, " +
                    "PRIMARY KEY (id)" +
                    ")";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        try {
            Statement statement = Util.getConnection().createStatement();
            String sql = "DROP TABLE IF EXISTS User";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            Statement statement = Util.getConnection().createStatement();
            String sql = "INSERT INTO User (name, lastname, age) VALUES('" + name + "','" + lastName + "'," + age + ")";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        try {
            Statement statement = Util.getConnection().createStatement();
            String sql = "DELETE FROM User WHERE id = " + id;
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try {
            Statement statement = Util.getConnection().createStatement();
            String sql = "SELECT * FROM User";
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                User user = new User();

                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastname"));
                user.setAge(resultSet.getByte("age"));

                users.add(user);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return users;
    }

    public void cleanUsersTable() {
        try {
            Statement statement = Util.getConnection().createStatement();
            String sql = "TRUNCATE TABLE User";
            statement.executeUpdate(sql);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
