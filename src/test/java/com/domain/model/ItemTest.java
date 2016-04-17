package com.domain.model;

import com.domain.exception.ItemNotSameTypeException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ItemTest {

    private static final double DELTA = 1e-15;

    @Test
    public void createItem() {
        Item item = new Item("Apple", 0.35);
        assertEquals(item.getName(), "Apple");
        assertEquals(item.getPrice(), 0.35, 0.01);
    }

    @Test
    public void addItem() throws ItemNotSameTypeException {

        Item item = new Item("Boo", 0.35);
        Item newItem = new Item(item);
        newItem.setName("Foo");
        newItem.setPrice(0.1);
        assertEquals(newItem.getName(), "Foo");
        assertEquals(newItem.getPrice(), 0.1, DELTA);

    }
}