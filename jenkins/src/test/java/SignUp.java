import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SignUp {

    private static FirefoxDriver driver;
    private String url = "http://seltr-kbp1-1.synapse.com:8080";
    private String name = "Andrew";
    private String password = "password123";
    private String confirmPassword = "password123";
    private String fullName = "Andrew Kors";
    private String email = "abc123@gmail.com";
    private String signupUrl = "http://seltr-kbp1-1.synapse.com:8080/signup";
    private String signUpError = "Invalid e-mail address";
    private String errorSameUser = "User name is already taken";
    private String errorNotMatching = "Password didnt match";
    private String errorIncorrectEmail = "Invalid e-mail address";


    //private String alfaNum = "ASdf12";
    //private String specChar = "!@#$%^&*()";

    WebElement usernameField;
    WebElement passwordField;
    WebElement confirmPasswordField;
    WebElement fullNameField;
    WebElement emailField;
    WebElement SignupButton;

    @BeforeClass
    public static void startUpBrowser(){
        driver = new FirefoxDriver();
    }

    @BeforeMethod
    public void setUp(){
        //SignUp page:
        driver.get(signupUrl);

        usernameField = driver.findElement(By.id("username"));
        passwordField = driver.findElement(By.name("password1"));
        confirmPasswordField = driver.findElement(By.name("password2"));
        fullNameField = driver.findElement(By.name("fullname"));
        emailField = driver.findElement(By.name("email"));
        SignupButton = driver.findElement(By.id("yui-gen1-button"));
    }

    //Sign Up with empty fields

    @Test
    public void invalidEmptyFields (){
        doSignUp("", "", "", "", "");
        Assert.assertEquals(driver.findElement(By.xpath(".//*[@class='error']")).getText(), signUpError, "Error");
    }

    //Not matching password

    @Test
    public void invalidNotMatchingPassword (){
        doSignUp(name, password, "abc123", fullName, email);
        Assert.assertEquals(driver.findElement(By.xpath(".//*[@class = 'error']")).getText(), errorNotMatching, "Error");
    }

    //Invalid email (without "@")
    @Test
    public void invalidEmail (){
        doSignUp(name, password, confirmPassword, fullName, "abcgmail.com");
        Assert.assertEquals(driver.findElement(By.xpath(".//*[@class = 'error']")).getText(), errorIncorrectEmail, "Error");
    }

    //Valid Sign Up

    @Test
    public void validSignUp(){
        doSignUp(name, password, confirmPassword, fullName, email);
        //logged in page
        Assert.assertEquals(driver.findElement(By.xpath(".//*[@class='model-link inside inverse']/b")).getText().trim(), fullName, "Error");
    }

    //Already existing user

   @Test
    public void userAlreadyExistTest(){
       doSignUp(name, password, confirmPassword, fullName, email);
        //logged in page
        Assert.assertEquals(driver.findElement(By.xpath(".//*[@class='model-link inside inverse']/b")).getText().trim(), fullName, "Error");
       driver.get(signupUrl);
        //SignUp page:
       doSignUp(name, password, confirmPassword, fullName, email);
        Assert.assertEquals(driver.findElement(By.xpath(".//*[@class = 'error']")).getText(), errorSameUser, "Error");
    }

    private void doSignUp(String name, String password, String confirmPassword, String fullName, String email) {
        usernameField.sendKeys(name);
        passwordField.sendKeys(password);
        confirmPasswordField.sendKeys(confirmPassword);
        fullNameField.sendKeys(fullName);
        emailField.sendKeys(email);
        SignupButton.click();
    }

    @AfterClass
    public static void shutDownActivities() {
        driver.quit();
    }
}
