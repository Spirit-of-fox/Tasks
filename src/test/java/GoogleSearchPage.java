import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GoogleSearchPage {
    WebDriver webDriver = BaseTestActions.driver;
    private WebElement q = webDriver.findElement(By.name("q"));

    public void searchFor(String text) {
        q.sendKeys(text);
        q.submit();
    }
}
