package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.Logger;

public class InstallingPage extends BasePage {
    private static final By INSTALL_STEAM_BTN_LOC = By.xpath("//div[@id='about_greeting']//a[@class='about_install_steam_link']");

    public InstallingPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void clickInstallSteamBtn() {
        Logger.logDebug("Install steam file");
        getElement(INSTALL_STEAM_BTN_LOC).click();
    }

}
