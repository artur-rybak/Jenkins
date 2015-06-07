package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import static org.testng.Assert.assertTrue;

public class UserDeletePage extends Page<UserDeletePage> {

    private String userName;

    @FindBy (id = "yui-gen1-button")
    private WebElement confirmButton;

    public UserDeletePage(WebDriver driver, String userName){
        super(driver);
        this.userName = userName;
    }

    @Override
    public String getPageURL() {
        return String.format("http://seltr-kbp1-1.synapse.com:8080/user/%s/delete", userName);
    }

    @Override
    protected void checkUniqueElements() throws Error {
        assertTrue(confirmButton.isDisplayed());
    }

    public HomePage deleteUser (){
        log.info("Delete user");
        confirmButton.click();
        return new HomePage(wd);
    }
}
