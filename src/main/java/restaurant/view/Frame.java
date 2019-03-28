package restaurant.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class Frame implements ActionListener {

    static final GridBagConstraints gbc = new GridBagConstraints();
    JFrame frame = new JFrame();
    JPanel panel = new JPanel();

    static {
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 0, 10, 0);
    }

    void setFrame(String title) {
        frame.setTitle(title);
        frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        frame.pack();
        frame.setSize(new Dimension(350, 400));
        frame.setResizable(true);
    }

    void setFrame(String title, int width, int height, boolean isResizable) {
        frame.setTitle(title);
        frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        frame.pack();
        frame.setSize(new Dimension(width, height));
        frame.setResizable(isResizable);
    }

    void setMainPanel() {
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.setLayout(new GridBagLayout());
    }

    void finishFrame() {
        frame.add(panel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

}
