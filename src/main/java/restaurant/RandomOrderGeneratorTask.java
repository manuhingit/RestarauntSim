package restaurant;

import java.util.List;

public class RandomOrderGeneratorTask implements Runnable {

    private List<Tablet> tablets;
    private int interval;

    public RandomOrderGeneratorTask(List<Tablet> tablets, int interval) {
        this.tablets = tablets;
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
