package com.domain.filter;

import com.domain.exception.ItemNotSameTypeException;
import com.domain.model.Item;
import org.junit.Test;

import java.util.concurrent.CopyOnWriteArrayList;

import static org.junit.Assert.assertEquals;

public class BuyOneGetOneFilterTest {

    private BuyOneGetOneFilter buyOneGetOneFilter;

    @Test
    public void testBuyOneGetOne() throws ItemNotSameTypeException {
        buyOneGetOneFilter = new BuyOneGetOneFilter();

        CopyOnWriteArrayList<Item> threadSafeItemList = new CopyOnWriteArrayList<Item>();
        threadSafeItemList.add(new Item("Apple", 1));
        threadSafeItemList.add(new Item("Apple", 1));

        assertEquals(buyOneGetOneFilter.filterPrice(threadSafeItemList), 1.0d, 0.01);
    }

    @Test
    public void testBuyOneGetOneWithEmptyList() throws ItemNotSameTypeException {
        buyOneGetOneFilter = new BuyOneGetOneFilter();
        CopyOnWriteArrayList<Item> threadSafeItemList = new CopyOnWriteArrayList<Item>();

        assertEquals(buyOneGetOneFilter.filterPrice(threadSafeItemList), 0.0d, 0.00);
    }

    @Test
    public void testBuyOneGetOneWithOneItem() throws ItemNotSameTypeException {
        buyOneGetOneFilter = new BuyOneGetOneFilter();
        CopyOnWriteArrayList<Item> threadSafeItemList = new CopyOnWriteArrayList<Item>();
        threadSafeItemList.add(new Item("Apple", 1));

        assertEquals(buyOneGetOneFilter.filterPrice(threadSafeItemList), 1.0d, 0.01);
    }

    @Test(expected = ItemNotSameTypeException.class)
    public void testBuyOneGetOneWithException() throws ItemNotSameTypeException {
        buyOneGetOneFilter = new BuyOneGetOneFilter();
        CopyOnWriteArrayList<Item> threadSafeItemList = new CopyOnWriteArrayList<Item>();
        threadSafeItemList.add(new Item("Apple", 15));
        threadSafeItemList.add(new Item("Banana", 0.6));
        buyOneGetOneFilter.filterPrice(threadSafeItemList);
    }

}