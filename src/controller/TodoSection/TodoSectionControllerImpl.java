package controller.TodoSection;

import entity.TodoSection;
import entity.User;
import repository.TodoSection.TodoSectionRepository;

public class TodoSectionControllerImpl implements TodoSectionController {
    private final TodoSectionRepository todoSectionRepository;

    public TodoSectionControllerImpl(TodoSectionRepository todoSectionRepository) {
        this.todoSectionRepository = todoSectionRepository;
    }

    @Override
    public TodoSection addTodoSection(String name, User user) {
        TodoSection todoSection = new TodoSection(name, user);
        try {
            int idTodoSection = todoSectionRepository.addTodoSection(todoSection);
            todoSection.setId(idTodoSection);
            return todoSection;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public TodoSection[] getAllTodoSections(User user) {
        try {
            return todoSectionRepository.getAllTodoSections(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteTodoSection(int id) {
        TodoSection todoSection = new TodoSection();
        todoSection.setId(id);
        try {
            return todoSectionRepository.deleteTodoSection(todoSection);
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
            return todoSectionRepository.updateTodoSection(todoSection);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
