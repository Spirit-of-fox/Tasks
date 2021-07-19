import com.company.PropertiesReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


public class BaseTestActions {
    protected static WebDriver driver;

    @BeforeMethod
    public void setUp() {
        PropertiesReader properties = new PropertiesReader("src/main/resources/config.properties");
        String browser = properties.getProperties("browser");

        if(browser.equals("firefox")){
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }
        else {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }

        driver.get("http://www.google.com");
    }

    @AfterMethod
    public void ending () {
        // Закрываем браузер
        driver.quit();
    }
}
