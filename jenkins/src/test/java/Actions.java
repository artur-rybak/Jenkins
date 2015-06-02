import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProfilePage;

public class Actions {
    private static ChromeDriver driver;

    private ProfilePage profilePage;
    private String fullSearchName = "Andrew Kors";
    private String loginName = "Kamilla";
    private String loginPassword = "password123";

    @BeforeClass
    public static void startUpBrowser() {
        System.setProperty("webdriver.chrome.driver", "C://Program Files//chromedriver//chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test

    @BeforeMethod
    public void setUp() {
        profilePage = new ProfilePage(driver);
        profilePage.get();
    }

    @AfterClass
    public static void shutDownActivities() {
        driver.quit();
    }

}
