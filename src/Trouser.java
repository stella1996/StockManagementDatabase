public class Trouser extends Stocks {


    public Trouser() {

    }

    public Trouser(String category, String size, String color, String brand, int count) {
        super(category, size, color, brand, count);
    }

    @Override
    public String toString() {
        return "Trouser{" +
                "category='" + category + '\'' +
                ", size='" + size + '\'' +
                ", color='" + color + '\'' +
                ", brand='" + brand + '\'' +
                ", count=" + count +
                '}';
    }
}
