package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import support.AbstractPage;
import support.Util;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NewEmployeePage extends AbstractPage {

    Util util = new Util();

    public NewEmployeePage(WebDriver driver) {
        super(driver);
    }

    // Register a new employee with valid data
    public List<String> registerNewEmployee() throws Exception {
        Random random = new Random();
        List<String> list = new ArrayList<>();
        String name = util.generateFullName();
        String cpf = util.generateCPF();
        String admissao = util.generateDate();
        String cargo = "QA Tester";

        sendKeysId("inputNome", name);
        sendKeysId("cpf", cpf);
        Select comboBox = getComboBox("slctSexo");
        comboBox.selectByIndex(random.nextInt(3) + 1); // Select a random genre
        sendKeysId("inputAdmissao", admissao);
        sendKeysId("inputCargo", cargo);
        sendKeysId("dinheiro", String.valueOf( (random.nextInt(9) + 1) * 100000)); // Input a random salary between R$1000 and R$10000
        // Select a random employment contract
        if (random.nextInt(2) % 2 != 0) clickOnElementId("clt");
        else clickOnElementId("pj");

        // Add all data from this new employee to a list
        list.add(name);
        list.add(cpf);
        list.add(comboBox.getFirstSelectedOption().getText());
        list.add(cargo);
        list.add(admissao);

        clickOnElementXPath("//*[@class='cadastrar-form-btn']"); // Click on register
        return list;
    }

    // Register with invalid CPF
    public void registerWithInvalidCpf() throws UnhandledAlertException {
        try {
            sendKeysId("cpf", "111.111.111-11");
            clickOnElementXPath("//*[@class='cadastrar-form-btn']");
        }

        catch (UnhandledAlertException e) {}
    }

    // Edit employee with valid data
    public List<String> editEmployee() throws Exception {
        Random random = new Random();
        List<String> list = new ArrayList<>();
        String name = util.generateFullName();
        String cpf = util.generateCPF();
        String admissao = util.generateDate();
        String cargo = "QA Tester";

        // Clear all fields
        clearField("inputNome");
        clearField("cpf");
        try {
            Alert alert = driver.switchTo().alert();
            alert.accept();
        }
        catch (UnhandledAlertException e) {
            e.printStackTrace();
        }
        clearField("inputAdmissao");
        clearField("inputCargo");
        clearField("dinheiro");

        // Input all new data on fields
        sendKeysId("inputNome", name);
        sendKeysId("cpf", cpf);
        Select comboBox = getComboBox("slctSexo");
        comboBox.selectByIndex(random.nextInt(2)); // Select a random genre
        sendKeysId("inputAdmissao", admissao);
        sendKeysId("inputCargo", cargo);
        sendKeysId("dinheiro", String.valueOf( (random.nextInt(9) + 1) * 100000)); // Input a random salary between R$1000 and R$10000
        // Select a random employment contract
        if (random.nextInt(2) % 2 != 0) clickOnElementId("clt");
        else clickOnElementId("pj");

        // Add all data from this new employee to a list
        list.add(name);
        list.add(cpf);
        list.add(comboBox.getFirstSelectedOption().getText());
        list.add(cargo);
        list.add(admissao);

        clickOnElementXPath("//*[@class='cadastrar-form-btn']"); // Click on register
        return list;
    }

    // Click on 'Cancel' button to cancel the registration
    public void cancelRegistration () {
        clickOnElementXPath("//*[@class='cancelar-form-btn'][/html/body/div/div[2]/div/form/div[4]/input]");
    }

    // Check if 'invalid CPF' alert is displayed
    public boolean isInvalidCpf() {
        Alert alert = driver.switchTo().alert();
        if (alert.getText().equals("CPF Invalido!")) return true;
        else return false;
    }
}
