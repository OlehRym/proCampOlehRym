package com.globallogic.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import javax.naming.ldap.LdapReferralException;
import java.util.List;

public class CountryPage extends Page {
    public CountryPage(WebDriver driver, String baseUrl) {
        super(driver, baseUrl);
    }


    public void openFirstCountryFromList() {
        driver.findElement(By.xpath("//*[@name='countries_form']//tr//a[@title='Edit']")).click();
    }

    public List<WebElement> getElementExternalLink() {
        return driver.findElements(By.xpath("//i[@class='fa fa-external-link']"));
    }
}
