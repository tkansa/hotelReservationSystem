package api;

import models.Customer;
import models.IRoom;
import models.Reservation;
import services.CustomerService;
import services.ReservationService;

import java.util.Collection;
import java.util.Date;

public class HotelResource {

    public static CustomerService customerService = CustomerService.getInstance();

    public static ReservationService reservationService = ReservationService.getInstance();

    public Customer getCustomer(String email){
        Customer customer = customerService.getCustomer(email);
        return customer;
    }

    public void createACustomer(String firstName, String lastName, String email){
        customerService.addCustomer(firstName, lastName, email);
    }

    public IRoom getRoom(String roomNumber){
        IRoom room = reservationService.getARoom(roomNumber);
        return room;
    }

    public Reservation bookARoom(String customerEmail, IRoom room, Date checkInDate, Date checkOutDate){
        return reservationService.reserveARoom(customerService.getCustomer(customerEmail), room, checkInDate, checkOutDate);
    }

    public Collection<Reservation> getCustomerReservations(Customer customer){
        return reservationService.getCustomerReservations(customer);
    }

    public Collection<IRoom> findARoom(Date checkInDate, Date checkOutDate){
        return reservationService.findRooms(checkInDate, checkOutDate);
    }
}
