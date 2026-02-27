package tests;

import base.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.HomePage;

public class HomePageTest extends BaseTest {

    @Test
    void loadHomePage() {
        HomePage homePage = new HomePage(driver, wait);
        homePage.open();
        homePage.isHomePageLoaded();
    }
}