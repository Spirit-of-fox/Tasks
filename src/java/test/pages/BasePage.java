package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class BasePage {
    protected WebDriver webDriver;

    public BasePage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    protected WebElement getElement(By locator){
        return webDriver.findElement(locator);
    }

    protected List<WebElement> getElements(By locator){
        return webDriver.findElements(locator);
    }
}
