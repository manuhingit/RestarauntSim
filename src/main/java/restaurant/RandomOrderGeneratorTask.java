package restaurant;

import restaurant.kitchen.KitchenStorage;

import java.util.List;

public class RandomOrderGeneratorTask implements Runnable {

    private List<Tablet> tablets = KitchenStorage.getInstance().getTablets();
    private int interval;

    public RandomOrderGeneratorTask(int interval) {
        this.interval = interval;
    }

    @Override
    public void run() {
        while(true) {
            int randomTabletNumber = (int) (Math.random() * tablets.size());
            tablets.get(randomTabletNumber).createTestOrder();
            try {
                Thread.sleep(interval);
            } catch (Exception e) {
                break;
            }
        }
    }
}
