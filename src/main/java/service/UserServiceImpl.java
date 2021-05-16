package service;


import dao.UserDaoJDBCImpl;
import model.User;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();

    public void createUsersTable() {
        try {
            userDaoJDBC.createUsersTable();
        } catch (SQLException e) {

        }
    }

    public void dropUsersTable() {
        try {
            userDaoJDBC.dropUsersTable();
        } catch (SQLException e) {

        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            userDaoJDBC.saveUser(name, lastName, age);
        } catch (SQLException e) {

        }
    }

    public void removeUserById(long id) {
        try {
            userDaoJDBC.removeUserById(id);
        } catch (SQLException e) {

        }
    }

    public List<User> getAllUsers() {
        try {
            return userDaoJDBC.getAllUsers();
        } catch (SQLException e) {

        }
        return null;
    }

    public void cleanUsersTable() {
        try {
            userDaoJDBC.cleanUsersTable();
        } catch (SQLException e) {

        }
    }
}
