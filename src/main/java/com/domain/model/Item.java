package com.domain.model;

import com.domain.exception.ItemNotSameTypeException;

public class Item {

    private String name;
    private double price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Item(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public Item(Item item) throws ItemNotSameTypeException {

        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null.");
        }

        this.name = item.name;
        this.price = item.getPrice();
    }
}
