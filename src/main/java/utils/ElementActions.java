package utils;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ElementActions {

    private ElementActions() {
        throw new UnsupportedOperationException("Utility class - cannot be instantiated");
    }

    @Step("Sending text: {text} to the element: {locator}")
    public static void sendKeys(WebDriver driver, By locator, String text){

        clear(driver, locator);
        findElement(driver, locator).sendKeys(text);
    }

    @Step("Sending append text: {text} to the element: {locator}")
    public static void sendKeysAppend(WebDriver driver, By locator, String text){

        Waits.waitForElementToBeVisible(driver, locator);
        Scrolling.scrollToElement(driver, locator);
        findElement(driver, locator).sendKeys(text);
    }

    @Step("Clearing the text from element: {locator}")
    public static void clear(WebDriver driver, By locator){

        Waits.waitForElementToBeVisible(driver, locator);
        Scrolling.scrollToElement(driver, locator);
        WebElement element = findElement(driver, locator);

        element.clear();
    }

    @Step("Clicking on the element: {locator}")
    public static void click(WebDriver driver, By locator){

        Waits.waitForElementToBeClickable(driver, locator);
        Scrolling.scrollToElement(driver, locator);
        findElement(driver, locator).click();
    }

    @Step("Getting text from the element: {locator}")
    public static String getText(WebDriver driver, By locator){
        Waits.waitForElementToBeVisible(driver, locator);
        Scrolling.scrollToElement(driver, locator);
        return findElement(driver, locator).getText();
    }

    @Step("Getting web element: {locator}")
    public static WebElement getWebElement(WebDriver driver, By locator){
        Waits.waitForElementToBeVisible(driver, locator);
        Scrolling.scrollToElement(driver, locator);
        return findElement(driver, locator);
    }
    @Step("Getting web elements: {locator}")
    public static List<WebElement> getWebElements(WebDriver driver, By locator){
        Waits.waitForElementToBeVisible(driver, locator);
        Scrolling.scrollToElement(driver, locator);
        return driver.findElements(locator);
    }

    private static WebElement findElement(WebDriver driver, By locator){
        return driver.findElement(locator);
    }

}
