package my.project;

import java.util.Scanner;

/**
 * This class has all the fix output of the program.
 * @author shawn
 */
public class IO {

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
    private ShoppingCenter s;
    private Manager m;
    private Store currentLocation;

    public IO(ShoppingCenter s, Manager m) {
        this.s = s;
        this.m = m;
    }

    /**
     * This method is for my border box. I implemented this method so i can give my program a
     * nicer look. I use my border box for menus for the user.
     * @param strings
     * @param mode
     */
    public static void outPutListInBox(String[] strings, int mode) {
        int borderLength = 0;

        int i;
        for(i = 0; i < strings.length; ++i) {
            if (i == 0 || strings[i].length() + 3 > borderLength) {
                borderLength = strings[i].length() + 3;
            }
        }

        System.out.print("╔");
        line(borderLength + 1);
        System.out.println("╗");

        for(i = 0; i < strings.length; ++i) {
            System.out.print("║");
            switch(mode) {
                case 1:
                    System.out.print(" " + (i + 1) + ". " + strings[i]);
                    break;
                case 2:
                    System.out.print(" " + strings[i]);
                    break;
                case 3:
                    System.out.print(" •  " + strings[i]);
            }

            for(int j = 0; j < borderLength - strings[i].length(); ++j) {
                System.out.print(" ");
            }

            System.out.print("║");
            System.out.println();
        }

        System.out.print("╚");
        line(borderLength + 1);
        System.out.println("╝");
    }

    private static void line(int length) {
        for(int i = 0; i < length; ++i) {
            System.out.print("═");
        }

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
            input = m.readInt();
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
     * This method is to move around the mall. Depending on the user input, different methods are used.
     *
     */
    public void move() {

        int selectMovement = 1;
        while (selectMovement >= 1 && selectMovement <= 5) {
            IO.outPutListInBox(new String[]{"[1] shopping", "[2] our stores", "[3] map", "[4] shop other floor", "[5] exit",}, 2);
            selectMovement = m.readInt();
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
     * When the user decides to enter a store, this method is used. The user
     * can decide whether to buy something or leave the store
     *
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

        currentLocation.getProducts().forEach(product -> {
            if (input.equals(product.getName())) {
                budget -= product.getPrice();
            }
        });

        budget = Math.round(budget / 0.05) * 0.05;
        System.out.printf("new budget: %.2f\n", budget);

    }

    /**
     * This method is used to print the current location.
     *
     */
    public void printCurrentPosition() {
        System.out.println(ANSI_BLUE + "current location: " + ANSI_RESET + (1 + s.getFloors().indexOf(m.findFloor(currentLocation))) + " floor" + " " + currentLocation.getName());
    }


    /**
     * This method is to print the map for the first floor.
     *
     */
    public void printFirstFloor() {
        System.out.println(ANSI_YELLOW + "1st floor map" + ANSI_RESET);
        System.out.println("╔═════════════╦══════════╦════════════════════╗\n" +
                "║             ║          ║                    ║\n" +
                "║             ║          ║                    ║\n" +
                "║             ║                               ║\n" +
                "║   star                 ║        zara        ║\n" +
                "║   bucks     ║          ║                    ║\n" +
                "║             ║          ║                    ║ \n" +
                "║             ║          ║                    ║\n" +
                "╠═════════════╝          ╚═══════════ ════════╣\n" +
                "║                                             ║\n" +
                "║                                             ║\n" +
                "║                                             ║ \n" +
                "║                                             ║    \n" +
                "╠═════ ═══════╗          ╔════════════════════╣\n" +
                "║             ║          ║                    ║\n" +
                "║             ║          ║                    ║\n" +
                "║   mobile               ║       foot         ║ \n" +
                "║   zone      ║          ║       locker       ║\n" +
                "║             ║                               ║\n" +
                "║             ║          ║                    ║\n" +
                "╠═════════════╣          ╠════════════════════╣ \n" +
                "║             ║          ║                    ║\n" +
                "║                        ║                    ║\n" +
                "║             ║          ║                    ║\n" +
                "║   lacoste   ║                   nike        ║\n" +
                "║             ║          ║                    ║\n" +
                "║             ║          ║                    ║   \n" +
                "╚═════════════╝          ╚════════════════════╝\n"
               );
    }

    /**
     * This method is to print the map for the second floor.
     *
     */
    public void printSecondFloor() {
        System.out.println(ANSI_YELLOW + "2nd floor map" + ANSI_RESET);
        System.out.println("╔═════════════╦══════════╦════════════════════╗\n" +
                "║             ║          ║                    ║\n" +
                "║             ║          ║                    ║\n" +
                "║             ║                               ║\n" +
                "║    zara                ║        migros      ║\n" +
                "║             ║          ║                    ║\n" +
                "║             ║          ║                    ║ \n" +
                "║             ║          ║                    ║\n" +
                "╠═════════════╝          ╚═══════════ ════════╣\n" +
                "║                                             ║\n" +
                "║                                             ║\n" +
                "║                                             ║ \n" +
                "║                                             ║    \n" +
                "╠═════ ═══════╗          ╔════════════════════╣\n" +
                "║             ║          ║                    ║\n" +
                "║             ║          ║                    ║\n" +
                "║             ║          ║                    ║ \n" +
                "║             ║          ║                    ║\n" +
                "║             ║                  media        ║\n" +
                "║   mc        ║          ║       markt        ║\n" +
                "║   donalds   ║          ║                    ║\n" +
                "║             ║          ║                    ║\n" +
                "║                        ║                    ║\n" +
                "║             ║          ║                    ║\n" +
                "║             ║          ║                    ║\n" +
                "║             ║          ║                    ║\n" +
                "║             ║          ║                    ║   \n" +
                "╚═════════════╩══════════╩════════════════════╝");

    }

    /**
     * This method is to print the stores in the first floor.
     *
     */
    public void storesFirstFloor() {
        outPutListInBox(new String[]{"1st floor: ", " ", "[1] nike", "[2] foot locker", "[3] star bucks", "[4] mobile zone", "[5] lacoste", "[6] zara"}, 2);
    }

    /**
     * This method is to print the stores in the second floor.
     */
    public void storesSecondFloor() {
        outPutListInBox(new String[]{"2nd floor: ", " ", "[1] media markt", "[2] migros", "[3] mc donalds", "[4] ice box"}, 2);
    }

}
