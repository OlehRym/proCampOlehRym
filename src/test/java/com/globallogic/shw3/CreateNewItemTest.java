package com.globallogic.shw3;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreateNewItemTest extends TestBase {

    @Test
    public void createNewItem() {
        int countProductBeforeAdd = app.getCountItems();
        app.openMenuItem("Catalog");
        app.addNewProduct();
        int countProductAfterAdd = app.getCountItems();
        assertEquals(countProductBeforeAdd + 1, countProductAfterAdd, "Added Item is not save");
    }

    @AfterEach
    public void deleteItem() {
        app.deleteItem();
    }
}
