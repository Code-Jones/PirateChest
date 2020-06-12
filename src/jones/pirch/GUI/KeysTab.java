package jones.pirch.GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class KeysTab extends TabBase{
    KeyManager keyManager;
    JTextField inputUrlBox;
    JTextField inputLoginBox;
    JTextField inputPasswordBox;

    public KeysTab(KeyManager keyManager){
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
        JButton findFlights = new JButton("Add Key");
//        try {
////            reservationModel.clear();
////            findFlights.addActionListener(new FindReservationsListener());
////        } catch (Exception e) {
////            System.out.println("Input fields are empty");
////        }

        panel.add(title, BorderLayout.NORTH);
        panel.add(innerSouthPanel(), BorderLayout.CENTER);
        panel.add(findFlights, BorderLayout.SOUTH);
        return panel;
    }

    private JPanel innerSouthPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraint = new GridBagConstraints();

        inputUrlBox = new JTextField(10);
        inputLoginBox = new JTextField(10);
        inputPasswordBox = new JTextField(10);

        constraint.gridx = 0;
        constraint.gridy = 0;
        panel.add( new JLabel("URL: "), constraint);
        constraint.gridy = 1;
        panel.add( new JLabel("Login: "), constraint);
        constraint.gridy = 2;
        panel.add( new JLabel("Password: "), constraint);

        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.weightx = 1;
        constraint.gridx = 1;
        constraint.gridy = 0;
        panel.add(inputUrlBox, constraint);
        constraint.gridy = 1;
        panel.add(inputLoginBox, constraint);
        constraint.gridy = 2;
        panel.add(inputPasswordBox, constraint);
        return panel;
    }

    private JPanel createCenterPanel() {
        JPanel panel = new JPanel();
        panel.setLayout( new BorderLayout());
        panel.setBorder( new EmptyBorder(10, 10, 10, 10));

        /*
        reservationModel = new DefaultListModel<>();
        reservationList = new JList<>(reservationModel);
        reservationList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);*/
        JScrollPane scrollPane = new JScrollPane(/* this.list */);
        // reservationList.addListSelectionListener( new ReservationsTab.MyListSelectionListener());

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
}
