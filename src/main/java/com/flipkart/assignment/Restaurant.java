package com.flipkart.assignment;

import java.util.List;

public interface Restaurant {
    Menu getMenus(Inventory inventory);

    void addIngredient(String name, int quantity);

    List<Ingredient> getAvailableIngredient();

    void addDish(Dish dish);

    List<Dish> getAllMenuCards();

    boolean placeOrderForDishes(String dishName, int quantity);
}
