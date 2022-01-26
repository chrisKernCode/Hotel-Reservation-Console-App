package api;
import model.Customer;
import model.IRoom;
import model.Room;
import service.CustomerService;
import service.ReservationService;
import java.util.List;

public class AdminResource {
    private static AdminResource adminResource = null;

    private AdminResource() {
    }

    public static AdminResource getInstance() {
        if (null == adminResource) {
            adminResource = new AdminResource();
        }
        return adminResource;
    }

    CustomerService customerService = CustomerService.getInstance();;
    ReservationService reservationService = ReservationService.getInstance();


    // Get Customer (Email)
    public Customer getCustomer(String email) {
        return customerService.getCustomer(email);
    }


    // Get All Customers
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    // Display All Reservations
    public void displayAllReservations() {
        reservationService.printAllReservations();
    }


    // Add a Room
    public void addRoom(Room rooms) {
        reservationService.addRoom(rooms);
    }


    // Get All Rooms
    public List<IRoom> getAllRooms() {
        return reservationService.getAllRooms();
    }

}
