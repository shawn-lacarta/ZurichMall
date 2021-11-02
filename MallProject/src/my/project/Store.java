package my.project;

import java.util.ArrayList;

/**
 * This class has the array list of products.
 * @author shawn
 */
public class Store {
    private ArrayList<Product> products = new ArrayList<>();
    private String name;
    public Store(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public ArrayList<Product> getProducts() {
        return products;
    }

    public Store() {
    }
}
