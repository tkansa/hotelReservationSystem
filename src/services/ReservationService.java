package services;

import models.*;

import java.util.*;

public class ReservationService {

    List<Room> rooms = new ArrayList<>(Arrays.asList(
            new Room("101", 45.99, RoomType.DOUBLE),
            new Room("102", 35.99, RoomType.SINGLE),
            new Room("103", 35.99, RoomType.SINGLE),
            new FreeRoom("201", RoomType.DOUBLE),
            new FreeRoom("202", RoomType.SINGLE)));

    List<Reservation> reservations = new ArrayList<>();

    private static ReservationService reservationService = new ReservationService();

    public void addRoom(IRoom room){
        rooms.add((Room) room);
    }

    public IRoom getARoom(String roomNumber) {
        Room room = null;
        for(Room r : rooms){
            if(r.getRoomNumber() == roomNumber){
                room = r;
            }
        }
        return room;
    }

    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate){
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
