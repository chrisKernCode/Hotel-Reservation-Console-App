package api;
import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;
import service.ReservationService;

import java.util.Date;
import java.util.List;

public class HotelResource {
    private static HotelResource hotelResource = null;

    private HotelResource() {
    }

    public static HotelResource getInstance() {
        if (null == hotelResource) {
            hotelResource = new HotelResource();
        }
        return hotelResource;
    }

    CustomerService customerService = CustomerService.getInstance();
    ReservationService reservationService = ReservationService.getInstance();


    // Get Customer
    public Customer getCustomer(String email) {
        return customerService.getCustomer(email);
    }


    // Create a Customer
    public void createACustomer(String email, String firstName, String lastName) {
        customerService.addCustomer(email, firstName, lastName);
    }

    // Get Customer Reservations
    public List<Reservation> getCustomerReservations(String customerEmail) {
        return reservationService.getCustomerReservations(customerEmail);
    }


    // Get Room
    public IRoom getRoom(String roomNumber) {
        return reservationService.getARoom(roomNumber);
    }


    // Book A Room
    public Reservation bookARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        return reservationService.reserveRoom(customer, room, checkInDate, checkOutDate);
    }

    // Find a Room
    public List<IRoom> findARoom(Date checkIn, Date checkOut) {
        return reservationService.findRooms(checkIn, checkOut);
    }

}
