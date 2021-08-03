package tests;

import enums.TimeOuts;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.Logger;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    public static WebDriver driver;

    @BeforeMethod
    public void setUp() {
        Logger.logInfo("Test " + getClass().getMethods()[0].getName() + " started");
        Logger.logTrace("Checking which browser will be used");
        if (System.getProperty("browser").equals("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else {
            Logger.logDebug("No browser has been selected, the default browser is launched");
            WebDriverManager.chromedriver().setup();
            HashMap<String, Object> chromePrefs = new HashMap<>();
            chromePrefs.put("profile.default_content_settings.popups", 0);
            chromePrefs.put("download.default_directory", System.getProperty("user.dir"));
            ChromeOptions options = new ChromeOptions();
            options.setExperimentalOption("prefs", chromePrefs);
            driver = new ChromeDriver(options);
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(TimeOuts.SMALL_TIMEOUT_SECONDS.getTimeout(), TimeUnit.SECONDS);
        driver.get("https://store.steampowered.com/ ");

    }

    @AfterMethod
    public void ending() {
        driver.quit();
        Logger.logInfo("Test " + getClass().getMethods()[0].getName() + " end");
    }
}
