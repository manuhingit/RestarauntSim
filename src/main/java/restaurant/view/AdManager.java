package restaurant.view;

import restaurant.ad.Advertisement;
import restaurant.ad.AdvertisementStorage;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdManager implements ActionListener {

    private static final GridBagConstraints gbc = new GridBagConstraints();

    static {
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 0, 10, 0);
    }

    private JFrame setFrame(String title, int width, int height, boolean isResizable) {
        JFrame frame = new JFrame();
        frame.setTitle(title);
        frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        frame.pack();
        frame.setSize(new Dimension(width, height));
        frame.setResizable(isResizable);
        return frame;
    }

    private JPanel setMainPanel() {
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.setLayout(new GridBagLayout());
        return panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFrame frame = setFrame("Ads Manager", 350, 400, true);

        JPanel panel = setMainPanel();

        JPanel ads = new JPanel(new GridBagLayout());
        JLabel label = new JLabel("Ads");
        label.setHorizontalAlignment(JLabel.CENTER);
        ads.add(label, gbc);

        DefaultListModel<String> listModel = new DefaultListModel<>();
        updateListModel(listModel);

        JList<String> list = new JList<>(listModel);
        list.setBorder(new EmptyBorder(20, 20, 20, 20));
        list.setFixedCellHeight(30);
        ads.add(list, gbc);

        JButton button = new JButton("New ad");
        button.addActionListener(new NewAdd());
        ads.add(button, gbc);

        panel.add(ads);

        frame.add(panel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        Thread thread = new Thread(() -> {
            while (frame.isVisible()) {
                updateListModel(listModel);
                try {
                    Thread.sleep(500);
                } catch (Exception e1) {
                    break;
                }
            }
        });

        thread.setDaemon(true);
        thread.start();
    }

    private void updateListModel(DefaultListModel<String> listModel) {
        listModel.clear();
        for (Advertisement ad : AdvertisementStorage.getInstance().list())
            listModel.addElement(ad.getName() + " - "
                + (ad.getDuration() / 60 > 0 ? ad.getDuration() / 60 + " min | " : ad.getDuration() + " sec | ")
                + ad.getHits() + "/"
                + ad.getInitialAmount() + " hits");
    }

    public class NewAdd implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JFrame frame = setFrame("New Add", 300, 350, false);

            JPanel panel = setMainPanel();

            JPanel form = new JPanel(new GridBagLayout());
            JLabel label = new JLabel("Name");
            label.setHorizontalAlignment(JLabel.CENTER);
            form.add(label, gbc);

            JTextField name = new JTextField();
            form.add(name, gbc);

            JLabel label1 = new JLabel("Amount");
            label1.setHorizontalAlignment(JLabel.CENTER);
            form.add(label1, gbc);

            JTextField amount = new JTextField();
            form.add(amount, gbc);

            JLabel label2 = new JLabel("Duration (sec)");
            label2.setHorizontalAlignment(JLabel.CENTER);
            form.add(label2, gbc);

            JTextField duration = new JTextField();
            form.add(duration, gbc);

            JButton button = new JButton("Add");
            button.addActionListener((ActionEvent event) -> {
                String nameField = name.getText();
                if (nameField.length() < 1) {
                    JOptionPane.showMessageDialog(frame, "Name cannot be empty.");
                    return;
                }

                int initialAmount = 0;
                try {
                    initialAmount = Integer.parseInt(amount.getText());
                } catch (Exception ignored) {

                }

                if (initialAmount < 1) {
                    JOptionPane.showMessageDialog(frame, "Amount has an illegal value.");
                    return;
                }

                int durationField = 0;
                try {
                    durationField = Integer.parseInt(duration.getText());
                } catch (Exception ignored) {

                }

                if (durationField < 1) {
                    JOptionPane.showMessageDialog(frame, "Duration has an illegal value.");
                    return;
                }

                AdvertisementStorage.getInstance().add(new Advertisement(null, nameField, initialAmount, 0, durationField));
                frame.setVisible(false);
            });
            form.add(button, gbc);

            panel.add(form);

            frame.add(panel);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        }
    }
}
