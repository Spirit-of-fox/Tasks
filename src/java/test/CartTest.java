package test;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.CatalogPage;
import pages.HomePage;

import java.util.concurrent.TimeUnit;

public class CartTest extends BaseTest {
    private int indexOfMenuElement = 1;
    private String titleOfSubMenuItem = "Видеоигры";
    private String productType = "Игровые приставки";
    private int numberOfGroup = 1;

    @Test
    public void testOne() throws InterruptedException {

        HomePage gsp = new HomePage();
        gsp.openCatalog();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        checkingTitles(gsp.getTabName(), "Каталог Onliner");

        CatalogPage catalogPage = new CatalogPage();
        catalogPage.choseMenuElement(indexOfMenuElement);
        catalogPage.choseSubMenuItem(titleOfSubMenuItem);

        catalogPage.choiceOfProductType(productType);

        checkingTitles(catalogPage.getPageTitle(), productType);
        Assert.assertTrue(catalogPage.getPageTitle().contains("Игровые приставки"), "Invalid title");

        catalogPage.clickOnProductGroup(numberOfGroup);
        Assert.assertTrue(catalogPage.getCartElementText().contains("В корзину"), "Invalid title");
        catalogPage.clickToCart();
        Assert.assertTrue(catalogPage.getCartElementText().contains("В корзине"), "Invalid title");
        catalogPage.clickToCart();

        CartPage cartPage = new CartPage();
        Assert.assertTrue(cartPage.getProductFromCart(), "Cart is empty");

        //driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

    }

    private void checkingTitles(String actualTitle, String expectedTitle) {
        Assert.assertEquals(actualTitle, expectedTitle, "Invalid title");
        /*for(String i:a) {
            Assert.assertTrue(i.contains(wordForSearch.toLowerCase()), "Not all blocks contain the keyword");
        }*/
    }
}
