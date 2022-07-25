package api;

import models.Customer;
import models.IRoom;
import services.CustomerService;
import services.ReservationService;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class AdminResource {

    public static CustomerService customerService = CustomerService.getInstance();

    public static ReservationService reservationService = ReservationService.getInstance();

    public Customer getCustomer(String email){
        Customer customer = customerService.getCustomer(email);
        return customer;
    }

    public Collection<IRoom> getAllRooms(){
        return reservationService.getRooms();
    }

    public void addRoom(List<IRoom> rooms){
        for(IRoom room : rooms){
            reservationService.addRoom(room);
        }
    }

    public Collection<Customer> getAllCustomers(){
        return customerService.getAllCustomers();
    }

    public void displayAllReservations(){
        reservationService.printAllReservations();
    }
}
