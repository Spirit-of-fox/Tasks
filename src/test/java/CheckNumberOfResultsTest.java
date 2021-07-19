import com.company.PropertiesReader;
import org.testng.Assert;
import org.testng.annotations.*;
import java.util.concurrent.TimeUnit;

public class CheckNumberOfResultsTest extends BaseTestActions {

    @Test
    public void testTwo () {
        PropertiesReader properties = new PropertiesReader("src/main/resources/config.properties");
        String wordForSearch = properties.getProperties("searchWord");
        String numRes = properties.getProperties("numRes");

        GoogleSearchPage gsp = new GoogleSearchPage();
        gsp.searchFor(wordForSearch);

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        GoogleResultsPage grp = new GoogleResultsPage();
        String countOfResults = grp.getCountOfResults();
        String timeOfSearch = grp.getTimeOfSearch();

        Assert.assertTrue((Integer.parseInt(countOfResults))>Integer.parseInt(numRes), "CHECK FAILED: The number of search results is less than " + numRes);
        System.out.println("The number of search is " + countOfResults + ", time of waiting: " + timeOfSearch);
    }
}

