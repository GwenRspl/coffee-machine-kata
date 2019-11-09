package com.gwenrspl.entity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class Report {
    private int numberOfCoffee;
    private int numberOfChocolate;
    private int numberOfTea;
    private int numberOfOrangeJuice;
    private double totalMoneyEarned;

    public Report() {
    }

    public Report(int numberOfCoffee, int numberOfChocolate, int numberOfTea, int numberOfOrangeJuice, double totalMoneyEarned) {
        this.numberOfCoffee = numberOfCoffee;
        this.numberOfChocolate = numberOfChocolate;
        this.numberOfTea = numberOfTea;
        this.numberOfOrangeJuice = numberOfOrangeJuice;
        this.totalMoneyEarned = totalMoneyEarned;
    }

    public void addToHistory(Order order) {
        switch (order.getDrinkType()){
            case COFFEE :
                this.numberOfCoffee++;
                break;
            case CHOCOLATE:
                this.numberOfChocolate++;
                break;
            case TEA:
                this.numberOfTea++;
                break;
            case ORANGE:
                this.numberOfOrangeJuice++;
                break;
            default: return;
        }
        this.totalMoneyEarned += BigDecimal.valueOf(order.getDrinkType().getPrice()).setScale(3, RoundingMode.HALF_UP).doubleValue();
    }

    @Override
    public String toString() {
        return "Coffees: "+ numberOfCoffee + "\n" +
                "Chocolates: "+ numberOfChocolate + "\n" +
                "Tea: "+ numberOfTea + "\n" +
                "Orange juice: "+ numberOfOrangeJuice + "\n" +
                "Money earned: "+ totalMoneyEarned + " euros";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Report report = (Report) o;
        return numberOfCoffee == report.numberOfCoffee &&
                numberOfChocolate == report.numberOfChocolate &&
                numberOfTea == report.numberOfTea &&
                numberOfOrangeJuice == report.numberOfOrangeJuice &&
                Double.compare(report.totalMoneyEarned, totalMoneyEarned) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numberOfCoffee, numberOfChocolate, numberOfTea, numberOfOrangeJuice, totalMoneyEarned);
    }
}
