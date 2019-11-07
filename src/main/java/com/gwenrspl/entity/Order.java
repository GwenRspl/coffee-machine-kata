package com.gwenrspl.entity;

/**
 * The entity representing the order
 * @author GwenRspl
 */
public class Order {

    /**
     * The type of the drink
     */
    private DrinkType drinkType;

    /**
     * The number of sugars
     */
    private int sugar;

    public Order(DrinkType drinkType, int sugar) {
        this.drinkType = drinkType;
        this.sugar = sugar;
    }

    public DrinkType getDrinkType() {
        return drinkType;
    }

    public int getSugar() {
        return sugar;
    }
}
