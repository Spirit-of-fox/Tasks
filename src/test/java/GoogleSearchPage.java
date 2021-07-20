import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GoogleSearchPage {
    WebDriver webDriver = BaseTestActions.driver;
    private WebElement q = webDriver.findElement(By.name("q"));
    private static Logger logger = LoggerFactory.getLogger(GoogleSearchPage.class);

    public void searchFor(String text) {
        logger.debug("Send word for search = " + text);
        q.sendKeys(text);
        q.submit();
    }
}
