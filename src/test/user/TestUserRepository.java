package test.user;

import entity.User;
import repository.User.UserRepository;
import repository.User.UserRepositoryImpl;
import util.DatabaseUtil;
import util.EncryptionUtil;

import java.sql.Connection;

public class TestUserRepository {
    public static void main(String[] args) {
        testAddUser();
        testGetAllUsers();
        testLoginUser();
        testFailedLoginUser();
        testUpdateUser();
        testDeleteUser();
    }

    public static void testAddUser() {
        Connection connection = DatabaseUtil.getConnection();
        UserRepository userRepository = new UserRepositoryImpl(connection);

        User user = new User();
        String username = "test_user_" + (int) (Math.random() * 100000);
        user.setUsername(username);
        user.setPassword(EncryptionUtil.encryptPassword(username));

        long result = userRepository.addUser(user);
        System.out.println("Add user result: " + result);

    }

    public static void testGetAllUsers() {
        Connection connection = DatabaseUtil.getConnection();
        UserRepository userRepository = new UserRepositoryImpl(connection);
        User[] users = userRepository.getAllUsers();
        for (User user : users) {
            System.out.println(user.getId() + " " + user.getUsername() + " " + user.getPassword());
        }
    }

    public static void testLoginUser() {
        Connection connection = DatabaseUtil.getConnection();
        UserRepository userRepository = new UserRepositoryImpl(connection);

        User user = new User();
        String username = "test_user_" + (int) (Math.random() * 100000);
        user.setUsername(username);
        user.setPassword(EncryptionUtil.encryptPassword(username));

        long addUserResult = userRepository.addUser(user);

        int isLogin = userRepository.loginUser(user);
        System.out.println("Login result: " + isLogin);
    }

    public static void testFailedLoginUser() {
        Connection connection = DatabaseUtil.getConnection();
        UserRepository userRepository = new UserRepositoryImpl(connection);

        User user = new User();
        String username = "test_user_" + (int) (Math.random() * 100000);
        user.setUsername(username);
        user.setPassword(EncryptionUtil.encryptPassword(username));

        int isLogin = userRepository.loginUser(user);
        System.out.println("Login failed result: " + isLogin);
    }

    public static void testUpdateUser() {
        Connection connection = DatabaseUtil.getConnection();
        UserRepository userRepository = new UserRepositoryImpl(connection);

        User user = new User();
        String username = "test_user_" + (int) (Math.random() * 100000);
        user.setUsername(username);
        user.setPassword(EncryptionUtil.encryptPassword(username));

        long addUserResult = userRepository.addUser(user);

        user.setId((int) addUserResult);
        user.setUsername("test_user_" + (int) (Math.random() * 100000));
        user.setPassword(EncryptionUtil.encryptPassword(username));

        Boolean updateUserResult = userRepository.updateUser(user);
        System.out.println("Update user result: " + updateUserResult);
    }

    public static void testDeleteUser() {
        Connection connection = DatabaseUtil.getConnection();
        UserRepository userRepository = new UserRepositoryImpl(connection);

        User user = new User();
        String username = "test_user_" + (int) (Math.random() * 100000);
        user.setUsername(username);
        user.setPassword(EncryptionUtil.encryptPassword(username));

        long addUserResult = userRepository.addUser(user);

        user.setId((int) addUserResult);

        Boolean deleteUserResult = userRepository.deleteUser(user);
        System.out.println("Delete user result: " + deleteUserResult);
    }
}
