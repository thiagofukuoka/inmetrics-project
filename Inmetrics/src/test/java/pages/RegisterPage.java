package pages;

import org.openqa.selenium.WebDriver;
import support.*;

public class RegisterPage extends AbstractPage {

    public RegisterPage (WebDriver driver) {
        super(driver);
    }

    // Register with valid data
    public LoginPage registerNewUser(String username, String password) {
        sendKeysXPath("//*[@class='input100'][@name='username']", username);
        sendKeysXPath("//*[@type='password'][@name='pass']", password);
        sendKeysXPath("//*[@type='password'][@name='confirmpass']", password);
        clickOnElementClassName("login100-form-btn");
        return new LoginPage(driver);
    }

    // Register without username
    public void registerWithoutUsername(String password) {
        sendKeysXPath("//*[@type='password'][@name='pass']", password);
        sendKeysXPath("//*[@type='password'][@name='confirmpass']", password);
        clickOnElementClassName("login100-form-btn");
    }

    // Register without password
    public void registerWithoutPassword(String username, String password) {
        sendKeysXPath("//*[@class='input100'][@name='username']", username);
        sendKeysXPath("//*[@type='password'][@name='confirmpass']", password);
        clickOnElementClassName("login100-form-btn");
    }

    // Register without confirm passwowrd
    public void registerWithoutConfirmPassword(String username, String password) {
        sendKeysXPath("//*[@class='input100'][@name='username']", username);
        sendKeysXPath("//*[@type='password'][@name='pass']", password);
        clickOnElementClassName("login100-form-btn");
    }

    // Register with a password confirmation that doesn't match the password
    public void registerWithoutMatchPasswords(String username) {
        sendKeysXPath("//*[@class='input100'][@name='username']", username);
        sendKeysXPath("//*[@type='password'][@name='pass']", "password1");
        sendKeysXPath("//*[@type='password'][@name='confirmpass']", "password2");
        clickOnElementClassName("login100-form-btn");
    }

    // Register with a username that has less than 8 characters
    public void registerWithLessThan8Characters(String password) {
        sendKeysXPath("//*[@class='input100'][@name='username']", "user");
        sendKeysXPath("//*[@type='password'][@name='pass']", password);
        sendKeysXPath("//*[@type='password'][@name='confirmpass']", password);
        clickOnElementClassName("login100-form-btn");
    }

    // Check if 'empty username' alert is displayed
    public boolean isEmptyUsernameAlert() {
        return isElementPresentXPath("//*[@class='wrap-input100 validate-input alert-validate'][/html/body/div/div/div/form/div[2]]");
    }

    // Check if 'empty password' alert is displayed
    public boolean isEmptyPasswordAlert() {
        return isElementPresentXPath("//*[@class='wrap-input100 validate-input alert-validate'][/html/body/div/div/div/form/div[4]]");
    }

    // Check if 'empty confirm password' alert is displayed
    public boolean isEmptyConfirmPasswordAlert() {
        return isElementPresentXPath("//*[@class='wrap-input100 validate-input alert-validate'][/html/body/div/div/div/form/div[6]]");
    }

    // Check if 'passwords doesn't match' alert is displayed
    public boolean isPasswordsDoesntMatchAlert() {
        return isElementPresentXPath("//*[@class='container-login100-form-btn m-t-17 text-center']");
    }
}
