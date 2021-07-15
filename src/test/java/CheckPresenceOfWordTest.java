import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

import static com.company.GetPropertiesClass.searchWord;

public class CheckPresenceOfWordTest extends BaseTestActions {

    @Test
    public void testOne () {
        getWebPage(searchWord);

        for(WebElement i:getWebElements()) {
            Assert.assertTrue(i.getText().toLowerCase().contains(searchWord.toLowerCase())
                    , "Not all blocks contain the keyword");
        }

        GoToNextPage(2);

        for(WebElement i:getWebElements()) {
            Assert.assertTrue(i.getText().toLowerCase().contains(searchWord.toLowerCase())
                    , "Not all blocks contain the keyword");
        }
    }

    private void GoToNextPage(int numberOfPage) {
        WebElement elementNextPage = driver.findElement(By.xpath("//a[contains(@aria-label, 'Page "
                + numberOfPage +"')]"));
        elementNextPage.click();
    }

    private void getWebPage(String keyWord) {
        WebElement element = driver.findElement(By.name("q"));
        element.sendKeys(keyWord);
        element.submit();
    }

    private List<WebElement> getWebElements() {
        List<WebElement> elementTexts  = driver.findElements(By.xpath("//*[@id='rso']//div[@class='g']"));
        return elementTexts;
    }


}
