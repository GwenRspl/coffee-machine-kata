package com.gwenrspl;

import com.gwenrspl.entity.Order;
import com.gwenrspl.entity.Report;
import com.gwenrspl.interfaces.BeverageQuantityChecker;
import com.gwenrspl.interfaces.DrinkMaker;
import com.gwenrspl.interfaces.EmailNotifier;

/**
 * The logic translating orders from customers of the coffee machine to the drink maker
 * @author GwenRspl
 */
public class CoffeeMachineLogic {

    private DrinkMaker drinkMaker;
    private Report report;
    private BeverageQuantityChecker beverageQuantityChecker;
    private EmailNotifier emailNotifier;

    public CoffeeMachineLogic(DrinkMaker drinkMaker, Report report, BeverageQuantityChecker beverageQuantityChecker, EmailNotifier emailNotifier) {
        this.drinkMaker = drinkMaker;
        this.report = report;
        this.beverageQuantityChecker = beverageQuantityChecker;
        this.emailNotifier = emailNotifier;
    }

    /**
     * The method translating the order into a protocol
     * @param order The order of the customer
     * @return A protocol as a String
     */
    String translateOrder(Order order){
        if(order.getDrinkType() == null || order.getSugar() > 2) {
            return "M:There-was-a-problem-with-your-order.Try-again-later.";
        } else if(this.beverageQuantityChecker.isEmpty(Character.toString(order.getDrinkType().getProtocolLetter()))) {
            this.emailNotifier.notifyMissingDrink(Character.toString(order.getDrinkType().getProtocolLetter()));
            return "M:The-ordered-drink-is-not-available.A-notification-has-been-sent.";
        } else if (order.getInsertedMoney() < order.getDrinkType().getPrice()){
            float missingAmount = (order.getDrinkType().getPrice() - order.getInsertedMoney());
            return "M:" + missingAmount + "euros";
        }
        StringBuilder translatedOrder = new StringBuilder().append(order.getDrinkType().getProtocolLetter());
        if(order.isExtraHot()){
            translatedOrder.append("h");
        }
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
        if(translatedOrder.charAt(0) != 'M'){
            report.addToHistory(order);
        }
        this.drinkMaker.execute(translatedOrder);
    }

    public String printReport() {
        return this.report.toString();
    }

}

