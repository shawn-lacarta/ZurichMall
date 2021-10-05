package my.project;

import java.util.ArrayList;

public class Store {
    private ArrayList<Product> products = new ArrayList<>();
    private String name;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Store(String name) {
        this.name = name;
    }


    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }


}
