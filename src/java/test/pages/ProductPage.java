package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.Logger;

public class ProductPage extends BasePage {
    private static final By PRODUCT_PRICE_LOC =  By.xpath("//div[@class='game_purchase_action']//div[@class = 'discount_final_price']");
    private static final By PRODUCT_DISCOUNT_LOC =  By.xpath("//div[@class='game_purchase_action']//div[@class = 'discount_pct']");
    private static final By INSTALL_STEAM_LOC =  By.xpath("//a[@class='header_installsteam_btn_content']");

    public ProductPage(WebDriver webDriver) {
        super(webDriver);
    }

    public String getProductPriceText() {
        Logger.logDebug("Get product price text");
        return getElement(PRODUCT_PRICE_LOC).getText();
    }
    public String getProductDiscountText() {
        Logger.logDebug("Get product discount text");

        return getElement(PRODUCT_DISCOUNT_LOC).getText();
    }

    public void clickInstallSteam() {
        Logger.logDebug("Click on install steam button");
        getElement(INSTALL_STEAM_LOC).click();
    }
}
