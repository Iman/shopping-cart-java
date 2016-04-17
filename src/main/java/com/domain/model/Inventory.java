package com.domain.model;

import com.domain.filter.*;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.IntStream;

public class Inventory {

    private ConcurrentMap<String, Item> listingItems = new ConcurrentHashMap<String, Item>();
    private ConcurrentMap<String, PromotionType> promotions = new ConcurrentHashMap<String, PromotionType>();
    private ConcurrentMap<String, AbstractFilter> filter = new ConcurrentHashMap<String, AbstractFilter>();
    private MarkedPriceFilter markedPriceFilter;
    private BuyOneGetOneFilter buyOneGetOneFilter;
    private BuyThreeForPriceOfTwoFilter buyThreeForPriceOfTwoHelper;

    public ConcurrentMap<String, Item> getListingItems() {
        return listingItems;
    }

    public ConcurrentMap<String, PromotionType> getPromotions() {
        return promotions;
    }

    public ConcurrentMap<String, AbstractFilter> getFilter() {
        return filter;
    }

    public AbstractFilter defaultNormalHelper() {
        return new MarkedPriceFilter();
    }

    public Inventory(List<Item> items, List<PromotionType> itemPromotions) {

        markedPriceFilter = new MarkedPriceFilter();
        buyOneGetOneFilter = new BuyOneGetOneFilter();
        buyThreeForPriceOfTwoHelper = new BuyThreeForPriceOfTwoFilter();

        IntStream.range(0, items.size()).forEach(i -> {
            Item item = items.get(i);

            listingItems.put(item.getName(), item);
            promotions.put(item.getName(), itemPromotions.get(i));

            if (itemPromotions.get(i).equals(PromotionType.BUY_ONE_GET_ONE)) {
                filter.put(item.getName(), buyOneGetOneFilter);
            } else if (itemPromotions.get(i).equals(PromotionType.BUY_THREE_FOR_PRICE_OF_TWO)) {
                filter.put(item.getName(), buyThreeForPriceOfTwoHelper);
            } else {
                filter.put(item.getName(), markedPriceFilter);
            }
        });
    }
}