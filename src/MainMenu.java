import api.AdminResource;
import api.HotelResource;
import model.Customer;
import model.IRoom;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class MainMenu {
    public static AdminResource adminResource = AdminResource.getInstance();
    public static HotelResource hotelResource = HotelResource.getInstance();
    public static Scanner scanner;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);

        try {
            boolean closeMainMenu = false;
            while (!closeMainMenu) {
                String mainMenuSelection = showMainMenu();
                switch (mainMenuSelection) {
                    case "1": {
                        findAndReserveARoom();
                        break;
                    }
                    case "2": {
                        seeMyReservations();
                        break;
                    }
                    case "3": {
                        createAnAccount();
                        break;
                    }
                    case "4": {
                        // 4. Admin
                        AdminMenu.setAdminResource(adminResource);
                        AdminMenu.selectAdminMenu();
                        break;
                    }
                    case "5":
                        // 5. Exit
                        closeMainMenu = true;
                        break;
                    default:
                        showMainMenu();
                }
            }
            System.exit(0);
        } catch (Exception ex) {
            ex.getLocalizedMessage();
        } finally{
            scanner.close();
        }
    }

    // 2. See my Reservations
    private static void seeMyReservations() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your Email:");

        String email = scanner.next();
        System.out.println(hotelResource.getCustomerReservations(email));
    }

    // 3. Create an Account
    private static Customer createAnAccount() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your First Name:");
        String firstName = scanner.next();

        System.out.println("Enter your Last Name:");
        String lastName = scanner.next();

        System.out.println("Enter your Email:");
        String email = scanner.next();

        try {
            hotelResource.createACustomer(email, firstName, lastName);
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getLocalizedMessage());
        }
        System.out.println("Name: " + firstName + " " + lastName + ", Email: " + email);
        return new Customer(firstName, lastName, email);
    }

     // 1. Find and Reserve a Room
     private static void findAndReserveARoom() throws ParseException {
        SimpleDateFormat bookingDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("Enter your Check-In Date in the Format dd/mm/yyyy"  + " E.g.: 10/12/2022");
        Date checkInDate = bookingDateFormat.parse(scanner.nextLine());

        System.out.println("Enter your Check-Out Date in the Format dd/mm/yyyy" + " E.g.: 31/12/2022");
        Date checkOutDate = bookingDateFormat.parse(scanner.nextLine());

        List<IRoom> emptyRooms = hotelResource.findARoom(checkInDate, checkOutDate);
        System.out.println("Check-In Date: " + checkInDate + ", " + "Check-Out Date: " + checkOutDate);

        if (!emptyRooms.isEmpty()) {
            Customer customer;

            System.out.println("Do you want to book a room? Select (y/n)");
            char wantToBookARoom = scanner.next().trim().charAt(0);

            if (wantToBookARoom == 'y') {
                System.out.println("Do you have an account? Select (y/n)");
                char customerWithAccount = scanner.next().trim().charAt(0);

                if (customerWithAccount == 'y') {
                    customer = getCustomerAccount();

                    if (customer == null) {
                        System.out.println("No customer found.");
                        return;
                    }

                } else {
                    // Create new Customer Account
                    customer = createAnAccount();
                }

                boolean roomOccupation = false;
                while (!roomOccupation) {
                    System.out.println("The following rooms are available:");
                    System.out.println(emptyRooms);

                    System.out.println("Enter room number:");
                    String roomNumber = scanner.next();

                    IRoom roomChoice = hotelResource.getRoom(roomNumber);

                    if (!emptyRooms.contains(roomChoice)) {
                        System.out.println("Room-Nr. " + roomNumber + " not available.");
                    } else {
                        hotelResource.bookARoom(customer, roomChoice, checkInDate, checkOutDate);
                        System.out.println("Booking of Room-Nr: " + roomChoice + " from " + checkInDate + " till " + checkOutDate + " was successfull!");
                        System.out.println("Thank you for your booking!");

                        roomOccupation = true;
                    }
                }
            }
        } else {
            System.out.println("No rooms available.");
        }
    }

    // Helper Method (1. Find and Reserve a Room): Get Customer Account
    private static Customer getCustomerAccount() {
        System.out.println("Enter your Email");
        String email = scanner.next();

        return hotelResource.getCustomer(email);
    }

        // Show Main Menu
        private static String showMainMenu() {
            System.out.println("******************************");
            System.out.println("        MAIN MENU             ");
            System.out.println("1. Find and Reserve a Room");
            System.out.println("2. See my Reservations");
            System.out.println("3. Create an Account");
            System.out.println("4. Admin");
            System.out.println("5. Exit");
            System.out.println("******************************");
            System.out.println("       Enter 1-5              ");
            System.out.println("------------------------------");
            return scanner.nextLine();
        }
}
