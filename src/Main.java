import controller.TodoSection.TodoSectionController;
import controller.TodoSection.TodoSectionControllerImpl;
import controller.TodolistController.TodolistController;
import controller.TodolistController.TodolistControllerImpl;
import controller.UserController.UserContoller;
import controller.UserController.UserContollerImpl;
import entity.TodoList;
import entity.TodoSection;
import repository.TodoList.TodoListRepository;
import repository.TodoList.TodoListRepositoryImpl;
import repository.TodoSection.TodoSectionRepository;
import repository.TodoSection.TodoSectionRepositoryImpl;
import repository.User.UserRepository;
import repository.User.UserRepositoryImpl;
import util.DatabaseUtil;
import view.View;
import view.page.*;

import javax.swing.*;
import java.sql.Connection;

public class Main {

    public static void main(String[] args) {
        Connection dbConnection = DatabaseUtil.getConnection();
        TodoListRepository todoListRepository = new TodoListRepositoryImpl(dbConnection);
        TodolistController todolistController = new TodolistControllerImpl(todoListRepository);

        UserRepository userRepository = new UserRepositoryImpl(dbConnection);
        UserContoller userContoller = new UserContollerImpl(userRepository);

        TodoSectionRepository todoSectionRepository = new TodoSectionRepositoryImpl(dbConnection);
        TodoSectionController todoSectionController = new TodoSectionControllerImpl(todoSectionRepository);

        LoginPage loginPage = new LoginPage(userContoller);
        RegisterPage registerPage = new RegisterPage(userContoller);
        TodoSectionPage todoSectionPage = new TodoSectionPage(todoSectionController);
        TodoListPage todoListPage = new TodoListPage(todolistController);
        AddTodoSectionPage addTodoSectionPage = new AddTodoSectionPage(todoSectionController);
        EditTodoSectionPage editTodoSectionPage = new EditTodoSectionPage(todoSectionController);

        View view = new View();
        view.addPage("login", loginPage);
        view.addPage("register", registerPage);
        view.addPage("todoSection", todoSectionPage);
        view.addPage("todoList", todoListPage);
        view.addPage("addTodoSection", addTodoSectionPage);
        view.addPage("editTodoSection", editTodoSectionPage);

        SwingUtilities.invokeLater(() -> view.start());
    }
}
