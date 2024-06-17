package com.flipkart.assignment;

import lombok.Getter;

import java.util.List;

@Getter
public class Dish {

    private final String name;
    private final List<Ingredient> requiredIngredients;

    public Dish(String name, List<Ingredient> requiredIngredients) {
        this.name = name;
        this.requiredIngredients = requiredIngredients;
    }

    public String getName() {
        return name;
    }

    public List<Ingredient> getRequiredIngredients() {
        return requiredIngredients;
    }
}
