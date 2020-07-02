package com.globallogic.shw2;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.stream.Stream;

public class DifferentBrowsers {

    private static Stream<WebDriver> browsers() {
        WebDriverManager.chromedriver().setup();
        WebDriverManager.firefoxdriver().setup();

        return Stream.of(
                new ChromeDriver(),
                new FirefoxDriver());
    }

    @ParameterizedTest(name = "{index} Running in: {0}")
    @MethodSource("browsers")
    @Test
    void simpleTest(WebDriver browser) {
        try {
        browser.get("http://google.com");
        browser.findElement(By.xpath("//input[@name='q']")).sendKeys("Selenium" + Keys.ENTER);
            String actualString = browser.findElement(By.tagName("h3")).getText();
            String expectedString = "Selenium";
            System.out.println("Actual result =" + actualString);
            Assertions.assertTrue(actualString.contains(expectedString));
        } finally {
            browser.quit();
        }

    }

}
