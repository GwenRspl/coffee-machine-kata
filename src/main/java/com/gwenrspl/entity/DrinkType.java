package com.gwenrspl.entity;

/**
 * The different types of drink available in the coffee machine
 * @author GwenRspl
 */
public enum DrinkType {
    COFFEE('C'),
    CHOCOLATE('H'),
    TEA('T');

    /**
     * The letter corresponding to the drink
     */
    private char protocolLetter;

    DrinkType(char protocolLetter) {
        this.protocolLetter = protocolLetter;
    }

    public char getProtocolLetter() {
        return protocolLetter;
    }
}
