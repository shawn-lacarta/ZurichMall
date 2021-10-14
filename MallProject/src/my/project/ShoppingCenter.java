package my.project;

import java.util.ArrayList;

/**
 * This class has the array list of floors and the generation of the whole mall.
 * @author shawn
 */
public class ShoppingCenter {

    private ArrayList<Floor> floors = new ArrayList<>();

    public ArrayList<Floor> getFloors() {
        return floors;
    }

    /**
     * This method is the creating of my mall. I'm creating the floors, stores and products.
     *
     */
    public void createShoppingCenter() {
        getFloors().add(new Floor());
        getFloors().add(new Floor());
        getFloors().get(0).getStores().add(new Store("nike"));
        getFloors().get(0).getStores().add(new Store("foot locker"));
        getFloors().get(0).getStores().add(new Store("star bucks"));
        getFloors().get(0).getStores().add(new Store("mobile zone"));
        getFloors().get(0).getStores().add(new Store("lacoste"));
        getFloors().get(0).getStores().add(new Store("zara"));
        getFloors().get(0).getStores().add(new Store("hallway"));
        getFloors().get(1).getStores().add(new Store("media markt"));
        getFloors().get(1).getStores().add(new Store("migro"));
        getFloors().get(1).getStores().add(new Store("mc donalds"));
        getFloors().get(1).getStores().add(new Store("ice box"));
        getFloors().get(1).getStores().add(new Store("hallway"));
        getFloors().get(0).getStores().get(0).getProducts().add(new Product("swoosh t-shirt", "baby-blue", "M", 39.90));
        getFloors().get(0).getStores().get(0).getProducts().add(new Product("swoosh hoodie", "black", "L", 89.90));
        getFloors().get(0).getStores().get(0).getProducts().add(new Product("swoosh pants", "grey", "L", 59.90));
        getFloors().get(0).getStores().get(1).getProducts().add(new Product("jordan 4 white cement", "white", "EU 44", 249.95));
        getFloors().get(0).getStores().get(1).getProducts().add(new Product("jordan 11 concord", "white / black", "EU 42", 259.95));
        getFloors().get(0).getStores().get(1).getProducts().add(new Product("nike air max plus supreme", "white", "EU 45", 209.90));
        getFloors().get(0).getStores().get(1).getProducts().add(new Product("jordan 3 unc", "white / baby-blue", "EU 44", 249.95));
        getFloors().get(0).getStores().get(2).getProducts().add(new Product("coffee", "", "big cup", 9.00));
        getFloors().get(0).getStores().get(2).getProducts().add(new Product("chocolate chip cookie", "", "big", 4.90));
        getFloors().get(0).getStores().get(2).getProducts().add(new Product("water", "", "500ml", 3.90));
        getFloors().get(0).getStores().get(3).getProducts().add(new Product("iphone 12", "gold", "64GB", 799.00));
        getFloors().get(0).getStores().get(3).getProducts().add(new Product("samsung galaxy s20", "black", "126GB", 599.90));
        getFloors().get(0).getStores().get(3).getProducts().add(new Product("ipad pro (11)", "space grey", "128GB", 849.00));
        getFloors().get(0).getStores().get(4).getProducts().add(new Product("lacoste track suit", "green", "L", 299.00));
        getFloors().get(0).getStores().get(4).getProducts().add(new Product("logo-cap", "black", "one size", 39.90));
        getFloors().get(0).getStores().get(5).getProducts().add(new Product("jeans", "blue", "34/34", 79.90));
        getFloors().get(0).getStores().get(5).getProducts().add(new Product("basic t-shirt", "white", "M", 29.90));
        getFloors().get(1).getStores().get(0).getProducts().add(new Product("hp laptop", "grey", "1000GB", 1199.90));
        getFloors().get(1).getStores().get(0).getProducts().add(new Product("apple macbook pro", "space grey", "128GB", 1499.00));
        getFloors().get(1).getStores().get(0).getProducts().add(new Product("samsung T7", "black", "512GB", 89.90));
        getFloors().get(1).getStores().get(1).getProducts().add(new Product("red bull", "", "500ml", 1.60));
        getFloors().get(1).getStores().get(1).getProducts().add(new Product("paprika chips", "", "XXL", 5.90));
        getFloors().get(1).getStores().get(1).getProducts().add(new Product("ice tea peach", "", "500ml", 0.90));
        getFloors().get(1).getStores().get(1).getProducts().add(new Product("salad", " ", "big", 4.90));
        getFloors().get(1).getStores().get(2).getProducts().add(new Product("big tasty menu", "", "medium", 14.90));
        getFloors().get(1).getStores().get(2).getProducts().add(new Product("cheeseburger menu", "", "medium", 10.90));
        getFloors().get(1).getStores().get(2).getProducts().add(new Product("big mac menu", "", "medium", 14.90));
        getFloors().get(1).getStores().get(3).getProducts().add(new Product("iced out chain", "", "M", 12000.00));
        getFloors().get(1).getStores().get(3).getProducts().add(new Product("iced out grills", "", "M", 8000.00));
        getFloors().get(1).getStores().get(3).getProducts().add(new Product("iced out ear rings", "", "M", 5000.00));
    }
}
