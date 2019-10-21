package org.auk.ui;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class MainFrame extends JFrame {

    public MainFrame() {
        initUI();
        init();
    }

    private void init() {
        setTitle("TechUp JAVA");
        setSize(640, 480);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void initUI() {
        var label = new JLabel("Hello...");
        var nameField = new JTextField(10);
        var clickBtn = new JButton("Greeting");

        clickBtn.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = "" + nameField.getText().trim();
                if (!name.isEmpty()) {
                    label.setText("Hello, " + name);
                }
            }
        });

        var pane = getContentPane();
        pane.add(label);
        pane.add(nameField);
        pane.add(clickBtn);

        var groupLayout = new GroupLayout(pane);
        groupLayout.setAutoCreateGaps(true);
        groupLayout.setAutoCreateContainerGaps(true);

        GroupLayout.SequentialGroup hGroup = groupLayout.createSequentialGroup();
        hGroup.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.TRAILING).addComponent(label).addComponent(clickBtn))
                .addGroup(groupLayout.createParallelGroup()
                        .addComponent(nameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE));
        groupLayout.setHorizontalGroup(hGroup);

        GroupLayout.SequentialGroup vGroup = groupLayout.createSequentialGroup();
        vGroup.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).
                addComponent(label)
                .addComponent(nameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                        GroupLayout.PREFERRED_SIZE))
                .addGroup(groupLayout.createParallelGroup().addComponent(clickBtn));

        groupLayout.setVerticalGroup(vGroup);

        // Add JMenuBar
        var menuBar = new JMenuBar();
        var menu = new JMenu("File");
        var newMenuItem = new JMenuItem("New");
        menu.add(newMenuItem);
        menuBar.add(menu);

        setJMenuBar(menuBar);
        pack();
        pane.setLayout(groupLayout);
    }
}
