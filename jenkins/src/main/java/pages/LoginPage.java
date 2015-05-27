package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.testng.Assert;

public class LoginPage extends LoadableComponent<LoginPage>{

    private static WebDriver driver;

    private String urlLogin = "http://clm-aus-002258.bmc.com:8080/login?from=%2F";

    @FindBy (id = "j_username")
    private WebElement userField;

    @FindBy (name = "j_password")
    private WebElement passwordField;

    @FindBy (id = "yui-gen1-button")
    private WebElement loginButton;

    public LoginPage (WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void doLogin (String username, String password){
        userField.sendKeys(username);
        passwordField.sendKeys(password);
        loginButton.click();
    }

    @Override
    protected void load() {
        driver.get(urlLogin);
    }

    @Override
    protected void isLoaded() throws Error {
        String url = driver.getCurrentUrl();
        Assert.assertEquals(url, urlLogin, "Incorrect page: " + url);
    }

}
