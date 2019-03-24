package restaurant;

import restaurant.ad.Advertisement;
import restaurant.ad.StatisticAdvertisementManager;
import restaurant.statistic.StatisticManager;

import java.math.BigDecimal;
import java.util.Map;

public class DirectorTablet {

    private StatisticManager manager = StatisticManager.getInstance();
    private StatisticAdvertisementManager advertisementManager = StatisticAdvertisementManager.getInstance();

    public void printAdvertisementProfit() {
        Map<String, Long> map = manager.getAdvertisementProfit();
        if (map.isEmpty()) return;
        final long[] total = {0};
        map.forEach((a, b) -> {
            ConsoleHelper.writeMessage(a + " - " + new BigDecimal(b).movePointLeft(2).toString());
            total[0] += b;
        });
        ConsoleHelper.writeMessage("Total - " + new BigDecimal(total[0]).movePointLeft(2).toString());
    }

    public void printCookWorkloading() {
        Map<String, Map<String, Integer>> map = manager.getCooksWorkload();
        if (map.isEmpty()) return;
        map.forEach((a, b) -> {
            ConsoleHelper.writeMessage(a);
            for (Map.Entry<String, Integer> cook : b.entrySet())
                ConsoleHelper.writeMessage(cook.getKey() + " - " + cook.getValue() / 60 + " min");
        });
    }

    public void printActiveVideoSet() {
        for (Advertisement video : advertisementManager.getActiveVideos()) {
            ConsoleHelper.writeMessage(video.getName() + " - " + video.getHits());
        }
    }

    public void printArchivedVideoSet() {
        for (Advertisement video : advertisementManager.getInactiveVideos()) {
            ConsoleHelper.writeMessage(video.getName());
        }
    }
}
