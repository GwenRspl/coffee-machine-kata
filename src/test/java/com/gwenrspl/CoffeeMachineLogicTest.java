package com.gwenrspl;

import com.gwenrspl.entity.DrinkType;
import com.gwenrspl.entity.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class CoffeeMachineLogicTest {
    @Mock
    private DrinkMaker drinkMaker;
    @InjectMocks
    private CoffeeMachineLogic coffeeMachineLogic;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        coffeeMachineLogic = new CoffeeMachineLogic(drinkMaker);
    }

    @Test
    public void should_return_correct_string_given_order_of_coffee_without_sugar() {
        Order order = new Order(DrinkType.COFFEE, 0, 0.7f, false);
        String expectedString = "C::";
        String actualString = coffeeMachineLogic.translateOrder(order);
        assertThat(actualString).isEqualTo(expectedString);
    }

    @Test
    public void should_return_correct_string_given_order_of_chocolate_without_sugar() {
        Order order = new Order(DrinkType.CHOCOLATE, 0, 0.7f, false);
        String expectedString = "H::";
        String actualString = coffeeMachineLogic.translateOrder(order);
        assertThat(actualString).isEqualTo(expectedString);
    }

    @Test
    public void should_return_correct_string_given_order_of_tea_without_sugar() {
        Order order = new Order(DrinkType.TEA, 0, 0.7f, false);
        String expectedString = "T::";
        String actualString = coffeeMachineLogic.translateOrder(order);
        assertThat(actualString).isEqualTo(expectedString);
    }

    @Test
    public void should_return_correct_string_given_order_with_one_sugar() {
        Order order = new Order(DrinkType.TEA, 1, 0.7f, false);
        String expectedString = "T:1:0";
        String actualString = coffeeMachineLogic.translateOrder(order);
        assertThat(actualString).isEqualTo(expectedString);
    }

    @Test
    public void should_return_correct_string_given_order_with_two_sugar() {
        Order order = new Order(DrinkType.TEA, 2, 0.7f, false);
        String expectedString = "T:2:0";
        String actualString = coffeeMachineLogic.translateOrder(order);
        assertThat(actualString).isEqualTo(expectedString);
    }

    @Test
    public void should_call_execute_method_from_DrinkMaker_when_sending_order(){
        Order order = new Order(DrinkType.CHOCOLATE, 0, 0.7f, false);
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
        String expectedString = "M:There-was-a-problem-with-your-order.Try-again-later.";
        String actualString = coffeeMachineLogic.translateOrder(order);
        assertThat(actualString).isEqualTo(expectedString);
    }

    @Test
    public void should_return_message_string_with_missing_amount_given_insufficient_money(){
        Order order = new Order(DrinkType.TEA, 0, 0.1f, false);
        String expectedString = "M:0.3cents";
        String actualString = coffeeMachineLogic.translateOrder(order);
        assertThat(actualString).isEqualTo(expectedString);
    }

    @Test
    public void should_return_correct_string_given_the_exact_amount_of_money(){
        Order order = new Order(DrinkType.TEA, 0, 0.4f, false);
        String expectedString = "T::";
        String actualString = coffeeMachineLogic.translateOrder(order);
        assertThat(actualString).isEqualTo(expectedString);
    }

    @Test
    public void should_return_correct_string_given_more_money_than_required(){
        Order order = new Order(DrinkType.TEA, 0, 1f, false);
        String expectedString = "T::";
        String actualString = coffeeMachineLogic.translateOrder(order);
        assertThat(actualString).isEqualTo(expectedString);
    }

    @Test
    public void should_return_correct_string_given_order_of_orange_juice() {
        Order order = new Order(DrinkType.ORANGE, 0, 0.7f, false);
        String expectedString = "O::";
        String actualString = coffeeMachineLogic.translateOrder(order);
        assertThat(actualString).isEqualTo(expectedString);
    }

    @Test
    public void should_return_correct_string_given_order_of_extra_hot_coffee_without_sugar() {
        Order order = new Order(DrinkType.COFFEE, 0, 0.7f, true);
        String expectedString = "Ch::";
        String actualString = coffeeMachineLogic.translateOrder(order);
        assertThat(actualString).isEqualTo(expectedString);
    }

    @Test
    public void should_return_correct_string_given_order_of_extra_hot_chocolate_with_one_sugar() {
        Order order = new Order(DrinkType.CHOCOLATE, 1, 0.7f, true);
        String expectedString = "Hh:1:0";
        String actualString = coffeeMachineLogic.translateOrder(order);
        assertThat(actualString).isEqualTo(expectedString);
    }

    @Test
    public void should_return_correct_string_given_order_of_extra_hot_tea_with_two_sugar() {
        Order order = new Order(DrinkType.TEA, 2, 0.7f, true);
        String expectedString = "Th:2:0";
        String actualString = coffeeMachineLogic.translateOrder(order);
        assertThat(actualString).isEqualTo(expectedString);
    }

}
