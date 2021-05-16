package dao;

import model.User;
import util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl extends Util implements UserDao {

    Connection connection = getConnection();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() throws SQLException {
        String sql = "CREATE TABLE `users`.`users` (\n" +
                "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                "  `name` VARCHAR(45) NOT NULL,\n" +
                "  `lastname` VARCHAR(45) NOT NULL,\n" +
                "  `age` INT(3) NOT NULL,\n" +
                "  PRIMARY KEY (`id`))\n" +
                "ENGINE = InnoDB\n" +
                "DEFAULT CHARACTER SET = utf8";

        try(Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
            System.out.println("Таблица создана");
        } catch (SQLException e) {
            System.err.println("Таблица не создана!");
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    public void dropUsersTable() throws SQLException {
        String sql = "DROP TABLE `users`.`users`";

        try(Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
            System.out.println("OK");
        } catch (SQLException e) {
            System.out.println("Error");
        }
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        String sql = "insert into users (name, lastname, age) values (?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, age);

            preparedStatement.executeUpdate();
            System.out.println("User с именем – " + name + " добавлен в базу данных");
        } catch (SQLException e) {

        } finally {

            if (connection != null) {
                connection.close();
            }
        }
    }

    public void removeUserById(long id) throws SQLException {
        String sql = "delete from users where id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setLong(3, id);

            preparedStatement.executeUpdate();
            System.out.println("OK");
        } catch (SQLException e) {

        } finally {

            if (connection != null) {
                connection.close();
            }
        }
    }

    public List<User> getAllUsers() throws SQLException {
        List<User> userList = new ArrayList<>();
        Statement statement = null;

        String sql = "select id, name, lastname, age from users";

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastname"));
                user.setAge(resultSet.getByte("age"));

                userList.add(user);
            }
        } catch (SQLException e) {

        } finally {
            if(statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }

        return userList;
    }

    public void cleanUsersTable() throws SQLException {
        Statement statement = null;
        String sql = "delete from users";

        try {
            statement = connection.createStatement();
            statement.executeUpdate(sql);
            System.out.println("OK");
        } catch (SQLException e) {

        } finally {
            if(statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
}
