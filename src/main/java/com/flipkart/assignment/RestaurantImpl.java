package com.flipkart.assignment;

import java.util.List;

public class RestaurantImpl implements Restaurant {
    private final Inventory inventory = new Inventory();
    private final Menu menu = new Menu(inventory);
    private final OrderService orderService = new OrderService(inventory, menu);

    @Override
    public Menu getMenus(Inventory inventory) {
        return menu;
    }

    //1. API to add ingredients with quantity.
    @Override
    public void addIngredient(String name, int quantity) {
        inventory.addIngredient(name, quantity);
    }

    //2. API to get available ingredients with respective quantities.
    @Override
    public List<Ingredient> getAvailableIngredient() {
        return List.copyOf(inventory.getAvailableIngredients().values());
    }

    //3. API to add dishes with required ingredients. Dishes can be added without availability of the ingredients but should not display in the menu card until the ingredient is available.
    @Override
    public void addDish(Dish dish) {
        menu.addDish(dish);
    }

    //4. API to fetch menu cards with the available dishes.
    @Override
    public List<Dish> getAllMenuCards() {
        return menu.getAvailableDishes();
    }

    //5. API to order dishes in one or more quantities.
    @Override
    public boolean placeOrderForDishes(String dishName, int quantity) {
        return orderService.placeOrder(dishName, quantity);
    }

}
