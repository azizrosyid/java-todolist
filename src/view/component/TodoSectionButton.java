package view.component;

import entity.TodoSection;

import javax.swing.*;
import java.awt.*;

public class TodoSectionButton extends JButton {

    private final TodoSection todoSection;

    public TodoSectionButton(String text, TodoSection todoSection) {
        super(text);
        setFont(new Font("Arial", Font.PLAIN, 20));
        setBackground(Color.WHITE);
        setForeground(Color.BLACK);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setPreferredSize(new Dimension(200, 50));
        this.todoSection = todoSection;
    }

    public TodoSection getTodoSection() {
        return todoSection;
    }
}
