package com.domain.filter;

import com.domain.exception.ItemNotSameTypeException;
import com.domain.model.Item;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class BuyOneGetOneFilter extends AbstractFilter {

    @Override
    public double filterPrice(List<Item> items) throws ItemNotSameTypeException {

        super.filterItemsBeforeCalculatePrice(items);

        if (items.size() == 0) {
            return 0;
        }

        AtomicInteger numberOfGroup = new AtomicInteger(items.size() / 2);
        AtomicInteger numberRemain = new AtomicInteger(items.size() % 2);

        return numberOfGroup.get() * items.get(0).getPrice() + numberRemain.get() * items.get(0).getPrice();
    }
}
