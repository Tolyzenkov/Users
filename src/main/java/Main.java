import service.UserService;
import service.UserServiceImpl;

import java.sql.*;

public class Main {

    private static final UserService userService = new UserServiceImpl();

    private static final String testName = "Ivan";
    private static final String testLastName = "Ivanov";
    private static final byte testAge = 5;

    public static void main(String[] args) {
        createUsersTable();
    }

    public static void createUsersTable() {
        try {
            userService.dropUsersTable();
            userService.createUsersTable();
        } catch (Exception e) {
            System.out.println("Error");
        }
    }
}
