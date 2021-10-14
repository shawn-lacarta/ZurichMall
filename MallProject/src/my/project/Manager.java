package my.project;

import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * This class will manage the whole program.
 * @author shawn
 */
public class Manager {

    public static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_RESET = "\u001B[0m";
    private Scanner scan = new Scanner(System.in);
    private final int CLEANING_SERVICE = 500;
    private final int GARBAGE_COLLECTOR = 850;
    private final int OFFICE_WORKER = 1200;
    private final int INVESTOR = 2000;
    private final int CEO = 5000;
    private double budget;
    private ShoppingCenter s = new ShoppingCenter();
    private Store currentLocation;


    /**
     * This is my start method. First the user can choose his profession. Afterwards the user can enter the mall.
     *
     */
    public void startProgram() {
        professionChoice();
        enterMall();
    }

    /**
     * The user can choose his profession in this method by entering a number, which is stored a
     * profession. Depending on the profession, the budget is set to a different value.
     *
     */
    public void professionChoice() {
        int input = 0;
        while (input < 1 || input > 5) {
            IO.outPutListInBox(new String[]{"choose your profession: ", " ", "[1] cleaning service", "[2] garbage collector", "[3] office worker", "[4] investor", "[5] CEO"}, 2);
            try {
                input = scan.nextInt();
            } catch (Exception e) {

            } finally {
                scan.nextLine();
            }

            switch (input) {
                case 1 -> budget = CLEANING_SERVICE;
                case 2 -> budget = GARBAGE_COLLECTOR;
                case 3 -> budget = OFFICE_WORKER;
                case 4 -> budget = INVESTOR;
                case 5 -> budget = CEO;
                default -> System.out.println("wrong input");
            }
        }
        System.out.println(ANSI_RED + "your budget: " + budget + " CHF" + ANSI_RESET);
    }

    /**
     * Here the user can decide whether to enter the mall or prefer to work. The
     * user decides by spelling out enter or work.
     *
     */
    public void enterMall() {

        String input = " ";
        s.createShoppingCenter();
        currentLocation = findHallway(s.getFloors().get(0));

        while (!(input.equals("enter") || input.equals("work"))) {
            do {
                IO.outPutListInBox(new String[]{"to enter the mall spell enter / to work spell work / to leave spell exit: "}, 2);
                input = scan.nextLine();

                switch (input) {
                    case "enter" -> System.out.println(ANSI_BLUE + " _    _      _                            _          _   _            ______           _      _      ___  ___      _ _ \n" +
                            "| |  | |    | |                          | |        | | | |          |___  /          (_)    | |     |  \\/  |     | | |\n" +
                            "| |  | | ___| | ___ ___  _ __ ___   ___  | |_ ___   | |_| |__   ___     / / _   _ _ __ _  ___| |__   | .  . | __ _| | |\n" +
                            "| |/\\| |/ _ | |/ __/ _ \\| '_ ` _ \\ / _ \\ | __/ _ \\  | __| '_ \\ / _ \\   / / | | | | '__| |/ __| '_ \\  | |\\/| |/ _` | | |\n" +
                            "\\  /\\  |  __| | (_| (_) | | | | | |  __/ | || (_) | | |_| | | |  __/ ./ /__| |_| | |  | | (__| | | | | |  | | (_| | | |\n" +
                            " \\/  \\/ \\___|_|\\___\\___/|_| |_| |_|\\___|  \\__\\___/   \\__|_| |_|\\___| \\_____/\\__,_|_|  |_|\\___|_| |_| \\_|  |_/\\__,_|_|_|\n" +
                            "                                                                                                                       \n" +
                            "                                                                                                                       " + ANSI_RESET);
                    case "work" -> goWork();
                    case "exit" -> System.exit(0);
                    default -> System.out.println("wrong input");
                }
            }while(!input.equals("enter"));
        }
        move();

    }

