package my.project;

import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * This class will manage the whole program.
 *
 * @author shawn
 */
public class Manager {
    private double budget;
    private ShoppingCenter s = new ShoppingCenter();
    private Store currentLocation;
    private IO io;


    /**
     * This is my start method. First the user can choose his profession. Afterwards the user can enter the mall.
     */
    public void startProgram() {
        io = new IO(s, this);
        s.createShoppingCenter();
        io.professionChoice();
        io.enterMall();
    }




    /**
     * This method is to find the hallway. Everytime the user wants to leave the store or change the
     * floor, the user must be in the hallway.
     *
     * @param floor
     * @return
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
     * This method is used to find the current floor.
     *
     * @param store
     * @return
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
     * This method is used to find the current store. The Store will
     * be saved in currentLocation.
     *
     * @param floor
     */
    public void findStore(Floor floor) {
        Scanner scan = new Scanner(System.in);

        if (s.getFloors().indexOf(findFloor(currentLocation)) == 0) {
            io.storesSecondFloor();
        } else {
            io.storesFirstFloor();
        }
        System.out.println("store: ");
        int chooseStore = scan.nextInt();

        currentLocation = floor.getStores().get(chooseStore - 1);

    }

    /**
     * This method is used to change floors.
     */
    public void changeFloor() {

        Floor currentFloor = findFloor(currentLocation);
        for (Floor floor : s.getFloors()) {
            if (currentFloor != floor) {
                currentFloor = floor;
                break;
            }
        }
        currentLocation = findHallway(currentFloor);
        io.printCurrentPosition();
    }


    /**
     * If the user decides to work he will land in this method. It has a randomizer with a min = 300 and max 1000. That
     * means the user can earn money between 300.- and 1000.-.
     */
    public void goWork() {
        Random r = new Random();
        int low = 300;
        int high = 1000;
        int result = r.nextInt(high - low) + low;

        IO.outPutListInBox(new String[]{"your earnings: " + result}, 2);
        budget += result;
        IO.outPutListInBox(new String[]{"total budget: " + budget}, 2);
    }

    public static int readInt() {
        Scanner scan = new Scanner(System.in);
        int input = 0;
        boolean isValid = false;
        while (!isValid) {
            try {
                input = Integer.parseInt(scan.nextLine());
                isValid = true;
            } catch (NumberFormatException e) {
                System.out.println("wrong input");
            }
        }
        return input;
    }
}