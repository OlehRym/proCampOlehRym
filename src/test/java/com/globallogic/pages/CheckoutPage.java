package com.globallogic.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CheckoutPage extends Page {

    public CheckoutPage(WebDriver driver, String baseUrl) {
        super(driver, baseUrl);
    }

    public void removalProductOneByOne() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@name='remove_cart_item']")));
        int trashQuantity = driver.findElements(By.xpath("//button[@name='remove_cart_item']")).size();
        System.out.println("Initial quantity = " + trashQuantity);
        while (trashQuantity > 0) {
            driver.findElement(By.xpath("//button[@name='remove_cart_item']")).click();
            trashQuantity--;
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("box-checkout-customer")));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("box-checkout-shipping")));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("box-checkout-summary")));
        }

    }

    public String getProductInCart() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@class='btn btn-default']")));
        return driver.findElement(By.id("content")).getText();
    }
}
