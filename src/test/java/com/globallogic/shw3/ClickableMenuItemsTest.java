package com.globallogic.shw3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ClickableMenuItemsTest extends TestBase{



    @Test
    public void checkClickableMenu(){

        app.openAdminSideMenu();

        app.wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#box-apps-menu")));

        for (int i = 0; i < app.getMenuItems().size(); i++) {
            app.getMenuItems().get(i).click();
            if(app.isPresentSubMenuItems()){
                for(int j=0;j<app.getSubMenuItems().size();j++){
                    app.getSubMenuItems().get(j).click();
                    Assertions.assertTrue(app.isHeaderPresent(),"Header is absent");
                }
            }
            Assertions.assertTrue(app.isHeaderPresent(),"Header is absent");

        }
    }



}