    /**
     * This method is to move around the mall. Depending on the user input, different methods are used.
     *
     */
    public void move() {
        IO o = new IO();
        int selectMovement = 0;
        while (selectMovement < 1 || selectMovement > 4) {
            IO.outPutListInBox(new String[]{"[1] shopping", "[2] our stores", "[3] map", "[4] shop other floor", "[5] exit",}, 2);
            try {
                selectMovement = scan.nextInt();
            } catch (Exception e) {

            } finally {
                scan.nextLine();
            }
            switch (selectMovement) {
                case 1 -> enterStore();
                case 2 -> {
                    System.out.println(ANSI_YELLOW + "our stores: " + ANSI_RESET);
                    o.storesFirstFloor();
                    o.storesSecondFloor();
                }
                case 3 -> {
                    if (s.getFloors().indexOf(findFloor(currentLocation)) == 0) {
                        o.printFirstFloor();
                    } else {
                        o.printSecondFloor();
                    }
                }
                case 4 -> changeFloor();
                case 5 -> enterMall();
                default -> System.out.println("wrong input");
            }
        }
    }

    /**
     * When the user decides to enter a store, this method is used. The user
     * can decide whether to buy something or leave the store
     *
     */
    public void enterStore() {
        Floor currentFloor;
        char storeMovement;
        currentLocation = findHallway(currentFloor = findFloor(currentLocation));
        findStore(currentFloor);
        printCurrentPosition();
        do {
            IO.outPutListInBox(new String[]{"[b] buy item", "[l] leave store"}, 2);
            storeMovement = scan.next().charAt(0);
            switch (storeMovement) {
                case 'b' -> items();

                case 'l' -> currentLocation = findHallway(currentFloor);
                default -> System.out.println("wrong input");
            }
        } while (storeMovement != 'l');
        printCurrentPosition();
    }

    /**
     * This method is to find the hallway. Everytime the user wants to leave the store or change the
     * floor, the user must be in the hallway.
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
     * @param store
     * @return
     */
    public Floor findFloor(Store store) {
        for (Floor floor : s.getFloors()) {
            for (Store localStore : floor.getStores()) {
                if (localStore == store) {
                    return floor;
                }
            }
        }
        return null;
    }

    /**
     * This method is used to find the current store. The Store will
     * be saved in currentLocation.
     * @param floor
     */
    public void findStore(Floor floor) {
        IO o = new IO();
        if (s.getFloors().indexOf(findFloor(currentLocation)) == 0) {
            o.storesFirstFloor();
        } else {
            o.storesSecondFloor();
        }
        System.out.println("store: ");
        int chooseStore = scan.nextInt();

        currentLocation = floor.getStores().get(chooseStore - 1);

    }

    /**
     * This method is used to change floors.
     *
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
        printCurrentPosition();
    }

    /**
     * This method is used to print the current location.
     *
     */
    public void printCurrentPosition() {
        System.out.println(ANSI_BLUE + "current location: " + ANSI_RESET + (1 + s.getFloors().indexOf(findFloor(currentLocation))) + " floor" + " " + currentLocation.getName());
    }


    /**
     * This method is to choose, which item the user wants to buy.
     *
     */
    public void items() {
        currentLocation.getProducts().forEach(product -> {
            System.out.println();
            IO.outPutListInBox(new String[]{product.getName(), product.getSize(), product.getColor(), "" + product.getPrice()}, 2);
        });
        System.out.println("what do you want to buy: ");
        scan.nextLine();
        String input = scan.nextLine();
        if (budget < 0) {
            currentLocation.getProducts().forEach(product -> {
                if (input.equals(product.getName())) {
                    budget -= product.getPrice();
                }
            });
        } else {
            System.out.println(ANSI_RED + "NO MONEY" + ANSI_RESET);
        }
        budget = Math.round(budget / 0.05) * 0.05;
        System.out.printf("new budget: %.2f\n", budget);

    }

    /**
     * If the user decides to work he will land in this method. It has a randomizer with a min = 300 and max 1000. That
     * means the user can earn money between 300.- and 1000.-.
     *
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
}