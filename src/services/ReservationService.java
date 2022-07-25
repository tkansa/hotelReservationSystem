package services;

import models.*;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

public class ReservationService {

    private ReservationService(){

    }
    private Collection<IRoom> rooms = new ArrayList<>();


    // use SimpleDateFormat
    List<Reservation> reservations = new ArrayList<>();


    private static ReservationService reservationService = new ReservationService();

    public void addRoom(IRoom room){
        rooms.add((Room) room);
    }

    public Collection<IRoom> getRooms() {
        return rooms;
    }
    public IRoom getARoom(String roomNumber) {
        IRoom room = null;
        for(IRoom r : rooms){
            if(r.getRoomNumber() == roomNumber){
                room = r;
            }
        }
        return room;
    }

    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate){
        reservations.add(new Reservation(customer, room, checkInDate, checkOutDate));
        return new Reservation(customer, room, checkInDate, checkOutDate);
    }

    public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate){
        Collection<IRoom> foundRooms = new ArrayList<>();
        for(Reservation reservation : reservations){
            if(reservation.getCheckInDate().equals(checkInDate) && reservation.getCheckOutDate().equals(checkOutDate)){
                foundRooms.add(reservation.getRoom());
            }
        }

        return foundRooms;
    }

    public Collection<Reservation> getCustomerReservations(Customer customer){
        Collection<Reservation> foundReservations = new ArrayList<>();
        for(Reservation reservation : reservations){
            if(reservation.getCustomer().equals(customer)){
                foundReservations.add(reservation);
            }
        }

        return foundReservations;
    }

    public void printAllReservations(){
        for(Reservation reservation : reservations){
            System.out.println(reservation);
        }
    }

    public static ReservationService getInstance(){
        return reservationService;
    }


}
