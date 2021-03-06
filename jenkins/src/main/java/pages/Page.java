package pages;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

public abstract class Page<T extends Page<T>> extends LoadableComponent<T> {

    protected final Logger log = LogManager.getLogger(this);
    protected final WebDriver wd;

    @FindBy(id = "search-box")
    private WebElement searchField;

    @FindBy (xpath = ".//*[@href='/logout']")
    private WebElement logoutButton;

    public Page(WebDriver wd) {
        this.wd = wd;
        PageFactory.initElements(wd, this);
    }

    public abstract String getPageURL();

    protected abstract void checkUniqueElements() throws Error;

    @Override
    protected void load() {
        log.info("Loading page: {}", getPageURL());
        wd.get(getPageURL());
    }

    @Override
    protected void isLoaded() throws Error {
        Assert.assertThat("Wrong page URL", wd.getCurrentUrl(), Matchers.equalToIgnoringCase(getPageURL()));
        checkUniqueElements();
    }

    public UserDeletePage doSearch(String fullSearchName) {
        log.info("Search for the user: '"+ fullSearchName +"'");
        searchField.sendKeys(fullSearchName);
        searchField.sendKeys(Keys.ENTER);
        return new UserDeletePage(wd, fullSearchName);
    }

    public HomePage doLogout(){
        logoutButton.click();
        return new HomePage(wd);
    }
}
