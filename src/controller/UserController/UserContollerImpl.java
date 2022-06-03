package controller.UserController;

import entity.User;
import model.User.UserModel;
import util.EncryptionUtil;

import javax.naming.AuthenticationException;

public class UserContollerImpl implements UserContoller {
    private final UserModel userModel;

    public UserContollerImpl(UserModel userModel) {
        this.userModel = userModel;
    }

    @Override
    public User login(String username, String password) {
        try {
            String encryptedPassword = EncryptionUtil.encryptPassword(password);
            User user = new User(username, encryptedPassword);
            int idUser = userModel.loginUser(user);
            if (idUser == -1) {
                throw new AuthenticationException("Username or password is incorrect");
            }
            user.setId(idUser);
            return user;
        } catch (Exception e) {
            if (e instanceof AuthenticationException) {
                throw new RuntimeException(e.getMessage());
            }
            throw new RuntimeException("Failed to login");
        }
    }

    @Override
    public User register(String username, String password) {
        try {
            String encryptedPassword = EncryptionUtil.encryptPassword(password);
            User user = new User(username, encryptedPassword);
            long idUser = userModel.addUser(user);
            user.setId((int) idUser);
            return user;
        } catch (Exception e) {
            if (e.getMessage().contains("user.username")) {
                throw new RuntimeException("Username already exists");
            }
            throw new RuntimeException(e);
        }

    }

    @Override
    public void logout() {
        return;
    }
}
