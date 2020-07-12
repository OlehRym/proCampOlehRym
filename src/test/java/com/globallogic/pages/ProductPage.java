package com.globallogic.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductPage extends Page {

    public ProductPage(WebDriver driver, String baseUrl) {
        super(driver, baseUrl);
    }

    public void addToCart(int countItemsInCart) {
        driver.findElement(By.xpath("//button[@class='btn btn-success']")).click();
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//div[@class='badge quantity']"),
                String.valueOf(countItemsInCart)));
        System.out.println("Count items in the cart =" +countItemsInCart);
        driver.findElement(By.id("cart")).click();
    }
}
