import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.UserDeletePage;
import pages.UserPage;
import utils.WebDriverController;

import static junit.framework.TestCase.assertTrue;

public class ActionsTests {

    private static WebDriver driver;

    private String noResultMessage = "Nothing seems to match.";
    private String fullSearchName = "Chicago Chicago";
    private String username = "NewYork";
    private String password = "password";
    private String invalidLoginError = "Try again";

    @BeforeClass
    public static void startUpBrowser() {

        driver = WebDriverController.getDriver();
    }

    @Test
    public void searchUser (){
        new LoginPage(driver).get().doLogin(username, password);
        new HomePage(driver).get().doSearch(fullSearchName);
        Assert.assertEquals(driver.findElement(By.xpath(".//*[@id='main-panel-content']/h1")).getText().trim(), fullSearchName, "No matching result found.");
    }

    @Test (dependsOnMethods = {"searchUser"})
   public void deleteUser (){
        new UserPage(driver, "Chicago").get().clickDeleteButton();
        new UserDeletePage(driver, "Chicago").get().deleteUser();
        assertTrue(driver.findElement(By.className("dashboard")).isDisplayed());
    }

    @Test (dependsOnMethods = {"deleteUser"})
    public void findDeletedUser (){
        new HomePage(driver).get().doSearch(fullSearchName);
        Assert.assertEquals(driver.findElement(By.className("error")).getText().trim(), noResultMessage, "User was not deleted");
    }

    @AfterClass
    public static void shutDownActivities() {
        driver.quit();
    }
}
