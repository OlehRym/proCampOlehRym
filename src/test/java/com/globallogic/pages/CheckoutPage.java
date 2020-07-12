package com.globallogic.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CheckoutPage extends Page{

    public CheckoutPage(WebDriver driver, String baseUrl) {
        super(driver, baseUrl);
    }

    public void removalProductOneByOne() {
        int initialQuantity=Integer.parseInt(wait.until((WebDriver d) -> d.findElement(By.xpath("//*[@type='number']"))
                .getAttribute("value")));
        System.out.println("Initial quantity = "+initialQuantity);
        while(initialQuantity>0){
            initialQuantity--;
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@class='table table-striped table-bordered data-table']")));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@type='number']")));
            driver.findElement(By.xpath("//*[@type='number']")).clear();
            driver.findElement(By.xpath("//*[@type='number']")).sendKeys(String.valueOf(initialQuantity));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='btn btn-default']")));
            driver.findElement(By.xpath("//button[@class='btn btn-default']")).click();
            int quantity=Integer.parseInt(wait.until((WebDriver d) -> d.findElement(By.xpath("//*[@type='number']"))
                    .getAttribute("value")));
            System.out.println("Current qunatity = "+ quantity);
        }

    }

    public String getProductInCart() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@class='btn btn-default']")));
        return driver.findElement(By.id("content")).getText();
    }
}
