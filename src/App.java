import api.AdminResource;
import api.HotelResource;
import models.*;
import services.CustomerService;
import sun.java2d.loops.GraphicsPrimitiveProxy;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class App {
    public static void main(String[] args) {

        HotelResource hotelResource = new HotelResource();
        AdminResource adminResource = new AdminResource();
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to the Hotel Reservation Application");
        System.out.println();
        int mainMenuSelection = 0;
        do{
            mainMenuSelection = Integer.parseInt(Validators.getMainMenu());

            if(mainMenuSelection == 2){
                System.out.println("Please enter your email address.");
                String customerEmail = sc.nextLine();
                Collection<Reservation> customerReservations = hotelResource.getCusomerReservations(customerEmail);
                if(customerReservations.isEmpty()){
                    System.out.println("Sorry, it doesn't appear you have any reservations with us.");
                }
                else{
                    System.out.println("Your reservation(s):");
                    for(Reservation reservation : customerReservations){
                        System.out.println(reservation.toString());
                    }
                }

            }

            if(mainMenuSelection == 3){
                Customer customer = Validators.createAccount();
                boolean isDupCustomer = false;
                for(Customer c : adminResource.getAllCustomers()){
                    if(c.getEmail().equals(customer.getEmail())){
                        isDupCustomer = true;
                    }
                }
                if(isDupCustomer){
                    System.out.println("You already have an account with us.");
                }
                else {
                    hotelResource.createACustomer(customer.getFirstName(), customer.getLastName(), customer.getEmail());
                    System.out.println("Thank you " + customer.getFirstName() + " for registering with us!");
                }
            }

            if(mainMenuSelection == 4){
                int adminMenuSelection = 0;
                do {
                    adminMenuSelection = Integer.parseInt(Validators.getAdminMenu());

                    if(adminMenuSelection == 1){
                        Collection<Customer> customers = adminResource.getAllCustomers();
                        for(Customer customer: customers){
                            System.out.println(customer.toString());
                        }
                    }
                    if(adminMenuSelection == 2){
                        Collection<IRoom> rooms = adminResource.getAllRooms();
                        for(IRoom room: rooms){
                            System.out.println(room.toString());
                        }
                    }
                    if(adminMenuSelection == 3){
                        adminResource.displayAllReservations();
                    }
                    if(adminMenuSelection == 4){
                        Collection<IRoom> existingRooms = adminResource.getAllRooms();
                        List<IRoom> newRooms = new ArrayList<>();

                        String addAnotherRoom = "y";
                        while(addAnotherRoom.equals("y")){
                            IRoom room = Validators.addARoom();
                            boolean isDupRoomNum = false;
                            for(IRoom r : existingRooms){
                                if(r.getRoomNumber().equals(room.getRoomNumber())){
                                    isDupRoomNum = true;
                                }
                            }
                            if(isDupRoomNum){
                                System.out.println("Sorry. Room number " + room.getRoomNumber() + " is already in the system.");
                            }
                            else{
                                newRooms.add(room);
                                System.out.println("Thank you. Room " + room.getRoomNumber() + " was added.");
                            }

                            System.out.println("Would you like to add another room (y/n)?");
                            addAnotherRoom = sc.nextLine().toLowerCase();
                            while(!addAnotherRoom.equals("y") && !addAnotherRoom.equals("n")){
                                System.out.println("Please enter y or n");
                                System.out.println("Would you like to add another room (y/n)?");
                                addAnotherRoom = sc.nextLine().toLowerCase();
                            }
                        }

                        adminResource.addRoom(newRooms);
                    }
                    if(adminMenuSelection == 5){
                        // add some rooms to the database

                        Room room101 = new Room("101", 59.99, RoomType.DOUBLE);
                        Room room102 = new Room("102", 59.99, RoomType.DOUBLE);
                        List<IRoom> rooms = new ArrayList<>();
                        rooms.add(new Room(room101.getRoomNumber(), room101.getRoomPrice(), room101.getRoomType()));
                        rooms.add(new Room(room102.getRoomNumber(), room102.getRoomPrice(), room102.getRoomType()));
                        rooms.add(new Room("103", 59.99, RoomType.DOUBLE));
                        rooms.add(new Room("104", 49.99, RoomType.SINGLE));
                        rooms.add(new Room("105", 49.99, RoomType.SINGLE));
                        rooms.add(new Room("106", 59.99, RoomType.SINGLE));
                        rooms.add(new FreeRoom("107", RoomType.DOUBLE));
                        rooms.add(new FreeRoom("108", RoomType.DOUBLE));
                        rooms.add(new FreeRoom("109", RoomType.SINGLE));
                        adminResource.addRoom(rooms);

                        // add some customers
                        Customer tiia = new Customer("Tiia", "Kansa", "tiia@gmail.com");
                        Customer igor = new Customer("Igor", "Catsworth", "meow@gail.com");
                        hotelResource.createACustomer(tiia.getFirstName(), tiia.getLastName(), tiia.getEmail());
                        hotelResource.createACustomer(igor.getFirstName(), igor.getLastName(), igor.getEmail());

                        // add some reservations
                        Date chin1 = new Date();
                        Date chout1 = new Date();;
                        Date chin2 = new Date();;
                        Date chout2 = new Date();;
                        try{
                            chin1 = new SimpleDateFormat("MM/dd/yyyy").parse("07/22/2022");
                            chout1 = new SimpleDateFormat("MM/dd/yyyy").parse("08/04/2022");
                            chin2 = new SimpleDateFormat("MM/dd/yyyy").parse("09/22/2022");
                            chout2 = new SimpleDateFormat("MM/dd/yyyy").parse("10/01/2022");
                        } catch (ParseException e){
                            System.out.println("invalid date format");
                        }

                        hotelResource.bookARoom(tiia.getEmail(), room101, chin1, chout1);
                        hotelResource.bookARoom(igor.getEmail(), room102, chin2, chout2);

                    }
                } while (adminMenuSelection != 6);
            }
        }while(mainMenuSelection != 5);
        System.out.println("Thank you for visiting the Overlook!");
    }
}
