package my.project;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class is responsible for the in/output. Any method that has
 * something to do with the user is implemented here.
 * @author shawn
 */
public class IO {

    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_RESET = "\u001B[0m";
    private Scanner scan = new Scanner(System.in);
    private final int CLEANING_SERVICE = 500;
    private final int GARBAGE_COLLECTOR = 850;
    private final int OFFICE_WORKER = 1200;
    private final int INVESTOR = 2000;
    private final int CEO = 5000;
    private double budget;
    private ShoppingCenter s;
    private Manager m;
    private Store currentLocation;


    public IO(ShoppingCenter s, Manager m) {
        this.s = s;
        this.m = m;

    }

    /**
     * This method is for my border box. I use this border box for
     * my menus. The different selections are framed with this method.
     * @param strings
     * @param mode
     */
    public static void outPutListInBox(String[] strings, int mode) {
        int borderLength = 0;

        int i;
        for (i = 0; i < strings.length; ++i) {
            if (i == 0 || strings[i].length() + 3 > borderLength) {
                borderLength = strings[i].length() + 3;
            }
        }

        System.out.print("╔");
        line(borderLength + 1);
        System.out.println("╗");

        for (i = 0; i < strings.length; ++i) {
            System.out.print("║");
            switch (mode) {
                case 1:
                    System.out.print(" " + (i + 1) + ". " + strings[i]);
                    break;
                case 2:
                    System.out.print(" " + strings[i]);
                    break;
                case 3:
                    System.out.print(" •  " + strings[i]);
            }

            for (int j = 0; j < borderLength - strings[i].length(); ++j) {
                System.out.print(" ");
            }

            System.out.print("║");
            System.out.println();
        }

        System.out.print("╚");
        line(borderLength + 1);
        System.out.println("╝");
    }

    /**
     * This method is used to separate the border boxes. It
     * is implemented in the outPutListInBox method.
     * @param length
     */
    private static void line(int length) {
        for (int i = 0; i < length; ++i) {
            System.out.print("═");
        }

    }

    /**
     * With this method, the user can select an occupation. Depending
     * on the selection, the user gets another budget.
     */
    public void professionChoice() {
        int input = 0;
        IO.outPutListInBox(new String[]{"choose your profession: ", " ", "[1] cleaning service", "[2] garbage collector", "[3] office worker", "[4] investor", "[5] CEO"}, 2);
        input = readRangedInt(1, 5);
        switch (input) {
            case 1 -> budget = CLEANING_SERVICE;
            case 2 -> budget = GARBAGE_COLLECTOR;
            case 3 -> budget = OFFICE_WORKER;
            case 4 -> budget = INVESTOR;
            case 5 -> budget = CEO;
            default -> System.out.println("wrong input");
        }
        System.out.println(ANSI_RED + "your budget: " + budget + " CHF" + ANSI_RESET);
    }

