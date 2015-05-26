import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by artur.rybak on 5/26/2015.
 */
public class Login {

    //Login page data

    private static FirefoxDriver driver;
    private String urlLogin = "http://seltr-kbp1-1.synapse.com:8080/login?from=%2F";
    private String username = "Andrew";
    private String password = "password123";
    private String fullName = "Andrew Kors";
    private String invalidLoginError = "Try again";

   /* //Signup page data

    private String urlSignup = "http://clm-aus-002258.bmc.com:8080/signup";
    private String signupName = "Jason";
    private String signupPassword = "password123";
    private String confirmSignupPassword = "password123";
    private String signupFullName = "Jason Some";
    private String signupEmail = "email@email.com"; */

    //Login page elements

    WebElement userField;
    WebElement passwordField;
    //WebElement rememberCheckbox;
    WebElement loginButton;

  /*  //SignUp page elements

    WebElement usernameField;
    WebElement passwordSignupField;
    WebElement confirmPasswordField;
    WebElement signupfullNameField;
    WebElement emailField;
    WebElement SignupButton;*/

    @BeforeClass
    public static void startUpBrowser() {
        driver = new FirefoxDriver();
    }

    @BeforeMethod
    public void setUp() {
        driver.get(urlLogin);

        //Login page

        userField = driver.findElement(By.id("j_username"));
        passwordField = driver.findElement(By.name("j_password"));
        //rememberCheckbox = driver.findElement(By.id("remember_me"));
        loginButton = driver.findElement(By.id("yui-gen1-button"));

     /*   //Sign Up page

        usernameField = driver.findElement(By.id("username"));
        passwordSignupField = driver.findElement(By.name("password1"));
        confirmPasswordField = driver.findElement(By.name("password2"));
        signupfullNameField = driver.findElement(By.name("fullname"));
        emailField = driver.findElement(By.name("email"));
        SignupButton = driver.findElement(By.id("yui-gen1-button")); */
    }

    //Empty fields

    @Test
    public void loginEmptyFields(){
        doLogin("", "");
        //error page
        Assert.assertEquals(driver.findElement(By.xpath("//div[@id='main-panel-content']/div/a")).getText(), invalidLoginError, "Error");
    }

    @Test
    public void loginMissingName(){
        doLogin("", "password123");
        //error page
        Assert.assertEquals(driver.findElement(By.xpath("//div[@id='main-panel-content']/div/a")).getText(), invalidLoginError, "Error");
    }

    @Test
    public void loginMissingPassword(){
        doLogin("Andrew", "");
        //error page
        Assert.assertEquals(driver.findElement(By.xpath("//div[@id='main-panel-content']/div/a")).getText(), invalidLoginError, "Error");
    }

   /* //Newly registered user
    @Test
    public void loginWithNewlyRegisteredUser(){
        driver.get(urlSignup);
        doSignUp(signupName, signupPassword, confirmSignupPassword, signupFullName, signupEmail);
        //logged in page
        Assert.assertEquals(driver.findElement(By.xpath("//*[@class='model-link inside inverse']/b")).getText().trim(), fullName, "Error");
        //ToDo click Logout
        driver.get(urlLogin);
        doLogin("Jason", "password123");
        //logged in page
        Assert.assertEquals(driver.findElement(By.xpath("//*[@class='model-link inside inverse']/b")).getText().trim(), fullName, "Error");
    } */

    //Valid login
    @Test
    public void loginValid (){
        doLogin(username, password);
        //logged in page
        Assert.assertEquals(driver.findElement(By.xpath("//*[@class='model-link inside inverse']/b")).getText().trim(), fullName, "Error");
    }

    private void doLogin (String username, String password){
        userField.sendKeys(username);
        passwordField.sendKeys(password);
        loginButton.click();
    }

   /* private void doSignUp(String signupName, String signupPassword, String confirmSignupPassword, String signupFullName, String signupEmail) {
        usernameField.sendKeys(signupName);
        passwordSignupField.sendKeys(signupPassword);
        confirmPasswordField.sendKeys(confirmSignupPassword);
        signupfullNameField.sendKeys(signupFullName);
        emailField.sendKeys(signupEmail);
        SignupButton.click();
    } */

    @AfterClass
    public static void shutDownActivities() {
        driver.quit();
    }
}
