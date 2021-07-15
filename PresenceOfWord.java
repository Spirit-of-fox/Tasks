import com.company.DPClass;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;

import java.util.List;

public class PresenceOfWord {
    private WebDriver driver;

    @BeforeSuite
    public void setUp() {
        ChromeOptions chromeOptions = new ChromeOptions();
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(chromeOptions);
    }

    @BeforeClass
    public void appSetup() {
        driver.get("http://www.google.com");
    }

    @Test (dataProvider ="test-data", dataProviderClass= DPClass.class)
    public void testOne (String keyWord) {
        WebElement element = driver.findElement(By.name("q"));
        element.sendKeys(keyWord);
        element.submit();

        List<WebElement> elementTexts  = driver.findElements(By.xpath("//*[@id='rso']//div[@class='g']"));

        for(WebElement i:elementTexts){
            if(i.getText().toLowerCase().contains(keyWord.toLowerCase())) {
                System.out.println("Содержит");
            }
            else {
                System.out.println("Не во всех блоках присутствует ключевое слово");
                break;
            }
        }

        WebElement elementNextPage = driver.findElement(By.xpath("//a[@aria-label=\"Page 2\"]"));
        elementNextPage.click();

        List<WebElement> elementTextsPageTwo  = driver.findElements(By.xpath("//*[@id='rso']//div[@class='g']"));

        for(WebElement i:elementTextsPageTwo){
            if(i.getText().toLowerCase().contains(keyWord.toLowerCase())) {
                System.out.println("Содержит");
            }
            else {
                System.out.println("Не во всех блоках присутствует ключевое слово");
                break;
            }
        }
        /*String elementText = driver.findElement(By.xpath("//*[@id='rso']/div[1]/div/div/div")).getText();
        elementText.toLowerCase();
        System.out.println(keyWord);
        System.out.println("Text: " + elementText.toLowerCase() + elementText.toLowerCase().contains(keyWord.toLowerCase()));
*/

        /*List<WebElement> elementCountOfSearches = driver.findElements(By.xpath("//*[@id='rso']//following-sibling::div[@class='g' or @class ='hlcw0c']"));
        List<WebElement> elementCountOfMatches  = driver.findElements(By.xpath("//span/em[1]"));

        if (elementCountOfSearches.size()+1==elementCountOfMatches.size()) {
            System.out.println("Все блоки первой страницы содержат ключевое слово");
        }
        else {
            System.out.println("CHECK FAILED: Не все блоки первой страницы имеют ключевое слово!");
            System.out.println("Количество результатов поиска " + (elementCountOfSearches.size()+1));
            System.out.println("Количество ключевых слов " + elementCountOfMatches.size());
        }

        WebElement elementNextPage = driver.findElement(By.xpath("//a[@aria-label=\"Page 2\"]"));
        elementNextPage.click();

        List<WebElement> elementCountOfSearches2 = driver.findElements(By.xpath("//*[@id=\"rso\"]//following-sibling::div[@class=\"g\" or @class =\"hlcw0c\"]"));
        List<WebElement> elementCountOfMatches2  = driver.findElements(By.xpath("//span/em[1]"));

        if (elementCountOfSearches2.size()+1==elementCountOfMatches2.size()) {
            System.out.println("Все блоки второй страницы содержат ключевое слово");
        }
        else {
            System.out.println("CHECK FAILED: Не все блоки второй страницы имеют ключевое слово!");
            System.out.println("Количество результатов поиска " + (elementCountOfSearches2.size()+1));
            System.out.println("Количество ключевых слов " + elementCountOfMatches2.size());
        }*/
    }

    @AfterTest
    public void ending () {
        // Закрываем браузер
        driver.quit();
    }
}
