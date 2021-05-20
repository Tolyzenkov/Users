import model.User;
import service.UserService;
import service.UserServiceImpl;

import java.sql.*;
import java.util.List;

public class Main {

    private static final UserService userService = new UserServiceImpl();

    private static final String testName = "Denis";
    private static final String testLastName = "Tolyzenkov";
    private static final byte testAge = 27;

    public static void main(String[] args) {
        userService.saveUser(testName, testLastName, testAge);
    }

    public static void saveUser() {
        try {
           // userService.dropUsersTable();
           // userService.createUsersTable();
            userService.saveUser(testName, testLastName, testAge);

            User user = userService.getAllUsers().get(0);

            if (!testName.equals(user.getName())
                    || !testLastName.equals(user.getLastName())
                    || testAge != user.getAge()
            ) {
                System.out.println("!!!");
            }

        } catch (Exception e) {
            System.out.println("!!!");
        }
    }


}
