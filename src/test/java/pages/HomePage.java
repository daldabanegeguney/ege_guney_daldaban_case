package pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private static final String URL = "https://useinsider.com/";

    private final By headerBlock = By.xpath("//header[@id='navigation']");
    private final By mainBlock   = By.xpath("//main[@class='flexible-layout']");
    private final By footerBlock = By.xpath("//footer[@id='footer']");

    public HomePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void open() {
        driver.get(URL);
    }

    public void isHomePageLoaded() {
        Assertions.assertTrue(isVisible(headerBlock), "Header not loaded");
        Assertions.assertTrue(isVisible(mainBlock), "Main content not loaded");
        Assertions.assertTrue(isVisible(footerBlock), "Footer not loaded");
    }

    private boolean isVisible(By locator) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return driver.findElement(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}