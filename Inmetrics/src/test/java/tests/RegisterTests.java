package tests;

import org.junit.After;
import org.junit.Test;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import pages.*;
import support.Driver;
import support.Util;
import java.util.Random;

public class RegisterTests {

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
    // Register a new admin with valid data
    public void registerSuccessfully() {
        Random random = new Random();
        LoginPage loginPage = new LoginPage(driver);
        RegisterPage registerPage = loginPage.clickOnRegister();
        registerPage.registerNewUser(util.generateUsername(), "123456");
        // Check if user is redirected to login page
        assert loginPage.isInLoginPage();
    }

    @Test
    // Try to register an admin without username
    public void registerWithoutUsername() {
        LoginPage loginPage = new LoginPage(driver);
        RegisterPage registerPage = loginPage.clickOnRegister();
        registerPage.registerWithoutUsername("123456");
        // Check if the empty field alert is displayed on the username field
        assert registerPage.isEmptyUsernameAlert();
    }

    @Test
    // Try to register an admin without password
    public void registerWithoutPassword() {
        LoginPage loginPage = new LoginPage(driver);
        RegisterPage registerPage = loginPage.clickOnRegister();
        registerPage.registerWithoutPassword(util.generateUsername(), "123456");
        // Check if the empty field alert is displayed on the password field
        assert registerPage.isEmptyPasswordAlert();
    }

    @Test
    // Try to register an admin without confirm password
    public void registerWithoutConfirmPassword() {
        LoginPage loginPage = new LoginPage(driver);
        RegisterPage registerPage = loginPage.clickOnRegister();
        registerPage.registerWithoutConfirmPassword(util.generateUsername(), "123456");
        // Check if the empty field alert is displayed on the confirm password field
        assert registerPage.isEmptyConfirmPasswordAlert();
    }

    @Test
    // Try to register an admin with a password confirmation that doesn't match the password
    public void registerWithoutMatchPasswords() {
        LoginPage loginPage = new LoginPage(driver);
        RegisterPage registerPage = loginPage.clickOnRegister();
        registerPage.registerWithoutMatchPasswords(util.generateUsername());
        // Check if the 'passwords doesnt match' alert is displayed
        assert registerPage.isPasswordsDoesntMatchAlert();
    }

    @Test
    // Try to register an admin with a username that has less than 8 characters
    public void registerWithLessThan8Characters() {
        LoginPage loginPage = new LoginPage(driver);
        RegisterPage registerPage = loginPage.clickOnRegister();
        registerPage.registerWithLessThan8Characters("123456");
        // Check if the user keeps on the register admin page
        assert !loginPage.isInLoginPage();
    }
}

