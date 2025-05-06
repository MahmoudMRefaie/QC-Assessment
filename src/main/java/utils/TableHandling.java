package utils;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class TableHandling {

    private TableHandling() {
        throw new IllegalStateException("Utility class");
    }

    @Step("Getting table element: {tableLocator}")
    public static WebElement getTableElement(WebDriver driver, By tableLocator) {
        return driver.findElement(tableLocator);
    }

    @Step("Getting last row")
    public static WebElement getLastRow(WebElement tableElement) {
        return tableElement.findElement(By.cssSelector("tbody tr:last-of-type"));
    }

    @Step("Getting cell in column: {columnName}")
    public static WebElement getCellInRow(WebDriver driver, By tableLocator, WebElement row, String columnName) {

        List<WebElement> columns = getTableElement(driver, tableLocator)
                .findElements(By.cssSelector("th"));
        int columnIndex = 0;
        for (int i = 0; i < columns.size(); i++) {
            if (columns.get(i).getText().equals(columnName)) {
                columnIndex = i;
                break;
            }
        }
        List<WebElement> cells = row.findElements(By.tagName("td"));
        return cells.get(columnIndex);
    }

    @Step("Get row cont for table: {tableLocator}")
    public static int getRowCount(WebDriver driver, By tableLocator) {
        return getTableElement(driver, tableLocator)
                .findElements(By.cssSelector("tbody tr")).size();
    }
}
