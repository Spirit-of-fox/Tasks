package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.Logger;
import test.BaseTest;

public class HomePage {
    WebDriver webDriver = BaseTest.driver;
    private WebElement catalogElement = webDriver.findElement(By.xpath("//a[@href ='https://catalog.onliner.by/']"));
    Logger logger = new Logger();

    @Step("Open catalog page")
    public void openCatalog() {
        logger.logDebug("Open catalog page");
        catalogElement.click();
    }

    public String getTabName() {
        logger.logDebug("Get tab name");
        return webDriver.getTitle();
    }
}
