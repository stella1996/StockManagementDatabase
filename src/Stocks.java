import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Stocks {

    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/stockmanagement?autoReconnect=true&useSSL=false";
    //  Database credentials
    static final String USERNAME = "root";
    static final String PASSWORD = "stella";
    String category;
    String size;
    String color;
    String brand;
    int count;
    ArrayList<HashMap<String, Object>> availableStocks;
    String categories[] = {"Shirt", "Trouser", "Blazzer"};

    public Stocks() {
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Stocks(String category, String size, String color, String brand, int count) {
        this.category = category;
        this.size = size;
        this.color = color;
        this.brand = brand;
        this.count = count;
    }

    public ArrayList<HashMap<String, Object>> getAvailableStocks() {
        return availableStocks;
    }



    public void stocksToBeImported(ArrayList<HashMap<String, Object>> stocksToBeImported) {

        this.availableStocks = stocksToBeImported;

        for (Map<String, Object> entry : stocksToBeImported) {
            for (String keyValue : entry.keySet()) {
                Object value = entry.get(keyValue);
                Stocks stock = (Stocks) value;
                importedStocks(stock);

            }
        }
    }

    void importedStocks(Stocks stock) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            String sql = "INSERT INTO STOCKS"
                    + "(CATEGORY,SIZEID,COLOR,BRANDNAME,STOCKCOUNT) VALUES "
                    + "(?,(SELECT SIZEID FROM SIZES WHERE SIZE=?),?,?,?)";
            pstmt = conn.prepareStatement(sql);
            String category = stock.getCategory();
            String sizeId = stock.getSize();
            String color = stock.getColor();
            String brand = stock.getBrand();
            int count = stock.getCount();
            pstmt.setString(1, category);
            pstmt.setString(2, sizeId);
            pstmt.setString(3, color);
            pstmt.setString(4, brand);
            pstmt.setInt(5, count);
            pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    void retrieveStocksDetails() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            String selectquery="SELECT SID,CATEGORY,SIZE,COLOR,BRANDNAME," +
                    "STOCKCOUNT FROM STOCKS INNER JOIN SIZES ON STOCKS.SIZEID=SIZES.SIZEID;";
            pstmt=conn.prepareStatement(selectquery);
            ResultSet rs= pstmt.executeQuery(selectquery);
            while (rs.next())
            {

                System.out.println("Stock Category :"+" "+rs.getString("CATEGORY")+"\t"
                        +"Stock Size  :"+" "+rs.getString("SIZE")+"\t"
                        +"Stock Color :"+" "+rs.getString("COLOR")+"\t"
                        +"Stock Brand :"+" "+rs.getString("BRANDNAME")+"\t"
                        +"Stock Count :"+" "+rs.getInt("STOCKCOUNT")+"\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



    public void stocksUpdate(String key, Object remainingStock) {


        for (Map<String, Object> entry : availableStocks) {
            for (String keyValue : entry.keySet()) {
                if (keyValue.equals(key)) {
                    Object value = entry.get(keyValue);
                    Stocks stock = (Stocks) remainingStock;
                    availableStocks(stock);


                }
            }
        }


    }
    void availableStocks(Stocks stock) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            String sql = "UPDATE STOCKS SET STOCKCOUNT= ? WHERE SIZEID IN (SELECT SIZEID FROM SIZES WHERE SIZE= ? ) && CATEGORY=?";
            pstmt = conn.prepareStatement(sql);
            int count = stock.getCount();
            String size = stock.getSize();
            String category = stock.getCategory();
            pstmt.setInt(1, count);
            pstmt.setString(2, size);
            pstmt.setString(3, category);
            pstmt.executeUpdate();
            System.out.println("Available Stocks\n");
            retrieveStocksDetails();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public String[] getCategories() {
        return categories;
    }
}
