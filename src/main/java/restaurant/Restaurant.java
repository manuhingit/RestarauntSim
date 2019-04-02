package restaurant;

import restaurant.kitchen.Cook;
import restaurant.kitchen.KitchenStorage;
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

        DirectorMenu menu = new DirectorMenu();

        KitchenStorage kitchenStorage = KitchenStorage.getInstance();

        kitchenStorage.addCook(new Cook("Amigo"));
        kitchenStorage.addCook(new Cook("Diego"));
        kitchenStorage.addWaiter(new Waiter());

        for (int i = 0; i < 5; i++) kitchenStorage.addTablet(new Tablet());

        Thread thread = new Thread(new RandomOrderGeneratorTask(ORDER_CREATING_INTERVAL));
        thread.start();
    }

    public static LinkedBlockingQueue<Order> getOrderQueue() {
        return orderQueue;
    }
}
