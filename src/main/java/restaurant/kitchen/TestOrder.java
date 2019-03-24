package restaurant.kitchen;


import restaurant.Tablet;

import java.io.IOException;
import java.util.ArrayList;

public class TestOrder extends Order {

    public TestOrder(Tablet tablet) throws IOException {
        super(tablet);
    }

    @Override
    protected void initDishes() {
        dishes = new ArrayList<>();
        int dishesSize = Dish.values().length;
        for (int i = 0; i <= (int) (Math.random() * dishesSize); i++) {
            dishes.add(Dish.values()[(int) (Math.random() * dishesSize)]);
        }
    }
}
