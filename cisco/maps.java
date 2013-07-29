public void testGoogleTestSearch() throws Exception 
{
	selenium.open("http://maps.google.com/webhp");
	 assertEquals("Google", selenium.getTitle());
	selenium.type("q", "London");
	selenium.click("gbqfi");
	selenium.waitForPageToLoad("5000");
	 assertEquals("Selenium OpenQA - Google Search",
	selenium.getTitle());
}
