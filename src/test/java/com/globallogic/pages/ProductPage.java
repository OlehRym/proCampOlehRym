package com.globallogic.pages;

import org.apache.commons.lang3.RandomUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.Random;

public class ProductPage extends Page {

    public ProductPage(WebDriver driver, String baseUrl) {
        super(driver, baseUrl);
    }

    public int addToCart(int countItems) {
        int countItemsInCart = countItems;
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@class='btn btn-success']")));
        if (driver.findElement(By.xpath("//button[@class='btn btn-success']")).isEnabled()) {
            driver.findElement(By.xpath("//button[@class='btn btn-success']")).click();
            wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//div[@class='badge quantity']"),
                    String.valueOf(countItems + 1)));
            countItemsInCart = Integer.parseInt(driver.findElement(By.xpath("//div[@class='badge quantity']")).getText());
            System.out.println("Count items in the cart =" + countItemsInCart);
            return countItemsInCart;
        } else {
            System.out.println("Product sold out");
            return countItemsInCart;
        }
    }

    public void openCart() {
        driver.findElement(By.id("cart")).click();
    }
}
