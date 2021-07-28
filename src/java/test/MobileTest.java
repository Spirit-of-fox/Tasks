package test;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CatalogPage;
import pages.HomePage;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MobileTest extends BaseTest {
    private int indexOfMenuElement = 1;
    private String titleOfSubMenuItem = "Мобильные телефоны и аксессуары";
    private String productType = "Мобильные телефоны";
    private String manufacturerName = "xiaomi";

    @Test
    public void testOne () throws InterruptedException {

        HomePage gsp = new HomePage();
        gsp.openCatalog();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        checkingTitles(gsp.getTabName(), "Каталог Onliner");
        CatalogPage catalogPage = new CatalogPage();
        catalogPage.choseMenuElement(indexOfMenuElement);
        catalogPage.choseSubMenuItem(titleOfSubMenuItem);

        catalogPage.choiceOfProductType(productType);

        checkingTitles(catalogPage.getPageTitle(), productType);
        Assert.assertTrue(catalogPage.getTabName().contains("Мобильный телефон"), "Invalid title");

        catalogPage.choiceManufacturer(manufacturerName);

        //Thread.sleep(5000);
        for(int i=1; i<=30; i++) {
            Assert.assertTrue(catalogPage.getTextFromProductGroup(i).toLowerCase().contains(manufacturerName.toLowerCase()), "Not all blocks contain the keyword");
        }

        catalogPage.choiceProductSort("Дорогие");

        //Thread.sleep(3000);
        Pattern patternForPrice = Pattern.compile("([\\d\\,]+)");
        for(int i=1; i<30; i++) {
            Matcher price = patternForPrice.matcher(catalogPage.getTextFromProductPrice(i));
            Matcher priceSecond = patternForPrice.matcher(catalogPage.getTextFromProductPrice(i+1));

            while (price.find() && priceSecond.find()) {
                Assert.assertTrue(Double.parseDouble(price.group().trim().replace(',', '.')) >= Double.parseDouble(priceSecond.group().trim().replace(',', '.')), "Sorting doesn't work");
            }
        }

    }

    private void checkingTitles(String actualTitle, String expectedTitle) {
        Assert.assertEquals(actualTitle, expectedTitle, "Invalid title");

    }

}
