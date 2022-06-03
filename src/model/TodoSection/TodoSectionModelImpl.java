package model.TodoSection;

import entity.TodoSection;
import entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class TodoSectionModelImpl implements TodoSectionModel {
    private final Connection connection;

    public TodoSectionModelImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public int addTodoSection(TodoSection todoSection) {
        String sql = "INSERT INTO todo_section (name, owner) VALUES (?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, todoSection.getName());
            preparedStatement.setInt(2, todoSection.getOwner().getId());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
            throw new RuntimeException("Can't add todo section");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public TodoSection[] getAllTodoSections(User user) {
        String sql = "SELECT * FROM todo_section WHERE owner = ?";
        try {
            ArrayList<TodoSection> todoSections = new ArrayList<>();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, user.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                TodoSection todoSection = new TodoSection();
                todoSection.setId(resultSet.getInt("id"));
                todoSection.setName(resultSet.getString("name"));
                todoSection.setOwner(user);
                todoSections.add(todoSection);
            }
            return todoSections.toArray(new TodoSection[0]);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean deleteTodoSection(TodoSection todoSection) {
        String sql = "DELETE FROM todo_section WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, todoSection.getId());
            preparedStatement.executeUpdate();
            if (preparedStatement.getUpdateCount() == 1) {
                return true;
            }
            throw new RuntimeException("Can't delete todo section");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public TodoSection updateTodoSection(TodoSection todoSection) {
        String sql = "UPDATE todo_section SET name = ? WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, todoSection.getName());
            preparedStatement.setInt(2, todoSection.getId());
            preparedStatement.executeUpdate();
            if (preparedStatement.getUpdateCount() == 1) {
                return todoSection;
            }
            throw new RuntimeException("Can't update todo section");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
