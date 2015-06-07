package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static junit.framework.TestCase.assertTrue;

public class HomePage extends Page<HomePage>{

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(className = "dashboard")
    private WebElement dashboard;

    @Override
    public String getPageURL() {
        return "http://seltr-kbp1-1.synapse.com:8080/";
    }

    @Override
    public void checkUniqueElements() throws Error {
        assertTrue(dashboard.isDisplayed());
    }
}