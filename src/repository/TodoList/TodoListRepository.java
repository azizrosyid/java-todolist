package repository.TodoList;

import entity.TodoList;
import entity.TodoSection;

public interface TodoListRepository {
    TodoList addTodoList(TodoList todoList);

    boolean removeTodoList(TodoList todoList);

    TodoList[] getAllTodoListsByUser(TodoSection todoSection);
}
