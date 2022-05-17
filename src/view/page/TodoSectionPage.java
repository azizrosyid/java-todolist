package view.page;

import controller.TodoSection.TodoSectionController;
import entity.TodoSection;
import view.View;
import view.component.TodoSectionButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class TodoSectionPage implements Page {
    private final TodoSectionController todoSectionController;
    private View view;
    private JPanel mainPanel;
    private JPanel todoListPanel;
    private JButton addButton;
    private JButton editButton;
    private boolean isEditing;
    private JLabel titleLabel;

    public TodoSectionPage(TodoSectionController todoSectionController) {
        this.createUIComponents();
        this.createActionListeners();
        this.todoSectionController = todoSectionController;
    }

    @Override
    public JPanel getPanel() {
        return this.mainPanel;
    }

    @Override
    public void createUIComponents() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setBackground(Color.WHITE);

        this.titleLabel = new JLabel("Todolist");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 40));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(titleLabel, new GridBagConstraints(0, 0, 1, 1, 1, 2, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

        JPanel actionPanel = new JPanel();
        actionPanel.setLayout(new BoxLayout(actionPanel, BoxLayout.X_AXIS));
        actionPanel.setBackground(Color.WHITE);

        actionPanel.add(Box.createHorizontalGlue());

        this.addButton = new JButton("Tambah");
        addButton.setFont(new Font("Arial", Font.BOLD, 20));
        addButton.setBackground(Color.WHITE);
        addButton.setSize(new Dimension(100, 100));
        addButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        actionPanel.add(addButton);

        this.editButton = new JButton("Update");
        editButton.setFont(new Font("Arial", Font.BOLD, 20));
        editButton.setBackground(Color.WHITE);
        editButton.setSize(new Dimension(100, 100));
        editButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        actionPanel.add(editButton);

        actionPanel.add(Box.createHorizontalGlue());


        mainPanel.add(actionPanel, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

        this.todoListPanel = new JPanel();
        todoListPanel.setLayout(new GridBagLayout());
        todoListPanel.setBackground(Color.WHITE);


        JScrollPane scrollPane = new JScrollPane(todoListPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        mainPanel.add(scrollPane, new GridBagConstraints(0, 2, 1, 1, 1, 5, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

        JButton backButton = new JButton("Logout");
        backButton.setFont(new Font("Arial", Font.BOLD, 20));
        backButton.setBackground(Color.WHITE);
        backButton.addActionListener(e -> {
            view.route("login");
        });

        mainPanel.add(backButton, new GridBagConstraints(0, 7, 1, 1, 1, 0.2, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
    }

    @Override
    public void createActionListeners() {
        this.addButton.addActionListener(e -> {
            view.route("addTodoSection");
        });

        this.editButton.addActionListener(e -> {
            if (this.isEditing) {
                this.isEditing = false;
                this.titleLabel.setText("Todolist");
            } else {
                this.isEditing = true;
                this.titleLabel.setText("Todolist (Edit Mode)");
            }
        });
    }

    @Override
    public void setView(View view) {
        this.view = view;
    }

    @Override
    public void pageChanged() {
        this.todoListPanel.removeAll();

        ActionListener todoButtonListener = e -> {
            TodoSectionButton todoSectionButton = (TodoSectionButton) e.getSource();
            TodoSection todoSection = todoSectionButton.getTodoSection();
            this.view.addDataView("idTodoSection", String.valueOf(todoSection.getId()));
            this.view.addDataView("todoSectionName", todoSection.getName());
            if (this.isEditing) {
                this.isEditing = false;
                this.titleLabel.setText("Todolist");
                this.view.route("editTodoSection");
            } else {
                this.view.route("todoList");
            }
        };

        TodoSection[] todoSections = this.todoSectionController.getAllTodoSections(view.getUserLoggedIn());
        for (TodoSection todoSection : todoSections) {
            TodoSectionButton todoSectionButton = new TodoSectionButton(todoSection.getName(), todoSection);
            todoSectionButton.addActionListener(todoButtonListener);
            this.todoListPanel.add(todoSectionButton);
        }

        this.todoListPanel.revalidate();
    }
}
