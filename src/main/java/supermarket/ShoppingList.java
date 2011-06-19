package supermarket;

import java.util.HashMap;
import java.util.Map;

public class ShoppingList {

    private Map<String, Integer> shopItems;

    public ShoppingList() {
        shopItems = new HashMap<String, Integer>();
    }

    public ShoppingList jotDown(int quantity, String itemName) {
        shopItems.put(itemName, quantity);
        return this;
    }

    public int find(String itemName) {
        return (shopItems.containsKey(itemName)) ? shopItems.get(itemName) : 0;
    }

    public boolean contains(String itemName) {
        return shopItems.containsKey(itemName);
    }
}
