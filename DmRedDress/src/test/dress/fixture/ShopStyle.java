package test.dress.fixture;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import test.dress.BaseFixture;
import test.utils.objects.DressObject;

import java.util.ArrayList;

import static org.hamcrest.core.IsEqual.equalTo;
import static test.utils.Actions.*;


public class ShopStyle extends BaseFixture{

    @Rule
    public ErrorCollector collector= new ErrorCollector();

    @Test
    public void testSSSearch() throws Exception {

	        searchForItem(searchTerm);
	        selectCountry(locale);
	        ArrayList<DressObject> webList = locateItems(10);
	        ArrayList<DressObject> apiList = runClient(10, apiSearchTerm);
	
	        System.out.println("Api List Size: " + apiList.size());
	        System.out.println("Web List Size: " + webList.size());
	        if (webList.size() == apiList.size()) {
	
	            for(int i = 0; i < webList.size(); i++ ){
	                System.out.println("********** Test "+ i +" **********");
	                System.out.println("Expected");
	                System.out.println(apiList.get(i).getValues());
	                System.out.println("Actual");
	                System.out.println(webList.get(i).getValues());
	
	                //
	                collector.checkThat("Test "+i+ "\n",webList.get(i).getName().toLowerCase().toString(), equalTo(apiList.get(i).getName().toLowerCase().toString()));
	                collector.checkThat("Test "+i+ "\n",webList.get(i).getPrice().toString(), equalTo(apiList.get(i).getPrice().toString()));
	                collector.checkThat("Test "+i+ "\n",webList.get(i).getRetailer().toLowerCase().toString(), equalTo(apiList.get(i).getRetailer().toLowerCase().toString()));
	            }
	        }
	        else {
	
	            Assert.fail();
	        }
	
	
    }
}
