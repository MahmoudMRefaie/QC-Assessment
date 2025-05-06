package utils;

import io.qameta.allure.Step;
import org.testng.Assert;

public class Validation {

    private Validation() {}

    @Step("Validating equals: {actual} and {expected}")
    public static void validateEquals(Object actual, Object expected, String message) {
        Assert.assertEquals(actual, expected, message);
    }

    @Step("Validating not equals: {actual} and {expected}")
    public static void validateNotEquals(Object actual, Object expected, String message) {
        Assert.assertNotEquals(actual, expected, message);
    }
}
