package utils;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

public class BrowserActions {

    private BrowserActions() {
        throw new UnsupportedOperationException("Utility class - cannot be instantiated");
    }

    @Step("Navigate to URL: {url}")
    public static void navigateToURL(WebDriver driver, String url) {
        driver.get(url);
    }

}
