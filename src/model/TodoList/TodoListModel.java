package model.TodoList;

import entity.TodoList;
import entity.TodoSection;

public interface TodoListModel {
    TodoList addTodoList(TodoList todoList);

    boolean removeTodoList(TodoList todoList);

    TodoList[] getAllTodoListsBySection(TodoSection todoSection);
}
