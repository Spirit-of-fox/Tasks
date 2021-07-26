package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import test.BaseTest;


public class LoginPage {
    WebDriver webDriver = BaseTest.driver;
    private WebElement loginElement;
    private WebElement registrationElement;
    private WebElement pageTitle;
    private WebElement promptElement;
    private WebElement repeatPasswordElement;

    private static Logger logger = LoggerFactory.getLogger(LoginPage.class);


    public String getPageTitle() {
        logger.debug("Getting page title");
        pageTitle = webDriver.findElement(By.xpath("//div[contains(@class, 'auth-form__title')]"));
        return pageTitle.getText();
    }
    private WebElement getLoginElement(String nameOfLoginElement) {
        logger.debug("Get entrance element");

        loginElement = webDriver.findElement(By.xpath("//div[contains(text(), '" + nameOfLoginElement + "')]"));
        return loginElement;
    }
    public void clickOnLogin(String nameOfLoginElement) {
        logger.debug("Click on login element");

        getLoginElement(nameOfLoginElement).click();
    }

    private WebElement getRegistrationElement() {
        logger.debug("Get registration element");

        registrationElement = webDriver.findElement(By.xpath("//a[@href = 'https://profile.onliner.by/registration']"));
        return registrationElement;
    }
    @Step("Go to the Login tab -> Register on Onliner")
    public void clickOnRegistration() {
        logger.debug("Click on registration element");
        getRegistrationElement().click();
    }

    private WebElement getInputElement(String typeOfField) {
        logger.debug("Get email input element");

        registrationElement = webDriver.findElement(By.xpath("//input[@type= '" + typeOfField + "']"));
        return registrationElement;
    }
    @Step("Input date")
    public void inputTextInField(String textForInput, String typeOfField) {
        logger.debug("Input text in input element");
        getInputElement(typeOfField).sendKeys(textForInput);
    }

    private WebElement getPromptElement(String expectedTextOfPrompt) {
        logger.debug("Get prompt element");

        promptElement = webDriver.findElement(By.xpath("//div[contains(text(), '" + expectedTextOfPrompt + "')]"));
        return promptElement;
    }
    public String getActualTextOfPrompt(String expectedTextOfPrompt) {
        return getPromptElement(expectedTextOfPrompt).getText();
    }

    private WebElement getRepeatPasswordElement() {
        logger.debug("Get repeat password element");

        repeatPasswordElement = webDriver.findElement(By.xpath(" //div[@autocomplete='repeatPassword']//input[@type= 'password']"));
        return repeatPasswordElement;
    }
    @Step("Input repeat password")
    public void inputRepeatPassword(String repeatPassword) {
        logger.debug("Input repeat password");
        getRepeatPasswordElement().sendKeys(repeatPassword);
    }

}
