package com.company;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Main {

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "G:\\Internship_QA_iTechArt\\Task33\\driv\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        // Открываем гугл, используя драйвер
        driver.get("https://www.onliner.by/");

        // Проверяем тайтл страницы
        System.out.println("Page title is: " + driver.getTitle());

        // Страницы гугл динамически отрисовывается с помощью javascript
        // Ждем загрузки страницы с таймаутом в 10 секунд
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getTitle().toLowerCase().startsWith("onliner");
            }
        });

        WebElement elementId = driver.findElement(By.id("widget-5-1"));
        WebElement elementName = driver.findElement(By.name("query"));
        WebElement elementClassName = driver.findElement(By.className("b-top-logo"));
        WebElement elementTagName = driver.findElement(By.tagName("body"));
        WebElement elementLinkText = driver.findElement(By.linkText("БелАЭС снова отключена от сети"));
        WebElement elementPartialLinkText = driver.findElement(By.partialLinkText("Белорусы останутся"));
        WebElement elementCssSelector = driver.findElement(By.cssSelector("#container"));

        System.out.println(elementId);
        System.out.println(elementName);
        System.out.println(elementClassName);
        System.out.println(elementTagName);
        System.out.println(elementLinkText);
        System.out.println(elementPartialLinkText);
        System.out.println(elementCssSelector);

        driver.quit();
    }
}
