package repository.TodoSection;

import entity.TodoSection;
import entity.User;

public interface TodoSectionRepository {
    int addTodoSection(TodoSection todoSection);

    TodoSection[] getAllTodoSections(User user);

    boolean deleteTodoSection(TodoSection todoSection);

    TodoSection updateTodoSection(TodoSection todoSection);
}
