package restaurant.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class KitchenManager extends Frame {

    @Override
    public void actionPerformed(ActionEvent e) {
        setFrame("Kitchen Manager");
        setMainPanel();

        JPanel cooksPanel = new JPanel(new GridBagLayout());
        JLabel cooksLabel = new JLabel();
        cooksLabel.setHorizontalAlignment(JLabel.CENTER);
        cooksPanel.add(cooksLabel, gbc);

    }
}
