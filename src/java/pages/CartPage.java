package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import test.BaseTest;
import utils.Logger;


public class CartPage {
    WebDriver webDriver = BaseTest.driver;
    private WebElement productInCart;

    Logger logger = new Logger();

    @Step("Get product from cart")
    public boolean getProductFromCart() {
        logger.logDebug("Get product from cart");
        productInCart = webDriver.findElement(By.xpath("//div[@class= 'cart-form__offers']"));
        if(productInCart!=null) {
            return true;
        }
        else {
            return false;
        }

    }

}
