package com.domain.filter;

import com.domain.exception.ItemNotSameTypeException;
import com.domain.model.Item;

import java.util.List;

public class MarkedPriceFilter extends AbstractFilter {
    @Override
    public double filterPrice(List<Item> items) throws ItemNotSameTypeException {
        super.filterItemsBeforeCalculatePrice(items);

        if (items.size() == 0) {
            return 0;
        }

        return items.size() * items.stream().findFirst().get().getPrice();
    }
}
