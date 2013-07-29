
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Maps {


	@BeforeTest
	public void setup () throws Exception {

		selenium = new DefaultSelenium ("localhost", 4444, "*chrome", "http://maps.google.co.uk"); 
	}
	
	@Test
	public void testGoogleTestSearch() throws Exception {
		selenium.open("http://maps.google.co.uk");
		 assertEquals("Google Maps", selenium.getTitle());
		selenium.type("q", "London");
		selenium.click("gbqfi");
		selenium.waitForPageToLoad("5000");
		 assertEquals("Google Maps",selenium.getTitle());
	}

	@AfterTest
	public void tearDown() {
		
		selenium.stop();
	}

}
