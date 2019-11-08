package com.gwenrspl;

import com.gwenrspl.entity.DrinkType;
import com.gwenrspl.entity.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class ReportTest {
    private Report report;

    @BeforeEach
    public void setUp(){
        report = new Report();
    }

    @Test
    public void should_add_coffee_and_right_amount_of_money_when_adding_order_of_coffee_to_history(){
        Order order = new Order(DrinkType.COFFEE, 0, 0.7f, false);
        Report expectedReport = new Report(1,0,0,0,0.6);
        report.addToHistory(order);
        assertThat(report).isEqualTo(expectedReport);
    }

    @Test
    public void should_contain_right_amount_when_adding_several_orders_to_history(){
        Order order1 = new Order(DrinkType.COFFEE, 0, 0.7f, false);
        Order order2 = new Order(DrinkType.CHOCOLATE, 0, 0.7f, false);
        Order order3 = new Order(DrinkType.TEA, 0, 0.7f, false);
        Order order4 = new Order(DrinkType.TEA, 0, 0.7f, false);
        Order order5 = new Order(DrinkType.ORANGE, 0, 0.7f, false);
        Report expectedReport = new Report(1,1,2,1,2.5);
        report.addToHistory(order1);
        report.addToHistory(order2);
        report.addToHistory(order3);
        report.addToHistory(order4);
        report.addToHistory(order5);
        assertThat(report).isEqualTo(expectedReport);
    }

}
