package tests;

import org.junit.After;
import org.junit.Test;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import pages.*;
import support.Driver;
import support.Util;

public class LoginTests {

    private WebDriver driver;
    Util util = new Util();

    @Before
    public void setUP() {
        driver = Driver.createChrome();
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    // Login with valid credentials
    public void loginWithValidCredentials() {
        LoginPage loginPage = new LoginPage(driver);
        EmployeePage employeePage = loginPage.loginWithValidCredentials("thiagofukuoka", "123456");
        // Check if the user is redirected to employee page
        assert employeePage.isInEmployeePage();
    }

    @Test
    // Try to login with invalid credentials
    public void loginWithInvalidCredentials() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginWithInvalidCredentials(util.generateUsername());
        // Check if invalid credentials alert is displayed
        assert loginPage.isInvalidUserAlert();
    }

    @Test
    // Try to login without username
    public void loginWithoutUsername() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginWithoutUsername("123456");
        // Check if the empty field alert is displayed on the username field
        assert loginPage.isEmptyUserAlert();
    }

    @Test
    // Try to login withou password
    public void loginWithoutPassword() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginWithoutPassword(util.generateUsername());
        // Check if the empty field alert is displayed on the password field
        assert loginPage.isEmptyPasswordAlert();
    }
}
