import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.SignupPage;

public class SignUp {

    private static FirefoxDriver driver;

    private SignupPage signupPage;
    private String name = "Andrew";
    private String password = "password123";
    private String confirmPassword = "password123";
    private String fullName = "Andrew Kors";
    private String email = "abc123@gmail.com";
    private String signUpError = "Invalid e-mail address";
    private String errorSameUser = "User name is already taken";
    private String errorNotMatching = "Password didnt match";
    private String errorIncorrectEmail = "Invalid e-mail address";


    //private String alfaNum = "ASdf12";
    //private String specChar = "!@#$%^&*()";

    @BeforeClass
    public static void startUpBrowser(){
        driver = new FirefoxDriver();
    }

    @BeforeMethod
    public void setUp(){
        signupPage = new SignupPage(driver);
        signupPage.get();
    }

    //Sign Up with empty fields

    @Test
    public void invalidEmptyFields (){
        signupPage.doSignUp("", "", "", "", "");
        Assert.assertEquals(driver.findElement(By.xpath(".//*[@class='error']")).getText(), signUpError, "Error");
    }

    //Not matching password

    @Test
    public void invalidNotMatchingPassword (){
        signupPage.doSignUp(name, password, "abc123", fullName, email);
        Assert.assertEquals(driver.findElement(By.xpath(".//*[@class = 'error']")).getText(), errorNotMatching, "Error");
    }

    //Invalid email (without "@")
    @Test
    public void invalidEmail (){
        signupPage.doSignUp(name, password, confirmPassword, fullName, "abcgmail.com");
        Assert.assertEquals(driver.findElement(By.xpath(".//*[@class = 'error']")).getText(), errorIncorrectEmail, "Error");
    }

    //Valid Sign Up

    @Test
    public void validSignUp(){
        signupPage.doSignUp(name, password, confirmPassword, fullName, email);
        //logged in page
        Assert.assertEquals(driver.findElement(By.xpath(".//*[@class='model-link inside inverse']/b")).getText().trim(), fullName, "Error");
    }

    //Already existing user

   @Test
    public void userAlreadyExistTest(){
       signupPage.doSignUp(name, password, confirmPassword, fullName, email);
        //logged in page
        Assert.assertEquals(driver.findElement(By.xpath(".//*[@class='model-link inside inverse']/b")).getText().trim(), fullName, "Error");
       signupPage.doLogout();
        //SignUp page:
       signupPage.doSignUp(name, password, confirmPassword, fullName, email);
        Assert.assertEquals(driver.findElement(By.xpath(".//*[@class = 'error']")).getText(), errorSameUser, "Error");
    }

    @AfterClass
    public static void shutDownActivities() {
        driver.quit();
    }
}
