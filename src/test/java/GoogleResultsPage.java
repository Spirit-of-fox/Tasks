import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GoogleResultsPage extends BaseTestActions {
    WebDriver webDriver = BaseTestActions.driver;

    private WebElement linkNextPage;
    private List<WebElement> elementsResultOfSearch = webDriver.findElements(By.xpath("//*[@id='rso']//div[@class='g']"));
    private String attributeCountOfResults = driver.findElement(By.xpath("//*[@id='result-stats']")).getText();
    private String timeElement = driver.findElement(By.xpath("//nobr")).getText();

    public String[] getTextFromElements() {
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
        String result = driver.findElement(By.xpath("//*[@id='result-stats']")).getText();

        Pattern patternForCountRes = Pattern.compile("\\s\\d+");
        Matcher matcherInCountRes = patternForCountRes.matcher(result);

        String countOfResults = "";
        while (matcherInCountRes.find()) {
            countOfResults = countOfResults.concat(matcherInCountRes.group().trim());
        }

        return countOfResults;
    }

    public String getTimeOfSearch() {
        Pattern patternForTime = Pattern.compile("([\\d\\,]+)");
        Matcher matcher = patternForTime.matcher(timeElement);
        String timeOfSearch = "";
        while (matcher.find()) {
            timeOfSearch = timeOfSearch.concat(matcher.group().trim());
        }
        return timeOfSearch;
    }

    public void clickOnLinkToNextPage(int numberOfPage) {
        linkNextPage = webDriver.findElement(By.xpath("//a[contains(@aria-label, 'Page " + numberOfPage + "')]"));
        linkNextPage.click();
    }
}
