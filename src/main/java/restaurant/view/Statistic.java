package restaurant.view;

import restaurant.statistic.StatisticManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Map;

public class Statistic extends Frame {

    private StatisticManager manager = StatisticManager.getInstance();
    private DefaultListModel<String> statisticModelData = new DefaultListModel<>();

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!isInit) {
            setFrame("Statistic", 350, 400, false);
            JLabel statisticLabel = new JLabel("Statistic");
            statisticLabel.setHorizontalAlignment(JLabel.CENTER);
            panel.add(statisticLabel, gbc);

            JList list = setList(statisticModelData);
            list.setMinimumSize(new Dimension(200, 340));
            JScrollPane scrollPane = new JScrollPane(list);
            panel.add(scrollPane, gbc);
        }

        frame.setVisible(true);

        Thread thread = new Thread(() -> {
            while (frame.isVisible()) {
                try {
                    statisticModelData.clear();
                    statisticModelData.addElement("Ads Profit:");
                    for (Map.Entry<String, Long> entry : manager.getAdvertisementProfit().entrySet()) {
                        statisticModelData.addElement(entry.getKey() + " - " + entry.getValue());
                    }

                    statisticModelData.addElement("");
                    statisticModelData.addElement("Cooks Workload:");
                    for (Map.Entry<String, Map<String, Integer>> pair : manager.getCooksWorkload().entrySet()) {
                        statisticModelData.addElement(pair.getKey() + ": ");
                        for (Map.Entry<String, Integer> cook : pair.getValue().entrySet())
                            statisticModelData.addElement(cook.getKey() + " - " + cook.getValue() + " min");
                    }
                    Thread.sleep(1000);
                } catch (Exception exception) {
                    break;
                }
            }
        });

        thread.start();
    }

}
