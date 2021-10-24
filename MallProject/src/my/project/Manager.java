package my.project;

import java.util.Random;

/**
 * This class is responsible for the logical part
 * of the program.
 * @author shawn
 */
public class Manager {

    private ShoppingCenter s = new ShoppingCenter();
    private IO io;

    /**
     * This method is used to start the program.
     */
    public void startProgram() {
        io = new IO(s, this);
        s.createShoppingCenter();
        io.professionChoice();
        io.enterMall();
    }

    /**
     * Every time the user wants to exit a store the
     * user should be in the hallway. To find the hallway
     * i wrote this method
     * @param floor This parameter is for the floor
     * @return This method returns the store which is the hallway
     */
    public Store findHallway(Floor floor) {
        for (Store store : floor.getStores()) {
            if (store.getName().equals("hallway")) {
                return store;
            }
        }
        return null;
    }

    /**
     * This method is to find the floor.
     * @param store This parameter is for the store.
     * @return This method returns the current floor.
     */
    public Floor findFloor(Store store) {
        for (Floor floor : s.getFloors()) {
            for (Store localStore : floor.getStores()) {
                if (localStore.equals(store)) {
                    return floor;
                }
            }
        }
        return null;
    }

    /**
     * This method is to find the stores.
     * @param floor This parameter is for the floor. It
     * depends on the floor which stores will be shown.
     */
    public void findStore(Floor floor) {
        if (s.getFloors().indexOf(findFloor(io.getCurrentLocation())) == 0) {
            io.storesFirstFloor();
        } else {
            io.storesSecondFloor();
        }
        System.out.println("store: ");
        int chooseStore = IO.readRangedInt(1, floor.getStores().size());

         io.setCurrentLocation(floor.getStores().get(chooseStore - 1));
    }

    /**
     * This method is responsible to change the floors.
     */
    public void changeFloor() {
        Floor currentFloor = findFloor(io.getCurrentLocation());
        for (Floor floor : s.getFloors()) {
            if (currentFloor != floor) {
                currentFloor = floor;
                break;
            }
        }
        io.setCurrentLocation(findHallway(currentFloor));
        io.printCurrentPosition();
    }

    /**
     * This method is used for the user to work.
     */
    public void goWork() {
        Random r = new Random();
        int low = 300;
        int high = 1000;
        int result = r.nextInt(high - low) + low;

        IO.outPutListInBox(new String[]{"your earnings: " + result}, 2);
        io.setBudget(io.getBudget() + result);
        IO.outPutListInBox(new String[]{"total budget: " + io.getBudget()}, 2);
    }

}