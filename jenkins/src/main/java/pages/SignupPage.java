package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.testng.Assert.assertTrue;

public class SignupPage extends Page<SignupPage> {

    @FindBy (id = "username")
    private WebElement usernameField;

    @FindBy (name = "password1")
    private WebElement passwordField;

    @FindBy (name = "password2")
    private WebElement confirmPasswordField;

    @FindBy (name = "fullname")
    private WebElement fullNameField;

    @FindBy (name = "email")
    private WebElement emailField;

    @FindBy (id = "yui-gen1-button")
    private WebElement signupButton;

    public SignupPage (WebDriver driver){
        super(driver);
    }

    @Override
    public String getPageURL() {
        return "http://seltr-kbp1-1.synapse.com:8080/signup";
    }

    @Override
    protected void checkUniqueElements() throws Error {
        assertTrue(signupButton.isDisplayed());
    }

    public HomePage doSignUp(String name, String password, String confirmPassword, String fullName, String email) {
        log.info("Execute sign up with name: '"+ name +"', password: '"+ password +"', confirmPassword: '"+ confirmPassword +"', fullName: '"+ fullName +"', email: '"+ email +"'");
        usernameField.sendKeys(name);
        passwordField.sendKeys(password);
        confirmPasswordField.sendKeys(confirmPassword);
        fullNameField.sendKeys(fullName);
        emailField.sendKeys(email);
        signupButton.click();
        return new HomePage(wd);
    }
}
