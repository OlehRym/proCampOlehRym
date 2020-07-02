package com.globallogic.shw3;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class OpenLinkInNewWindowTest extends TestBase {

    @Test
    public void openNewWindowOnClick(){
        app.openMenuItem("Countries");
        app.openEditFirstCountry();
        for (int i=0;i< app.getExternalLink().size();i++){
            String windowTitle= app.getCurrentWindowTitle();
            String mainWindow = app.getMainWindowHandle(app.getDriver());
            app.getExternalLink().get(i).click();
            assertEquals(2,app.getMainWindowHandles().size());
            // To get the main window handle
            assertTrue(app.closeAllOtherWindows(mainWindow));
            assertTrue(windowTitle.contains("Edit Country | Selenium Pro Camp"), "Main window title is not matching");

        }
    }
}
