package com.globallogic.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

public class AdminConsolePage extends Page {

    public AdminConsolePage(WebDriver driver, String baseUrl) {
        super(driver, baseUrl);
    }

    public AdminConsolePage open()
    {
        driver.get(BaseUrl + "/admin");
        return this;
    }

    public Set<String> getCustomersIds() {
        driver.get(BaseUrl + "/admin/?app=customers&doc=customers");
        return driver.findElements(By.cssSelector("table.data-table tbody > tr")).stream()
                .map(e -> e.findElements(By.tagName("td")).get(2).getText())
                .collect(toSet());
    }

    public AdminConsolePage performLogin() {
        if (driver.findElements(By.id("box-login")).size() > 0) {
            driver.findElement(By.name("username")).sendKeys("admin");
            driver.findElement(By.name("password")).sendKeys("gl_admin");
            driver.findElement(By.name("password")).submit();
            wait.until((WebDriver d) -> d.findElement(By.id("box-apps-menu")));
        }
        return this;
    }


    public List<WebElement> getElemetsOnSideMenu() {
        return driver.findElements(By.cssSelector(".app"));
    }

    public boolean isHeaderPresent() {
        return driver.findElements((By.cssSelector(".panel-heading"))).size() > 0;
    }

    public boolean isPresentSubMenuItems() {
        return driver.findElements((By.cssSelector(".doc"))).size() > 0;
    }

    public List<WebElement> getElementsOnSubMenu() {
        return  driver.findElements(By.cssSelector(".doc"));
    }

    public void openMenuItem(String menuItem) {
        driver.findElement(By.xpath("//ul[@id='box-apps-menu']//span[text()='"+menuItem+"']")).click();
    }

   /* public boolean isCustomerPresent(Customer customer) {
        return driver.findElements(By.cssSelector("table.data-table tbody > tr")).stream()
                .map(e -> e.findElements(By.tagName("td")).get(3).getText())
                .collect(toSet()).contains(customer.getEmail());
    }*/
}
