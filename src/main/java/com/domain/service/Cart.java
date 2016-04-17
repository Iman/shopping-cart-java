package com.domain.service;


import com.domain.exception.ItemNotSameTypeException;
import com.domain.model.Inventory;
import com.domain.model.Item;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

public class Cart implements ICart {

    private List<Item> items = Collections.synchronizedList(new ArrayList());

    private Inventory inventory;

    public Cart(Inventory inventory) {
        this.inventory = inventory;
    }

    public void empty() {
        this.items.clear();
    }

    public List<Item> getItems() {
        return items;
    }

    public void add(String itemName) throws ItemNotSameTypeException {
        Item inventoryItem = inventory.getListingItems().get(itemName);
        items.add(new Item(inventoryItem));
    }

    public void add(List<String> itemNames) {

        itemNames.forEach((itemName) -> {
            try {
                add(itemName);
            } catch (ItemNotSameTypeException e) {
                e.printStackTrace();
            }
        });
    }

    public double calculateFinalPrice() throws ItemNotSameTypeException {
        double finalPrice = 0;
        ConcurrentMap<String, List<Item>> groupItems = items.stream().collect(Collectors.groupingByConcurrent(Item::getName));

        for (ConcurrentMap.Entry<String, List<Item>> listItemEntry : groupItems.entrySet()) {
            finalPrice += inventory.getFilter().get(listItemEntry.getKey()).filterPrice(listItemEntry.getValue());
        }

        return finalPrice;
    }

    @Override
    public double calculateMarkerPrice() throws ItemNotSameTypeException {
        double normalPrice = 0;
        ConcurrentMap<String, List<Item>> groupItems = items.stream().collect(Collectors.groupingByConcurrent(Item::getName));

        for (ConcurrentMap.Entry<String, List<Item>> listItemEntry : groupItems.entrySet()) {
            normalPrice += inventory.defaultNormalHelper().filterPrice(listItemEntry.getValue());
        }

        return normalPrice;
    }
}
