package stepdefinitions;

import io.cucumber.java.en.*;
import java.time.Duration;
import static org.junit.Assert.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.LoginPage;

public class LoginSteps {
    //testing
    WebDriver driver = Hooks.driver;
    LoginPage loginPage = new LoginPage(driver);

    @Given("I am on the Sigma login page")
    public void i_am_on_the_sigma_login_page() {
        driver.get("http://localhost:7073/#/Login");
        loginPage = new LoginPage(driver);
        new WebDriverWait(driver, Duration.ofSeconds(10))
            .until(ExpectedConditions.urlToBe("http://localhost:7073/#/Login"));
    }

    @Given("I enter username {string} and password {string}")
    public void i_enter_username_and_password(String username, String password) {
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
    }

    @When("I click on the login button")
    public void i_click_on_the_login_button() {
        loginPage.clickLogin();
    }

    @Then("I should be logged in successfully")
    public void i_should_be_logged_in_successfully() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("http://localhost:7073/#/")); 
        assertNotEquals("http://localhost:7073/", driver.getCurrentUrl());
    }

    @Then("I should see error message {string}")
    public void i_should_see_error_message(String expectedMessage) {
        String actualMessage = loginPage.getErrorMessage();
        assertTrue("Expected error message: " + expectedMessage + " but got: " + actualMessage, 
                  actualMessage.contains(expectedMessage));
    }
    
    @Then("I should see alert {string}")
    public void i_should_see_alert(String expectedMessage) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        String alertMessage = loginPage.getAlertMessage();
        assertEquals(expectedMessage, alertMessage);
    }
}