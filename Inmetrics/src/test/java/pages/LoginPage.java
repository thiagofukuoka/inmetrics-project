package pages;

import org.openqa.selenium.WebDriver;
import support.*;

import java.util.Random;

public class LoginPage extends AbstractPage {

    Util util = new Util();

    public LoginPage (WebDriver driver) {
        super(driver);
    }

    // Login with valid credentials
    public EmployeePage loginWithValidCredentials(String username, String password) {
        sendKeysXPath("//*[@class='input100'][@name='username']", "thiagofukuoka");
        sendKeysXPath("//*[@type='password'][@name='pass']", password);
        clickOnElementClassName("login100-form-btn");
        return new EmployeePage(driver);
    }

    // Login without username
    public void loginWithoutUsername(String password) {
        sendKeysXPath("//*[@type='password'][@name='pass']", password);
        clickOnElementClassName("login100-form-btn");
    }

    // Login without password
    public void loginWithoutPassword(String username) {
        sendKeysXPath("//*[@class='input100'][@name='username']", username);
        clickOnElementClassName("login100-form-btn");
    }

    // Login with invalid credentials
    public void loginWithInvalidCredentials(String username) {
        sendKeysXPath("//*[@class='input100'][@name='username']", username);
        sendKeysXPath("//*[@type='password'][@name='pass']", "123456");
        clickOnElementClassName("login100-form-btn");
    }

    // Click on register button
    public RegisterPage clickOnRegister() {
        clickOnElementXPath("//*[@class='txt2 bo1']");
        return new RegisterPage(this.driver);
    }

    // Check if the user is in login page through the 'Entre' button
    public boolean isInLoginPage() {
        return isElementPresentXPath("//*[@class='login100-form-title p-b-1']");
    }

    // Check if the 'empty user' alert is displayed
    public boolean isEmptyUserAlert() {
        return isElementPresentXPath("//*[@class='wrap-input100 validate-input alert-validate'][/html/body/div/div[2]/div/form/div[3]]");
    }

    // Check if the 'empty password' alert is displayed
    public boolean isEmptyPasswordAlert() {
        return isElementPresentXPath("//*[@class='wrap-input100 validate-input alert-validate'][/html/body/div/div[2]/div/form/div[5]]");
    }

    // Check if the 'invalid user' alert is displayed
    public boolean isInvalidUserAlert() {
        return isElementPresentXPath("//*[@class='alert alert-danger alert-dismissible fade show']");
    }
}
