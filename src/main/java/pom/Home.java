package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.ElementActions;

public class Home {

    private final WebDriver driver;

    public Home(WebDriver driver) {
        this.driver = driver;
    }

    public By getMenuItemLocator(String menuItemName) {
        return By.xpath("//span[text()='" + menuItemName + "']");
    }

    public Admin openAdminMenuItem() {
        ElementActions.click(driver, getMenuItemLocator("Admin"));
        return new Admin(driver);
    }

}
