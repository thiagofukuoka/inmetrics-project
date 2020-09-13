package pages;
import support.*;
import org.openqa.selenium.WebDriver;
import java.util.ArrayList;
import java.util.List;

public class EmployeePage extends AbstractPage {

    public EmployeePage(WebDriver driver) {
        super(driver);
    }

    // Go to register new employee page
    public NewEmployeePage goToRegisterEmployee() {
        clickOnElementXPath("//*[@id='navbarSupportedContent']/ul/li[2]/a");
        return new NewEmployeePage(driver);
    }

    // Search an employee by name
    public void searchEmployee(String name) {
        sendKeysXPath("//*[@id='tabela_filter']/label/input", name);
    }

    // Get all data from a searched employee
    public List<String> getEmployeeData() {
        List<String> list = new ArrayList<>();
        list.add(getTextByXPath("//*[@id='tabela']/tbody/tr/td[1]"));
        list.add(getTextByXPath("//*[@id='tabela']/tbody/tr/td[2]"));
        if (getTextByXPath("//*[@id='tabela']/tbody/tr/td[3]").equals("Indefinido")) list.add("Indiferente"); // Need to change this because 'Indiferente' text is displayed as 'Indefinido' on Employee page
        else list.add(getTextByXPath("//*[@id='tabela']/tbody/tr/td[3]"));
        list.add(getTextByXPath("//*[@id='tabela']/tbody/tr/td[4]"));
        list.add(getTextByXPath("//*[@id='tabela']/tbody/tr/td[5]").replace("/", "-")); // Need to change this because the date is displayed with '/' instead of '-' on Employee page
        return list;
    }

    // Get the name of the first employee listed (or a searched employee)
    public String getEmployeeName() {
        return getTextByXPath("//*[@id='tabela']/tbody/tr/td[1]");
    }

    // Go to edit employee page clicking on the edit button of the first employee listed
    public NewEmployeePage goToEditEmployee() {
        clickOnElementXPath("//*[@id='tabela']/tbody/tr[1]/td[6]/a[2]/button");
        return new NewEmployeePage(driver);
    }

    // Delete the first employee listed
    public void deleteEmployee() {
        clickOnElementXPath("//*[@id='delete-btn']['/html/body/div/div[2]/div/table/tbody/tr[1]/td[6]/a[1]/button']");
    }

    // Check if the user is on the employee page through the 'search' field
    public boolean isInEmployeePage() {
        return isElementPresentXPath("//*[@id='tabela_filter']/label/input");
    }

    // Check if the 'new employee', 'employee updated' or 'employee deleted' alert is displayed
    public boolean isEmployeeUpdateAlert() {
        return isElementPresentXPath("//*[@class='alert alert-success alert-dismissible fade show'][/html/body/div/div[1]/div]");
    }

    // Check if the table is empty or if a search didn't return any results
    public boolean isTableEmpty() {
        return isElementPresentXPath("//*[@id='tabela']/tbody/tr/td");
    }
}
