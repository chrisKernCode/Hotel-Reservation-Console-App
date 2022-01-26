import api.AdminResource;
import model.Room;
import model.RoomType;
import java.util.Scanner;

public class AdminMenu {
    public static AdminResource adminResource = AdminResource.getInstance();
    public static Scanner scanner;

public static void setAdminResource(AdminResource adminResource) {
        AdminMenu.adminResource = adminResource;
    }



    // Select from Admin Menu
    public static void selectAdminMenu() {
        scanner = new Scanner(System.in);
        try {
            boolean closeAdminMenu = false;
            while (!closeAdminMenu) {
                String adminMenuSelection = showAdminMenu();
                switch (adminMenuSelection) {
                    case "1": {
                        System.out.println(adminResource.getAllCustomers());
                        break;
                    }
                    case "2": {
                        System.out.println(adminResource.getAllRooms());
                        break;
                    }
                    case "3": {
                        adminResource.displayAllReservations();
                        break;
                    }
                    case "4": {
                        addARoom();
                        break;
                    }
                    case "5":
                        closeAdminMenu = true;
                        break;
                    default:
                        showAdminMenu();
                }
            }
            String[] args = new String[] {""};
            MainMenu.main(args);
        } catch (Exception ex) {
            ex.getLocalizedMessage();
        } finally{
            scanner.close();
        }
    }

    //4. Add a Room
    private static void addARoom() {
        Room room = new Room();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the room number");
        room.roomNumber = scanner.nextLine().trim();

        System.out.println("Enter the room price (one day)");
        room.price = scanner.nextDouble();

        System.out.println("Enter the room-type: 1 = SINGLE BED,  2 = DOUBLE BED");
        int roomTypes = scanner.nextInt();

        if (roomTypes == 1) {
            room.roomType = RoomType.SINGLE;
        } else {
            room.roomType = RoomType.DOUBLE;
        }
        adminResource.addRoom(room);

        System.out.println("Room created: " + "Room-Nr.: " + room.roomNumber + " |" + " Price: " + room.price + " |"
                + " Euro " + " |" + "Room-Type: " + room.roomType);
    }


       // Show Admin Menu
       private static String showAdminMenu() {
        System.out.println("******************************");
        System.out.println("         ADMIN MENU           ");
        System.out.println("1. See all Customers");
        System.out.println("2. See all Rooms");
        System.out.println("3. See all Reservations");
        System.out.println("4. Add a Room");
        System.out.println("5. Back to Main Menu");
        System.out.println("******************************");
        System.out.println("        Enter 1-5            ");
        System.out.println("------------------------------");
        return scanner.nextLine();
    }
}
