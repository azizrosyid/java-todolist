package controller.TodoSection;

import entity.TodoSection;
import entity.User;

public interface TodoSectionController {
    TodoSection addTodoSection(String name, User user);

    TodoSection[] getAllTodoSections(User user);

    boolean deleteTodoSection(int id);

    TodoSection updateTodoSection(int id, String name);
}
