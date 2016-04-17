package com.domain.filter;

import com.domain.exception.ItemNotSameTypeException;
import com.domain.model.Item;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractFilter {

    public abstract double filterPrice(List<Item> items) throws ItemNotSameTypeException;

    public void filterItemsBeforeCalculatePrice(List<Item> items) throws ItemNotSameTypeException {
        HashSet<String> itemNames = items.stream()
                .map(Item::getName)
                .collect(Collectors.toCollection(HashSet::new));

        if (itemNames.size() > 1) {
            throw new ItemNotSameTypeException("Items are not same type");
        }
    }
}
