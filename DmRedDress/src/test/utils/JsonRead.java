package test.utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import test.utils.objects.DressObject;

import java.util.ArrayList;
import java.util.HashMap;

public class JsonRead{


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
