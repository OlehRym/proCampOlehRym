package com.globallogic.application;


import com.globallogic.listeners.Listener;
import com.globallogic.pages.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Set;

public class Application {
    public WebDriver driver;
    public WebDriverWait wait;
    public String BaseUrl = "http://3.122.51.38/litecart";
    EventFiringWebDriver edr;

    private AdminConsolePage adminPage;
    private CountryPage countryPage;
    private MainShopPage shopPage;
    private ProductPage productPage;
    private CheckoutPage checkoutPage;
    private CatalogPage catalogPage;

    public Application() {
        WebDriverManager.chromedriver().setup();
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.PAGE_LOAD_STRATEGY, "normal");
        driver = new ChromeDriver(capabilities);
        edr = new EventFiringWebDriver(driver);
        edr.register(new Listener());
        wait = new WebDriverWait(edr, 5);

        adminPage = new AdminConsolePage(edr, BaseUrl);
        countryPage = new CountryPage(edr, BaseUrl);
        shopPage = new MainShopPage(edr, BaseUrl);
        productPage = new ProductPage(edr, BaseUrl);
        checkoutPage = new CheckoutPage(edr, BaseUrl);
        catalogPage = new CatalogPage(edr, BaseUrl);
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

    public WebDriver getDriver() {
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

    public void addProductToCart(int countItemInCart) {
        int countItems=0 ;
        while(countItems<countItemInCart){
            shopPage.open().сhooseRandomProduct();
            countItems= productPage.addToCart(countItems);
        }
        productPage.openCart();
    }

    public void removalProductOneByOne() {
        checkoutPage.removalProductOneByOne();

    }

    public String getProductInCart() {
        return checkoutPage.getProductInCart();
    }

    public void addNewProduct() {
        catalogPage.addNewProduct();
    }

    public int getCountItems() {
        openAdminSideMenu();
        adminPage.openMenuItem("Catalog");
        return catalogPage.getCountItems();
    }

    public void deleteItem() {
        catalogPage.deleteItem();

    }
}
