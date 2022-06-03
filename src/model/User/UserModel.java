package model.User;

import entity.User;

public interface UserModel {
    int addUser(User user);

    boolean updateUser(User user);

    boolean deleteUser(User user);

    User[] getAllUsers();

    int loginUser(User user);
}
