package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.interactions.Keyboard;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class ProfilePage extends LoadableComponent<ProfilePage>{

    private static WebDriver driver;

    private String urlLogin = "http://seltr-kbp1-1.synapse.com:8080/login?from=%2F";

    @FindBy (id = "j_username")
    private WebElement userField;

    @FindBy (name = "j_password")
    private WebElement passwordField;

    @FindBy (id = "yui-gen1-button")
    private WebElement loginButton;

    @FindBy(id = "search-box")
    private WebElement searchField;

    @FindBy (xpath = ".//*[@class='icon-edit-delete icon-md']")
    private WebElement deleteIcon;

    @FindBy (id = "yui-gen1-button")
    private WebElement confirmButton;

    public ProfilePage (WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void doLogin (String loginName, String loginPassword){
        userField.sendKeys(loginName);
        passwordField.sendKeys(loginPassword);
        loginButton.click();
    }

    public void doSearch (String fullSearchName){
        searchField.sendKeys(fullSearchName);
        pressKey(Keys.ENTER);
    }

    private void pressKey(Keys enter) {
        Keyboard keyboard = ((HasInputDevices)driver).getKeyboard();
        pressKey(Keys.ENTER);
    }

    public void deleteUser (){
        deleteIcon.click();
        waitForElement();
        confirmButton.click();
    }

    private void waitForElement (){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(confirmButton));
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
