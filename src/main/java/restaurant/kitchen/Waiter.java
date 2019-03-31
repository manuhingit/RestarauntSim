package restaurant.kitchen;

import restaurant.ConsoleHelper;

import java.util.Observable;
import java.util.Observer;

public class Waiter implements Observer {

    private int id;

    public Waiter() {
        id = KitchenStorage.getInstance().getWaiters().size();
    }

    @Override
    public void update(Observable o, Object arg) {
        ConsoleHelper.writeMessage(arg.toString() + " was cooked by " + o.toString());
    }

    @Override
    public String toString() {
        return "Waiter{" + id + "}";
    }
}
