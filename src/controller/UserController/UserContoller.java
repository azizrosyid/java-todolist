package controller.UserController;

import entity.User;

public interface UserContoller {
    User login(String username, String password) ;
    User register(String username, String password) ;
    void logout();
}
