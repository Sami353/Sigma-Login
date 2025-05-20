package stepdefinitions;

import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import pages.DashboardPage;
import static utils.TestData.*;

public class LoginValidationSteps {

    WebDriver driver = Hooks.driver;
    LoginPage loginPage = new LoginPage(driver);
    DashboardPage dashboardPage = new DashboardPage(driver);

    @Given("I am on the login page")
    public void i_am_on_the_login_page() {
        driver.get("http://localhost:7073/#/Login");
    }

    @When("I enter valid username")
    public void i_enter_valid_username() {
        loginPage.enterUsername(VALID_USERNAME);
    }

    @When("I enter valid password")
    public void i_enter_valid_password() {
        loginPage.enterPassword(VALID_PASSWORD);
    }

    @When("I enter invalid username")
    public void i_enter_invalid_username() {
        loginPage.enterUsername(INVALID_USERNAME);
    }

    @When("I enter invalid password")
    public void i_enter_invalid_password() {
        loginPage.enterPassword(INVALID_PASSWORD);
    }

    @When("I leave username empty")
    public void i_leave_username_empty() {
        loginPage.leaveUsernameEmpty();
    }

    @When("I leave password empty")
    public void i_leave_password_empty() {
        loginPage.leavePasswordEmpty();
    }

    @When("I click login button")
    public void i_click_login_button() {
        loginPage.clickLogin();
    }

    @Then("I should see dashboard page")
    public void i_should_see_dashboard_page() {
        Assert.assertTrue(dashboardPage.isDisplayed());
    }

    @Then("I should see toast error message")
    public void i_should_see_toast_error_message() {
        Assert.assertFalse(loginPage.getErrorMessage().isEmpty());
    }

    @Then("I should see field alert message")
    public void i_should_see_field_alert_message() {
        Assert.assertFalse(loginPage.getAlertMessage().isEmpty());
    }

    @When("I click login button without entering credentials")
    public void i_click_login_button_without_credentials() {
        loginPage.leaveUsernameEmpty();
        loginPage.leavePasswordEmpty();
        loginPage.clickLogin();
    }

    @Then("I should see field alert message for username field")
    public void i_should_see_field_alerts_for_username_field() {
        Assert.assertTrue("Username error not shown", loginPage.getUsernameAlert().contains("required"));
        Assert.assertTrue("Password error not shown", loginPage.getPasswordAlert().contains("required"));
    }
        
    @Then("I should see field alert message for password field")
    public void i_should_see_field_alerts_for_password_field() {
        Assert.assertTrue("Username error not shown", loginPage.getUsernameAlert().contains("required"));
        Assert.assertTrue("Password error not shown", loginPage.getPasswordAlert().contains("required"));
    }
}
