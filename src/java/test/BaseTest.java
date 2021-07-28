package test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    public static WebDriver driver;
    private static Logger logger = LogManager.getLogger(BaseTest.class);
    private String browser = java.lang.System.getProperties().getProperty("browser");

    @BeforeMethod
    public void setUp() {
        logger.info("Test " + getClass() + " started\n");
        logger.trace("Checking which browser will be used");
        if(browser.equals("firefox")){
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }
        else {
            logger.warn("No browser has been selected, the default browser is launched");
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }
        logger.trace("Start with " + browser);

        driver.get("https://www.onliner.by/");
    }

    @AfterMethod
    public void ending () {
        driver.quit();
        logger.info("Test " + getClass() + " end");
    }
}
