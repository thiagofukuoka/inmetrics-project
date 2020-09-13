package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pages.EmployeePage;
import pages.LoginPage;
import pages.NewEmployeePage;
import support.Driver;
import java.util.List;

public class EmployeeTests {

    private WebDriver driver;

    @Before
    public void setUP() {
        driver = Driver.createChrome();
        driver.manage().window().maximize();
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    // Register an employee with valid data
    public void registerEmployeeSuccessfully() throws Exception {
        List<String> registered_employee_data; // List to save all data from the new employee from the NewEmployee page
        List<String> searched_employee_data; // List to save all data from the new employee from the Employee Page

        LoginPage loginPage = new LoginPage(driver);
        EmployeePage employeePage = loginPage.loginWithValidCredentials("thiagofukuoka", "123456");
        NewEmployeePage newEmployeePage = employeePage.goToRegisterEmployee();
        registered_employee_data = newEmployeePage.registerNewEmployee();

        // Check if the 'Usuário cadastrado com sucesso' alert is displayed
        assert employeePage.isEmployeeUpdateAlert();

        // Search the recent new employee and get its data
        employeePage.searchEmployee(registered_employee_data.get(0));
        searched_employee_data = employeePage.getEmployeeData();

        // Check if each data from the new employee matches with the registered in the system
        for (int i = 0; i < registered_employee_data.size(); i++) {
            assert registered_employee_data.get(i).equals(searched_employee_data.get(i));
        }
    }

    @Test
    // Try to register an employee with invalid CPF
    public void registerEmployeeInvalidCpf() {
        LoginPage loginPage = new LoginPage(driver);
        EmployeePage employeePage = loginPage.loginWithValidCredentials("thiagofukuoka", "123456");
        NewEmployeePage newEmployeePage = employeePage.goToRegisterEmployee();
        newEmployeePage.registerWithInvalidCpf();
        // Check if 'invalid CPF' alert is displayed
        assert newEmployeePage.isInvalidCpf();
    }

    @Test
    // Cancel an employee registration
    public void cancelEmployeeRegistration() {
        LoginPage loginPage = new LoginPage(driver);
        EmployeePage employeePage = loginPage.loginWithValidCredentials("thiagofukuoka", "123456");
        NewEmployeePage newEmployeePage = employeePage.goToRegisterEmployee();
        newEmployeePage.cancelRegistration();
        // Check if user is redirected to employee page
        assert employeePage.isInEmployeePage();
    }

    @Test
    // Edit the first employee listed
    public void editEmployee() throws Exception {
        List<String> edited_employee_data; // List to save all data from the edited employee
        List<String> searched_employee_data; // List to save all data from the new employee from the Employee Page

        LoginPage loginPage = new LoginPage(driver);
        EmployeePage employeePage = loginPage.loginWithValidCredentials("thiagofukuoka", "123456");
        NewEmployeePage newEmployeePage = employeePage.goToEditEmployee();
        edited_employee_data = newEmployeePage.editEmployee();

        // Check if the 'Informações atualizadas com sucesso' alert is displayed
        assert employeePage.isEmployeeUpdateAlert();

        // Search the recent new employee and get its data
        employeePage.searchEmployee(edited_employee_data.get(0));
        searched_employee_data = employeePage.getEmployeeData();

        // Check if each data from the new employee matches with the registered in the system
        for (int i = 0; i < edited_employee_data.size(); i++) {
            assert edited_employee_data.get(i).equals(searched_employee_data.get(i));
        }
    }

    @Test
    // Delete the first employee listed
    public void deleteEmployee() {
        LoginPage loginPage = new LoginPage(driver);
        EmployeePage employeePage = loginPage.loginWithValidCredentials("thiagofukuoka", "123456");
        String deletedEmployee = employeePage.getEmployeeName(); // String to save the name of the employee that will be deleted
        employeePage.deleteEmployee();

        // Check if the 'Funcionário removido com sucesso' alert is displayed
        assert employeePage.isEmployeeUpdateAlert();

        // Search the deleted employee
        employeePage.searchEmployee(deletedEmployee);

        // Search for the deleted employee and check that no results are returned
        assert employeePage.isTableEmpty();
    }
}
