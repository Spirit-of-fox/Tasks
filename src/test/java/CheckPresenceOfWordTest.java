import com.company.PropertiesReader;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class CheckPresenceOfWordTest extends BaseTestActions {

    @Test
    public void testOne () {
        PropertiesReader properties = new PropertiesReader("src/main/resources/config.properties");
        String wordForSearch = properties.getProperties("searchWord");

        GoogleSearchPage gsp = new GoogleSearchPage();
        gsp.searchFor(wordForSearch);

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        GoogleResultsPage grp = new GoogleResultsPage();
        checkingPresence(grp.getTextFromElements());

        grp.clickOnLinkToNextPage(2);

        checkingPresence(grp.getTextFromElements());
    }

    private void checkingPresence(String[] a) {
        PropertiesReader properties = new PropertiesReader("src/main/resources/config.properties");
        String wordForSearch = properties.getProperties("searchWord");
        for(String i:a) {
            Assert.assertTrue(i.contains(wordForSearch.toLowerCase()), "Not all blocks contain the keyword");
        }
    }
}
