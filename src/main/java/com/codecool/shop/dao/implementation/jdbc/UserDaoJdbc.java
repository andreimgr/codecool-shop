package com.codecool.shop.dao.implementation.jdbc;

import com.codecool.shop.dao.UserDao;
import com.codecool.shop.model.User;

import javax.sql.DataSource;
import java.sql.*;

public class UserDaoJdbc implements UserDao {

    private DataSource dataSource;

    public UserDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void addUser(User user) {

        try (Connection conn = dataSource.getConnection()) {

            String sql = "INSERT INTO users (firstname, lastname, password, email) VALUES (?, ?, ?, ?);";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getEmail());

            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            user.setId(resultSet.getInt(1));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public User getUserByEmail(String email) {

        try (Connection conn = dataSource.getConnection()) {
            ResultSet resultSet;

            String sql = "SELECT id, firstname, lastname, email, password FROM users WHERE users.email = ?;";

            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, email);

            resultSet = preparedStatement.executeQuery();
            resultSet.next();

            int id = resultSet.getInt(1);
            String firstname = resultSet.getString(2);
            String lastname = resultSet.getString(3);
            String password = resultSet.getString(5);

            User user = new User(firstname, lastname, email, password);
            user.setId(id);
            return user;
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
