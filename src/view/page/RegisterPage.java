package view.page;

import controller.UserController.UserContoller;
import entity.User;
import view.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RegisterPage implements Page {
    private final UserContoller userController;
    private JPanel mainPanel;
    private View view;
    private JPasswordField passwordTextField;
    private JTextField usernameTextField;
    private JLabel loginLabel;
    private JButton registerButton;

    public RegisterPage(UserContoller userContoller) {
        this.createUIComponents();
        this.createActionListeners();
        this.userController = userContoller;
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

        JLabel registerLabel = new JLabel("Register");
        registerLabel.setFont(new Font("Arial", Font.BOLD, 40));
        registerLabel.setForeground(Color.BLACK);
        registerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(registerLabel, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

        JPanel usernamePanel = new JPanel();
        usernamePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        usernamePanel.setBackground(Color.WHITE);

        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        usernameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        usernameLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 30));
        usernamePanel.add(usernameLabel);

        this.usernameTextField = new JTextField();
        usernameTextField.setFont(new Font("Arial", Font.PLAIN, 20));
        usernameTextField.setPreferredSize(new Dimension(200, 30));
        usernamePanel.add(usernameTextField);

        mainPanel.add(usernamePanel, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

        JPanel passwordPanel = new JPanel();
        passwordPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        passwordPanel.setBackground(Color.WHITE);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 20));
        passwordLabel.setHorizontalAlignment(SwingConstants.CENTER);
        passwordLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 30));
        passwordPanel.add(passwordLabel);

        this.passwordTextField = new JPasswordField();
        passwordTextField.setFont(new Font("Arial", Font.PLAIN, 20));
        passwordTextField.setPreferredSize(new Dimension(200, 30));
        passwordPanel.add(passwordTextField);

        mainPanel.add(passwordPanel, new GridBagConstraints(0, 2, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.Y_AXIS));
        loginPanel.setBackground(Color.WHITE);

        this.registerButton = new JButton("Register");
        registerButton.setFont(new Font("Arial", Font.BOLD, 20));
        registerButton.setPreferredSize(new Dimension(200, 30));
        registerButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        registerButton.setBackground(Color.WHITE);
        registerButton.setBorder(BorderFactory.createEmptyBorder(30, 30, 20, 30));
        loginPanel.add(registerButton);

        this.loginLabel = new JLabel("Already have an account? Login here.");
        loginLabel.setFont(new Font("Arial", Font.ITALIC, 15));
        loginLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginLabel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        loginPanel.add(loginLabel);

        mainPanel.add(loginPanel, new GridBagConstraints(0, 3, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

    }

    @Override
    public void createActionListeners() {
        this.registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameTextField.getText();
                String password = String.valueOf(passwordTextField.getPassword());

                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(mainPanel, "Please fill in all fields.");
                } else {
                    try {
                        User user = userController.register(username, password);
                        if (user != null) {
                            JOptionPane.showMessageDialog(mainPanel, "Registration successful.");
                            view.route("login");
                        }

                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(mainPanel, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        this.loginLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                view.route("login");
            }
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
