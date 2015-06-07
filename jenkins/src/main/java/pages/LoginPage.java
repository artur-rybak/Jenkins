package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.testng.Assert.assertTrue;

public class LoginPage extends Page<LoginPage> {

    @FindBy(xpath = ".//*[@href='/login?from=%2F']")
    private WebElement loginButton;

    @FindBy(id = "j_username")
    private WebElement userField;

    @FindBy(name = "j_password")
    private WebElement passwordField;

    @FindBy(id = "yui-gen1-button")
    private WebElement submitLoginButton;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public String getPageURL() {
        return "http://seltr-kbp1-1.synapse.com:8080/login?from=%2F";
    }

    @Override
    protected void checkUniqueElements() throws Error {
        assertTrue(submitLoginButton.isDisplayed());
    }

    public HomePage doLogin(String username, String password) {
        log.info("Execute login with name: '" + username + "', password: '" + password + "'");
        loginButton.click();
        userField.sendKeys(username);
        passwordField.sendKeys(password);
        submitLoginButton.click();
        return new HomePage(wd);
    }
}
