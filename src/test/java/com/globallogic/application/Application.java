package com.globallogic.application;


import com.globallogic.listeners.Listener;
import com.globallogic.pages.AdminConsolePage;
import com.globallogic.pages.CountryPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public class Application {
    public WebDriver driver;
    public WebDriverWait wait;
    public String BaseUrl = "http://3.122.51.38/litecart";
    EventFiringWebDriver edr;

    private AdminConsolePage adminPage;
    private CountryPage countryPage;

    public Application() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        edr = new EventFiringWebDriver(driver);
        edr.register(new Listener());
        wait = new WebDriverWait(edr, 5);

        adminPage = new AdminConsolePage(edr, BaseUrl);
        countryPage = new CountryPage(edr,BaseUrl);
    }

    public void quit() {
        edr.quit();
    }


    public void openAdminSideMenu() {
        adminPage.open().performLogin();
    }

    public List<WebElement> getMenuItems() {
        return adminPage.getElemetsOnSideMenu();

    }

    public boolean isHeaderPresent() {
        return adminPage.isHeaderPresent();


    }

    public boolean isPresentSubMenuItems() {
        return adminPage.isPresentSubMenuItems();
    }

    public List<WebElement> getSubMenuItems() {
        return adminPage.getElementsOnSubMenu();
    }

    public void openMenuItem(String menuItem) {
        openAdminSideMenu();
        adminPage.openMenuItem(menuItem);
    }

    public void openEditFirstCountry() {
        countryPage.openFirstCountryFromList();
    }

    public List<WebElement> getExternalLink() {
        return countryPage.getElementExternalLink();
    }

    public ExpectedCondition<String> anyWindowOtherThan(Set<String> windows) {

        return new ExpectedCondition<String>() {
            public String apply(WebDriver input) {
                Set<String> handles = driver.getWindowHandles();
                handles.removeAll(windows);
                return handles.size() > 0 ? handles.iterator().next() : null;
            }
        };
    }

    public String getWindowHandle() {

        String currentW = edr.getWindowHandle();
        return currentW;
    }

    public Set<String> getWindowHandles() {
        return edr.getWindowHandles();
    }

    public void switchToNewWindow(String newW) {
        edr.switchTo().window(newW);
    }
    public WebDriver getDriver(){
        return edr;
    }

    public String getMainWindowHandle(WebDriver driver) {
        return driver.getWindowHandle();
    }

    public String getCurrentWindowTitle() {
        String windowTitle = driver.getTitle();
        return windowTitle;
    }

    //To close all the other windows except the main window.
    public boolean closeAllOtherWindows(String openWindowHandle) {
        Set<String> allWindowHandles = edr.getWindowHandles();
        for (String currentWindowHandle : allWindowHandles) {
            if (!currentWindowHandle.equals(openWindowHandle)) {
                edr.switchTo().window(currentWindowHandle);
                edr.close();
            }
        }

        edr.switchTo().window(openWindowHandle);
        if (edr.getWindowHandles().size() == 1)
            return true;
        else
            return false;
    }

    public Set<String> getMainWindowHandles() {
        return edr.getWindowHandles();
    }
}
