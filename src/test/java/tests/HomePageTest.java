package tests;

import functions.seleniumFunctions;
import org.junit.jupiter.api.Test;
import pages.HomePage;

public class HomePageTest extends seleniumFunctions {

    @Test
    void loadHomePage() {
        HomePage homePage = new HomePage(driver, wait);
        homePage.open();
        homePage.isHomePageLoaded();
    }

}