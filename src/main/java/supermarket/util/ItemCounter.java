package supermarket.util;

import supermarket.SupermarketItem;

import java.util.List;

public class ItemCounter {

    public static int countItem(SupermarketItem item, List<SupermarketItem> items) {
        int count = 0;
        for (SupermarketItem i : items) {
            if (item.equals(i)) {
                count++;
            }
        }
        return count;
    }
}
