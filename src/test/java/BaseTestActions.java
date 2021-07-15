import com.company.GetPropertiesClass;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;

import static com.company.GetPropertiesClass.browser;

public class BaseTestActions {
    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {
        GetPropertiesClass.getPropertie();

        if(browser.equals("firefox")){
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }
        else {
            ChromeOptions chromeOptions = new ChromeOptions();
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver(chromeOptions);
        }

        driver.get("http://www.google.com");
    }

    @AfterTest
    public void ending () {
        // Закрываем браузер
        driver.quit();
    }
}
