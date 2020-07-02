package com.globallogic.shw3;

import com.globallogic.application.Application;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {

    static Application app;

    @BeforeAll
    public static void startDriver() {
        app = new Application();
    }

    @AfterAll
    public static void stopDriver() {
        app.quit();
    }
}
