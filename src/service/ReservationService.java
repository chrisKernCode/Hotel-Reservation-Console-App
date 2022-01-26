package service;
import model.Customer;
import model.IRoom;
import model.Reservation;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReservationService {
    private static ReservationService reservationService = null;

    private ReservationService() {
    }

    public static ReservationService getInstance() {
        if (null == reservationService) {
            reservationService = new ReservationService();
        }
        return reservationService;
    }

    ArrayList<IRoom> rooms = new ArrayList<>();
    ArrayList<Reservation> reservations = new ArrayList<>();


    // Get Room
    public IRoom getARoom(String roomId) {
        for (IRoom room : rooms) {
            if (room.getRoomNumber().equals(roomId)) {
                return room;
            }
        }
        return null;
    }

    // Add Room
    public void addRoom(IRoom room) {
        rooms.add(room);
    }


    // Find Rooms
    public List<IRoom> findRooms(Date checkInDate, Date checkOutDate) {
        List<IRoom> emptyRooms = new ArrayList<>();
        try {
            List<IRoom> reservedRooms = getReservedRooms(checkInDate, checkOutDate);
            for (IRoom room : rooms) {
                if(!reservedRooms.contains(room)){
                    emptyRooms.add(room);
                }
            }
        } catch (Exception e){
            if (emptyRooms.isEmpty()) return null;
        }
        return emptyRooms;
    }


    // Reserve Room
    public Reservation reserveRoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        Reservation reservation = new Reservation(customer, room, checkInDate, checkOutDate);
        reservations.add(reservation);
        return reservation;
    }


    // Get Reserved Rooms
    public List<IRoom> getReservedRooms(Date checkInDate, Date checkOutDate) {
        List<IRoom> roomsWithReservation = new ArrayList<>();
        try {
            for (Reservation reservation : reservations) {
                if (reservation.getCheckInDate().getTime() <= checkInDate.getTime() &&
                        reservation.getCheckOutDate().getTime() >= checkOutDate.getTime()) {
                        roomsWithReservation.add(reservation.getRoom());
                }
            }
        } catch (Exception e){
            if (roomsWithReservation.isEmpty()) return null;
        }
        return roomsWithReservation;
    }
    

        // Room Availability
        public boolean isRoomAvailable(IRoom room, List<IRoom> emptyRooms) {
            for (IRoom emptyRoom : emptyRooms) {
                if (emptyRoom.equals(room)) {
                    return true;
                }
            }
            return false;
        }


    // Get Reservations By Customer Email
    public List<Reservation> getCustomerReservations(String customerEmail) {
        List<Reservation> reservationsByCustomerEmail = new ArrayList<>();
        for (Reservation reservation : reservations) {
            if (reservation.getCustomer().getEmail().equals(customerEmail)) {
                reservationsByCustomerEmail.add(reservation);
            }
        }
        return reservationsByCustomerEmail;
    }


    // Get Customer Reservation
    public List<Reservation> getCustomerReservation(Customer customer) {
        List<Reservation> reservationsOfCustomer = new ArrayList<>();
        for (Reservation reservation : reservations) {
            if (reservation.getCustomer().equals(customer)) {
                reservationsOfCustomer.add(reservation);
            }
        }
        return reservationsOfCustomer;
    }



    // Print All Reservations
    public void printAllReservations() {
        for (Reservation reservation : reservations) {
            System.out.println("Reservations: " + reservation);
        }
    }

    // Get All Rooms
    public List<IRoom> getAllRooms() {
        return rooms;
    }
}
