package com.globallogic.pages;

import org.apache.commons.lang3.RandomUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;


public class MainShopPage extends Page {

    public MainShopPage(WebDriver driver, String baseUrl) {
        super(driver, baseUrl);
    }

    public MainShopPage open()
    {
        driver.get(BaseUrl);
        return this;
    }

    public void сhooseRandomProduct() {
        List<WebElement> products = driver.findElements(By.xpath("//div[@class='listing products']//a"));
        products.get(RandomUtils.nextInt(0,products.size()-1)).click();
    }
}
