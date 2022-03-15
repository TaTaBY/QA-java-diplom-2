package model;

import org.apache.commons.lang3.RandomStringUtils;
import support.OrderClient;

import java.util.Arrays;

public class Order{
    private String [] ingredients;
    private static OrderClient orderClient;

    public Order(String[] ingredients) {
        this.ingredients = ingredients;
    }

    public Order() {

    }

    public static Order getIngredients() {
        OrderClient orderClient = new OrderClient();
        String[] ingredients = {orderClient.getRandomHashOfIngredients(), orderClient.getRandomHashOfIngredients()};
        return new Order(ingredients);
    }

    public static Order getEmptyIngredients() {
        String[] ingredients = {};
        return new Order(ingredients);
    }

    public static Order getIncorrectIngredients() {
        String[] ingredients = {RandomStringUtils.randomAlphabetic(10)};
        return new Order(ingredients);
    }

    @Override
    public String toString() {
        return "Order{" +
                "ingredients=" + Arrays.toString(ingredients) +
                '}';
    }
}
