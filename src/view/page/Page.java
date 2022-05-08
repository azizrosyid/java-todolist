package view.page;

import view.View;

import javax.swing.*;

public interface Page {
    JPanel getPanel();

    void createUIComponents();

    void createActionListeners();

    void setView(View view);

    void pageChanged();
}
