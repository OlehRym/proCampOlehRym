package com.globallogic.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Page {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected String BaseUrl;

    public Page(WebDriver driver, String baseUrl) {
        this.driver = driver;
        this.BaseUrl = baseUrl;
        wait = new WebDriverWait(driver, 10);
    }
}
