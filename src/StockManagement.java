import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class StockManagement {


    static HashMap<String, Object> stock = new HashMap<String, Object>();

    public static void main(String s[]) {
        //Welcome Message for the Application
        System.out.println("Welcome to Stock Management System\n");

        //Detals about the stock availablity
        Stocks categories = new Stocks();
        System.out.println("Types of Stocks Present in shop is :" + "" + Arrays.toString(categories.getCategories()) + "\n");


        ArrayList<HashMap<String, Object>> stockList = new ArrayList<HashMap<String, Object>>();

        //Stock 1
        Shirt shirtS = new Shirt("Shirt", "S", "Blue", "VanHuesen", 30);
        stock.put("shirtS", shirtS);

        //Stock 2
        Shirt shirtM = new Shirt("Shirt", "M", "Black", "Polo", 20);
        stock.put("shirtM", shirtM);

        //Stock 3
        Trouser size32 = new Trouser("Trouser", "32", "Black", "VanHuesen", 10);
        stock.put("size32", size32);

        //Stock 4
        Trouser size34 = new Trouser("Trouser", "34", "Maroon", "VanHuesen", 20);
        stock.put("size34", size34);

        //Stock 5
        Trouser size36 = new Trouser("Trouser", "36", "white", "VanHuesen", 40);
        stock.put("size36", size36);

        //Stock 6
        Blazzer blazzer = new Blazzer("Blazzer", "M", "Orange", "VanHuesen", 50);
        stock.put("blazzer", blazzer);

        stockList.add(stock);
        Stocks stocks = new Stocks();
        stocks.stocksToBeImported(stockList);
        System.out.println("Imported Stocks\n");
        stocks.retrieveStocksDetails();

        //Stock Availability
        System.out.println("Total Number of Stocks Available in terms of size category :" + "" + stock.size() + "\n");


        //Calculating the remaining stock using count datamember
        int orginalCount = shirtS.getCount();
        int exportedCount = 5;
        int remainingCount = orginalCount - exportedCount;

        //Exported Stock Details
        Shirt shirt = new Shirt();
        shirt.setCategory("Shirt");
        shirt.setSize("S");
        shirt.setColor("Blue");
        shirt.setBrand("Vanhuesen");
        shirt.setCount(exportedCount);

        //Stock Exported
        System.out.println("Exported Stocks " + " " + "\n\n" + shirt.toString() + "\n");
        shirt.setCount(remainingCount);
        stocks.stocksUpdate("shirtS", shirt);
    }
}
