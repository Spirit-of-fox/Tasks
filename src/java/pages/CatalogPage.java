package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import test.BaseTest;

public class CatalogPage {
    WebDriver webDriver = BaseTest.driver;
    WebDriverWait wait = new WebDriverWait(webDriver,30);
    private WebElement menuElement;
    private WebElement subMenuElement;
    private WebElement productTypeLink;
    private WebElement pageTitle;
    private WebElement manufacturerCheckbox;
    private WebElement product;
    private WebElement productGroup;
    private WebElement productSort;
    private WebElement productPrice;
    private WebElement cartElement;

    private static Logger logger = LoggerFactory.getLogger(CatalogPage.class);

    @Step("Go to Electronics")
    public void choseMenuElement(int indexOfMenuElement) {
        logger.debug("Open tab Electronic in catalog");

        menuElement = webDriver.findElement(By.xpath("//li[@data-id ='" + indexOfMenuElement + "']"));
        menuElement.click();
    }
    @Step("Chose sub menu item")
    public void choseSubMenuItem(String titleOfSubMenuItem) {
        logger.debug("Chose " + titleOfSubMenuItem + " in catalog");

        subMenuElement = webDriver.findElement(By.xpath("//div[@class='catalog-navigation-list__aside-title'][contains(text(), '" + titleOfSubMenuItem + "')]"));
        subMenuElement.click();

    }

    @Step("Chose product type")
    public void choiceOfProductType(String productTypeText) {
        logger.debug("Selecting " + productTypeText + " in catalog");
        productTypeLink = webDriver.findElement(By.xpath("//span[contains(text(), '" + productTypeText + "')]"));
        productTypeLink.click();
    }

    public String getPageTitle() {
        logger.debug("Getting PageTitle");
        pageTitle = webDriver.findElement(By.xpath("//h1[@class='schema-header__title']"));
        return pageTitle.getText();
    }

    public String getTabName() {
        logger.debug("Get tab name");
        return webDriver.getTitle();
    }

    @Step("Sort by manufacturer")
    public void choiceManufacturer(String manufacturerName) {
        logger.debug("Selecting " + manufacturerName + " as manufacturer");

        manufacturerCheckbox = webDriver.findElement(By.xpath("//input[contains(@value, '" + manufacturerName + "')]/../..//span[@class='schema-filter__checkbox-text']"));

        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("arguments[0].scrollIntoView();", manufacturerCheckbox);

        manufacturerCheckbox.click();
    }

    private WebElement getProductGroup(int numberOfGroup) throws InterruptedException {
        logger.debug("Getting ProductGroup " + numberOfGroup);

        productGroup = webDriver.findElement(By.xpath("//div[@class = 'schema-product__group']["+ numberOfGroup +"]"));
        return productGroup;
    }

    public String getTextFromProductGroup(int numberOfGroup) throws InterruptedException {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class = 'schema-filter-button__inner']//span[contains(text(), 'Найдено')]")));

        String TextFromProductGroup = getProductGroup(numberOfGroup).getText();

        return TextFromProductGroup;
    }

    public void clickOnProductGroup(int numberOfGroup) throws InterruptedException {
        product = getProductGroup(numberOfGroup).findElement(By.xpath("//div[@class = 'schema-product__title']//a[@href]"));
        product.click();
    }

    private WebElement getSortLink() {
        WebElement SortLink = webDriver.findElement(By.xpath("//a[@class='schema-order__link']"));

        return SortLink;
    }

    @Step("Sort by price")
    public void choiceProductSort(String nameOfProductSort) {
        logger.debug("Selecting ProductGroup");
        getSortLink().click();

        productSort = webDriver.findElement(By.xpath("//div[@class='schema-order__item']/span[contains(text(), '" + nameOfProductSort + "')]"));
        productSort.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class = 'schema-filter-button__inner']//span[contains(text(), 'Нет товаров')]")));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class = 'schema-filter-button__inner']//span[contains(text(), 'Найдено')]")));
    }

    private WebElement getProductPrice(int numberOfGroup) {
        logger.debug("Getting ProductPrice " + numberOfGroup);

        productPrice = webDriver.findElement(By.xpath("//div[@class = 'schema-product__group'][ "+ numberOfGroup + "]//div[@class='schema-product__price']//a[@class='schema-product__price-value schema-product__price-value_primary']//span"));
        return productPrice;
    }

    public String getTextFromProductPrice(int numberOfGroup) {
        String TextFromProductPrice = getProductPrice(numberOfGroup).getText();

        return TextFromProductPrice;
    }

    private WebElement getCartElement() {
        cartElement = webDriver.findElement(By.xpath("//div[@class='product-aside__box']//a[contains(@class, 'button')][2]"));
        return cartElement;
    }

    public String getCartElementText() {
        return getCartElement().getText();
    }

    public void clickToCart() {
        getCartElement().click();
    }
}

