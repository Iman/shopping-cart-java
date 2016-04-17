package com.domain.service;

import com.domain.filter.PromotionType;
import com.domain.model.Inventory;
import com.domain.model.Item;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class StepDefinitions {
    private Cart cart;
    private Inventory inventory;

    @Before
    public void setUp() {
        List<Item> items = Arrays.asList(new Item("Apple", 0.35), new Item("Banana", 0.2), new Item("Melon", 0.5), new Item("Lime", 0.15));
        List<PromotionType> itemPromotions = Arrays.asList(PromotionType.MARKED_PRICE, PromotionType.MARKED_PRICE, PromotionType.BUY_ONE_GET_ONE, PromotionType.BUY_THREE_FOR_PRICE_OF_TWO);

        inventory = new Inventory(items, itemPromotions);
    }

    @Given("^The shopping basket has (\\d+) Apple, (\\d+) Banana, (\\d+) Melon, (\\d+) Lime$")
    public void the_shopping_basket_has_Apple_Banana_Melon_Lime(int arg1, int arg2, int arg3, int arg4) throws Throwable {

        List<String> addItems = Arrays.asList("Apple", "Banana", "Banana", "Melon", "Melon", "Melon", "Lime", "Lime", "Lime", "Lime");

        cart = new Cart(inventory);
        cart.add(addItems);
    }

    @When("^I calculate the final price$")
    public void i_calculate_the_final_price() throws Throwable {
        cart.calculateFinalPrice();
    }

    @Then("^The price should show (\\d+.\\d+)$")
    public void the_price_should_show(double arg1) throws Throwable {
        assertEquals(cart.calculateFinalPrice(), arg1, 0.01);
    }

    @Given("^The shopping basket has (\\d+) Apple$")
    public void the_shopping_basket_has_Apple(int arg1) throws Throwable {
        List<String> addItems = Arrays.asList("Apple");

        cart = new Cart(inventory);
        cart.add(addItems);
    }

    @Given("^The shopping basket has (\\d+) Apple and (\\d+) banana$")
    public void the_shopping_basket_has_Apple_and_banana(int arg1, int arg2) throws Throwable {
        List<String> addItems = Arrays.asList("Apple", "Banana", "Banana");

        cart = new Cart(inventory);
        cart.add(addItems);
    }

    @Given("^The shopping basket has (\\d+) Apple and (\\d+) banana and (\\d+) melon$")
    public void the_shopping_basket_has_Apple_and_banana_and_melon(int arg1, int arg2, int arg3) throws Throwable {
        List<String> addItems = Arrays.asList("Apple", "Banana", "Banana", "Melon", "Melon", "Melon");

        cart = new Cart(inventory);
        cart.add(addItems);
    }
}
