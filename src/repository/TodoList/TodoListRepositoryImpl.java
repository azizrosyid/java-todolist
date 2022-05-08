package repository.TodoList;

import entity.TodoList;
import entity.TodoSection;

import java.sql.*;
import java.util.ArrayList;

public class TodoListRepositoryImpl implements TodoListRepository {

    private final Connection connection;

    public TodoListRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public TodoList addTodoList(TodoList todoList) {
        String sql = "INSERT INTO todolist (id_section, todo) VALUES (?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, todoList.getIdSection());
            preparedStatement.setString(2, todoList.getTodo());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                todoList.setId(resultSet.getInt(1));
                return todoList;
            }
            throw new RuntimeException("TodoList not added");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean removeTodoList(TodoList todoList) {
        String sql = "DELETE FROM todolist WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, todoList.getId());
            preparedStatement.executeUpdate();
            if (preparedStatement.getUpdateCount() == 1) {
                return true;
            }
            throw new RuntimeException("TodoList not removed");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateTodoList(TodoList todoList) {

    }

    @Override
    public TodoList[] getAllTodoListsByUser(TodoSection todoSection) {
        String sql = "SELECT * FROM todolist WHERE id_section = ?";
        ArrayList<TodoList> todoLists = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, todoSection.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String todo = resultSet.getString("todo");
                TodoList todoList = new TodoList(id, todo);
                todoLists.add(todoList);
            }
            return todoLists.toArray(new TodoList[0]);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
