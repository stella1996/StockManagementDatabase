public class Blazzer extends Stocks {


    public Blazzer() {

    }

    public Blazzer(String category, String size, String color, String brand, int count) {
        super(category, size, color, brand, count);
    }

    @Override
    public String toString() {
        return "Blazzer{" +
                "category='" + category + '\'' +
                ", size='" + size + '\'' +
                ", color='" + color + '\'' +
                ", brand='" + brand + '\'' +
                ", count=" + count +
                '}';
    }
}
