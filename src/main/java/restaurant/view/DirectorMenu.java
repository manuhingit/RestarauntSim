package restaurant.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class DirectorMenu {

    public DirectorMenu() {
        JFrame frame = new JFrame();
        frame.setTitle("Director's restaurant.Tablet");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(new Dimension(350, 400));
        frame.setResizable(false);

        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 0, 10, 0);

        JPanel buttons = new JPanel(new GridBagLayout());
        JButton adManagerButton = new JButton("Ad manager");
        adManagerButton.addActionListener(new AdManager());
        buttons.add(adManagerButton, gbc);

        buttons.add(new JButton("Kitchen manager"), gbc);
        buttons.add(new JButton("Statistics"), gbc);
        buttons.add(new JButton("restaurant.Restaurant simulation"), gbc);
        gbc.weighty = 1;

        panel.add(buttons, gbc);

        frame.add(panel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
