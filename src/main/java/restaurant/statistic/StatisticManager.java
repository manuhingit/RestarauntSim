package restaurant.statistic;

import restaurant.statistic.event.CookedOrderEventDataRow;
import restaurant.statistic.event.EventDataRow;
import restaurant.statistic.event.EventType;
import restaurant.statistic.event.VideoSelectedEventDataRow;

import java.text.SimpleDateFormat;
import java.util.*;

public class StatisticManager {
    private static StatisticManager instance;
    private StatisticStorage statisticStorage = new StatisticStorage();

    private class StatisticStorage {
        private Map<EventType, List<EventDataRow>> storage = new HashMap<>();

        StatisticStorage() {
            for (EventType eventType : EventType.values()) storage.put(eventType, new ArrayList<>());
        }

        private void put(EventDataRow data) {
            storage.get(data.getType()).add(data);
        }

        private List<EventDataRow> get(EventType eventType) {
            return storage.get(eventType);
        }
    }

    private StatisticManager() {
    }

    public static StatisticManager getInstance() {
        if (instance == null) instance = new StatisticManager();
        return instance;
    }

    public Map<String, Long> getAdvertisementProfit() {
        Map<String, Long> map = new TreeMap<>(Collections.reverseOrder());
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        for (EventDataRow data : statisticStorage.get(EventType.SELECTED_VIDEOS)) {
            VideoSelectedEventDataRow video = (VideoSelectedEventDataRow) data;
            String date = df.format(video.getDate());
            if (video.getAmount() > 0) {
                if (map.containsKey(date)) map.replace(date, map.get(date) + video.getAmount());
                else map.put(date, video.getAmount());
            }
        }
        return map;
    }

    public Map<String, Map<String, Integer>> getCooksWorkload() {
        Map<String, Map<String, Integer>> map = new TreeMap<>(Collections.reverseOrder());

        for (EventDataRow data : statisticStorage.get(EventType.COOKED_ORDER)) {
            CookedOrderEventDataRow cookOrder = (CookedOrderEventDataRow) data;
            SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
            String date = df.format(cookOrder.getDate());
            if (map.containsKey(date)) {
                Map<String, Integer> dateMap = map.get(date);
                if (dateMap.containsKey(cookOrder.getCookName()))
                    dateMap.replace(cookOrder.getCookName(),
                            dateMap.get(cookOrder.getCookName()) + cookOrder.getTime());
                else {
                    dateMap.put(cookOrder.getCookName(), cookOrder.getTime());
                }
            } else {
                Map<String, Integer> dateMap = new TreeMap<>();
                dateMap.put(cookOrder.getCookName(), cookOrder.getTime());
                map.put(date, dateMap);
            }
        }
        return map;
    }

    public void register(EventDataRow data) {
        statisticStorage.put(data);
    }
}
