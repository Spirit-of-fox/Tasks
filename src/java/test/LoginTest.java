package test;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import test.BaseTest;
import test.CartTest;

import java.util.concurrent.TimeUnit;

public class LoginTest extends BaseTest {
    private static Logger logger = LogManager.getLogger(CartTest.class);
    private String nameOfLoginElement = "Вход";
    private String typeOfField = "email";
    private String email = "invalid@email";
    private String password = "1234567";
    private String repeatPassword = "12345677";
    private String expectedTextOfPrompt = "Некорректный e-mail";

    @Test
    public void testOne() throws InterruptedException {

        LoginPage loginPage = new LoginPage();
        loginPage.clickOnLogin(nameOfLoginElement);
        loginPage.clickOnRegistration();

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        Assert.assertTrue(loginPage.getPageTitle().contains("Регистрация"), "Invalid title");

        loginPage.inputTextInField(email, typeOfField);
        checkingTextOfPrompt(loginPage.getActualTextOfPrompt(expectedTextOfPrompt), expectedTextOfPrompt);

        typeOfField = "password";
        expectedTextOfPrompt = "Минимум 8 символов";
        loginPage.inputTextInField(password, typeOfField);
        checkingTextOfPrompt(loginPage.getActualTextOfPrompt(expectedTextOfPrompt), expectedTextOfPrompt);

        loginPage.inputRepeatPassword(repeatPassword);

        expectedTextOfPrompt = "Пароли не совпадают";
        checkingTextOfPrompt(loginPage.getActualTextOfPrompt(expectedTextOfPrompt), expectedTextOfPrompt);

    }

    private void checkingTextOfPrompt(String actualTextOfPrompt, String expectedTextOfPrompt) {
        Assert.assertEquals(actualTextOfPrompt, expectedTextOfPrompt, "Invalid prompt");
    }
}
