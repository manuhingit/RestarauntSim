package restaurant.ad;


import java.util.Comparator;
import java.util.TreeSet;

public class StatisticAdvertisementManager {
    private static StatisticAdvertisementManager instance;
    private restaurant.ad.AdvertisementStorage storage = restaurant.ad.AdvertisementStorage.getInstance();

    private StatisticAdvertisementManager() {

    }

    public static StatisticAdvertisementManager getInstance() {
        if (instance == null) instance = new StatisticAdvertisementManager();
        return instance;
    }

    public TreeSet<Advertisement> getActiveVideos() {
        TreeSet<Advertisement> list = new TreeSet<>(Comparator.comparing(adv -> adv.getName().toLowerCase()));
        for (Advertisement video : storage.list()) if (video.getHits() > 0) list.add(video);
        return list;
    }

    public TreeSet<Advertisement> getInactiveVideos() {
        TreeSet<Advertisement> list = new TreeSet<>(Comparator.comparing(adv -> adv.getName().toLowerCase()));
        for (Advertisement video : storage.list()) if (video.getHits() < 1) list.add(video);
        return list;
    }
}
