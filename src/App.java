import api.AdminResource;
import api.HotelResource;
import models.*;
import services.CustomerService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class App {
    public static void main(String[] args) {

        HotelResource hotelResource = new HotelResource();
        AdminResource adminResource = new AdminResource();
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
                        Collection<IRoom> newRooms = new ArrayList<>();
                        IRoom room = Validators.addARoom();
                        System.out.println(room);
                    }
                } while (adminMenuSelection != 6);
            }
        }while(mainMenuSelection != 5);
        System.out.println("Thank you for visiting the Overlook!");
    }
}
