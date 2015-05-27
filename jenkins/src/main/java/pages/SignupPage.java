package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.testng.Assert;

public class SignupPage extends LoadableComponent<SignupPage> {

    private static WebDriver driver;

    private String signupUrl = "http://clm-aus-002258.bmc.com:8080/signup";

    @FindBy (id = "username")
    WebElement usernameField;

    @FindBy (name = "password1")
    WebElement passwordField;

    @FindBy (name = "password2")
    WebElement confirmPasswordField;

    @FindBy (name = "fullname")
    WebElement fullNameField;

    @FindBy (name = "email")
    WebElement emailField;

    @FindBy (id = "yui-gen1-button")
    WebElement SignupButton;

    @FindBy (xpath = ".//*[@href='/logout']")
    private WebElement logoutButton;

    public SignupPage (WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void doSignUp(String name, String password, String confirmPassword, String fullName, String email) {
        usernameField.sendKeys(name);
        passwordField.sendKeys(password);
        confirmPasswordField.sendKeys(confirmPassword);
        fullNameField.sendKeys(fullName);
        emailField.sendKeys(email);
        SignupButton.click();
    }

    public void doLogout(){
        logoutButton.click();
    }

    @Override
    protected void load() {
        driver.get(signupUrl);
    }

    @Override
    protected void isLoaded() throws Error {
        String url = driver.getCurrentUrl();
        Assert.assertEquals(url, signupUrl, "Incorrect page: " + url);
    }

}
