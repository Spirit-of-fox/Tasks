package test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.Logger;

public class BaseTest {
    public static WebDriver driver;
    private String browser = java.lang.System.getProperties().getProperty("browser");

    Logger logger = new Logger();

    @BeforeMethod
    public void setUp() {
        logger.logInfo("Test " + getClass() + " started\n");
        logger.logDebug("Checking which browser will be used");
        if(browser.equals("firefox")){
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }
        else {
            logger.logWarn("No browser has been selected, the default browser is launched");
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }
        logger.logDebug("Start with " + browser);

        driver.get("https://www.onliner.by/");
    }

    @AfterMethod
    public void ending () {
        driver.quit();
        logger.logInfo("Test " + getClass() + " end");
    }
}
