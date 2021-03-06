import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.WebDriverController;

import java.io.File;
import java.io.IOException;

public class LoginTests {

    //Login page data

    private static WebDriver driver;

    private LoginPage loginPage;
    private String username = "Andrew";
    private String password = "password123";
    private String fullName = "Andrew Kors";
    private String invalidLoginError = "Try again";

    @BeforeClass
    public static void startUpBrowser() {

        driver = WebDriverController.getDriver();
    }

    @BeforeMethod
    public void setUp() {
        loginPage = new LoginPage(driver);
        loginPage.get();
    }

    //Empty fields

    @Test
    public void loginEmptyFields(){
        loginPage.doLogin("", "");
        //error page
        Assert.assertEquals(driver.findElement(By.xpath(".//div[@id='main-panel-content']/div/a")).getText().trim(), invalidLoginError, "Error");
    }

    @Test
    public void loginMissingName(){
        loginPage.doLogin("", "password123");
        //error page
        Assert.assertEquals(driver.findElement(By.xpath(".//div[@id='main-panel-content']/div/a")).getText().trim(), invalidLoginError, "Error");
    }

    @Test
    public void loginMissingPassword(){
        loginPage.doLogin("Andrew", "");
        //error page
        Assert.assertEquals(driver.findElement(By.xpath(".//div[@id='main-panel-content']/div/a")).getText().trim(), invalidLoginError, "Error");
    }

    //Valid login
    @Test
    public void loginValid (){
        loginPage.doLogin(username, password);
        //logged in page
        loginPage.get();
        Assert.assertEquals(driver.findElement(By.xpath(".//*[@class='model-link inside inverse']/b")).getText().trim(), fullName, "Error");
        takeScreenshot();
    }

    public static void takeScreenshot (){
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile, new File("D:\\Artur\\Automation\\scr\\screen1.png"), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @AfterClass
    public static void shutDownActivities() {
        driver.quit();
    }
}
