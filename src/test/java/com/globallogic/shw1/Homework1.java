package com.globallogic.shw1;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Homework1 {

    WebDriver driver;

    @BeforeEach
    void setUp() {

        WebDriverManager.chromedriver().setup();

        ChromeOptions opt = new ChromeOptions();
        //opt.setExperimentalOption("w3c",false);
        opt.setCapability("acceptInsecureCerts", true);
        opt.addArguments("start-fullscreen");
        opt.setHeadless(true);

        driver = new ChromeDriver(opt);

        System.out.println(((HasCapabilities) driver).getCapabilities());

    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    @Test
    void simpleTest() {
        driver.get("http://google.com");
        driver.findElement(By.xpath("//input[@name='q']")).sendKeys("Selenium" + Keys.ENTER);
        String actualString = driver.findElement(By.tagName("h3")).getText();
        String expectedString = "Selenium";
        System.out.println("Actual result =" + actualString);
        Assertions.assertTrue(actualString.contains(expectedString));
    }

}
