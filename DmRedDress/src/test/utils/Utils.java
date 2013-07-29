package test.utils;

import org.junit.Assert;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import test.utils.objects.DressObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Utils extends test.dress.BaseFixture{

    public static void clickCheckBox(String inputValue) {
        WebElement checkBox = selenium.findElement(By.xpath("//input[@value='"+inputValue+"']"));
        if (!checkBox.isSelected()) {
            checkBox.click();
        }
        Assert.assertTrue(checkBox.isSelected());
    }

    public static void deselectCheckBox(String inputValue) {
        WebElement checkBox = selenium.findElement(By.xpath("//input[@value='"+inputValue+"']"));
        if (checkBox.isSelected()) {
            checkBox.click();
        }
        Assert.assertFalse(checkBox.isSelected());
    }

    public static void selectDropDownByName(String element, String selection){
        Select ddBox = new Select(selenium.findElement(By.name(element)));
        ddBox.selectByVisibleText(selection);
    }

    public static void selectDropDownByClass(String element, String selection){
        Select ddBox = new Select(selenium.findElement(By.className(element)));
        ddBox.selectByVisibleText(selection);
    }

    public static void enterTextIntoField(String tagName, String text) throws Exception {

        WebElement inputField = selenium.findElement(By.name(tagName));
        inputField.sendKeys(text);
        if (inputField.getAttribute("value").equals(text)){
            inputField.submit();
        }
        else {
            throw new Exception(text + ", text not entered into field");
        }
    }

    public static void clickOnTableCellMenu(String outerClassName, String[] innerClassName, String title){

        // Click on Outer element
        WebElement menu = selenium.findElement(By.className(outerClassName));
        menu.click();
        //Interact with inner elements
        WebElement inner = selenium.findElement(By.className(innerClassName[0]));
        List<WebElement> items = inner.findElements(By.className(innerClassName[1]));
        for (WebElement e: items) {

            if (e.getText().contains(title)) {
                e.findElement(By.tagName("a")).click();
                return;
            }
        }

    }

    public static Boolean checkElementValueByClass(String element, String value){

        WebElement check = selenium.findElement(By.className(element));
        if (check.getText() == value) {
            return true;
        }
        else {
            return false;
        }

    }

    public static void findEmbeddedElement(String[] elements, String Selection) {

        WebElement check1 = selenium.findElement(By.className(elements[0]));
        List<WebElement> check2 = check1.findElements(By.tagName(elements[1]));

        for (WebElement e : check2) {

             if (e.getAttribute("value").toString().equals(Selection)) {
                 e.click();
             }

        }

    }

    public static ArrayList<DressObject> parse(String jsonObject) {

        ArrayList<DressObject> dressObjectArrayList = new ArrayList<DressObject>();
        JSONParser parser = new JSONParser();

        try {

            Object obj = parser.parse(jsonObject);

            JSONObject jObject = (JSONObject) obj;

            //String[] name = (String[]) jObject.get("products");

            // loop array
            JSONArray products = (JSONArray) jObject.get("products");

            HashMap<String, String> pairs = new HashMap<String, String>();
            for (Object jo : products){

                DressObject dmDo = new DressObject(
                        ((JSONObject) jo).get("priceLabel").toString(),
                        ((JSONObject)jo).get("name").toString(),
                        ((JSONObject)((JSONObject) jo).get("retailer")).get("name").toString()
                );
                dressObjectArrayList.add(dmDo);
            }


        } catch (ParseException e) {
            e.printStackTrace();
        }

        return dressObjectArrayList;

    }

}
