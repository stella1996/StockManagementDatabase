

public class Shirt extends Stocks {


    public Shirt() {

    }

    public Shirt(String category, String size, String color, String brand, int count) {
        super(category, size, color, brand, count);
    }

    @Override
    public String toString() {
        return "Shirt{" +
                "category='" + category + '\'' +
                ", size='" + size + '\'' +
                ", color='" + color + '\'' +
                ", brand='" + brand + '\'' +
                ", count=" + count +
                '}';
    }
}
