package controller.TodolistController;

import entity.TodoList;

public interface TodolistController {
    TodoList[] getAllTodolists(int idSection);

    TodoList addTodolist(String name, int idSection);

    boolean removeTodolist(TodoList todolist);

    void renameTodolist(String oldName, String newName);
}