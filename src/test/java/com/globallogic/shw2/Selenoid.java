package com.globallogic.shw2;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;


import java.net.MalformedURLException;
import java.net.URI;

public class Selenoid {
    RemoteWebDriver driver;


    @BeforeEach
    void setUp() {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("chrome");
        capabilities.setVersion("81.0");
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", false);

        try {
            driver = new RemoteWebDriver(
                    URI.create("http://127.0.0.1:4444/wd/hub").toURL(),
                    capabilities
            );
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
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
