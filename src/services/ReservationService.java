package services;

import models.*;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

public class ReservationService {

    private ReservationService(){

    }
    Collection<IRoom> rooms = new ArrayList<>();

    List<Reservation> reservations = new ArrayList<>();


    private static ReservationService reservationService = null;

    public void addRoom(IRoom room){
        rooms.add((Room) room);
    }

    public Collection<IRoom> getRooms() {
        return rooms;
    }
    public IRoom getARoom(String roomNumber) {
        IRoom room = null;
        for(IRoom r : rooms){
            if(r.getRoomNumber().equals(roomNumber)){
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

        // find all the rooms that aren't booked at all
        for(IRoom room : rooms){

            if(findUnreservedRoom(room)){
                foundRooms.add(room);
            }
            /*boolean isFree = true;
            for(Reservation reservation : reservations){
                if(room.getRoomNumber().equals(reservation.getRoom().getRoomNumber())){
                    isFree = false;
                }
            }
            if(isFree){
                foundRooms.add(room);
            }*/
        }
        for(Reservation reservation : reservations){
            Date reservationCheckInDate = reservation.getCheckInDate();
            Date reservationCheckOutDate = reservation.getCheckOutDate();
            if(checkInDate.before(reservationCheckInDate) && checkOutDate.before(reservationCheckInDate) || checkInDate.after(reservationCheckOutDate) && checkOutDate.after(reservationCheckOutDate)){
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

    boolean findUnreservedRoom(IRoom room){
        boolean isUnreserved = true;
        for(Reservation r: reservations){
            if(room.getRoomNumber().equals(r.getRoom().getRoomNumber())){
                isUnreserved = false;
            }
        }
        return isUnreserved;
    }

    public static ReservationService getInstance(){
        if(reservationService == null){
            reservationService = new ReservationService();
        }
        return reservationService;
    }

}
