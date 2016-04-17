package com.domain.model;

import com.domain.filter.BuyOneGetOneFilter;
import com.domain.filter.BuyThreeForPriceOfTwoFilter;
import com.domain.filter.MarkedPriceFilter;
import com.domain.filter.PromotionType;
import org.junit.Test;

import java.util.concurrent.CopyOnWriteArrayList;

import static org.junit.Assert.*;

public class InventoryTest {

    private CopyOnWriteArrayList<Item> items = new CopyOnWriteArrayList<Item>();
    private CopyOnWriteArrayList<PromotionType> itemPromotions = new CopyOnWriteArrayList<PromotionType>();

    @Test
    public void testCreateInventory() {
        items.add(new Item("Apple", 0.35));
        items.add(new Item("Banana", 0.2));
        items.add(new Item("Melon", 0.5));
        items.add(new Item("Lime", 0.15));

        itemPromotions.add(PromotionType.MARKED_PRICE);
        itemPromotions.add(PromotionType.MARKED_PRICE);
        itemPromotions.add(PromotionType.BUY_ONE_GET_ONE);
        itemPromotions.add(PromotionType.BUY_THREE_FOR_PRICE_OF_TWO);

        Inventory inventory = new Inventory(items, itemPromotions);

        assertNotNull(inventory);

        assertNotNull(inventory.getFilter());
        assertTrue(inventory.getFilter().get("Apple") instanceof MarkedPriceFilter);
        assertTrue(inventory.getFilter().get("Banana") instanceof MarkedPriceFilter);
        assertTrue(inventory.getFilter().get("Melon") instanceof BuyOneGetOneFilter);
        assertTrue(inventory.getFilter().get("Lime") instanceof BuyThreeForPriceOfTwoFilter);

        assertNotNull(inventory.getListingItems());
        assertEquals(inventory.getListingItems().get("Apple").getName(), "Apple");
        assertEquals(inventory.getListingItems().get("Apple").getPrice(), 0.35d, 0.01);
        assertEquals(inventory.getListingItems().get("Banana").getName(), "Banana");
        assertEquals(inventory.getListingItems().get("Banana").getPrice(), 0.2d, 0.01);
        assertEquals(inventory.getListingItems().get("Melon").getName(), "Melon");
        assertEquals(inventory.getListingItems().get("Melon").getPrice(), 0.5d, 0.01);
        assertEquals(inventory.getListingItems().get("Lime").getName(), "Lime");
        assertEquals(inventory.getListingItems().get("Lime").getPrice(), 0.15d, 0.01);

        assertNotNull(inventory.getPromotions());
        assertEquals(inventory.getPromotions().get("Apple"), PromotionType.MARKED_PRICE);
        assertEquals(inventory.getPromotions().get("Banana"), PromotionType.MARKED_PRICE);
        assertEquals(inventory.getPromotions().get("Melon"), PromotionType.BUY_ONE_GET_ONE);
        assertEquals(inventory.getPromotions().get("Lime"), PromotionType.BUY_THREE_FOR_PRICE_OF_TWO);
    }
}
