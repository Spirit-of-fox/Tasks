import com.company.PropertiesReader;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.*;
import java.util.concurrent.TimeUnit;

public class CheckNumberOfResultsTest extends BaseTestActions {
    private static Logger logger = LogManager.getLogger(CheckNumberOfResultsTest.class);

    @Test
    public void testTwo () {
        try {
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

            String text = String.format("The number of search is %s, time of waiting: %s", countOfResults, timeOfSearch);
            System.out.println(text);
        }
        catch (Exception e) {
            logger.fatal("Test two crashed");

        }
    }
}

