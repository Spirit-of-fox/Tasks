import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GoogleResultsPage extends BaseTestActions {
    WebDriver webDriver = BaseTestActions.driver;

    private WebElement linkNextPage;
    private List<WebElement> elementsResultOfSearch = webDriver.findElements(By.xpath("//*[@id='rso']//div[@class='g']"));
    private String attributeCountOfResults = driver.findElement(By.xpath("//*[@id='result-stats']")).getText();
    private String timeElement = driver.findElement(By.xpath("//nobr")).getText();

    private static Logger logger = LoggerFactory.getLogger(GoogleResultsPage.class);

    public String[] getTextFromElements() {
        logger.debug("Retrieving text from search result blocks");

        elementsResultOfSearch = webDriver.findElements(By.xpath("//*[@id='rso']//div[@class='g']"));

        String[] textFromResult = new String[elementsResultOfSearch.size()];
        int j=0;
        for(WebElement i:elementsResultOfSearch) {
            textFromResult[j] = i.getText().toLowerCase();
            j++;
        }

        return textFromResult;
    }

    public String getCountOfResults() {
        logger.debug("Getting the number of search results");

        String result = attributeCountOfResults;

        Pattern patternForCountRes = Pattern.compile("\\s\\d+");
        Matcher matcherInCountRes = patternForCountRes.matcher(result);

        String countOfResults = "";
        while (matcherInCountRes.find()) {
            countOfResults = countOfResults.concat(matcherInCountRes.group().trim());
        }

        return countOfResults;
    }

    public String getTimeOfSearch() {
        logger.debug("Getting the time of search");

        Pattern patternForTime = Pattern.compile("([\\d\\,]+)");
        Matcher matcher = patternForTime.matcher(timeElement);
        String timeOfSearch = "";
        while (matcher.find()) {
            timeOfSearch = timeOfSearch.concat(matcher.group().trim());
        }
        return timeOfSearch;
    }

    public void clickOnLinkToNextPage(int numberOfPage) {
        logger.debug("Go to page number " + numberOfPage);
        linkNextPage = webDriver.findElement(By.xpath("//a[contains(@aria-label, 'Page " + numberOfPage + "')]"));
        linkNextPage.click();
    }
}
