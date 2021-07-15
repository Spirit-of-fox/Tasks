import com.company.DPClass;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;

public class NumberOfResults {
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

    @Test(dataProvider ="test-data", dataProviderClass= DPClass.class)
    public void testTwo (String keyWord, String CountOfResults) {
        WebElement element = driver.findElement(By.name("q"));
        element.sendKeys(keyWord);
        element.submit();

        System.out.println(driver.findElement(By.xpath("//*[@id='result-stats']")).getText());
        String[] result = driver.findElement(By.xpath("//*[@id='result-stats']"))
                .getText().split(" ");

        String buf = "";
        for (String i:result) {
            try {
                Integer.parseInt(i);
                buf = buf.concat(i);
            }
            catch (Exception e) {
                continue;
            }
        }

        if ((Integer.parseInt(buf))>Integer.parseInt(CountOfResults)) {
            System.out.println("Количество результатов поиска больше " + CountOfResults);
        }
        else {
            System.out.println("CHECK FAILED: Количество результатов поиска меньше " + CountOfResults);
        }

    }

    @AfterTest
    public void ending () {
        driver.quit();
    }
}

