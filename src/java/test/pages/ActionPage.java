package pages;

import enums.TimeOuts;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Logger;

public class ActionPage extends BasePage {

    private static final String PRODUCT_GROUP_PATTERN = "//div[@id = 'tab_content_NewReleases']//a[contains(@class, 'tab_item')][%d]";
    private static final By ITEM_LOC =  By.xpath("//div[@id = 'tab_content_NewReleases']//a[contains(@class, 'tab_item')]");
    private static final String ITEM_PRICE_PATTERN = "//div[@id = 'tab_content_NewReleases']//a[contains(@class, 'tab_item')][%d]//div[@class = 'discount_final_price']";
    private static final By ITEM_DISCOUNT_LOC =  By.xpath("//div[@id = 'tab_content_NewReleases']//a[contains(@class, 'tab_item')]//div[@class = 'discount_pct']");
    private static final String ITEM_DISCOUNT_PATTERN = "//div[@id = 'tab_content_NewReleases']//a[contains(@class, 'tab_item')][%d]//div[@class = 'discount_pct']";
    private static final String PAGE_LINK_PATTERN = "//span[@id= 'NewReleases_links']//span[@class='paged_items_paging_pagelink' and text()='%d ']";
    private static final By BLACKOUT_LOC = By.xpath("//div[@id='NewReleasesRows' and @style='opacity: 0.5;']");

    public ActionPage(WebDriver webDriver) {
        super(webDriver);
    }


    private WebElement getProductGroup(int numberOfGroup) {
        Logger.logDebug("Getting ProductGroup " + numberOfGroup);
        return getElement(By.xpath(String.format(PRODUCT_GROUP_PATTERN, numberOfGroup)));
    }
    public int getItemsQuantity() {
        Logger.logDebug("Getting items quantity");
        return getElements(ITEM_LOC).size();
    }
    public String getItemPrice(int numberOfGroup) {
        Logger.logDebug("Getting item price");
        return getElement(By.xpath(String.format(ITEM_PRICE_PATTERN, numberOfGroup))).getText();
    }
    public String getItemDiscount(int numberOfGroup) {
        Logger.logDebug("Getting item discount");
        new WebDriverWait(webDriver, TimeOuts.TIMEOUT_FOR_CONDITION_SECONDS.getTimeout()).until(ExpectedConditions.invisibilityOfElementLocated(BLACKOUT_LOC));
        return getElement(By.xpath(String.format(ITEM_DISCOUNT_PATTERN, numberOfGroup))).getText();
    }
    public void selectProduct(int indexOfMaxPrice) {
        Logger.logDebug("Selecting Product");
        getProductGroup(indexOfMaxPrice).click();
    }

    public Boolean availabilityOfDiscounts() {
        Logger.logDebug("Availability of discounts  ");
        return getElements(ITEM_DISCOUNT_LOC).size() > 0;
    }

    public void selectPageOfResults(int page) {
        Logger.logDebug("Selecting page results");
        getElement(By.xpath(String.format(PAGE_LINK_PATTERN, page))).click();
    }


}
