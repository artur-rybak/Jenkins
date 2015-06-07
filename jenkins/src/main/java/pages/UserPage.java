package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import static org.testng.Assert.assertTrue;

public class UserPage extends Page<UserPage> {

    private String userName;

    @FindBy(xpath = "//a[contains(@href, '/delete')]")
    private WebElement deleteButton;

    public UserPage(WebDriver driver, String userName){
        super(driver);
        this.userName = userName;
    }

    public String getPageURL() {
        return String.format("http://seltr-kbp1-1.synapse.com:8080/user/%s/", userName);
    }

    @Override
    protected void checkUniqueElements() throws Error {
        assertTrue(deleteButton.isDisplayed());
    }

    public UserDeletePage clickDeleteButton() {
        log.info("Delete user");
        deleteButton.click();
        return new UserDeletePage(wd, userName);
    }
}
