package model.TodoSection;

import entity.TodoSection;
import entity.User;

public interface TodoSectionModel {
    int addTodoSection(TodoSection todoSection);

    TodoSection[] getAllTodoSections(User user);

    boolean deleteTodoSection(TodoSection todoSection);

    TodoSection updateTodoSection(TodoSection todoSection);
}
