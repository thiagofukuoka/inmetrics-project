package support;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class AbstractPage {
    protected WebDriver driver;

    public AbstractPage (WebDriver driver) {
        this.driver = driver;
    }

    // Click on element by XPath
    public void clickOnElementXPath(String xpath) {
        driver.findElement(By.xpath(xpath)).click();
    }

    // Click on element by ClassName
    public void clickOnElementClassName(String classname) {
        driver.findElement(By.className(classname)).click();
    }

    public void clickOnElementId(String id) {
        driver.findElement(By.id(id)).click();
    }

    // Send keys to a element by XPath
    public void sendKeysXPath(String xpath, String text) {
        driver.findElement(By.xpath(xpath)).sendKeys(text);
    }

    // Send keys to a element by Id
    public void sendKeysId(String id, String text) {
        driver.findElement(By.id(id)).sendKeys(text);
    }

    // Get Combo Box by Id
    public Select getComboBox(String id) {
        return new Select(driver.findElement(By.id(id)));
    }

    // Get text from element by XPath
    public String getTextByXPath(String xpath) {
        return driver.findElement(By.xpath(xpath)).getText();
    }

    // Clear field by Id
    public void clearField(String id) {
        driver.findElement(By.id(id)).clear();
    }

    // Check if element is present by XPath
    public boolean isElementPresentXPath (String xpath) {
        try {
            if (driver.findElement(By.xpath(xpath)) != null) {
                return true;
            }
        }

        catch (NoSuchElementException e) {
            System.out.println("Element '" + xpath + "' not found!");
        }

        return false;
    }

    // Check if element is present by Id
    public boolean isElementPresentID (String id) {
        try {
            if (driver.findElement(By.id(id)) != null) {
                return true;
            }
        }

        catch (NoSuchElementException e) {
            System.out.println("Element '" + id + "' not found!");
        }

        return false;
    }
}
