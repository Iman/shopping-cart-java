package com.domain.filter;

import com.domain.exception.ItemNotSameTypeException;
import com.domain.model.Item;
import org.junit.Test;

import java.util.concurrent.CopyOnWriteArrayList;

import static org.junit.Assert.assertEquals;

public class MarkedPriceFilterTest {

    private MarkedPriceFilter markedPriceFilter;

    @Test
    public void testMarkedPrice() throws ItemNotSameTypeException {
        markedPriceFilter = new MarkedPriceFilter();
        CopyOnWriteArrayList<Item> threadSafeItemList = new CopyOnWriteArrayList<Item>();
        threadSafeItemList.add(new Item("Limes", 15));

        assertEquals(markedPriceFilter.filterPrice(threadSafeItemList), 15.0d, 0.15);
    }

    @Test
    public void testMarkedPriceWithEmptyList() throws ItemNotSameTypeException {
        markedPriceFilter = new MarkedPriceFilter();
        CopyOnWriteArrayList<Item> threadSafeItemList = new CopyOnWriteArrayList<Item>();

        assertEquals(markedPriceFilter.filterPrice(threadSafeItemList), 0.0d, 0.00);
    }

    @Test
    public void testMarkedPriceWithManyNonePromotedItem() throws ItemNotSameTypeException {
        markedPriceFilter = new MarkedPriceFilter();
        CopyOnWriteArrayList<Item> threadSafeItemList = new CopyOnWriteArrayList<Item>();
        threadSafeItemList.add(new Item("Limes", 15));
        threadSafeItemList.add(new Item("Limes", 15));
        threadSafeItemList.add(new Item("Limes", 15));
        threadSafeItemList.add(new Item("Limes", 15));

        assertEquals(markedPriceFilter.filterPrice(threadSafeItemList), 60.0d, 6.00);
    }

    @Test(expected = ItemNotSameTypeException.class)
    public void testMarkedPriceWithException() throws ItemNotSameTypeException {
        markedPriceFilter = new MarkedPriceFilter();
        CopyOnWriteArrayList<Item> threadSafeItemList = new CopyOnWriteArrayList<Item>();
        threadSafeItemList.add(new Item("Limes", 15));
        threadSafeItemList.add(new Item("Banana", 0.6));
        markedPriceFilter.filterPrice(threadSafeItemList);
    }

}