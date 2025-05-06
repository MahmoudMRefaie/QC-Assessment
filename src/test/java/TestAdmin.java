import driver.DriverManager;
import io.JSONFileManager;
import io.PropertiesManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pom.Admin;
import pom.Login;
import utils.Validation;

public class TestAdmin {

    private WebDriver driver;
    private Login login;
    private Admin admin;
    private JSONFileManager testData;

    @Test
    public void addAndDeleteItemFromSavedRecords() {

        int usersCount = login.login(testData.getTestData("loginCred.userName"),
                        testData.getTestData("loginCred.password"))
                .openAdminMenuItem()
                .getAdminUserCount();

        admin.clickAddBtn()
                .AddUser(testData.getTestData("newUser.role"),
                        testData.getTestData("newUser.employeeName"),
                        testData.getTestData("newUser.status"),
                        testData.getTestData("newUser.username") + System.currentTimeMillis(),
                        testData.getTestData("newUser.password"));

        int usersCountAfterAdding = admin.getAdminUserCount();

        Validation.validateEquals(usersCountAfterAdding, usersCount + 1,"Record not added");

        int usersCountAfterDeleting = admin.deleteAdminUser(admin.getLastAdminUser())
                .getAdminUserCount();

        Validation.validateEquals(usersCountAfterDeleting, usersCount,"Record not deleted");
    }


    @BeforeMethod
    public void beforeMethod() {
        login.navigateToLoginPage();
    }

    @BeforeClass
    public void setUp() {
        driver = DriverManager.createInstance(PropertiesManager.getPropertyValue("browserType")).getDriver();
        login = new Login(driver);
        admin = new Admin(driver);
        testData = new JSONFileManager("TestData");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    @BeforeSuite
    public void setupSuite() {
        PropertiesManager.loadProperties();
    }
}
