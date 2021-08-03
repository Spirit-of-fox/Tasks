package pages;

import enums.TimeOuts;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Logger;

public class HomePage extends BasePage {
    Actions actions = new Actions(webDriver);
    private static final By LANGUAGE_LOC = By.xpath("//span[@id='language_pulldown']");
    private static final By ENGLISH_LANGUAGE_LOC = By.xpath("//a[@class = 'popup_menu_item tight'][contains(text(), 'English')]");
    private static final String PULLDOWN_PATTERN = "//a[@class='pulldown_desktop'][contains(text(), '%s')]";
    private static final String POPUP_PATTERN = "//div[contains(@class, 'popup_menu_subheader')]//a[contains(text(), '%s')]";

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public String getLanguageText() {
        Logger.logDebug("Getting language element text");
        return getElement(LANGUAGE_LOC).getText();
    }
    public void clickLanguage() {
        Logger.logDebug("Clicking on language element");
        getElement(LANGUAGE_LOC).click();
    }
    public void clickEnglishLanguage() {
        Logger.logDebug("Chose english language");
        getElement(ENGLISH_LANGUAGE_LOC).click();
    }
    public void hoverPulldownMenu(String pulldownMenu) {
        Logger.logDebug("Hover pulldown menu element");
        new WebDriverWait(webDriver, TimeOuts.TIMEOUT_FOR_CONDITION_SECONDS.getTimeout()).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(PULLDOWN_PATTERN, pulldownMenu))));
        actions.moveToElement(getElement(By.xpath(String.format(PULLDOWN_PATTERN, pulldownMenu)))).build().perform();
    }
    public void clickPopupMenuSubheader(String popupMenuSubheader) {
        Logger.logDebug("Click on popup menu subheader");
        getElement(By.xpath(String.format(POPUP_PATTERN, popupMenuSubheader))).click();
    }
}
