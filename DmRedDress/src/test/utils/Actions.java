package test.utils;

import org.apache.commons.httpclient.HttpException;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.xml.sax.SAXException;
import test.utils.objects.DressObject;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static test.utils.Assertions.assertTitle;
import static test.utils.Assertions.assertURl;
import static test.utils.Utils.clickOnTableCellMenu;
import static test.utils.Utils.enterTextIntoField;

public class Actions extends test.dress.BaseFixture{

    public static void searchForItem(String searchTerm) throws Exception {

       selenium.navigate().to(testURL);
       if (selenium.getCurrentUrl().contains(otherURL)) {
           assertTitle(ukTitle);
       }
       else {
           assertTitle(usTitle);
       }

        enterTextIntoField("fts", searchTerm);
        if (selenium.getCurrentUrl().contains(otherURL)) {
            assertURl(otherURL+initialSearchUrl);
        }
        else {
            assertURl(testURL+initialSearchUrl);
        }

    }

    public static void selectCountry(String country){

        clickOnTableCellMenu("menu-selected-item", new String[] {"menu-inner","menu-item"}, country);
        if (selenium.getCurrentUrl().contains(otherURL)) {
            assertURl(otherURL+linkSearchUrl);
        }
        else {
            assertURl(testURL+linkSearchUrl);
        }
    }

    public static ArrayList<DressObject> locateItems(int numberOfItems){

        String title, retailer, price;
        WebElement productView = selenium.findElement(By.className("productViewerWrapper"));
        List<WebElement> productItems = productView.findElements(By.className("rawCell"));
        List<WebElement> productList = productItems.subList(0,numberOfItems);

        ArrayList<DressObject> dmDO = new ArrayList<DressObject>();

        for (WebElement e : productList){

            if (!e.findElement(By.className("title")).equals(null)) {
                title = e.findElement(By.className("title")).getText();
            }
            else {
                title = "";
            }
            if (!e.findElement(By.className("price")).equals(null)) {
                if (e.findElement(By.className("price")).getText().contains(".")){
                    price =e.findElement(By.className("price")).getText();
                }
                else {
                    price = e.findElement(By.className("price")).getText()+".00";
                }
            }
            else {
                price = "0.00";
            }

            if (!e.findElement(By.className("title")).equals(null)) {
                retailer = checkRetailerName(e.findElement(By.linkText(title)).getAttribute("href"));
            }
            else {
                retailer = "";
            }

            DressObject d = new DressObject(price, title, retailer);
            dmDO.add(d);


        }
        return dmDO;
    }

    public static String checkRetailerName(String productLink){

        WebElement filter = selenium.findElement(By.id("RetailerFilterCell"));
        if (!filter.getAttribute("class").contains("open")){
            filter.click();
        }
        WebElement stores = selenium.findElement(By.id("RetailerPopularFilterContainer"));
        List<WebElement> labels =  stores.findElements(By.className("label"));

        for (WebElement e: labels) {

            if (productLink.contains(e.getText().toLowerCase().replaceAll("[\\s.]","").substring(0,6))){
                return e.getText();
            }
        }

        for (String s : retailers){
            if (productLink.contains(s.toLowerCase().replaceAll("[\\s.]", "").substring(0, 6))){
                return s;
            }
        }

        return "";
    }

    public static ArrayList<DressObject> runClient(int limit, String searchTerm) throws HttpException, IOException, ParserConfigurationException,
            SAXException, XPathExpressionException, URISyntaxException {

        JsonRead jRead;
        InputStream inputStream = null;
        // Make the Rest request.
        URIBuilder builder = new URIBuilder();
        builder.setScheme("http").setHost("api.shopstyle.com").setPath("/api/v2/products")
                .setParameter("pid", "uid6784-22493972-77")
                .setParameter("fts", searchTerm)
                .setParameter("offset", "0" )
                .setParameter("limit", String.valueOf(limit));
        URI uri = builder.build();

        //Make the call
        DefaultHttpClient httpclient = new DefaultHttpClient();
        HttpGet httpget = new HttpGet(uri);
        HttpResponse response = httpclient.execute(httpget);
        Header header = response.getEntity().getContentEncoding();

        //Read output
        jRead = new JsonRead();
        ArrayList<DressObject> apiList = jRead.parse(EntityUtils.toString(response.getEntity()));
        return apiList;

    }




}
