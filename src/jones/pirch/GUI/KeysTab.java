package jones.pirch.GUI;

import jones.pirch.ProblemDomain.Key;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class KeysTab extends TabBase {
    private final KeyManager keyManager;
    private JList<Key> keyJList;
    private DefaultListModel<Key> keyModel;

    private JTextField inputUrlBox;
    private JTextField inputLoginBox;
    private JTextField inputPasswordBox;


    public KeysTab(KeyManager keyManager) {
        this.keyManager = keyManager;
        panel.setLayout(new BorderLayout());

        JPanel northPanel = createNorthPanel();
        panel.add(northPanel, BorderLayout.NORTH);

        JPanel centerPanel = createCenterPanel();
        panel.add(centerPanel, BorderLayout.CENTER);

        JPanel southPanel = createSouthPanel();
        panel.add(southPanel, BorderLayout.SOUTH);
    }

    private JPanel createSouthPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel title = new JLabel("Add Key", SwingConstants.CENTER);
        title.setFont(new Font("serif", Font.PLAIN, 25));
        panel.add(title, BorderLayout.NORTH);
        panel.add(innerSouthPanel(), BorderLayout.CENTER);
        panel.add(southSouthPanel(), BorderLayout.SOUTH);
        return panel;
    }

    private JPanel innerSouthPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraint = new GridBagConstraints();

        inputUrlBox = new JTextField(10);
        inputLoginBox = new JTextField(10);

        constraint.gridx = 0;
        constraint.gridy = 0;
        panel.add(new JLabel("URL: "), constraint);
        constraint.gridy = 1;
        panel.add(new JLabel("Login: "), constraint);
        constraint.gridy = 2;
        panel.add(new JLabel("Password: "), constraint);

        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.weightx = 1;
        constraint.gridx = 1;
        constraint.gridy = 0;
        panel.add(inputUrlBox, constraint);
        constraint.gridy = 1;
        panel.add(inputLoginBox, constraint);
        constraint.gridy = 2;
        panel.add(passwordPanel(), constraint);
        constraint.gridy = 3;
        return panel;
    }

    private JPanel passwordPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        inputPasswordBox = new JTextField(10);
        JButton generatorButton = new JButton("Generate Password");
        generatorButton.addActionListener( new GenerateKeyListener());
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1;
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(inputPasswordBox, constraints);
        constraints.gridx = 1;
        constraints.weightx = 0;
        panel.add(generatorButton, constraints);
        return panel;
    }

    private JPanel southSouthPanel() {
        JPanel panel = new JPanel(new GridLayout(0, 2));
        JButton addKey = new JButton("Add Key");
        addKey.addActionListener(new AddKeyListener());
        JButton removeKey = new JButton("Remove Key");
        removeKey.addActionListener(new RemoveKeyListener());
        panel.add(addKey);
        panel.add(removeKey);
        return panel;
    }

    private JPanel createCenterPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        keyModel = new DefaultListModel<>();// possible to skip this line ?
        keyJList = new JList<>(keyModel);
        keyJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(keyJList);
        keyModel.clear();
        keyModel.addAll(keyManager.keyList);

//        keyJList.addListSelectionListener(e -> { // do we want this?
//            Key selected = keyModel.get(keyJList.getSelectedIndex());
//            inputUrlBox.setText(selected.getUrl());
//            inputLoginBox.setText(selected.getLogin());
//            inputPasswordBox.setText(selected.getPassword());
//        }
//        );

        panel.add(scrollPane);
        return panel;
    }

    private JPanel createNorthPanel() {
        JPanel panel = new JPanel();
        JLabel title = new JLabel("Keys", SwingConstants.CENTER);
        title.setFont(new Font("serif", Font.PLAIN, 25));
        panel.add(title);
        return panel;
    }

    private class AddKeyListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("add button pressed.");
            String url = inputUrlBox.getText();
            String login = inputLoginBox.getText();
            String password = inputPasswordBox.getText();
            if (!(url.isEmpty() || login.isEmpty() || password.isEmpty())) {
                keyManager.addNewKey(new Key(url, login, password));
            } else {
                JOptionPane.showConfirmDialog(null, "Hey dummy you forgot to fill in the information", "Do better next time", JOptionPane.DEFAULT_OPTION);
            }
            keyModel.clear();
            keyModel.addAll(keyManager.keyList);
        }
    }

    private class RemoveKeyListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("remove button pressed.");
            if (keyJList.getSelectedIndex() != -1 ) {
                keyManager.removeKey(keyJList.getSelectedValue(), keyJList.getSelectedIndex());
            } else {
                JOptionPane.showConfirmDialog(null, "Hey dummy you forgot to click a key to remove", "Do better next time", JOptionPane.DEFAULT_OPTION);
            }
            keyModel.clear();
            keyModel.addAll(keyManager.keyList);
        }
    }

    private class GenerateKeyListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("generate button pressed");
            inputPasswordBox.setText(KeyManager.generateKey());
        }
    }
}
