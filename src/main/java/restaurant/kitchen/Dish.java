package restaurant.kitchen;

public enum Dish {
    Fish (25),
    Steak (30),
    Soup (15),
    Juice (5),
    Water (3);

    private int duration;

    public int getDuration() {
        return duration;
    }

    Dish(int duration) {
        this.duration = duration;
    }

    public static String allDishesToString() {
        StringBuilder allDishes = new StringBuilder();
        for (Dish dish : Dish.values()) {
            allDishes.append(dish.toString()).append(", ");
        }
        return allDishes.toString().substring(0, allDishes.length() - 2);
    }


}
