package supermarket;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ShoppingListTest {

    private ShoppingList shoppingList;

    @Before
    public void setUp() throws Exception {
        shoppingList = new ShoppingList().jotDown(2, "Rice").jotDown(1, "Beans");
    }

    @Test
    public void keepsTheShoppingList() {
        assertThat(shoppingList.find("Rice"), is(2));
        assertThat(shoppingList.find("Beans"), is(1));
        assertThat(shoppingList.find("Orange"), is(0));
    }
}
