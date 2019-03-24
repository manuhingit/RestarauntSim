package restaurant.ad;


import restaurant.ConsoleHelper;
import restaurant.statistic.StatisticManager;
import restaurant.statistic.event.VideoSelectedEventDataRow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AdvertisementManager {
    private final restaurant.ad.AdvertisementStorage storage = AdvertisementStorage.getInstance();
    private int timeSeconds;

    public AdvertisementManager(int timeSeconds) {
        this.timeSeconds = timeSeconds;
    }

    public void processVideos() throws NoVideoAvailableException {
        if (storage.list().isEmpty()) throw new restaurant.ad.NoVideoAvailableException();
        restaurant.ad.AdFinder finder = new restaurant.ad.AdFinder(timeSeconds);
        List<restaurant.ad.Advertisement> videos = new ArrayList<>(storage.list());
        videos.removeIf(ad -> ad.getHits() < 1);
        finder.makeAllSets(videos);
        List<restaurant.ad.Advertisement> ads = finder.getBestOption();
        Collections.sort(ads, Comparator.comparing(restaurant.ad.Advertisement::getAmountPerOneDisplaying).reversed().thenComparing(restaurant.ad.Advertisement::getProfitForOneSecond));

        long amount = 0;
        int totalDuration = 0;

        for (restaurant.ad.Advertisement video : ads) {
            amount += video.getAmountPerOneDisplaying();
            totalDuration += video.getDuration();
        }

        StatisticManager.getInstance().register(new VideoSelectedEventDataRow(ads, amount, totalDuration));
        for (restaurant.ad.Advertisement video : ads) {
            ConsoleHelper.writeMessage(video.getName()
                    + " is displaying... "
                    + video.getAmountPerOneDisplaying() + ", "
                    + video.getProfitForOneSecond());
            video.revalidate();
        }
    }
}
