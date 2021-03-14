package ui;

import model.WorkoutBank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LookupPanel extends JPanel {
    private JPanel panel;
    private GroupLayout layout;
    private JLabel term;
    private JTextField uterm;
    private JButton submit;
    private JTextArea results;
    private WorkoutBank log;

    public LookupPanel(WorkoutBank log) {
        this.log = log;
        panel = new JPanel();
        layout = new GroupLayout(panel);
        panel.setLayout(layout);
        term = new JLabel("Name or Date (mm/dd/yy)  ");
        uterm = new JTextField(18);
        results = new JTextArea();
        results.setVisible(false);
        submit = new JButton("Submit");
        submit.addActionListener(new ClickHandler());
        setHorizontal();
        setVertical();
        add(panel, BorderLayout.EAST);
        validate();
    }

    public void setHorizontal() {
        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addComponent(term)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(uterm).addComponent(submit).addComponent(results)));
    }

    public void setVertical() {
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(term).addComponent(uterm))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addComponent(submit))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(results)));
    }

    private class ClickHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            results.setText(log.lookup(uterm.getText()));
            results.setVisible(true);
            validate();
        }
    }
}
