package com.flipkart.assignment;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class OrderService {
    private final Inventory inventory;
    private final Menu menu;
    private final Lock orderLock = new ReentrantLock();

    public OrderService(Inventory inventory, Menu menu) {
        this.inventory = inventory;
        this.menu = menu;
    }

    //user place order
    public boolean placeOrder(String dishName, int quantity) {
        Dish dish = menu.getDishByName(dishName);
        if (dish == null) {
            System.out.println("Dish not found - " + dishName);
            return false;
        }

        List<Ingredient> requiredIngredients = dish.getRequiredIngredients();

        for (Ingredient ingredient : requiredIngredients) {
            if (ingredient == null || ingredient.getQuantity() < quantity) {
                System.out.println("Only " + (ingredient == null ? 0 : ingredient.getQuantity())
                        + " quantity of " + ingredient.getName() + " available. Cannot fulfill order for " + quantity + " " + dishName);
                return false;
            }
        }

        orderLock.lock();
        try {
            if (inventory.areIngredientAvailable(requiredIngredients)) {
                inventory.useIngredients(requiredIngredients);
                System.out.println("Order placed for " + quantity + " dishes : " + dish.getName());
                return true;
            } else {
                System.out.println("Order fail for " + quantity + " due to insufficient Ingredients");
                return false;
            }
        } finally {
            orderLock.unlock();
        }

    }

}
