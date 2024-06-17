package com.flipkart.assignment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Inventory {
    private final Map<String, Ingredient> ingredients = new HashMap<>();
    private final Lock lock = new ReentrantLock();


    // 1 add ingredients
    public void addIngredient(String name, int quantity) {
//        lock.lock(); //not required here for all ingredient
        try {
            ingredients.putIfAbsent(name, new Ingredient(name, 0));
            Ingredient ingredient = ingredients.get(name);
            ingredient.setQuantity(ingredient.getQuantity() + quantity);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
//            lock.unlock();
        }
    }

    public Ingredient getIngredient(String name) {
        return ingredients.get(name);
    }

    public Map<String, Ingredient> getAvailableIngredients() {
        return new HashMap<>(ingredients);
    }

    public boolean areIngredientAvailable(List<Ingredient> requiredIngredients) {
        for (Ingredient ingredient : requiredIngredients) {
            if (ingredient == null || ingredient.getQuantity() < ingredient.getQuantity()) {
                return false;
            }
        }
        return true;
    }

    public void useIngredients(List<Ingredient> usedIngredients) {
        for (Ingredient ingredient : usedIngredients) {
            ingredient.setQuantity(ingredient.getQuantity() - ingredient.getQuantity());
        }
    }

}
