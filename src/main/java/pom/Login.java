package pom;

import io.PropertiesManager;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.BrowserActions;
import utils.ElementActions;

public class Login {

    private final WebDriver driver;
    private final By userNameField = By.name("username");
    private final By passwordField = By.name("password");
    private final By loginBtn = By.cssSelector("[type=submit]");

    public Login(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Login with username: {userName} and password: {password}")
    public Home login(String userName,  String password) {
        ElementActions.sendKeys(driver, userNameField, userName);
        ElementActions.sendKeys(driver, passwordField, password);
        ElementActions.click(driver, loginBtn);
        return new Home(driver);
    }

    @Step("Navigating to Login page")
    public Login navigateToLoginPage() {
        BrowserActions.navigateToURL(driver, PropertiesManager.getPropertyValue("BASE_URL"));
        return this;
    }
}
