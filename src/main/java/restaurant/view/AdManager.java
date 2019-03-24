package restaurant.view;

import restaurant.ad.Advertisement;
import restaurant.ad.AdvertisementStorage;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdManager implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        JFrame frame = new JFrame();
        frame.setTitle("Ads Manager");
        frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        frame.pack();
        frame.setSize(new Dimension(350, 400));

        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 0, 10, 0);

        JPanel ads = new JPanel(new GridBagLayout());
        JLabel label = new JLabel("Ads");
        label.setHorizontalAlignment(JLabel.CENTER);
        ads.add(label, gbc);

        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (Advertisement ad : AdvertisementStorage.getInstance().list()) listModel.addElement(ad.getName() + " - " + ad.getDuration() / 60 + " min | " + ad.getHits() + "/" + ad.getInitialAmount() + " hits");

        JList<String> list = new JList<>(listModel);
        list.setBorder(new EmptyBorder(20, 20, 20, 20));
        list.setFixedCellHeight(30);
        ads.add(list, gbc);

        JButton button = new JButton("New restaurant.ad");
        button.addActionListener(new NewAdd());
        ads.add(button, gbc);

        panel.add(ads);

        frame.add(panel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public class NewAdd implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JFrame frame = new JFrame();
            frame.setTitle("New Add");
            frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
            frame.pack();
            frame.setSize(new Dimension(350, 400));

            JPanel panel = new JPanel();
            panel.setBorder(new EmptyBorder(10, 10, 10, 10));
            panel.setLayout(new GridBagLayout());

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            gbc.anchor = GridBagConstraints.CENTER;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.insets = new Insets(10, 0, 10, 0);

            JPanel form = new JPanel(new GridBagLayout());
            JLabel label = new JLabel("Name");
            label.setHorizontalAlignment(JLabel.CENTER);
            form.add(label, gbc);

            JTextField name = new JTextField();
            form.add(name, gbc);

            JLabel label1 = new JLabel("Amount");
            label1.setHorizontalAlignment(JLabel.CENTER);
            form.add(label1, gbc);

            JLabel label2 = new JLabel("Duration (sec)");
            label2.setHorizontalAlignment(JLabel.CENTER);
            form.add(label2, gbc);

            JButton button = new JButton("Add");
            form.add(button, gbc);

            panel.add(form);

            frame.add(panel);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        }
    }
}
