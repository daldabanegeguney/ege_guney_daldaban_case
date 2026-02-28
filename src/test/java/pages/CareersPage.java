package pages;

import functions.pageFunctions;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;


public class CareersPage extends pageFunctions {

    public CareersPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    private static final String URL = "https://insiderone.com/careers/quality-assurance/";

    private final By headerBlock = By.xpath("//header[@id='navigation']");
    private final By qaJobsButton = By.xpath("//a[contains(normalize-space(),'See all QA jobs')]");
    private final By locationDropdown = By.xpath("//select[@id='filter-by-location']");
    private final By resultsContainer = By.xpath("//div[@id='jobs-list']");
    private final By jobCards = By.xpath("//div[@id='jobs-list']//div[contains(@class,'position-list-item')]");
    private final By cardDepartment = By.xpath(".//span[contains(@class,'position-department')]");
    private final By cardLocation = By.xpath(".//div[contains(@class,'position-location')]");
    private final By viewRoleButtons = By.xpath("//div[@id='jobs-list']//a[normalize-space()='View Role']");

    public void goToOpenPositions() {
        driver.get(URL);
        clickItem(qaJobsButton);
        Assertions.assertTrue(isVisible(headerBlock), "Careers page not loaded");

    }

    public void applyFilter(String location) {
        Assertions.assertTrue(selectFromDropdown(locationDropdown, location), "Location dropdown selection failed: " + location);
    }

    public void waitForResultsToLoad() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(resultsContainer));
        wait.until(driver -> driver.findElements(jobCards).size() > 0);
    }

    public void verifyAllCardsMatchFilters(String expectedLocation, String expectedDepartment) {

        waitForResultsToLoad();

        List<WebElement> cards = driver.findElements(jobCards);

        for (WebElement card : cards) {

            WebElement deptEl = wait.until(driver -> card.findElement(cardDepartment));

            WebElement locEl = wait.until(driver -> card.findElement(cardLocation));

            String actualDept = deptEl.getText();
            String actualLoc = locEl.getText();

            Assertions.assertEquals(expectedDepartment, actualDept, "Department mismatch. Expected: " + expectedDepartment + " Actual: " + actualDept);

            Assertions.assertEquals(expectedLocation, actualLoc, "Location mismatch. Expected: " + expectedLocation + " Actual: " + actualLoc);
        }
    }
    public void openRoleAndVerifyLeverPage() {
        String mainWindow = driver.getWindowHandle();
        int windowCountBefore = driver.getWindowHandles().size();

        wait.until(ExpectedConditions.visibilityOfElementLocated(resultsContainer));
        wait.until(driver -> driver.findElements(viewRoleButtons).size() > 0);

        driver.findElements(viewRoleButtons).get(0).click();

        wait.until(driver -> driver.getWindowHandles().size() > windowCountBefore);

        String roleWindow = driver.getWindowHandles().stream()
                .filter(handle -> !handle.equals(mainWindow))
                .findFirst()
                .orElseThrow();

        driver.switchTo().window(roleWindow);

        wait.until(ExpectedConditions.urlContains("jobs.lever.co/insider"));
        Assertions.assertTrue(driver.getCurrentUrl().contains("jobs.lever.co/insider"),
                "Lever page not opened. Current URL: " + driver.getCurrentUrl());

    }

}