package restaurant.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class Frame implements ActionListener {

    boolean isInit = false;

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
        isInit = true;
        setMainPanel();
    }

    void setFrame(String title, int width, int height, boolean isResizable) {
        frame = new JFrame();
        frame.setTitle(title);
        frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        frame.pack();
        frame.setSize(new Dimension(width, height));
        frame.setResizable(isResizable);
        isInit = true;
        setMainPanel();
    }

    private void setMainPanel() {
        panel = new JPanel();
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.setLayout(new GridBagLayout());
        frame.add(panel);
        frame.setLocationRelativeTo(null);
    }

    JList setList(DefaultListModel<String> listModel) {
        JList<String> list = new JList(listModel);
        list.setBorder(new EmptyBorder(20, 20, 20, 20));
        list.setFixedCellHeight(20);
        list.setVisibleRowCount(5);
        list.setLayoutOrientation(JList.VERTICAL);
        list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        return list;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

}
