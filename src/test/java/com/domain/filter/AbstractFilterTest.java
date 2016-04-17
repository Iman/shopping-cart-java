package com.domain.filter;

import com.domain.model.Item;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.concurrent.CopyOnWriteArrayList;

public class AbstractFilterTest {

    @Mock
    private Item mockItem;

    @Test
    public void testFilterPrice() throws Exception {

        //Given, When
        CopyOnWriteArrayList<Item> threadSafeItemList = new CopyOnWriteArrayList<Item>();
        AbstractFilter abstractFilter = Mockito.mock(AbstractFilter.class, Mockito.CALLS_REAL_METHODS);
        threadSafeItemList.add(mockItem);

        //Then
        assert abstractFilter.filterPrice(threadSafeItemList) == 0.0d;
    }

}