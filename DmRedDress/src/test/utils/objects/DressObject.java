package test.utils.objects;

public class DressObject{

    private String price;
    private String name;
    private String retailer;

    public DressObject(String amount, String item, String shop) {

        price = amount;
        name = item;
        retailer = shop;
    }

    public String getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public String getRetailer() {
        return retailer;
    }

    public String getValues(){
        return name + ", "+ price + ", "+ retailer;
    }


}