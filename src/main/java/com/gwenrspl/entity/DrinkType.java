package com.gwenrspl.entity;

/**
 * The different types of drink available in the coffee machine
 * @author GwenRspl
 */
public enum DrinkType {
    COFFEE('C', 0.6f),
    CHOCOLATE('H', 0.5f),
    TEA('T', 0.4f);

    /**
     * The letter corresponding to the drink
     */
    private char protocolLetter;

    /**
     * The price of the drink
     */
    private float price;

    DrinkType(char protocolLetter, float price) {
        this.protocolLetter = protocolLetter;
        this.price = price;
    }

    public char getProtocolLetter() {
        return protocolLetter;
    }

    public float getPrice() {
        return price;
    }
}
