package functions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class pageFunctions {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public pageFunctions(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    protected boolean isVisible(By locator) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return driver.findElement(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    protected void clickItem(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    public boolean selectFromDropdown(By locator, String text) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            wait.until(driver -> new Select(driver.findElement(locator)).getOptions().stream().anyMatch(option -> option.getText().equals(text)));
            new Select(driver.findElement(locator)).selectByVisibleText(text);

            return true;

        } catch (Exception e) {
            return false;
        }
    }

}