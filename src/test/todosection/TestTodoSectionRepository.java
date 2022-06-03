package test.todosection;

import entity.TodoSection;
import entity.User;
import model.TodoSection.TodoSectionModel;
import model.TodoSection.TodoSectionModelImpl;
import model.User.UserModel;
import model.User.UserModelImpl;
import util.DatabaseUtil;

import java.sql.Connection;

public class TestTodoSectionRepository {
    public static void main(String[] args) {
        TestTodoSectionRepository test = new TestTodoSectionRepository();
        test.testAddTodoSection();
        test.testGetAllOwnedTodoSections();
        test.testDeleteTodoSection();
    }

    Connection connection = DatabaseUtil.getConnection();
    TodoSectionModel todoSectionModel = new TodoSectionModelImpl(this.connection);
    UserModel userModel = new UserModelImpl(this.connection);

    public void testAddTodoSection() {
        User user = this.userModel.getAllUsers()[(int) (System.currentTimeMillis() % 5)];

        TodoSection todoSection = new TodoSection();
        todoSection.setName("Test " + System.currentTimeMillis());
        todoSection.setOwner(user);

        todoSectionModel.addTodoSection(todoSection);
    }

    public void testGetAllOwnedTodoSections() {
        User user = this.userModel.getAllUsers()[(int) (System.currentTimeMillis() % 5)];

        System.out.println(user.getUsername());
        for (TodoSection todoSection : todoSectionModel.getAllTodoSections(user)) {
            System.out.println(todoSection.getName());
        }
    }

    public void testDeleteTodoSection() {
        User user = this.userModel.getAllUsers()[(int) (System.currentTimeMillis() % 5)];
        TodoSection todoSection = todoSectionModel.getAllTodoSections(user)[0];

        boolean isDeleted = todoSectionModel.deleteTodoSection(todoSection);
        System.out.println(todoSection.getOwner().getId() + " " + todoSection.getId() + " " + isDeleted);
        System.out.println("Deleted " + todoSection.getName() + ": " + isDeleted);
    }

}
