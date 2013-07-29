package test.dress;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public abstract class BaseFixture {

    protected static WebDriver selenium;
    protected static Properties configFile = new Properties();
    protected DateFormat dateValue = new SimpleDateFormat("dd/MM/yyyy");
    protected Calendar dateAssert = Calendar.getInstance();
    public String baseURL;
    public static String testURL, otherURL, usTitle, ukTitle;
    public static String locale, searchTerm, initialSearchUrl, linkSearchUrl, apiSearchTerm;
    public static String[] retailers;

    @Before
	public void setup () throws Exception {

        loadConfig();
		selenium = new FirefoxDriver();
        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setJavascriptEnabled(true);
        dc.setCapability("locationContextEnabled", false);
        selenium.get(baseURL);
        selenium.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

    protected void loadConfig() throws FileNotFoundException {
        try {
            configFile.load(new FileInputStream("src/resources/config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        baseURL = configFile.getProperty("BASE_URL");
        testURL = configFile.getProperty("TEST_URL");
        otherURL = configFile.getProperty("OTHER_URL");
        usTitle = configFile.getProperty("us_TITLE");
        ukTitle = configFile.getProperty("uk_TITLE");
        locale = configFile.getProperty("LOCATION");
        searchTerm = configFile.getProperty("SEARCH_TERM");
        initialSearchUrl = configFile.getProperty("initial_SEARCH_URL");
        linkSearchUrl = configFile.getProperty("link_SEARCH_URL");
        retailers =configFile.getProperty("RETAILERS").split(",");
        apiSearchTerm = configFile.getProperty("FTS");
    }


    @After
	public void tearDown() {
		selenium.close();
	}

}
