package my.project;

import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Manager {

    public static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_RESET = "\u001B[0m";
    private Scanner scan = new Scanner(System.in);
    private final int CLEANING_SERVICE = 200;
    private final int GARBAGE_COLLECTOR = 650;
    private final int OFFICE_WORKER = 1200;
    private final int INVESTOR = 2000;
    private final int CEO = 5000;
    private double budget;
    private ShoppingCenter s = new ShoppingCenter();
    private Store currentLocation;

    public void startProgram() {
        professionChoice();
        enterMall();
    }

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

    public void enterMall() {

        String input = " ";
        s.createShoppingCenter();
        currentLocation = findHallway(s.getFloors().get(0));

        while (!(input.equals("enter") || input.equals("work"))) {
            IO.outPutListInBox(new String[]{"to enter the mall spell enter / to work spell work: "}, 2);
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
                default -> System.out.println("wrong input");
            }
        }
        move();

    }

    public void move() {
        IO o = new IO();
        char selectMovement = ' ';
        while (selectMovement < 1 || selectMovement > 4) {
            IO.outPutListInBox(new String[]{"[1] shopping", "[2] our stores", "[3] map", "[4] shop other floor", "[5] exit",}, 2);
            selectMovement = scan.next().charAt(0);
            switch (selectMovement) {
                case '1' -> enterStore();
                case '2' -> {
                    System.out.println(ANSI_YELLOW + "our stores: " + ANSI_RESET);
                    o.storesFirstFloor();
                    o.storesSecondFloor();
                }
                case '3' -> {
                    if (s.getFloors().indexOf(findFloor(currentLocation)) == 0) {
                        o.printFirstFloor();
                    } else {
                        o.printSecondFloor();
                    }
                }
                case '4' -> changeFloor();
                case '5' -> System.exit(0);
                default -> System.out.println("wrong input");
            }
        }
    }

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

    public Store findHallway(Floor floor) {
        for (Store store : floor.getStores()) {
            if (store.getName().equals("hallway")) {
                return store;
            }
        }
        return null;
    }

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

    public void printCurrentPosition() {
        System.out.println(ANSI_BLUE + "current location: " + ANSI_RESET + (1 + s.getFloors().indexOf(findFloor(currentLocation))) + " floor" + " " + currentLocation.getName());
    }

    public void items() {
        currentLocation.getProducts().forEach(product -> {
            System.out.println();
            IO.outPutListInBox(new String[]{ANSI_BLUE + product.getName() + ANSI_RESET}, 2);
            IO.outPutListInBox(new String[]{product.getSize()}, 2);
            IO.outPutListInBox(new String[]{product.getColor()}, 2);
            IO.outPutListInBox(new String[]{"" + product.getPrice()}, 2);
        });
        System.out.println("what do you want to buy: ");
        scan.nextLine();
        String input = scan.nextLine();

        currentLocation.getProducts().forEach(product -> {
            if (input.equals(product.getName())) {
                budget -= product.getPrice();
            }
        });

        budget = Math.round(budget / 0.05) * 0.05;
        System.out.printf("new budget: %.2f\n", budget);

    }

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

