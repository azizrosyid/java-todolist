package controller.TodoSection;

import entity.TodoSection;
import entity.User;
import model.TodoSection.TodoSectionModel;

public class TodoSectionControllerImpl implements TodoSectionController {
    private final TodoSectionModel todoSectionModel;

    public TodoSectionControllerImpl(TodoSectionModel todoSectionModel) {
        this.todoSectionModel = todoSectionModel;
    }

    @Override
    public TodoSection addTodoSection(String name, User user) {
        TodoSection todoSection = new TodoSection(name, user);
        try {
            int idTodoSection = todoSectionModel.addTodoSection(todoSection);
            todoSection.setId(idTodoSection);
            return todoSection;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public TodoSection[] getAllTodoSections(User user) {
        try {
            return todoSectionModel.getAllTodoSections(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteTodoSection(int id) {
        TodoSection todoSection = new TodoSection();
        todoSection.setId(id);
        try {
            return todoSectionModel.deleteTodoSection(todoSection);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public TodoSection updateTodoSection(int id, String name) {
        TodoSection todoSection = new TodoSection();
        todoSection.setId(id);
        todoSection.setName(name);
        try {
            return todoSectionModel.updateTodoSection(todoSection);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
