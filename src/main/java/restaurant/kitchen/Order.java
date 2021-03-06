package restaurant.kitchen;

import restaurant.ConsoleHelper;
import restaurant.Tablet;

import java.io.IOException;
import java.util.List;

public class Order {
    private final Tablet tablet;
    protected List<Dish> dishes;

    public Order(Tablet tablet) throws IOException {
        this.tablet = tablet;
        initDishes();
    }

    public Order(Tablet tablet, List<Dish> dishes) throws IOException {
        this.tablet = tablet;
        this.dishes = dishes;
    }

    public boolean isEmpty() {
        return dishes.isEmpty();
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public Tablet getTablet() {
        return tablet;
    }

    protected void initDishes() {
        dishes = ConsoleHelper.getAllDishesForOrder();
    }

    @Override
    public String toString() {
        return dishes.size() > 0 ? "Your order: " + dishes.toString() + " of " + tablet.toString() : "";
    }

    public String orderInfo() {
        return tablet.toString() + ": " + dishes.toString();
    }

    public int getTotalCookingTime() {
        return dishes.stream().mapToInt(Dish::getDuration).sum();
    }
}
