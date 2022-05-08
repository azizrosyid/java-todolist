package test.user;

import controller.UserController.UserContoller;
import controller.UserController.UserContollerImpl;
import entity.User;
import repository.User.UserRepository;
import repository.User.UserRepositoryImpl;
import util.DatabaseUtil;
import util.EncryptionUtil;

import javax.naming.AuthenticationException;
import java.sql.Connection;

public class TestUserController {
    public static void main(String[] args) {
        TestUserController testUserController = new TestUserController();
        testUserController.testRegister();
        testUserController.testLogin();
    }

    UserContoller userContoller;
    User user;

    public TestUserController() {
        Connection connection = DatabaseUtil.getConnection();
        UserRepository userRepository = new UserRepositoryImpl(connection);
        this.userContoller = new UserContollerImpl(userRepository);
    }

    public void testRegister() {


        String username = "test_user" + System.currentTimeMillis();
        String password = username;

        User user = userContoller.register(username, password);
        this.user = user;
        System.out.println(user.getId());
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());

        String pass = EncryptionUtil.encryptPassword("admin");
        System.out.println(pass);
    }

    public void testLogin() {
        User user = userContoller.login(this.user.getUsername(), this.user.getUsername());
        System.out.println(user.getId());
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
    }
}
