package restaurant.ad;

import java.util.ArrayList;
import java.util.List;

public class AdFinder {
    private List<Advertisement> bestOption;
    private long maxTime;
    private long bestProfit;
    private long bestTime;
    private int bestSize;

    public AdFinder(long maxTime) {
        this.maxTime = maxTime;
    }

    private long calcTime(List<restaurant.ad.Advertisement> videos) {
        long time = 0;

        for (restaurant.ad.Advertisement ad : videos) time += ad.getDuration();
        return time;
    }

    private long calcProfit(List<restaurant.ad.Advertisement> videos) {
        long profit = 0;

        for (restaurant.ad.Advertisement ad : videos) profit += ad.getAmountPerOneDisplaying();
        return profit;
    }

    private void checkSet(List<restaurant.ad.Advertisement> videos) {
        if (bestOption == null) {
            long time = calcTime(videos);
            if (time <= maxTime) {
                bestOption = videos;
                bestProfit = calcProfit(videos);
                bestTime = time;
                bestSize = videos.size();
            }
        }
        else {
            long profit = calcProfit(videos);
            long time = calcTime(videos);
            if (time <= maxTime && profit > bestProfit) {
                bestOption = videos;
                bestProfit = profit;
            } else if (time <= maxTime && profit == bestProfit) {
                if (time > bestTime || (time == bestTime && videos.size() < bestSize)) {
                    bestOption = videos;
                    bestTime = time;
                    bestProfit = profit;
                    bestSize = videos.size();
                }
            }
        }
    }

    public void makeAllSets(List<restaurant.ad.Advertisement> videos) {
        if (!videos.isEmpty()) checkSet(videos);

        for (int i = 0; i < videos.size(); i++) {
            ArrayList<restaurant.ad.Advertisement> newList = new ArrayList<>(videos);
            newList.remove(i);
            makeAllSets(newList);
        }
    }

    public List<restaurant.ad.Advertisement> getBestOption() {
        return bestOption;
    }
}
