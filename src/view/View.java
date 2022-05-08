package view;

import entity.User;
import view.page.LoginPage;
import view.page.Page;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

import static java.lang.Thread.sleep;

public class View {
    private JPanel mainPanel;
    private CardLayout cardLayout;
    private User userLoggedIn;
    private final HashMap<String, String> dataView = new HashMap<>();
    private final HashMap<String, Page> pages = new HashMap<>();

    public View() {
        this.initComponents();
    }

    public void start() {
        this.route("login");
    }

    public void initComponents() {
        JFrame frame = new JFrame("Todo List App");
        mainPanel = new JPanel();
        cardLayout = new CardLayout();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setContentPane(mainPanel);
        mainPanel.setLayout(cardLayout);
    }

    public void route(String path) {
        cardLayout.show(mainPanel, path);
        Page page = pages.get(path);
        page.pageChanged();
    }

    public void addPage(String path, Page page) {
        page.setView(this);
        mainPanel.add(page.getPanel(), path);
        pages.put(path, page);
    }

    public User getUserLoggedIn() {
        return userLoggedIn;
    }

    public void setUserLoggedIn(User userLoggedIn) {
        this.userLoggedIn = userLoggedIn;
    }

    public void addDataView(String key, String value) {
        dataView.put(key, value);
    }

    public String getDataView(String key) {
        return dataView.get(key);
    }
}
