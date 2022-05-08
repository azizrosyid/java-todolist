package test.todosection;

import entity.TodoSection;
import entity.User;
import repository.TodoSection.TodoSectionRepository;
import repository.TodoSection.TodoSectionRepositoryImpl;
import repository.User.UserRepository;
import repository.User.UserRepositoryImpl;
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
    TodoSectionRepository todoSectionRepository = new TodoSectionRepositoryImpl(this.connection);
    UserRepository userRepository = new UserRepositoryImpl(this.connection);

    public void testAddTodoSection() {
        User user = this.userRepository.getAllUsers()[(int) (System.currentTimeMillis() % 5)];

        TodoSection todoSection = new TodoSection();
        todoSection.setName("Test " + System.currentTimeMillis());
        todoSection.setOwner(user);

        todoSectionRepository.addTodoSection(todoSection);
    }

    public void testGetAllOwnedTodoSections() {
        User user = this.userRepository.getAllUsers()[(int) (System.currentTimeMillis() % 5)];

        System.out.println(user.getUsername());
        for (TodoSection todoSection : todoSectionRepository.getAllTodoSections(user)) {
            System.out.println(todoSection.getName());
        }
    }

    public void testDeleteTodoSection() {
        User user = this.userRepository.getAllUsers()[(int) (System.currentTimeMillis() % 5)];
        TodoSection todoSection = todoSectionRepository.getAllTodoSections(user)[0];

        boolean isDeleted = todoSectionRepository.deleteTodoSection(todoSection);
        System.out.println(todoSection.getOwner().getId() + " " + todoSection.getId() + " " + isDeleted);
        System.out.println("Deleted " + todoSection.getName() + ": " + isDeleted);
    }

}
