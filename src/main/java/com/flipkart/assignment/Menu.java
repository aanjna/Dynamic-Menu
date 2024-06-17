package com.flipkart.assignment;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    private final List<Dish> dishes = new ArrayList<>();
    private final Inventory inventory;

    public Menu(Inventory inventory) {
        this.inventory = inventory;
    }

    //add dish by restaurant owner
    public void addDish(Dish dish) {
        dishes.add(dish);
    }

    //find available dishes
    public List<Dish> getAvailableDishes() {
        List<Dish> availableDishes = new ArrayList<>();
        for (Dish dish : dishes) {
            if (inventory.areIngredientAvailable(dish.getRequiredIngredients())) {
                availableDishes.add(dish);
            }
        }
        return availableDishes;
    }

    public Dish getDishByName(String dishName) {
        return dishes.stream().filter(dish -> dish.getName().equals(dishName)).findFirst().orElse(null);
    }
}
