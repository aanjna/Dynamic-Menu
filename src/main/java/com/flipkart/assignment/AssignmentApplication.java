package com.flipkart.assignment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@SpringBootApplication
public class AssignmentApplication {

    public static void main(String[] args) {
//		SpringApplication.run(AssignmentApplication.class, args);

        Restaurant restaurant = new RestaurantImpl();
        restaurant.addIngredient("tomato", 10);
        restaurant.addIngredient("Potato", 12);
        restaurant.addIngredient("Bred", 5);
        restaurant.addIngredient("Onion", 10);
        restaurant.addIngredient("Capsicum", 8);
        restaurant.addIngredient("Cheese", 3);

        System.out.println("Available Ingredients :");
        for (Ingredient ind : restaurant.getAvailableIngredient()) {
            System.out.println("Ingredient - " + ind.getName() + " & quantity " + ind.getQuantity());
        }

        Ingredient ind = new Ingredient("Tomato", 3);
        Ingredient ind1 = new Ingredient("Bred", 8);
        Ingredient ind2 = new Ingredient("Cheese", 1);
        Ingredient ind3 = new Ingredient("Onion", 5);
        Ingredient ind4 = new Ingredient("Potato", 4);

        List<Ingredient> dish1Ingredient = new ArrayList<>();
        dish1Ingredient.add(ind);
        dish1Ingredient.add(ind2);
        dish1Ingredient.add(ind1);
        Dish dish1 = new Dish("Tomato Cheese Sandwish", dish1Ingredient);
        restaurant.addDish(dish1);

        List<Ingredient> dish2Ingredient = new ArrayList<>();
        dish2Ingredient.add(ind);
        dish2Ingredient.add(ind2);
        dish2Ingredient.add(ind3);
        dish2Ingredient.add(ind1);
        Dish dish2 = new Dish("Onion Cheese Sandwish", dish2Ingredient);
        restaurant.addDish(dish2);

        List<Ingredient> dish3Ingredient = new ArrayList<>();
        dish3Ingredient.add(ind4);
        dish3Ingredient.add(ind1);
        dish3Ingredient.add(ind2);
        Dish dish3 = new Dish("Potato Cheese Sandwish", dish3Ingredient);
        restaurant.addDish(dish3);


        System.out.println("Available Menus := ");
        for (Dish dish : restaurant.getAllMenuCards()) {
            System.out.println(dish.getName());
        }

        Runnable orderTask1 = () -> {
            boolean success = restaurant.placeOrderForDishes(dish1.getName(), 3);
            if (success)
                System.out.println("3x Tomato sandwish order placed Successfully ");
            else
                System.out.println("Failed for order 3x tomato sandwish");
        };

        Runnable orderTask2 = () -> {
            boolean success = restaurant.placeOrderForDishes(dish2.getName(), 4);
            if (success)
                System.out.println("4x Onion, Capcicum Cheese Sandwish order placed Successfully ");
            else
                System.out.println("Failed for order 4x Onion, Capcicum Cheese Sandwish");
        };

        Thread t1 = new Thread(orderTask1);
        Thread t2 = new Thread(orderTask2);

        t1.start();
        System.out.println("T1 start :- " + t1.getName());
        t2.start();
        System.out.println("T2 start :- " + t2.getName());

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        System.out.println("Available Menu after Orders");
        for (Dish dish : restaurant.getAllMenuCards()) {
            System.out.println(dish.getName());
        }

    }

}
