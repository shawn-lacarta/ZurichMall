package my.project;

public class IO {

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

    public void printFirstFloor() {
        System.out.println(Manager.ANSI_YELLOW + "1st floor map" + Manager.ANSI_RESET);
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

    public void printSecondFloor() {
        System.out.println(Manager.ANSI_YELLOW + "2nd floor map" + Manager.ANSI_RESET);
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

    public void storesFirstFloor() {
        outPutListInBox(new String[]{"1st floor: ", " ", "[1] nike", "[2] foot locker", "[3] star bucks", "[4] mobile zone", "[5] lacoste", "[6] zara"}, 2);
    }

    public void storesSecondFloor() {
        outPutListInBox(new String[]{"2nd floor: ", " ", "[1] media markt", "[2] migro", "[3] mc donalds", "[4] ice box"}, 2);
    }

}
