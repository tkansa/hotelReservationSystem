import api.AdminResource;
import api.HotelResource;
import models.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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

            switch(mainMenuSelection){

                // Find and reserve a room
                case 1:

                    Collection<IRoom> availableRooms = null;
                    String roomToReserve = "";
                    System.out.println("Do you have an account with us?");
                    String hasAccount = Validators.getYOrN();
                    if(hasAccount.equals("n")){
                        System.out.println("Please create an account before registering a room by choosing option 3 from the main menu.");
                    }
                    else{
                        Customer customer = null;
                        System.out.println("Please enter your email address: ");
                        String email = sc.nextLine();
                        customer = hotelResource.getCustomer(email);
                        if(customer == null){
                            System.out.println("It doesn't look like you have an account with us.");
                            System.out.println("Please create an account before registering a room by choosing option 3 from the main menu.");

                        }
                        else{
                            Date checkInDate = Validators.getDate("check in date");
                            Date checkOutDate = Validators.getDate("check out date");
                            if(checkInDate.after(checkOutDate)){
                                System.out.println("Invalid dates! Check out date cannot be before check in date.");
                            }
                            else{
                                availableRooms = hotelResource.findARoom(checkInDate, checkOutDate);
                                if (availableRooms == null){

                                    System.out.println("Sorry, nothing is available!");
                                    System.out.println("Would you like to search a week later?");
                                    String answer = Validators.getYOrN();
                                    if(answer.equals("y")){
                                        Calendar cal = Calendar.getInstance();
                                        cal.setTime(checkInDate);
                                        cal.add(Calendar.DAY_OF_WEEK, 7);
                                        checkInDate = cal.getTime();
                                        cal.setTime(checkOutDate);
                                        cal.add(Calendar.DAY_OF_WEEK, 7);
                                        checkOutDate = cal.getTime();

                                        availableRooms = hotelResource.findARoom(checkInDate, checkOutDate);
                                        if (availableRooms != null) {
                                            for (IRoom room : availableRooms){
                                                System.out.println(room.toString());
                                            }
                                            System.out.println("Would you like to book one of these rooms?");
                                            String bookARoom = Validators.getYOrN();
                                            if(bookARoom.equals("y")){
                                                roomToReserve = Validators.bookARoom(availableRooms);
                                                IRoom bookedRoom = hotelResource.getRoom(roomToReserve);
                                                Reservation reservation = hotelResource.bookARoom(email, bookedRoom, checkInDate, checkOutDate);
                                                System.out.println("Thank you for booking a room! Details:");
                                                System.out.println(reservation.toString());
                                            }
                                        }
                                        else {
                                            System.out.println("Sorry, we're all booked! Try again later!");

                                        }
                                    }


                                }
                                else{
                                    for (IRoom room : availableRooms){
                                        System.out.println(room.toString());
                                    }
                                    System.out.println("Would you like to book one of these rooms?");
                                    String bookARoom = Validators.getYOrN();
                                    if(bookARoom.equals("y")){
                                        roomToReserve = Validators.bookARoom(availableRooms);
                                        IRoom bookedRoom = hotelResource.getRoom(roomToReserve);
                                         Reservation reservation = hotelResource.bookARoom(email, bookedRoom, checkInDate, checkOutDate);
                                        System.out.println("Thank you for booking a room! Details:");
                                        System.out.println(reservation.toString());
                                    }
                                }
                            }
                        }
                    }

                    break;
                // See my reservations
                case 2:
                    System.out.println("Please enter your email address.");
                    String customerEmail = sc.nextLine();
                    Customer customer = adminResource.getCustomer(customerEmail);
                    Collection<Reservation> customerReservations = hotelResource.getCustomerReservations(customer);
                    if(customerReservations.isEmpty()){
                        System.out.println("Sorry, it doesn't appear you have any reservations with us.");
                    }
                    else{
                        System.out.println("Your reservation(s):");
                        for(Reservation reservation : customerReservations){
                            System.out.println(reservation.toString());
                        }
                    }
                    break;

                // Create an account
                case 3:
                    Customer cust = Validators.createAccount();
                    boolean isDupCustomer = false;
                    for(Customer c : adminResource.getAllCustomers()){
                        if (c.getEmail().equals(cust.getEmail())) {
                            isDupCustomer = true;
                            break;
                        }
                    }
                    if(isDupCustomer){
                        System.out.println("You already have an account with us.");
                    }
                    else {
                        hotelResource.createACustomer(cust.getFirstName(), cust.getLastName(), cust.getEmail());
                        System.out.println("Thank you " + cust.getFirstName() + " for registering with us!");
                    }
                    break;

                // See admin menu
                case 4:
                    int adminMenuSelection = 0;
                    do {
                        adminMenuSelection = Integer.parseInt(Validators.getAdminMenu());

                        switch(adminMenuSelection) {

                            // See all customers
                            case 1:
                                Collection<Customer> customers = adminResource.getAllCustomers();
                                for(Customer c: customers){
                                    System.out.println(c.toString());
                                }
                                break;

                            // See all rooms
                            case 2:
                                Collection<IRoom> rooms = adminResource.getAllRooms();
                                for(IRoom room: rooms){
                                    System.out.println(room.toString());
                                }
                                break;

                            // See all reservations
                            case 3:
                                adminResource.displayAllReservations();
                                break;

                            // Add a room
                            case 4:
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
                                break;

                            // Add test data
                            case 5:
                                // add some rooms to the database
                                Room room101 = new Room("101", 59.99, RoomType.DOUBLE);
                                Room room102 = new Room("102", 59.99, RoomType.DOUBLE);
                                List<IRoom> rms = new ArrayList<>();
                                /*rms.add(new Room(room101.getRoomNumber(), room101.getRoomPrice(), room101.getRoomType()));
                                rms.add(new Room(room102.getRoomNumber(), room102.getRoomPrice(), room102.getRoomType()));
                                rms.add(new Room("103", 59.99, RoomType.DOUBLE));
                                rms.add(new Room("104", 49.99, RoomType.SINGLE));
                                rms.add(new Room("105", 49.99, RoomType.SINGLE));
                                rms.add(new Room("106", 59.99, RoomType.SINGLE));
                                rms.add(new FreeRoom("107", RoomType.DOUBLE));
                                rms.add(new FreeRoom("108", RoomType.DOUBLE));
                                rms.add(new FreeRoom("109", RoomType.SINGLE));
                                adminResource.addRoom(rms);*/

                                // add some customers
                                Customer tiia = new Customer("Tiia", "Kansa", "tiia@gmail.com");
                                Customer igor = new Customer("Igor", "Catsworth", "meow@gail.com");
                                hotelResource.createACustomer(tiia.getFirstName(), tiia.getLastName(), tiia.getEmail());
                                hotelResource.createACustomer(igor.getFirstName(), igor.getLastName(), igor.getEmail());

                                // add some reservations
                                Date chin1 = new Date();
                                Date chout1 = new Date();
                                Date chin2 = new Date();
                                Date chout2 = new Date();
                                try{
                                    chin1 = new SimpleDateFormat("MM/dd/yyyy").parse("07/22/2022");
                                    chout1 = new SimpleDateFormat("MM/dd/yyyy").parse("08/04/2022");
                                    chin2 = new SimpleDateFormat("MM/dd/yyyy").parse("09/22/2022");
                                    chout2 = new SimpleDateFormat("MM/dd/yyyy").parse("10/01/2022");
                                } catch (ParseException e){
                                    System.out.println("Invalid date format");
                                }
                                hotelResource.bookARoom(tiia.getEmail(), room101, chin1, chout1);
                                hotelResource.bookARoom(igor.getEmail(), room102, chin2, chout2);
                                break;

                            default:
                                break;
                        }
                    } while (adminMenuSelection != 6);
                    break;
                default:
                    break;
            }
        }while(mainMenuSelection != 5);
        System.out.println("Thank you for visiting the Overlook!");
    }
}
