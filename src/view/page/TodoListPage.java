package view.page;

import controller.TodolistController.TodolistController;
import entity.TodoList;
import view.View;

import javax.swing.*;
import java.awt.*;

public class TodoListPage implements Page {
    private final TodolistController todoListController;
    private JPanel mainPanel;
    private View view;
    private JLabel titleLabel;
    private JButton addButton;
    private JTextField addTodoTextField;
    private JPanel todoListPanel;

    private int lastGridY = 0;

    public TodoListPage(TodolistController todolistController) {
        createUIComponents();
        createActionListeners();
        this.todoListController = todolistController;
    }

    @Override
    public JPanel getPanel() {
        return mainPanel;
    }

    @Override
    public void createUIComponents() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());

        this.titleLabel = new JLabel("Todo List");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 40));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBackground(Color.WHITE);

        mainPanel.add(titleLabel, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

        this.todoListPanel = new JPanel();
        todoListPanel.setLayout(new GridBagLayout());
        todoListPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        todoListPanel.setBackground(Color.WHITE);


        JScrollPane scrollPane = new JScrollPane(todoListPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBackground(Color.WHITE);

        mainPanel.add(scrollPane, new GridBagConstraints(0, 1, 1, 1, 1, 3, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        buttonPanel.setBackground(Color.WHITE);


        this.addTodoTextField = new JTextField();
        addTodoTextField.setFont(new Font("Arial", Font.PLAIN, 20));
        addTodoTextField.setHorizontalAlignment(SwingConstants.CENTER);

        this.addButton = new JButton("Add");
        addButton.setFont(new Font("Arial", Font.PLAIN, 20));
        addButton.setBackground(Color.WHITE);
        addButton.setForeground(Color.BLACK);

        buttonPanel.add(addTodoTextField);
        buttonPanel.add(addButton);

        mainPanel.add(buttonPanel, new GridBagConstraints(0, 4, 1, 1, 1, 0.1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.PLAIN, 20));
        backButton.setBackground(Color.WHITE);
        backButton.setForeground(Color.BLACK);
        backButton.addActionListener(e -> view.route("todoSection"));

        mainPanel.add(backButton, new GridBagConstraints(0, 5, 1, 1, 1, 0.1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
    }

    @Override
    public void createActionListeners() {
        this.addButton.addActionListener(e -> {
            String todo = addTodoTextField.getText();
            if (todo.isEmpty()) {
                JOptionPane.showMessageDialog(mainPanel, "Please enter a todo");
            } else {
                try {
                    TodoList todoListInserted = todoListController.addTodolist(todo, Integer.parseInt(view.getDataView("idTodoSection")));
                    addTodoTextField.setText("");

                    JPanel todoPanel = createTodoListPanel(todoListInserted);
                    todoListPanel.add(todoPanel, new GridBagConstraints(0, lastGridY, 1, 1, 1, 1, GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 10));
                    lastGridY++;
                    todoListPanel.revalidate();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(mainPanel, ex.getMessage());
                }
            }
        });
    }

    @Override
    public void setView(View view) {
        this.view = view;
    }

    private JPanel createTodoListPanel(TodoList todoList) {
        JPanel todoPanel = new JPanel();
        todoPanel.setBackground(Color.WHITE);

        JLabel todoLabel = new JLabel(todoList.getTodo());
        todoLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        todoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        todoLabel.setBackground(Color.WHITE);
        todoPanel.add(todoLabel);

        JButton deleteButton = new JButton("Delete");
        deleteButton.setFont(new Font("Arial", Font.PLAIN, 20));
        deleteButton.setBackground(Color.WHITE);
        deleteButton.setForeground(Color.RED);
        deleteButton.addActionListener(e -> {
            try {
                boolean isSuccess = todoListController.removeTodolist(todoList);
                if (isSuccess) {
                    JButton button = (JButton) e.getSource();
                    JPanel panel = (JPanel) button.getParent();
                    todoListPanel.remove(panel);
                    todoListPanel.revalidate();
                    todoListPanel.repaint();
                } else {
                    JOptionPane.showMessageDialog(mainPanel, "Something went wrong");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(mainPanel, ex.getMessage());
            }
        });
        todoPanel.add(deleteButton);
        return todoPanel;
    }

    @Override
    public void pageChanged() {
        todoListPanel.removeAll();
        this.titleLabel.setText("Todo List - " + view.getDataView("todoSectionName"));

        TodoList[] todoLists = todoListController.getAllTodolists(Integer.parseInt(view.getDataView("idTodoSection")));
        for (TodoList todoList : todoLists) {
            JPanel todoPanel = createTodoListPanel(todoList);

            todoListPanel.add(todoPanel, new GridBagConstraints(0, lastGridY, 1, 1, 1, 1, GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 10));
            lastGridY++;
        }

    }
}
