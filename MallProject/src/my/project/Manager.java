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
    private IO o = new IO();

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
                case 1:
                    budget = CLEANING_SERVICE;
                    break;
                case 2:
                    budget = GARBAGE_COLLECTOR;
                    break;
                case 3:
                    budget = OFFICE_WORKER;
                    break;
                case 4:
                    budget = INVESTOR;
                    break;
                case 5:
                    budget = CEO;
                    break;
                default:
                    System.out.println("wrong input");
            }
        }

        System.out.println(ANSI_RED + "your budget: " + budget + " CHF" + ANSI_RESET);
        enterMall();
    }

    public void enterMall() {
        char selectMovement = ' ';
        String input = " ";
        createShoppingCenter();
        currentLocation = findHallway(s.getFloors().get(0));

        while (!(input.equals("enter") || input.equals("work"))) {
            IO.outPutListInBox(new String[]{"to enter the mall spell enter / to work spell work: "}, 2);
            input = scan.nextLine();

            switch (input) {
                case "enter":
                    System.out.println(ANSI_BLUE + " _    _      _                            _          _   _            ______           _      _      ___  ___      _ _ \n" +
                            "| |  | |    | |                          | |        | | | |          |___  /          (_)    | |     |  \\/  |     | | |\n" +
                            "| |  | | ___| | ___ ___  _ __ ___   ___  | |_ ___   | |_| |__   ___     / / _   _ _ __ _  ___| |__   | .  . | __ _| | |\n" +
                            "| |/\\| |/ _ | |/ __/ _ \\| '_ ` _ \\ / _ \\ | __/ _ \\  | __| '_ \\ / _ \\   / / | | | | '__| |/ __| '_ \\  | |\\/| |/ _` | | |\n" +
                            "\\  /\\  |  __| | (_| (_) | | | | | |  __/ | || (_) | | |_| | | |  __/ ./ /__| |_| | |  | | (__| | | | | |  | | (_| | | |\n" +
                            " \\/  \\/ \\___|_|\\___\\___/|_| |_| |_|\\___|  \\__\\___/   \\__|_| |_|\\___| \\_____/\\__,_|_|  |_|\\___|_| |_| \\_|  |_/\\__,_|_|_|\n" +
                            "                                                                                                                       \n" +
                            "                                                                                                                       " + ANSI_RESET);
                    break;
                case "work":
                    goWork();
                    break;
                default:
                    System.out.println("wrong input");
            }
        }

        while (selectMovement < 1 || selectMovement > 4) {
            IO.outPutListInBox(new String[]{"[1] shopping", "[2] our stores", "[3] map", "[4] shop other floor", "[5] exit",}, 2);
            selectMovement = scan.next().charAt(0);
            switch (selectMovement) {
                case '1':
                    move();
                    break;
                case '2':
                    System.out.println(ANSI_YELLOW + "our stores: " + ANSI_RESET);
                    o.storesFirstFloor();
                    o.storesSecondFloor();
                    break;
                case '3':
                    if (s.getFloors().indexOf(findFloor(currentLocation)) == 0) {
                        o.printFirstFloor();
                    } else {
                        o.printSecondFloor();
                    }
                    break;
                case '4':
                    changeFloor();
                    move();
                    break;
                case '5':
                    System.exit(0);
                default:
                    System.out.println("wrong input");

            }
        }
    }

    public void createShoppingCenter() {
        s.getFloors().add(new Floor());
        s.getFloors().add(new Floor());
        s.getFloors().get(0).getStores().add(new Store("nike"));
        s.getFloors().get(0).getStores().add(new Store("foot locker"));
        s.getFloors().get(0).getStores().add(new Store("star bucks"));
        s.getFloors().get(0).getStores().add(new Store("mobile zone"));
        s.getFloors().get(0).getStores().add(new Store("lacoste"));
        s.getFloors().get(0).getStores().add(new Store("zara"));
        s.getFloors().get(0).getStores().add(new Store("hallway"));
        s.getFloors().get(1).getStores().add(new Store("media markt"));
        s.getFloors().get(1).getStores().add(new Store("migro"));
        s.getFloors().get(1).getStores().add(new Store("mc donalds"));
        s.getFloors().get(1).getStores().add(new Store("ice box"));
        s.getFloors().get(1).getStores().add(new Store("hallway"));
        s.getFloors().get(0).getStores().get(0).getProducts().add(new Product("swoosh t-shirt", "baby-blue", "M", 39.90));
        s.getFloors().get(0).getStores().get(0).getProducts().add(new Product("swoosh hoodie", "black", "L", 89.90));
        s.getFloors().get(0).getStores().get(0).getProducts().add(new Product("swoosh pants", "grey", "L", 59.90));
        s.getFloors().get(0).getStores().get(1).getProducts().add(new Product("jordan 4 white cement", "white", "EU 44", 249.95));
        s.getFloors().get(0).getStores().get(1).getProducts().add(new Product("jordan 11 concord", "white / black", "EU 42", 259.95));
        s.getFloors().get(0).getStores().get(1).getProducts().add(new Product("nike air max plus supreme", "white", "EU 45", 209.90));
        s.getFloors().get(0).getStores().get(1).getProducts().add(new Product("jordan 3 unc", "white / baby-blue", "EU 44", 249.95));
        s.getFloors().get(0).getStores().get(2).getProducts().add(new Product("coffee", "", "big cup", 9.00));
        s.getFloors().get(0).getStores().get(2).getProducts().add(new Product("chocolate chip cookie", "", "big", 4.90));
        s.getFloors().get(0).getStores().get(2).getProducts().add(new Product("water", "", "500ml", 3.90));
        s.getFloors().get(0).getStores().get(3).getProducts().add(new Product("iphone 12", "gold", "64GB", 799.00));
        s.getFloors().get(0).getStores().get(3).getProducts().add(new Product("samsung galaxy s20", "black", "126GB", 599.90));
        s.getFloors().get(0).getStores().get(3).getProducts().add(new Product("ipad pro (11)", "space grey", "128GB", 849.00));
        s.getFloors().get(0).getStores().get(4).getProducts().add(new Product("lacoste track suit", "green", "L", 299.00));
        s.getFloors().get(0).getStores().get(4).getProducts().add(new Product("logo-cap", "black", "one size", 39.90));
        s.getFloors().get(0).getStores().get(5).getProducts().add(new Product("jeans", "blue", "34/34", 79.90));
        s.getFloors().get(0).getStores().get(5).getProducts().add(new Product("basic t-shirt", "white", "M", 29.90));
        s.getFloors().get(1).getStores().get(0).getProducts().add(new Product("hp laptop", "grey", "1000GB", 1199.90));
        s.getFloors().get(1).getStores().get(0).getProducts().add(new Product("apple macbook pro", "space grey", "128GB", 1499.00));
        s.getFloors().get(1).getStores().get(0).getProducts().add(new Product("samsung T7", "black", "512GB", 89.90));
        s.getFloors().get(1).getStores().get(1).getProducts().add(new Product("red bull", "", "500ml", 1.60));
        s.getFloors().get(1).getStores().get(1).getProducts().add(new Product("paprika chips", "", "XXL", 5.90));
        s.getFloors().get(1).getStores().get(1).getProducts().add(new Product("ice tea peach", "", "500ml", 0.90));
        s.getFloors().get(1).getStores().get(1).getProducts().add(new Product("salad", " ", "big", 4.90));
        s.getFloors().get(1).getStores().get(2).getProducts().add(new Product("big tasty menu", "", "medium", 14.90));
        s.getFloors().get(1).getStores().get(2).getProducts().add(new Product("cheeseburger menu", "", "medium", 10.90));
        s.getFloors().get(1).getStores().get(2).getProducts().add(new Product("big mac menu", "", "medium", 14.90));
        s.getFloors().get(1).getStores().get(3).getProducts().add(new Product("iced out chain", "", "M", 12000.00));
        s.getFloors().get(1).getStores().get(3).getProducts().add(new Product("iced out grills", "", "M", 8000.00));
        s.getFloors().get(1).getStores().get(3).getProducts().add(new Product("iced out ear rings", "", "M", 5000.00));
    }

    public void move() {
        Floor currentFloor;
        char storeMovement;
        currentLocation = findHallway(currentFloor = findFloor(currentLocation));
        findStore(currentFloor);
        printCurrentPosition();
        do {
            IO.outPutListInBox(new String[]{"[b] buy item", "[l] leave store"}, 2);
            storeMovement = scan.next().charAt(0);
            switch (storeMovement) {
                case 'b': items();
                    break;
                case 'l':
                    currentLocation = findHallway(currentFloor);
                    break;
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
        Store hallway = null;
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

        for (Product product : currentLocation.getProducts()){
            System.out.println();
            IO.outPutListInBox(new String[]{ANSI_BLUE + product.getName() + ANSI_RESET}, 2);
            IO.outPutListInBox(new String[]{product.getSize()}, 2);
            IO.outPutListInBox(new String[]{product.getColor()}, 2);
            IO.outPutListInBox(new String[]{"" + product.getPrice()}, 2);
        }
        System.out.println("what do you want to buy: ");
        scan.nextLine();
        String input = scan.nextLine();

        for (Product product : currentLocation.getProducts()){
            if (input.equals(product.getName())){
                budget -= product.getPrice();
            }
        }
        budget = Math.round(budget / 0.05) * 0.05;
        System.out.printf("new budget: %.2f\n", budget);

    }

    public void goWork() {
        Random r = new Random();
        int low = 300;
        int high = 1000;
        int result = r.nextInt(high-low) + low;

       IO.outPutListInBox(new String[] {"your earnings: " + result}, 2);
       budget += result;
       IO.outPutListInBox(new String[] {"total budget: " + budget}, 2);
    }
    }

