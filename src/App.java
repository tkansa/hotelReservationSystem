import api.AdminResource;
import api.HotelResource;
import models.*;
import services.CustomerService;
import sun.java2d.loops.GraphicsPrimitiveProxy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

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

            if(mainMenuSelection == 3){
                Customer customer = Validators.createAccount();
                hotelResource.createACustomer(customer.getFirstName(), customer.getLastName(), customer.getEmail());
                System.out.println(customer);
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
                } while (adminMenuSelection != 6);
            }
        }while(mainMenuSelection != 5);
        System.out.println("Thank you for visiting the Overlook!");
    }
}
