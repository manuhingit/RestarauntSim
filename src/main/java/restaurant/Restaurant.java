package restaurant;

import restaurant.kitchen.Cook;
import restaurant.kitchen.Order;
import restaurant.kitchen.Waiter;
import restaurant.view.DirectorMenu;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

public class Restaurant {

    private static final LinkedBlockingQueue<Order> orderQueue = new LinkedBlockingQueue<>();
    private static final int ORDER_CREATING_INTERVAL = 100;

    public static void main(String[] args) {

//        DirectorMenu menu = new DirectorMenu();

        Cook[] cooks =  {new Cook("Amigo"), new Cook("Diego")};
        List<Cook> cookList = new LinkedList<>(Arrays.asList(cooks));
        Waiter waiter = new Waiter();
        for (Cook cook : cookList) {
            cook.addObserver(waiter);
            cook.setQueue(orderQueue);
            Thread cooker = new Thread(cook);
            cooker.start();
        }

        List<Tablet> tablets = new LinkedList<>();

        for (int i = 0; i < 5; i++) {
            Tablet tablet = new Tablet(i);
            tablet.setQueue(orderQueue);
            tablets.add(tablet);
        }

        Thread thread = new Thread(new RandomOrderGeneratorTask(tablets, ORDER_CREATING_INTERVAL));
        thread.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread.interrupt();

        DirectorTablet directorTablet = new DirectorTablet();
        directorTablet.printAdvertisementProfit();
        directorTablet.printCookWorkloading();
        directorTablet.printActiveVideoSet();
        directorTablet.printArchivedVideoSet();
    }
}
