package com.domain.filter;

import com.domain.exception.ItemNotSameTypeException;
import com.domain.model.Item;
import org.junit.Test;

import java.util.concurrent.CopyOnWriteArrayList;

import static org.junit.Assert.assertEquals;

public class BuyThreeForPriceOfTwoFilterTest {

    private BuyThreeForPriceOfTwoFilter buyThreeForPriceOfTwoFilter;

    @Test
    public void testBuyThreeForPriceOfTwo() throws ItemNotSameTypeException {
        buyThreeForPriceOfTwoFilter = new BuyThreeForPriceOfTwoFilter();

        CopyOnWriteArrayList<Item> threadSafeItemList = new CopyOnWriteArrayList<Item>();
        threadSafeItemList.add(new Item("Banana", 3));
        threadSafeItemList.add(new Item("Banana", 3));
        threadSafeItemList.add(new Item("Banana", 3));

        assertEquals(buyThreeForPriceOfTwoFilter.filterPrice(threadSafeItemList), 6.0d, 0.06);
    }

    @Test
    public void testBuyThreeForPriceOfTwoWithEmptyList() throws ItemNotSameTypeException {
        buyThreeForPriceOfTwoFilter = new BuyThreeForPriceOfTwoFilter();
        CopyOnWriteArrayList<Item> threadSafeItemList = new CopyOnWriteArrayList<Item>();

        assertEquals(buyThreeForPriceOfTwoFilter.filterPrice(threadSafeItemList), 0.0d, 0.01);
    }

    @Test
    public void testBuyThreeForPriceOfTwoWithTwoItems() throws ItemNotSameTypeException {
        buyThreeForPriceOfTwoFilter = new BuyThreeForPriceOfTwoFilter();
        CopyOnWriteArrayList<Item> threadSafeItemList = new CopyOnWriteArrayList<Item>();
        threadSafeItemList.add(new Item("Banana", 4));
        threadSafeItemList.add(new Item("Banana", 4));

        assertEquals(buyThreeForPriceOfTwoFilter.filterPrice(threadSafeItemList), 8.0d, 0.08);
    }

    @Test
    public void testBuyThreeForPriceOfTwoWithSingleItem() throws ItemNotSameTypeException {
        buyThreeForPriceOfTwoFilter = new BuyThreeForPriceOfTwoFilter();
        CopyOnWriteArrayList<Item> threadSafeItemList = new CopyOnWriteArrayList<Item>();
        threadSafeItemList.add(new Item("Melons", 50));

        assertEquals(buyThreeForPriceOfTwoFilter.filterPrice(threadSafeItemList), 50.0d, 0.50);
    }

    @Test
    public void testBuyThreeForPriceOfTwoWithEvenList() throws ItemNotSameTypeException {
        buyThreeForPriceOfTwoFilter = new BuyThreeForPriceOfTwoFilter();
        CopyOnWriteArrayList<Item> threadSafeItemList = new CopyOnWriteArrayList<Item>();
        threadSafeItemList.add(new Item("Melons", 50));
        threadSafeItemList.add(new Item("Melons", 50));
        threadSafeItemList.add(new Item("Melons", 50));
        threadSafeItemList.add(new Item("Melons", 50));

        assertEquals(buyThreeForPriceOfTwoFilter.filterPrice(threadSafeItemList), 150.0d, 1.50);
    }

    @Test
    public void testBuyThreeForPriceOfTwoWithOddList() throws ItemNotSameTypeException {
        buyThreeForPriceOfTwoFilter = new BuyThreeForPriceOfTwoFilter();
        CopyOnWriteArrayList<Item> threadSafeItemList = new CopyOnWriteArrayList<Item>();
        threadSafeItemList.add(new Item("Melons", 50));
        threadSafeItemList.add(new Item("Melons", 50));
        threadSafeItemList.add(new Item("Melons", 50));
        threadSafeItemList.add(new Item("Melons", 50));
        threadSafeItemList.add(new Item("Melons", 50));
        threadSafeItemList.add(new Item("Melons", 50));
        threadSafeItemList.add(new Item("Melons", 50));

        assertEquals(buyThreeForPriceOfTwoFilter.filterPrice(threadSafeItemList), 250.0d, 2.50);
    }

    @Test(expected = ItemNotSameTypeException.class)
    public void testBuyThreeForPriceOfTwoWithException() throws ItemNotSameTypeException {
        buyThreeForPriceOfTwoFilter = new BuyThreeForPriceOfTwoFilter();
        CopyOnWriteArrayList<Item> threadSafeItemList = new CopyOnWriteArrayList<Item>();
        threadSafeItemList.add(new Item("Limes", 15));
        threadSafeItemList.add(new Item("Banana", 0.6));
        buyThreeForPriceOfTwoFilter.filterPrice(threadSafeItemList);
    }

}