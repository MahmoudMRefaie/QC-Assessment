package driver;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.net.MalformedURLException;

public class DriverManager {

    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<WebDriver>();

    @Step("Create driver instance on: {browserName}")
    public static DriverManager createInstance(String browserName) {
        WebDriver driver;
        try {
            driver = BrowserFactory.geBrowser(browserName);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        setDriver(driver);
        return new DriverManager();
    }

    public WebDriver getDriver() {
        if(driverThreadLocal.get() == null) {
            Assert.fail("Driver has not been initialized.");
        }
        return driverThreadLocal.get();
    }

    private static void setDriver(WebDriver driver) {
       driverThreadLocal.set(driver);
    }

}
