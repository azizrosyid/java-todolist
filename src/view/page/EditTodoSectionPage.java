package view.page;

import controller.TodoSection.TodoSectionController;
import entity.TodoSection;
import view.View;

import javax.swing.*;
import java.awt.*;

public class EditTodoSectionPage implements Page {
    private final TodoSectionController todoSectionController;
    private JPanel mainPanel;
    private View view;
    private JLabel titleLabel;
    private JButton backButton;
    private JTextField nameTextField;
    private JButton deleteButton;
    private JButton saveButton;

    public EditTodoSectionPage(TodoSectionController todoSectionController) {
        this.todoSectionController = todoSectionController;
        this.createUIComponents();
        this.createActionListeners();
    }

    @Override
    public JPanel getPanel() {
        return mainPanel;
    }

    @Override
    public void createUIComponents() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setBackground(Color.WHITE);

        this.titleLabel = new JLabel("Edit Todolist");
        this.titleLabel.setFont(new Font("Arial", Font.BOLD, 40));
        this.titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        mainPanel.add(titleLabel, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));


        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.X_AXIS));
        inputPanel.setBackground(Color.WHITE);

        JLabel nameLabel = new JLabel("Name");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 20));

        this.nameTextField = new JTextField();
        nameTextField.setFont(new Font("Arial", Font.PLAIN, 20));
        nameTextField.setPreferredSize(new Dimension(200, 30));

        inputPanel.add(Box.createHorizontalStrut(200));
        inputPanel.add(nameLabel);
        inputPanel.add(Box.createHorizontalStrut(100));
        inputPanel.add(nameTextField);
        inputPanel.add(Box.createHorizontalStrut(200));

        mainPanel.add(inputPanel, new GridBagConstraints(0, 1, 1, 1, 1, 0.5, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));


        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.setBackground(Color.WHITE);

        this.saveButton = new JButton("Save");
        saveButton.setFont(new Font("Arial", Font.BOLD, 20));
        saveButton.setPreferredSize(new Dimension(200, 30));
        saveButton.setBackground(Color.WHITE);

        this.deleteButton = new JButton("Delete");
        deleteButton.setFont(new Font("Arial", Font.BOLD, 20));
        deleteButton.setPreferredSize(new Dimension(200, 30));
        deleteButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        deleteButton.setBackground(Color.RED);

        buttonPanel.add(Box.createHorizontalGlue());
        buttonPanel.add(saveButton);
        buttonPanel.add(Box.createHorizontalStrut(100));
        buttonPanel.add(deleteButton);
        buttonPanel.add(Box.createHorizontalGlue());

        mainPanel.add(buttonPanel, new GridBagConstraints(0, 2, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));


        this.backButton = new JButton("Back");
        this.backButton.setFont(new Font("Arial", Font.BOLD, 20));
        this.backButton.setPreferredSize(new Dimension(200, 30));
        this.backButton.setBackground(Color.WHITE);

        mainPanel.add(backButton, new GridBagConstraints(0, 3, 1, 1, 1, 0.3, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

    }

    @Override
    public void createActionListeners() {
        this.backButton.addActionListener(e -> {
            this.view.route("todoSection");
        });

        this.saveButton.addActionListener(e -> {
            String updatedName = this.nameTextField.getText();
            if (updatedName.equals("")) {
                JOptionPane.showMessageDialog(this.mainPanel, "Please enter a name", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    int idTodoSection = Integer.parseInt(this.view.getDataView("idTodoSection"));
                    this.todoSectionController.updateTodoSection(idTodoSection, updatedName);
                    JOptionPane.showMessageDialog(this.mainPanel, "Successfully updated todolist name!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    this.view.route("todoSection");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this.mainPanel, "Error updating todolist name!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        this.deleteButton.addActionListener(e -> {
            try {

                int idTodoSection = Integer.parseInt(this.view.getDataView("idTodoSection"));
                this.todoSectionController.deleteTodoSection(idTodoSection);
                JOptionPane.showMessageDialog(this.mainPanel, "Successfully deleted todolist!", "Success", JOptionPane.INFORMATION_MESSAGE);
                this.view.route("todoSection");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this.mainPanel, "Error deleting todolist!", "Error", JOptionPane.ERROR_MESSAGE);
            }

        });

    }

    @Override
    public void setView(View view) {
        this.view = view;
    }

    @Override
    public void pageChanged() {
        String name = this.view.getDataView("todoSectionName");
        this.nameTextField.setText(name);
    }
}
