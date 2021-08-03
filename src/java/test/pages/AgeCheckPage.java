package pages;

import enums.TimeOuts;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Logger;

public class AgeCheckPage extends BasePage {
    private static final String SELECT_YEAR_PATTERN = "//select[@id='ageYear']//option[@value='%d']";
    private static final By VIEW_PAGE_LOC =  By.xpath("//a[@onclick='ViewProductPage()']");

    public AgeCheckPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void choseAgeYear(int ageYear) {
        Logger.logDebug("Chose age year");
        new WebDriverWait(webDriver, TimeOuts.TIMEOUT_FOR_CONDITION_SECONDS.getTimeout()).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(SELECT_YEAR_PATTERN, ageYear))));
        getElement(By.xpath(String.format(SELECT_YEAR_PATTERN, ageYear))).click();
    }

    public void clickViewPageBtn() {
        Logger.logDebug("Click on view page button");
        getElement(VIEW_PAGE_LOC).click();
    }
}
