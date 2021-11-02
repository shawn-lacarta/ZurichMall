package my.project;

import org.junit.jupiter.api.BeforeEach;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ManagerTest {

    Manager manager;
    Floor floor;
    ShoppingCenter shoppingCenter;
    Store store;

    @BeforeEach
    void fillShoppingCenter() {
        shoppingCenter = new ShoppingCenter();
        floor = new Floor();
        manager = new Manager();
        shoppingCenter.createShoppingCenter();
        store = new Store("Sore");

        shoppingCenter.getFloors().add(floor);
        floor.getStores().add(new Store("nike"));
        floor.getStores().add(new Store("foot locker"));
        floor.getStores().add(new Store("star bucks"));
        floor.getStores().add(new Store("mobile zone"));
        floor.getStores().add(new Store("lacoste"));
        floor.getStores().add(new Store("hallway"));
        floor.getStores().add(store);
        manager.setS(shoppingCenter);

    }
    @org.junit.jupiter.api.Test
    void findHallway() {
        fillShoppingCenter();
        assertNotEquals(manager.findHallway(floor), null);
    }

    @org.junit.jupiter.api.Test
    void findFloor() {
        fillShoppingCenter();
        assertNotEquals(manager.findFloor(store), null);
    }
}