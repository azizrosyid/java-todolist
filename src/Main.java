import controller.TodoSection.TodoSectionController;
import controller.TodoSection.TodoSectionControllerImpl;
import controller.TodolistController.TodolistController;
import controller.TodolistController.TodolistControllerImpl;
import controller.UserController.UserContoller;
import controller.UserController.UserContollerImpl;
import model.TodoList.TodoListModel;
import model.TodoList.TodoListModelImpl;
import model.TodoSection.TodoSectionModel;
import model.TodoSection.TodoSectionModelImpl;
import model.User.UserModel;
import model.User.UserModelImpl;
import util.DatabaseUtil;
import view.View;
import view.page.*;

import javax.swing.*;
import java.sql.Connection;

public class Main {

    public static void main(String[] args) {
        Connection dbConnection = DatabaseUtil.getConnection();
        TodoListModel todoListModel = new TodoListModelImpl(dbConnection);
        TodolistController todolistController = new TodolistControllerImpl(todoListModel);

        UserModel userModel = new UserModelImpl(dbConnection);
        UserContoller userContoller = new UserContollerImpl(userModel);

        TodoSectionModel todoSectionModel = new TodoSectionModelImpl(dbConnection);
        TodoSectionController todoSectionController = new TodoSectionControllerImpl(todoSectionModel);

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

        SwingUtilities.invokeLater(view::start);
    }
}
