import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.company.GetPropertiesClass.numRes;
import static com.company.GetPropertiesClass.searchWord;

public class CheckNumberOfResultsTest extends BaseTestActions {
    private String buf;
    private String timeValue;

    @Test
    public void testTwo () {
        getElements();

        if ((Integer.parseInt(buf))>Integer.parseInt(numRes)) {
            System.out.println("The number of search results is more than " + numRes);
        }
        else {
            System.out.println("CHECK FAILED: The number of search results is less than " + numRes);
        }
        System.out.println("The number of search is " + buf + ", time of waiting: " + timeValue);
        ending();
    }

    private void getTimeValue() {
        String timeElement = driver.findElement(By.xpath("//nobr")).getText();//
        Pattern patternForTime = Pattern.compile("([\\d\\,]+)");
        Matcher matcher1 = patternForTime.matcher(timeElement);

        timeValue = "";
        while (matcher1.find()) {
            timeValue = timeValue.concat(matcher1.group().trim());
        }
    }

    private void getElements() {
        getWebPage(searchWord);

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        String result = driver.findElement(By.xpath("//*[@id='result-stats']"))
                .getText();//

        Pattern pattern = Pattern.compile("\\s\\d+");
        Matcher matcher = pattern.matcher(result);
        getTimeValue();
        buf = "";
        while (matcher.find()) {
            buf = buf.concat(matcher.group().trim());
        }
    }

    private void getWebPage(String keyWord) {
        WebElement element = driver.findElement(By.name("q"));
        element.sendKeys(keyWord);
        element.submit();
    }
}

