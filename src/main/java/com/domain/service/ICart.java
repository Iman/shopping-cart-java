package com.domain.service;

import com.domain.exception.ItemNotSameTypeException;
import com.domain.model.Item;

import java.util.List;

public interface ICart {
    public List<Item> getItems();

    public void empty();

    public void add(String itemName) throws ItemNotSameTypeException;

    public void add(List<String> itemNames) throws ItemNotSameTypeException;

    public double calculateFinalPrice() throws ItemNotSameTypeException;

    public double calculateMarkerPrice() throws ItemNotSameTypeException;
}
