package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import test.BaseTest;

public class CartPage {
    WebDriver webDriver = BaseTest.driver;
    private WebElement productInCart;

    private static Logger logger = LoggerFactory.getLogger(CartPage.class);

    @Step("Get product from cart")
    public boolean getProductFromCart() {
        logger.debug("Get product from cart");

        productInCart = webDriver.findElement(By.xpath("//div[@class= 'cart-form__offers']"));
        if(productInCart!=null) {
            return true;
        }
        else {
            return false;
        }
    }

}
