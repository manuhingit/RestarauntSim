package restaurant.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class DirectorMenu extends Frame {

    public DirectorMenu() {
        if (!isInit) {
            setFrame("Director's Tablet", 350, 400, false);

            JPanel buttons = new JPanel(new GridBagLayout());
            JButton adManagerButton = new JButton("Ad manager");
            adManagerButton.addActionListener(new AdManager());
            buttons.add(adManagerButton, gbc);

            JButton kitchenManagerButton = new JButton("Kitchen manager");
            kitchenManagerButton.addActionListener(new KitchenManager());
            buttons.add(kitchenManagerButton, gbc);

            JButton statisticButton = new JButton("Statistic");
            statisticButton.addActionListener(new Statistic());
            buttons.add(statisticButton, gbc);

            panel.add(buttons, gbc);
        }

        frame.setVisible(true);
    }
}
