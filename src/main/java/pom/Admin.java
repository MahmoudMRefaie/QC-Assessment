package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.ElementActions;

public class Admin {
    private final WebDriver driver;
    private final By addBtn = By.xpath("//button[contains(normalize-space(), 'Add')]");
    private final By adminUsersTable = By.cssSelector(".oxd-table-body");
    private final By adminUsersCountHeader = By.xpath("//span[contains(normalize-space(), 'Records Found')]");
    private final By adminUsersList = By.cssSelector(".oxd-table-card");
    private final By saveBtn = By.xpath("//button[contains(normalize-space(), 'Save')]");
    private final By deleteConfirmation = By.xpath("//button[contains(normalize-space(), 'Yes, Delete')]");

    public Admin(WebDriver driver) {
        this.driver = driver;
    }

    public Admin clickAddBtn() {
        ElementActions.click(driver, addBtn);
        return this;
    }

    private By getUserManagementFieldsLocator(String labelName) {
        return By.xpath("//label[text()='" + labelName + "']/ancestor::div[contains(@class, 'oxd-input-group')]//i | //label[text()='" + labelName + "']/ancestor::div[contains(@class, 'oxd-input-group')]//input");
    }

    private By getOptionsLocator(String option) {
        return By.xpath("//div[@role='option'][.//span[text()='" + option + "']]");
    }

    private By getEmployeeOptionLocator(int optionIndex) {
        return By.xpath("//div[@role='listbox'] //div[@role='option'][" + optionIndex + "]");
    }

    public Admin AddUser(String userRole, String employeeName, String status, String userName, String password) {

        ElementActions.click(driver, getUserManagementFieldsLocator("User Role"));
        ElementActions.click(driver, getOptionsLocator(userRole));

        ElementActions.sendKeys(driver, getUserManagementFieldsLocator("Employee Name"), employeeName);
        ElementActions.click(driver, getEmployeeOptionLocator(3));

        ElementActions.click(driver, getUserManagementFieldsLocator("Status"));
        ElementActions.click(driver, getOptionsLocator(status));

        ElementActions.sendKeys(driver, getUserManagementFieldsLocator("Username"), userName);
        ElementActions.sendKeys(driver, getUserManagementFieldsLocator("Password"), password);
        ElementActions.sendKeys(driver, getUserManagementFieldsLocator("Confirm Password"), password);

        ElementActions.click(driver, saveBtn);
        return this;
    }

    public WebElement getLastAdminUser() {
        return ElementActions.getWebElement(driver, adminUsersTable).findElements(adminUsersList).getLast();
    }

    public int getAdminUserCount() {
        return Integer.parseInt(ElementActions.getText(driver, adminUsersCountHeader).replaceAll("\\D+", ""));
    }

    public Admin deleteAdminUser(WebElement adminUserElement) {
        adminUserElement.findElement(By.cssSelector(".bi-trash")).click();
        ElementActions.click(driver, deleteConfirmation);
        return this;
    }

}
