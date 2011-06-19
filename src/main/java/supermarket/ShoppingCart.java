package supermarket;

import org.apache.commons.lang.builder.EqualsBuilder;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ShoppingCart {

    private Map<SupermarketItem, Integer> addedItems;

    public ShoppingCart() {
        addedItems = new HashMap<SupermarketItem, Integer>();
    }

    public ShoppingCart add(SupermarketItem item) {
        if (addedItems.containsKey(item)) {
            addedItems.put(item, addedItems.get(item) + 1);
        } else {
            addedItems.put(item, 1);
        }
        return this;
    }

    public boolean contains(SupermarketItem item) {
        return addedItems.containsKey(item);
    }

    public int getQuantity(SupermarketItem item) {
        return (addedItems.containsKey(item)) ? addedItems.get(item) : 0;
    }

    public List<SupermarketItem> getItems() {
        List<SupermarketItem> items = new LinkedList<SupermarketItem>();
        for (SupermarketItem item : addedItems.keySet()) {
            int itemQuantity = addedItems.get(item);
            for(int i = 0; i < itemQuantity; i++) {
                items.add(item);
            }
        }
        return items;
    }

    @Override
    public boolean equals(Object o) {
        return this == o || (o instanceof ShoppingCart && EqualsBuilder.reflectionEquals(this, o));
    }
}
