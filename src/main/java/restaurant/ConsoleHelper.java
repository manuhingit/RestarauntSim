package restaurant;

import restaurant.kitchen.Dish;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ConsoleHelper {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() {
        while (true) {
            try {
                return reader.readLine();
            } catch (IOException e) {
                writeMessage("Произошла ошибка при попытке ввода текста. Попробуйте еще раз.");
            }
        }
    }

    public static int readInt() {
        while (true) {
            try {
                return Integer.parseInt(readString());
            } catch (NumberFormatException e) {
                writeMessage("Произошла ошибка при попытке ввода числа. Попробуйте еще раз.");
            }
        }
    }

    public static List<Dish> getAllDishesForOrder() {
        writeMessage("Please choose a dish from list below, type exit to stop ordering:");
        writeMessage(Dish.allDishesToString());
        String dish;
        List<Dish> orderList = new ArrayList<>();
        while (!(dish = readString()).equals("exit")) {
            try {
                orderList.add(Dish.valueOf(dish));
            } catch (IllegalArgumentException | NullPointerException e) {
                writeMessage("Incorrect dish, type again.");
            }
        }
        return orderList;
    }
}
