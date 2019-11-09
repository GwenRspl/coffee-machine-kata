package com.gwenrspl;

import com.gwenrspl.entity.DrinkType;
import com.gwenrspl.entity.Order;
import com.gwenrspl.entity.Report;
import com.gwenrspl.interfaces.BeverageQuantityChecker;
import com.gwenrspl.interfaces.DrinkMaker;
import com.gwenrspl.interfaces.EmailNotifier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CoffeeMachineLogicTest {
    @Mock
    private DrinkMaker drinkMaker;
    @Mock
    private BeverageQuantityChecker beverageQuantityChecker;
    @Mock
    private EmailNotifier emailNotifier;
    @InjectMocks
    private CoffeeMachineLogic coffeeMachineLogic;
    private Report report;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        report = new Report();
        coffeeMachineLogic = new CoffeeMachineLogic(drinkMaker, report, beverageQuantityChecker, emailNotifier);
    }

    @Test
    public void should_return_correct_string_given_order_of_coffee_without_sugar() {
        Order order = new Order(DrinkType.COFFEE, 0, 0.7f, false);
        when(beverageQuantityChecker.isEmpty(Character.toString(order.getDrinkType().getProtocolLetter()))).thenReturn(false);
        String expectedString = "C::";
        String actualString = coffeeMachineLogic.translateOrder(order);
        assertThat(actualString).isEqualTo(expectedString);
    }

    @Test
    public void should_return_correct_string_given_order_of_chocolate_without_sugar() {
        Order order = new Order(DrinkType.CHOCOLATE, 0, 0.7f, false);
        when(beverageQuantityChecker.isEmpty(Character.toString(order.getDrinkType().getProtocolLetter()))).thenReturn(false);
        String expectedString = "H::";
        String actualString = coffeeMachineLogic.translateOrder(order);
        assertThat(actualString).isEqualTo(expectedString);
    }

    @Test
    public void should_return_correct_string_given_order_of_tea_without_sugar() {
        Order order = new Order(DrinkType.TEA, 0, 0.7f, false);
        when(beverageQuantityChecker.isEmpty(Character.toString(order.getDrinkType().getProtocolLetter()))).thenReturn(false);
        String expectedString = "T::";
        String actualString = coffeeMachineLogic.translateOrder(order);
        assertThat(actualString).isEqualTo(expectedString);
    }

    @Test
    public void should_return_correct_string_given_order_with_one_sugar() {
        Order order = new Order(DrinkType.TEA, 1, 0.7f, false);
        when(beverageQuantityChecker.isEmpty(Character.toString(order.getDrinkType().getProtocolLetter()))).thenReturn(false);
        String expectedString = "T:1:0";
        String actualString = coffeeMachineLogic.translateOrder(order);
        assertThat(actualString).isEqualTo(expectedString);
    }

    @Test
    public void should_return_correct_string_given_order_with_two_sugar() {
        Order order = new Order(DrinkType.TEA, 2, 0.7f, false);
        when(beverageQuantityChecker.isEmpty(Character.toString(order.getDrinkType().getProtocolLetter()))).thenReturn(false);
        String expectedString = "T:2:0";
        String actualString = coffeeMachineLogic.translateOrder(order);
        assertThat(actualString).isEqualTo(expectedString);
    }

    @Test
    public void should_call_execute_method_from_DrinkMaker_when_sending_order(){
        Order order = new Order(DrinkType.CHOCOLATE, 0, 0.7f, false);
        when(beverageQuantityChecker.isEmpty(Character.toString(order.getDrinkType().getProtocolLetter()))).thenReturn(false);
        String protocol = "H::";
        coffeeMachineLogic.sendOrder(order);
        verify(drinkMaker, times(1)).execute(protocol);
    }

    @Test
    public void should_return_message_string_given_error_order(){
        Order order = new Order(null, 0, 0.7f, false);
        String expectedString = "M:There-was-a-problem-with-your-order.Try-again-later.";
        String actualString = coffeeMachineLogic.translateOrder(order);
        assertThat(actualString).isEqualTo(expectedString);
    }

    @Test
    public void should_return_message_string_given_incorrect_number_of_sugar(){
        Order order = new Order(DrinkType.TEA, 5, 0.7f, false);
        when(beverageQuantityChecker.isEmpty(Character.toString(order.getDrinkType().getProtocolLetter()))).thenReturn(false);
        String expectedString = "M:There-was-a-problem-with-your-order.Try-again-later.";
        String actualString = coffeeMachineLogic.translateOrder(order);
        assertThat(actualString).isEqualTo(expectedString);
    }

    @Test
    public void should_return_message_string_with_missing_amount_given_insufficient_money(){
        Order order = new Order(DrinkType.TEA, 0, 0.1f, false);
        when(beverageQuantityChecker.isEmpty(Character.toString(order.getDrinkType().getProtocolLetter()))).thenReturn(false);
        String expectedString = "M:0.3euros";
        String actualString = coffeeMachineLogic.translateOrder(order);
        assertThat(actualString).isEqualTo(expectedString);
    }

    @Test
    public void should_return_correct_string_given_the_exact_amount_of_money(){
        Order order = new Order(DrinkType.TEA, 0, 0.4f, false);
        when(beverageQuantityChecker.isEmpty(Character.toString(order.getDrinkType().getProtocolLetter()))).thenReturn(false);
        String expectedString = "T::";
        String actualString = coffeeMachineLogic.translateOrder(order);
        assertThat(actualString).isEqualTo(expectedString);
    }

    @Test
    public void should_return_correct_string_given_more_money_than_required(){
        Order order = new Order(DrinkType.TEA, 0, 1f, false);
        when(beverageQuantityChecker.isEmpty(Character.toString(order.getDrinkType().getProtocolLetter()))).thenReturn(false);
        String expectedString = "T::";
        String actualString = coffeeMachineLogic.translateOrder(order);
        assertThat(actualString).isEqualTo(expectedString);
    }

    @Test
    public void should_return_correct_string_given_order_of_orange_juice() {
        Order order = new Order(DrinkType.ORANGE, 0, 0.7f, false);
        when(beverageQuantityChecker.isEmpty(Character.toString(order.getDrinkType().getProtocolLetter()))).thenReturn(false);
        String expectedString = "O::";
        String actualString = coffeeMachineLogic.translateOrder(order);
        assertThat(actualString).isEqualTo(expectedString);
    }

    @Test
    public void should_return_correct_string_given_order_of_extra_hot_coffee_without_sugar() {
        Order order = new Order(DrinkType.COFFEE, 0, 0.7f, true);
        when(beverageQuantityChecker.isEmpty(Character.toString(order.getDrinkType().getProtocolLetter()))).thenReturn(false);
        String expectedString = "Ch::";
        String actualString = coffeeMachineLogic.translateOrder(order);
        assertThat(actualString).isEqualTo(expectedString);
    }

    @Test
    public void should_return_correct_string_given_order_of_extra_hot_chocolate_with_one_sugar() {
        Order order = new Order(DrinkType.CHOCOLATE, 1, 0.7f, true);
        when(beverageQuantityChecker.isEmpty(Character.toString(order.getDrinkType().getProtocolLetter()))).thenReturn(false);
        String expectedString = "Hh:1:0";
        String actualString = coffeeMachineLogic.translateOrder(order);
        assertThat(actualString).isEqualTo(expectedString);
    }

    @Test
    public void should_return_correct_string_given_order_of_extra_hot_tea_with_two_sugar() {
        Order order = new Order(DrinkType.TEA, 2, 0.7f, true);
        when(beverageQuantityChecker.isEmpty(Character.toString(order.getDrinkType().getProtocolLetter()))).thenReturn(false);
        String expectedString = "Th:2:0";
        String actualString = coffeeMachineLogic.translateOrder(order);
        assertThat(actualString).isEqualTo(expectedString);
    }

    @Test
    public void should_print_correct_report_when_asking_for_report_after_orders_of_two_coffees_and_one_tea(){
        Order order1 = new Order(DrinkType.COFFEE, 2, 0.7f, true);
        Order order2 = new Order(DrinkType.COFFEE, 1, 0.7f, false);
        Order order3 = new Order(DrinkType.TEA, 0, 0.7f, true);
        when(beverageQuantityChecker.isEmpty(Character.toString(order1.getDrinkType().getProtocolLetter()))).thenReturn(false);
        when(beverageQuantityChecker.isEmpty(Character.toString(order2.getDrinkType().getProtocolLetter()))).thenReturn(false);
        when(beverageQuantityChecker.isEmpty(Character.toString(order3.getDrinkType().getProtocolLetter()))).thenReturn(false);
        coffeeMachineLogic.sendOrder(order1);
        coffeeMachineLogic.sendOrder(order2);
        coffeeMachineLogic.sendOrder(order3);

        String expectedString = "Coffees: 2\n" +
                "Chocolates: 0\n" +
                "Tea: 1\n" +
                "Orange juice: 0\n" +
                "Money earned: 1.6 euros";
        String actualString = coffeeMachineLogic.printReport();
        assertThat(actualString).isEqualTo(expectedString);
    }

    @Test
    public void should_return_message_indicating_missing_drink_when_missing_drink_is_ordered(){
        Order order = new Order(DrinkType.COFFEE, 1, 0.7f, false);
        String drink = "C";
        when(beverageQuantityChecker.isEmpty(drink)).thenReturn(true);
        String expectedString = "M:The-ordered-drink-is-not-available.A-notification-has-been-sent.";
        String actualString = coffeeMachineLogic.translateOrder(order);
        assertThat(actualString).isEqualTo(expectedString);
    }

    @Test
    public void should_send_notification_when_missing_drink_is_ordered(){
        Order order = new Order(DrinkType.COFFEE, 1, 0.7f, false);
        String drink = "C";
        when(beverageQuantityChecker.isEmpty(drink)).thenReturn(true);
        coffeeMachineLogic.sendOrder(order);
        verify(emailNotifier, times(1)).notifyMissingDrink(drink);
    }



}
