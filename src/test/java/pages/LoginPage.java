package pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private By usernameField = By.id("username");
    private By passwordField = By.id("password1");
    private By loginButton = By.id("signIn");
    private By errorMsg = By.xpath("//span[@class='p-toast-summary'][@data-pc-section='summary']");
    private By usernameAlert = By.cssSelector("#usernameHelp");
    private By passwordAlert = By.cssSelector("#password1Help");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
        
    public void enterUsername(String username) {
        clearAndEnterText(usernameField, username);
    }

    public void enterPassword(String password) {
        clearAndEnterText(passwordField, password);
    }

    public void clickLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    public String getErrorMessage() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMsg)).getText().trim();
        } catch (Exception e) {
            return "Error message not found";
        }
    }

    public String getAlertMessage() {
        try {
            // First try to find username field alert
            if (!driver.findElements(usernameAlert).isEmpty()) {
                return driver.findElement(usernameAlert).getText().trim();
            }
            // Then try password field alert
            if (!driver.findElements(passwordAlert).isEmpty()) {
                return driver.findElement(passwordAlert).getText().trim();
            }
        } catch (NoSuchElementException e) {
            throw new RuntimeException("Field validation alert not found", e);
        }
        return null;
    }
    
    public String getUsernameAlert() {
        return getElementText(usernameAlert, null);
    }

    public String getPasswordAlert() {
        return getElementText(passwordAlert, null);
    }
    
    public void leaveUsernameEmpty() {
        driver.findElement(usernameField).clear();
    }
    
    public void leavePasswordEmpty() {
        driver.findElement(passwordField).clear();
    }

    public boolean isLoginPage() {
        return wait.until(ExpectedConditions.urlContains("login"))
                || driver.getCurrentUrl().equals("http://localhost:7073/");
    }
    
    private void clearAndEnterText(By locator, String text) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).clear();
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).sendKeys(text);
    }

    private String getElementText(By locator, String defaultValue) {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText().trim();
        } catch (Exception e) {
            return defaultValue;
        }
    }
}
