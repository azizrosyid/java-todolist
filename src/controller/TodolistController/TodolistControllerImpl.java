package controller.TodolistController;

import entity.TodoList;
import entity.TodoSection;
import repository.TodoList.TodoListRepository;

public class TodolistControllerImpl implements TodolistController {
    public TodoListRepository todoListRepository;

    public TodolistControllerImpl(TodoListRepository todoListRepository) {
        this.todoListRepository = todoListRepository;
    }


    @Override
    public TodoList[] getAllTodolists(int idSection) {
        try {
            TodoSection todoSection = new TodoSection();
            todoSection.setId(idSection);
            return todoListRepository.getAllTodoListsByUser(todoSection);
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

            return todoListRepository.addTodoList(todoList);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean removeTodolist(TodoList todoList) {
        try {
            return todoListRepository.removeTodoList(todoList);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void renameTodolist(String oldName, String newName) {

    }
}
