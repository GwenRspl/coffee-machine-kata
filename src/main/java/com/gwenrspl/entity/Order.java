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

    /**
     * The money inserted in the coffee machine
     */
    private float insertedMoney;

    /**
     * Did the customer want the extra hot option
     */
    private boolean extraHot;

    public Order(DrinkType drinkType, int sugar, float insertedMoney, boolean extraHot) {
        this.drinkType = drinkType;
        this.sugar = sugar;
        this.insertedMoney = insertedMoney;
        this.extraHot = extraHot;
    }

    public DrinkType getDrinkType() {
        return drinkType;
    }

    public int getSugar() {
        return sugar;
    }

    public float getInsertedMoney() {
        return insertedMoney;
    }

    public boolean isExtraHot() {
        return extraHot;
    }
}