    /**
     * In this method, the user can decide whether to enter the
     * mall, work or close the program.
     */
    public void enterMall() {
        String input = " ";
        currentLocation = m.findHallway(s.getFloors().get(0));

        while (!(input.equals("enter"))) {

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
                case "work" -> m.goWork();
                case "exit" -> System.exit(0);
                default -> System.out.println("wrong input");
            }

        }
        move();

    }

    /**
     * This method is where the user can move around inside the mall. The user
     * can shop, get a list of stores, view the map, change the floor or
     * exit the mall.
     */
    public void move() {
        int selectMovement = 1;
        while (true) {
            IO.outPutListInBox(new String[]{"[1] shopping", "[2] our stores", "[3] map", "[4] change floor", "[5] exit",}, 2);
            selectMovement = readRangedInt(1, 5);
            switch (selectMovement) {
                case 1 -> enterStore();
                case 2 -> {
                    System.out.println(ANSI_YELLOW + "our stores: " + ANSI_RESET);
                    storesFirstFloor();
                    storesSecondFloor();
                }
                case 3 -> {
                    if (s.getFloors().indexOf(m.findFloor(currentLocation)) == 0) {
                        printFirstFloor();
                    } else {
                        printSecondFloor();
                    }
                }
                case 4 -> m.changeFloor();
                case 5 -> enterMall();
                default -> System.out.println("wrong input");
            }
        }
    }

    /**
     * When the user decides to enter a store he will come
     * in this method. Here the user can decide to buy or leave the store.
     */
    public void enterStore() {
        Floor currentFloor;
        char storeMovement;
        currentLocation = m.findHallway(currentFloor = m.findFloor(currentLocation));
        m.findStore(currentFloor);
        printCurrentPosition();
        do {
            IO.outPutListInBox(new String[]{"[b] buy item", "[l] leave store"}, 2);
            storeMovement = scan.next().charAt(0);
            switch (storeMovement) {
                case 'b' -> items();

                case 'l' -> currentLocation = m.findHallway(currentFloor);
                default -> System.out.println("wrong input");
            }
        } while (storeMovement != 'l');

    }

    /**
     * This method is responsible for the items. Depend on the store, different
     * items will be shown on the program.
     */
    public void items() {
        currentLocation.getProducts().forEach(product -> {
            System.out.println();
            IO.outPutListInBox(new String[]{product.getName(), product.getSize(), product.getColor(), "" + product.getPrice()}, 2);
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

    /**
     * This method is to print the current location.
     */
    public void printCurrentPosition() {
        System.out.println(ANSI_BLUE + "current location: " + ANSI_RESET + (1 + s.getFloors().indexOf(m.findFloor(currentLocation))) + " floor" + " " + currentLocation.getName());
    }

    /**
     * This method is to print the map of the first floor.
     */
    public void printFirstFloor() {
        System.out.println(ANSI_YELLOW + "1st floor map" + ANSI_RESET);
        System.out.println(readFloor("firstFloor.txt"));
    }

    /**
     * This method is to print the map of the second floor.
     */
    public void printSecondFloor() {
        System.out.println(ANSI_YELLOW + "2nd floor map" + ANSI_RESET);
        System.out.println(readFloor("secondFloor.txt"));

    }

    /**
     * This method is to print the stores within the first floor.
     */
    public void storesFirstFloor() {
        outPutListInBox(new String[]{"1st floor: ", " ", "[1] nike", "[2] foot locker", "[3] star bucks", "[4] mobile zone", "[5] lacoste", "[6] zara"}, 2);
    }

    /**
     * This method is to print the stores within the second floor.
     */
    public void storesSecondFloor() {
        outPutListInBox(new String[]{"2nd floor: ", " ", "[1] media markt", "[2] migros", "[3] mc donalds", "[4] ice box"}, 2);
    }

    /**
     * This method is responsible for the validation of Integers. If the
     * user input is not an Integer it will be parsed to an int.
     * @return It will return input. Input is the parsed value.
     */
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

    /**
     * This method is responsible for the validation of the min and
     * max value of the user inputs. In this method i can define the range
     * of the user inputs.
     * @param min This parameter is responsible for the min value.
     * @param max This parameter is responsible for the max value.
     * @return This method returns the readInt method.
     */
    public static int readRangedInt(int min, int max) {
        int input = readInt();
        while (input < min || input > max) {
            System.out.println("wrong input");
            input = readInt();
        }
        return input;
    }

    /**
     * This method is to print out my maps from files.
     * @param filename for the filename which this method should read the files
     * @return empty string
     */
    public String readFloor(String filename) {
        try {
            StringBuilder fileContent = new StringBuilder();
            Scanner fileReader = new Scanner(new File("maps/" + filename));
            while (fileReader.hasNextLine()) {
                fileContent.append(fileReader.nextLine()).append("\n");
            }
            return fileContent.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }


    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public Store getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(Store currentLocation) {
        this.currentLocation = currentLocation;
    }
}
