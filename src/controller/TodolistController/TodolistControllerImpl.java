package controller.TodolistController;

import entity.TodoList;
import entity.TodoSection;
import model.TodoList.TodoListModel;

public class TodolistControllerImpl implements TodolistController {
    public TodoListModel todoListModel;

    public TodolistControllerImpl(TodoListModel todoListModel) {
        this.todoListModel = todoListModel;
    }


    @Override
    public TodoList[] getAllTodolists(int idSection) {
        try {
            TodoSection todoSection = new TodoSection();
            todoSection.setId(idSection);
            return todoListModel.getAllTodoListsBySection(todoSection);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public TodoList addTodolist(String name, int idSection) {
        try {
            TodoList todoList = new TodoList();
            todoList.setTodo(name);
            todoList.setIdSection(idSection);

            return todoListModel.addTodoList(todoList);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean removeTodolist(TodoList todoList) {
        try {
            return todoListModel.removeTodoList(todoList);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void renameTodolist(String oldName, String newName) {

    }
}
