package com.gwenrspl;

import com.gwenrspl.entity.Order;

/**
 * The logic translating orders from customers of the coffee machine to the drink maker
 * @author GwenRspl
 */
public class CoffeeMachineLogic {

    private DrinkMaker drinkMaker;

    public CoffeeMachineLogic(DrinkMaker drinkMaker) {
        this.drinkMaker = drinkMaker;
    }

    /**
     * The method translating the order into a protocol
     * @param order The order of the customer
     * @return A protocol as a String
     */
    String translateOrder(Order order){
        if(order.getDrinkType() == null || order.getSugar() > 2) {
            return "M:There-was-a-problem-with-your-order.Try-again-later.";
        } else if (order.getInsertedMoney() < order.getDrinkType().getPrice()){
            float missingAmount = (order.getDrinkType().getPrice() - order.getInsertedMoney());
            return "M:" + missingAmount + "cents";
        }
        StringBuilder translatedOrder = new StringBuilder().append(order.getDrinkType().getProtocolLetter());
        translatedOrder.append(":");
        if(order.getSugar() != 0){
            translatedOrder.append(order.getSugar());
            translatedOrder.append(":");
            translatedOrder.append("0");
        } else {
            translatedOrder.append(":");
        }
        return translatedOrder.toString();
    }

    /**
     * The method sending the order to the DrinkMaker
     * @param order The order of the customer
     */
    public void sendOrder(Order order){
        String translatedOrder = translateOrder(order);
        this.drinkMaker.execute(translatedOrder);
    }
}
