package com.globallogic.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import java.io.File;

import static org.openqa.selenium.support.ui.ExpectedConditions.alertIsPresent;

public class CatalogPage extends Page {


    public CatalogPage(WebDriver driver, String baseUrl) {
        super(driver, baseUrl);
    }

    public void addNewProduct() {
        driver.findElement(By.xpath("//*[text()= ' Add New Product']")).click();
        driver.findElement(By.name("name[en]")).sendKeys("Product" + System.currentTimeMillis());
        driver.findElement(By.name("code")).sendKeys("112");
        driver.findElement(By.name("sku")).sendKeys("sku");
        driver.findElement(By.name("mpn")).sendKeys("mpn");
        driver.findElement(By.name("gtin")).sendKeys("gtin");
        driver.findElement(By.name("taric")).sendKeys("taric");
        driver.findElement(By.name("date_valid_from")).sendKeys("12/12/2019");
        driver.findElement(By.name("date_valid_to")).sendKeys("12/11/2020");
        Select drpManufactured = new Select(driver.findElement(By.name("manufacturer_id")));
        drpManufactured.selectByIndex(1);
        Select drpSupplier = new Select(driver.findElement(By.name("supplier_id")));
        drpSupplier.selectByIndex(0);
//load photo
        ClassLoader classLoader = getClass().getClassLoader();
        System.out.println("Classloader =" + classLoader);
        File file = new File(classLoader
                .getResource("example.jpg")
                .getFile());
        driver.findElement(By.name("new_images[]")).sendKeys(file.getAbsolutePath());

        driver.findElement(By.xpath("//a[text()='Information']")).click();
        driver.findElement(By.name("short_description[en]")).sendKeys("short description");
        driver.findElement(By.xpath("//*[@class='trumbowyg-textarea']"))
                .sendKeys("description test");
        driver.findElement(By.name("technical_data[en]")).sendKeys("technical test");
        driver.findElement(By.name("head_title[en]")).sendKeys("head test");
        driver.findElement(By.name("meta_description[en]")).sendKeys("meta description");

        driver.findElement(By.xpath("//a[text()='Prices']")).click();
        driver.findElement(By.name("purchase_price")).sendKeys("100");
        Select drpCurrency = new Select(driver.findElement(By.name("purchase_price_currency_code")));
        drpCurrency.selectByValue("USD");
        driver.findElement(By.name("prices[USD]")).sendKeys("100");
        driver.findElement(By.name("prices[EUR]")).sendKeys("90");
        driver.findElement(By.name("save")).click();

    }

    public int getCountItems() {
        return driver.findElements(By.xpath(".//*[@class='table table-striped table-hover data-table']//tbody//tr")).size();
    }

    public void deleteItem() {
        int countProducts = driver.findElements(By.xpath("//*[@class='fa fa-pencil']")).size();
        driver.findElements(By.xpath("//*[@class='fa fa-pencil']")).get(countProducts - 1).click();
        driver.findElement(By.name("delete")).click();
        Alert alert = wait.until(alertIsPresent());
        alert.accept();
    }
}
