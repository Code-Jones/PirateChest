package jones.pirch.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPage extends JFrame {
    private static final String TAB_KEYS = "keys";
    private static final String TAB_SETTINGS = "settings";
    final int WINDOW_H = 500;
    final int WINDOW_W = 500;
    private CardLayout cardLayout;
    private final JPanel centerPanel;

    private final KeyManager keyManager;
    private final SettingsManager settingManager;

    private JButton keysButton;
    private JButton settingsButton;

    public MainPage(KeyManager keyManager, SettingsManager settingsManager) {
        this.keyManager = keyManager;
        this.settingManager = settingsManager;

        setSize(WINDOW_W, WINDOW_H);
        setTitle("PirateChest");
        setResizable(false);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel northPanel = createNorthPanel();
        add(northPanel, BorderLayout.NORTH);

        centerPanel = createCenterPanel();
        add(centerPanel, BorderLayout.CENTER);
    }

    private JPanel createCenterPanel() {
        JPanel panel = new JPanel();
        cardLayout = new CardLayout();

        panel.setLayout(cardLayout);

        TabBase keysTab = new KeysTab(this.keyManager);
        TabBase settingsTab = new SettingsTab(this.settingManager);

        panel.add(keysTab.getPanel(), TAB_KEYS);
        panel.add(settingsTab.getPanel(), TAB_SETTINGS);

        cardLayout.first(panel);
        return panel;
    }

    private JPanel createNorthPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        JPanel tabPanel = createTabPanel();
        panel.add(tabPanel, BorderLayout.SOUTH);
        return panel;
    }

    private JPanel createTabPanel() {
        JPanel tabPanel = new JPanel();

        tabPanel.setLayout(new GridLayout(1, 2));

        keysButton = new JButton("keys");
        settingsButton = new JButton("Settings");

        keysButton.addActionListener(new TabButtonActionListener());
        settingsButton.addActionListener(new TabButtonActionListener());

        tabPanel.add(keysButton);
        tabPanel.add(settingsButton);

        return tabPanel;
    }

    public void display() {
        setVisible(true);
    }

    private class TabButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == keysButton) {
                cardLayout.show(centerPanel, TAB_KEYS);
            } else if (e.getSource() == settingsButton) {
                cardLayout.show(centerPanel, TAB_SETTINGS);
            }
        }
    }
}

