package restaurant.kitchen;

import restaurant.Restaurant;
import restaurant.Tablet;

import java.util.ArrayList;
import java.util.List;

public class KitchenStorage {
    private List<Cook> cooks = new ArrayList<>();
    private List<Waiter> waiters = new ArrayList<>();
    private List<Tablet> tablets = new ArrayList<>();

    private static KitchenStorage instance = null;

    public static KitchenStorage getInstance() {
        if (instance == null) instance = new KitchenStorage();
        return instance;
    }

    public void addTablet(Tablet tablet) {
        tablet.setQueue(Restaurant.getOrderQueue());
        tablets.add(tablet);
    }

    public List<Tablet> getTablets() {
        return tablets;
    }

    public void addCook(Cook cook) {
        cooks.add(cook);
        cook.setQueue(Restaurant.getOrderQueue());
        for (Waiter waiter : waiters) cook.addObserver(waiter);
        Thread cooker = new Thread(cook);
        cooker.start();
    }

    public void addWaiter(Waiter waiter) {
        waiters.add(waiter);
        for (Cook cook : cooks) cook.addObserver(waiter);
    }

    public List<Cook> getCooks() {
        return cooks;
    }

    public List<Waiter> getWaiters() {
        return waiters;
    }
}
