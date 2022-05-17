package view.page;

import controller.TodoSection.TodoSectionController;
import entity.TodoList;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import view.View;


public class AddTodoSectionPage implements Page {

    private JPanel mainPanel;
    private JTextField nameTextField;
    private JButton addButton;
    private JButton backButton;
    private View view;
    private final TodoSectionController todoSectionController;

    public AddTodoSectionPage(TodoSectionController todoSectionController){
        createUIComponents();
        createActionListeners();
        this.todoSectionController = todoSectionController;
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

        JLabel sectionLabel = new JLabel("Tambah Todolist");
        sectionLabel.setFont(new Font("Arial", Font.BOLD, 40));
        sectionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(sectionLabel, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

        JPanel nameSection = new JPanel();
        nameSection.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        nameSection.setBackground(Color.WHITE);

        JLabel nameLabel = new JLabel("Nama");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        nameLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 30));
        nameSection.add(nameLabel);

        this.nameTextField = new JTextField();
        nameTextField.setFont(new Font("Arial", Font.PLAIN, 20));
        nameTextField.setPreferredSize(new Dimension(300, 50));
        nameSection.add(nameTextField);

        mainPanel.add(nameSection, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

        JPanel passwordPanel = new JPanel();
        passwordPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        passwordPanel.setBackground(Color.WHITE);


        this.addButton = new JButton("Add");
        addButton.setFont(new Font("Arial", Font.BOLD, 20));
        addButton.setPreferredSize(new Dimension(200, 30));
        addButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        addButton.setBackground(Color.WHITE);
        addButton.setBorder(BorderFactory.createEmptyBorder(30, 30, 20, 30));
        mainPanel.add(addButton, new GridBagConstraints(0, 2, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

        this.backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.BOLD, 20));
        backButton.setPreferredSize(new Dimension(200, 30));
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.setBackground(Color.WHITE);
        backButton.setBorder(BorderFactory.createEmptyBorder(30, 30, 20, 30));
        mainPanel.add(backButton, new GridBagConstraints(0, 3, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
    }

    @Override
    public void createActionListeners() {
        this.addButton.addActionListener(e -> {
            String todolist = nameTextField.getText();
            if (todolist.isEmpty()) {
                JOptionPane.showMessageDialog(mainPanel, "Please enter a todolist");
            } else {
                try {
                    todoSectionController.addTodoSection(todolist, view.getUserLoggedIn());
                    JOptionPane.showMessageDialog(mainPanel, "Berhasil menambahkan todo section!");
                    this.view.route("todoSection");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(mainPanel, ex.getMessage());
                }
            }
        });
        this.backButton.addActionListener(e -> {
            view.route("todoSection");
        });
    }

    @Override
    public void setView(View view) {
        this.view = view;
    }

    @Override
    public void pageChanged() {

    }

}
