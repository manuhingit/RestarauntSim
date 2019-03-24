package restaurant.ad;

public class Advertisement {
    private Object content;
    private String name;
    private long initialAmount;
    private int hits;
    private int duration;

    private long amountPerOneDisplaying;

    public Advertisement(Object content, String name, long initialAmount, int hits, int duration) {
        this.content = content;
        this.name = name;
        this.initialAmount = initialAmount;
        this.hits = hits;
        this.duration = duration;

        amountPerOneDisplaying = (hits < 1) ? 0 : initialAmount / hits;
    }

    public int getHits() {
        return hits;
    }

    public String getName() {
        return name;
    }

    public int getDuration() {
        return duration;
    }

    public long getAmountPerOneDisplaying() {
        return amountPerOneDisplaying;
    }

    public long getProfitForOneSecond() {
        return (long) ((amountPerOneDisplaying * 1.0 / duration) * 1000);
    }

    public void revalidate() {
        if (hits < 1) throw new UnsupportedOperationException();
        hits--;
    }

    public long getInitialAmount() {
        return initialAmount;
    }
}
