package my.project;

/**
 * This class has all the properties of a product.
 * @author shawn
 */
public class Product {

    private String name;
    private String color;
    private String size;
    private double price;

    public Product(String name, String color, String size, double price) {
        this.name = name;
        this.color = color;
        this.size = size;
        this.price = price;
    }
    public String getName() {
        return name;
    }
    public String getColor() {
        return color;
    }
    public String getSize() {
        return size;
    }
    public double getPrice() {
        return price;
    }
}
