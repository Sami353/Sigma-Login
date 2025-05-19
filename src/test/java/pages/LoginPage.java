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
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField)).clear();
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField)).sendKeys(username);
    }

    public void enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField)).clear();
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField)).sendKeys(password);
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
}
