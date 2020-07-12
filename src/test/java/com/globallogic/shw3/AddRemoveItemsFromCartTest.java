package com.globallogic.shw3;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;



public class AddRemoveItemsFromCartTest extends TestBase {
    final int countItemInCart =3;

    @Test
    public void AddRemoveItems() {
        app.addProductToCart(countItemInCart);
        app.removalProductOneByOne();
        assertTrue(app.getProductInCart().contains("There are no items in your cart."),"There are not 0 items");
    }
}
