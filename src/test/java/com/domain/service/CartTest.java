package com.domain.service;

import com.domain.exception.ItemNotSameTypeException;
import com.domain.filter.PromotionType;
import com.domain.model.Inventory;
import com.domain.model.Item;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.*;

public class CartTest {

    private Inventory inventory;
    private List<Item> items = new CopyOnWriteArrayList<Item>();
    private List<PromotionType> itemPromotions = new CopyOnWriteArrayList<PromotionType>();

    @Before
    public void setUp() {
        items.add(new Item("Apple", 0.35));
        items.add(new Item("Banana", 0.2));
        items.add(new Item("Melon", 0.5));
        items.add(new Item("Lime", 0.15));

        itemPromotions.add(PromotionType.MARKED_PRICE);
        itemPromotions.add(PromotionType.MARKED_PRICE);
        itemPromotions.add(PromotionType.BUY_ONE_GET_ONE);
        itemPromotions.add(PromotionType.BUY_THREE_FOR_PRICE_OF_TWO);

        inventory = new Inventory(items, itemPromotions);
    }

    @Test
    public void testCreateBasket() throws ItemNotSameTypeException {
        List<String> order = new CopyOnWriteArrayList<String>(Arrays.asList("Apple", "Banana", "Banana", "Melon", "Melon", "Melon", "Lime", "Lime", "Lime", "Lime"));

        Cart cart = new Cart(inventory);
        cart.add(order);

        assertNotNull(cart);
        assertTrue(cart.getItems().size() > 0);

    }

    @Test
    public void testCalculateFinalPrice() throws ItemNotSameTypeException {
        List<String> order = new CopyOnWriteArrayList<String>(Arrays.asList("Apple", "Banana", "Banana", "Melon", "Melon", "Melon", "Lime", "Lime", "Lime", "Lime"));

        Cart cart = new Cart(inventory);
        cart.add(order);

        assertEquals(cart.calculateFinalPrice(), 2.2d, 0.001);
    }

    @Test
    public void testCalculateMarkedPrice() throws ItemNotSameTypeException {
        List<String> order = new CopyOnWriteArrayList<String>(Arrays.asList("Apple", "Banana", "Banana", "Melon", "Melon", "Melon", "Lime", "Lime", "Lime", "Lime"));

        Cart cart = new Cart(inventory);
        cart.add(order);

        assertEquals(cart.calculateMarkerPrice(), 2.85d, 0.001);
    }

    @Test
    public void testEmptyCart() throws ItemNotSameTypeException {
        List<String> order = new CopyOnWriteArrayList<String>(Arrays.asList("Apple", "Lime", "Lime", "Lime"));

        Cart cart = new Cart(inventory);
        cart.add(order);
        cart.empty();

        assertEquals(cart.calculateMarkerPrice(), 0, 0.0);
        assertEquals(cart.calculateFinalPrice(), 0, 0.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCatchEmptyAddException() throws ItemNotSameTypeException {
        Cart cart = new Cart(inventory);
        cart.add("");
        cart.empty();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCatchEmptyListOnAddException() throws ItemNotSameTypeException {
        List<String> order = new CopyOnWriteArrayList<String>(Arrays.asList("", ""));
        Cart cart = new Cart(inventory);
        cart.add(order);
        cart.empty();
    }

    @Test
    public void testConcurrency() throws InterruptedException, ItemNotSameTypeException {

        List<Item> items = new CopyOnWriteArrayList<Item>(Arrays.asList(new Item("Apple", 0.35), new Item("Banana", 0.2), new Item("Melon", 0.5), new Item("Lime", 0.15)));

        List<PromotionType> itemPromotions = new CopyOnWriteArrayList<PromotionType>(Arrays.asList(PromotionType.MARKED_PRICE, PromotionType.MARKED_PRICE, PromotionType.BUY_ONE_GET_ONE, PromotionType.BUY_THREE_FOR_PRICE_OF_TWO));

        Inventory inventory = new Inventory(items, itemPromotions);

        Cart cart = new Cart(inventory);

        List<String> order = new LinkedList<>(Arrays.asList("Apple", "Banana", "Banana", "Melon", "Melon", "Melon", "Lime", "Lime", "Lime", "Lime"));

        AtomicInteger finished = new AtomicInteger();

        for (int i = 0; i < 100; i++) {

            cart.add(getRandomItem());

            new Thread(() ->
            {
                cart.add(order);
                finished.incrementAndGet();

            }).start();
        }

        while (finished.get() < 100) {
            Thread.sleep(100);
        }

        assertTrue(cart.calculateMarkerPrice() > 0);
        assertTrue(cart.calculateFinalPrice() > 0);
        assertEquals(cart.getItems().size(), 1100);
        cart.empty();
    }


    private synchronized String getRandomItem() {

        List<String> order = new LinkedList<>(Arrays.asList("Apple", "Banana", "Banana", "Melon", "Melon", "Melon", "Lime", "Lime", "Lime", "Lime"));
        int rnd = new Random().nextInt(order.size());
        return order.get(rnd);
    }
}