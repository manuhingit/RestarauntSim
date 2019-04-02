package restaurant.view;

import restaurant.Restaurant;
import restaurant.Tablet;
import restaurant.kitchen.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class KitchenManager extends Frame {

    private DefaultListModel<String> cooksListModel = new DefaultListModel<>();
    private DefaultListModel<String> waitersListModel = new DefaultListModel<>();
    private DefaultListModel<String> tabletsListModel = new DefaultListModel<>();
    private DefaultListModel<String> ordersListModel = new DefaultListModel<>();
    private DefaultListModel<String> dishesListModel = new DefaultListModel<>();

    private KitchenStorage storage = KitchenStorage.getInstance();

    private GridBagConstraints mainConstraints = new GridBagConstraints();

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!isInit) {
            setFrame("Kitchen Manager", 1080, 720, true);

            mainConstraints.fill = GridBagConstraints.BOTH;
            mainConstraints.insets = new Insets(10, 10, 10, 10);

            setKitchenPanel("Cooks", cooksListModel, "Add Cook", 0, 0);
            setKitchenPanel("Waiters", waitersListModel, "Add Waiter", 1, 0);
            setKitchenPanel("Tablets", tabletsListModel, "Add Tablet", 0, 1);
            setKitchenPanel("Orders", ordersListModel, "Create Order", 1, 1);
        }

        updateModelsData();
        frame.setVisible(true);

        Thread thread = new Thread(() -> {
            while (frame.isVisible()) {
                updateModelsData();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e1) {
                    break;
                }
            }
        });
        thread.start();
    }

    private void setKitchenPanel(String panelLabel, DefaultListModel<String> listModel, String buttonText, int x, int y) {
        JPanel resultPanel = new JPanel(new GridBagLayout());

        mainConstraints.gridx = x;
        mainConstraints.gridy = y;

        JLabel tabletsLabel = new JLabel(panelLabel);
        tabletsLabel.setHorizontalAlignment(JLabel.CENTER);
        resultPanel.add(tabletsLabel, gbc);
        JScrollPane scroller = new JScrollPane(setList(listModel));
        scroller.setMinimumSize(new Dimension(250, 200));
        resultPanel.add(scroller, gbc);

        JButton addTabletButton = new JButton(buttonText);
        if (buttonText.equals("Create Order")) addTabletButton.addActionListener(new CreateOrderFrame());
        else
            addTabletButton.addActionListener((e) -> {
                switch (buttonText) {
                    case "Add Cook": {
                        String cookName;
                        do {
                            cookName = JOptionPane.showInputDialog("Enter a name of new cook:");
                        } while (cookName.length() <= 0);
                        storage.addCook(new Cook(cookName));

                        break;
                    }
                    case "Add Tablet": {
                        storage.addTablet(new Tablet());
                        break;
                    }
                    case "Add Waiter": {
                        storage.addWaiter(new Waiter());
                        break;
                    }
                    default: {
                        break;
                    }
                }
            });
        resultPanel.add(addTabletButton, gbc);

        resultPanel.setPreferredSize(new Dimension(300, 400));
        panel.add(resultPanel, mainConstraints);
    }

    private void updateModelsData() {
        cooksListModel.clear();
        for (Cook cook : storage.getCooks())
            cooksListModel.addElement(cook.toString());

        waitersListModel.clear();
        for (Waiter waiter : storage.getWaiters())
            waitersListModel.addElement(waiter.toString());

        tabletsListModel.clear();
        for (Tablet tablet : storage.getTablets())
            tabletsListModel.addElement(tablet.toString());

        ordersListModel.clear();
        for (Order order : Restaurant.getOrderQueue()) ordersListModel.addElement(order.orderInfo());
    }

    public class CreateOrderFrame extends Frame {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!isInit) {
                setFrame("Create new order", 350, 400, false);

                JPanel createOrderPanel = new JPanel(new GridBagLayout());
                JLabel dishesLabel = new JLabel("List of dishes");
                dishesLabel.setHorizontalAlignment(JLabel.CENTER);
                createOrderPanel.add(dishesLabel, gbc);
                for (Dish dish : Dish.values()) dishesListModel.addElement(dish.name());
                JList list = setList(dishesListModel);
                createOrderPanel.add(new JScrollPane(list), gbc);

                JButton button = new JButton("Create Order");
                button.addActionListener((event) -> {
                    List values = list.getSelectedValuesList();
                    if (!values.isEmpty()) {
                        List<Tablet> tablets = storage.getTablets();
                        int randomTabletNumber = (int) (Math.random() * tablets.size());
                        List<Dish> dishesOrder = new ArrayList<>();
                        for (Object dish : values) {
                            String dishString = (String) dish;
                            dishesOrder.add(Dish.valueOf(dishString));
                        }
                        tablets.get(randomTabletNumber).createOrder(dishesOrder);
                        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
                    }
                });
                createOrderPanel.add(button, gbc);

                panel.add(createOrderPanel, gbc);
            }

            frame.setVisible(true);
        }
    }

}
