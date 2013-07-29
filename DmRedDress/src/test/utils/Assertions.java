package test.utils;

import org.junit.Assert;
import test.dress.BaseFixture;


public class Assertions extends BaseFixture{

    public static void assertTitle(String checkTitle){
        Assert.assertEquals("*** Test: \n" , selenium.getTitle(), checkTitle);
    }

    public static void assertURl(String checkUrl){
        Assert.assertEquals( "*** Test: \n", selenium.getCurrentUrl(), checkUrl);
    }
}
