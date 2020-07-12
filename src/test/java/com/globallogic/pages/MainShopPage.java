package com.globallogic.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class MainShopPage extends Page {

    public MainShopPage(WebDriver driver, String baseUrl) {
        super(driver, baseUrl);
    }

    public MainShopPage open()
    {
        driver.get(BaseUrl);
        return this;
    }

    public void chooseFirstProduct() {
        driver.findElements(By.xpath("//div[@class='listing products']//a")).get(0).click();

    }
}
