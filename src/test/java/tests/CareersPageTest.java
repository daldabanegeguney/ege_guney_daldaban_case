package tests;

import functions.seleniumFunctions;
import org.junit.jupiter.api.Test;
import pages.CareersPage;

public class CareersPageTest extends seleniumFunctions {

    @Test
    void loadCareersPage() {
        CareersPage careersPage = new CareersPage(driver, wait);
        careersPage.goToOpenPositions();
        careersPage.applyFilter("Istanbul, Turkiye");
        careersPage.verifyAllCardsMatchFilters("Istanbul, Turkiye", "Quality Assurance");
        careersPage.openRoleAndVerifyLeverPage();
    }

}