package tests;

import enums.PopupMenuSubheader;
import enums.PulldownMenu;
import enums.TimeOuts;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;
import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static utils.Logger.logStep;

public class SteamTest extends BaseTest{
    private static final int PAGE_TO_CLICK = 4;
    private static final int DEFAULT_PAGE_TO_CLICK = 1;
    public static final int EXPECTED_ITEMS_QUANTITY = 20;
    private static final int AGE_YEAR_TO_CLICK = 1960;
    private static final Pattern patternForDiscounts = Pattern.compile("([\\d.]+)");
    private static final Pattern patternForPrice = Pattern.compile("([\\d.]+)");
    private static int indexOfMaxValue = 0;
    private static double currentPrice;
    private static double currentDiscount;
    private static final String PATH_TO_SETUP_FILE = System.getProperty("user.dir") + "\\SteamSetup.exe";

    @Test
    public void steamTest() {

        HomePage homePage = new HomePage(driver);
        logStep(1, "Checking language");
        if(!homePage.getLanguageText().equals("language")) {
            homePage.clickLanguage();
            homePage.clickEnglishLanguage();
        }
        logStep(2, "Hover Categories  -> click Action");
        String pulldownMenu = PulldownMenu.CATEGORIES.getMenu();
        homePage.hoverPulldownMenu(pulldownMenu);
        String popupMenuSubheader = PopupMenuSubheader.ACTION.getSubheader();
        homePage.clickPopupMenuSubheader(popupMenuSubheader);

        logStep(3, "Checking amount of results");
        ActionPage actionPage = new ActionPage(driver);
        Assert.assertTrue(actionPage.getItemsQuantity()<=EXPECTED_ITEMS_QUANTITY);
        actionPage.selectPageOfResults(PAGE_TO_CLICK);
        driver.manage().timeouts().implicitlyWait(TimeOuts.SMALL_TIMEOUT_SECONDS.getTimeout(), TimeUnit.SECONDS);
        Assert.assertTrue(actionPage.getItemsQuantity()<=EXPECTED_ITEMS_QUANTITY);
        actionPage.selectPageOfResults(DEFAULT_PAGE_TO_CLICK);
        logStep(4, "Find result with max discount or price");
        int itemsQuantity = actionPage.getItemsQuantity();
        if(actionPage.availabilityOfDiscounts()) {
            ArrayList<Double> discounts = new ArrayList<>();
            for (int i = 1; i <= itemsQuantity; i++) {
                try {
                    Matcher discount = patternForDiscounts.matcher(actionPage.getItemDiscount(i));
                    if (discount.find()) {
                        discounts.add(Double.parseDouble(discount.group().trim()));
                    }
                }
                catch (Exception e) {
                    discounts.add((double) 0);
                }
            }
            currentDiscount = discounts.get(getIndexOfMaxValue(discounts, itemsQuantity));
            actionPage.selectProduct(getIndexOfMaxValue(discounts, itemsQuantity)+1);
        }
        else {
            ArrayList<Double> prices = new ArrayList<>();
            for (int i = 1; i <= itemsQuantity; i++) {
                Matcher price = patternForPrice.matcher(actionPage.getItemPrice(i));
                if (price.find()) {
                    prices.add(Double.parseDouble(price.group().trim()));
                }
                else if (actionPage.getItemPrice(i).equals("Free To Play")) {
                    prices.add((double) 0);
                }
            }
            currentPrice = prices.get(getIndexOfMaxValue(prices, itemsQuantity));
            actionPage.selectProduct(getIndexOfMaxValue(prices, itemsQuantity)+1);
        }
        logStep(5, "Age restriction check");
        if(driver.getCurrentUrl().contains("agecheck")) {
            AgeCheckPage ageCheckPage = new AgeCheckPage(driver);
            ageCheckPage.choseAgeYear(AGE_YEAR_TO_CLICK);
            ageCheckPage.clickViewPageBtn();
        }

        logStep(6, "Checking the correctness of the price and discount");
        ProductPage productPage = new ProductPage(driver);
        if(currentDiscount!=0) {
            Matcher newDiscount = patternForDiscounts.matcher(productPage.getProductDiscountText());
            if(newDiscount.find()) {
                Assert.assertEquals(currentDiscount, Double.parseDouble(newDiscount.group().trim()));
            }
        }
        else if(currentPrice!=0) {
            Matcher newPrice = patternForPrice.matcher(productPage.getProductPriceText());
            if(newPrice.find()) {
                Assert.assertEquals(currentPrice, Double.parseDouble(newPrice.group().trim()));
            }
        }

        logStep(7, "Click on Install Steam ");
        productPage.clickInstallSteam();
        InstallingPage installingPage = new InstallingPage(driver);
        installingPage.clickInstallSteamBtn();

        logStep(8, "Download setup file");
        File file = new File(PATH_TO_SETUP_FILE);
        FluentWait<WebDriver> wait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(60)).pollingEvery(Duration.ofMillis(100));
        wait.until( x -> file.exists());
        file.delete();
    }

    private int getIndexOfMaxValue(ArrayList<Double> discounts, int itemsQuantity) {
        double maxValue = discounts.get(0);
        for (int i = 0; i < itemsQuantity; i++) {
            if(discounts.get(i).compareTo(maxValue) >= 0) {
                indexOfMaxValue = i;
                maxValue = discounts.get(i);
            }
        }
        return indexOfMaxValue;
    }

}
