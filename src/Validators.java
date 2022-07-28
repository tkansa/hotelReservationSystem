import models.*;

import java.util.Scanner;

public class Validators {

    public static IRoom addARoom(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a new room number:");
        String roomNumber = sc.nextLine();
        System.out.println("Enter a price (ex 125.59)");
        String priceString = sc.nextLine();
        double price = 0;
        boolean isValidPrice = false;
        while(!isValidPrice){
            try{
                price = Double.parseDouble(priceString);
                isValidPrice = true;
            } catch(NumberFormatException e){
                System.out.println("Please enter a valid price:");
                priceString = sc.nextLine();
            }
        }

        System.out.println("Room type: Enter 1 for a single, 2 for a double:");
        String roomTypeString = sc.nextLine();
        while(!roomTypeString.equals("1") && !roomTypeString.equals("2")){
            System.out.println("Please enter a valid room type");
            System.out.println("Room type: Enter 1 for a single, 2 for a double:");
            roomTypeString = sc.nextLine();
        }
        RoomType roomType;
        if(roomTypeString.equals("1")){
            roomType = RoomType.SINGLE;
        }
        else {
            roomType = RoomType.DOUBLE;
        }
        IRoom room;
        if(price == 0){
            room = new FreeRoom(roomNumber, roomType);
        }
        else {
            room = new Room(roomNumber, price, roomType);
        }
        return room;
    }
    public static Customer createAccount(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter your first name:");
        String firstName = sc.nextLine();
        System.out.println("Please enter your last name:");
        String lastName = sc.nextLine();
        System.out.println("Please enter a valid email address:");
        String email = sc.nextLine();
        Customer customer = null;
        boolean isValidEmail = false;
        while(!isValidEmail){
            try{
                customer = new Customer(firstName, lastName, email);
                isValidEmail = true;
            } catch(IllegalArgumentException e){
                System.out.println(e.getLocalizedMessage());
                email = sc.nextLine();
            }
        }
        return customer;

    }

    public static String getAdminMenu(){
        Scanner sc = new Scanner(System.in);
        String selection = "";
        do{
        System.out.println("Admin menu");
        System.out.println("--------------------------------");
        System.out.println("1. See all customers");
        System.out.println("2. See all rooms");
        System.out.println("3. See all reservations");
        System.out.println("4. Add a room");
        System.out.println("5. Add test data");
        System.out.println("6. Back to main menu");
        System.out.println("Please select a number for the menu option");
        selection = sc.nextLine();
        System.out.println("--------------------------------");
        } while(!selection.equals("1") && !selection.equals("2") && !selection.equals("3") && !selection.equals("4") && !selection.equals("5") && !selection.equals("6"));
        return selection;
    }
    public static String getMainMenu(){

        Scanner sc = new Scanner(System.in);
        String selection = "";
        do{
            System.out.println("--------------------------------");
            System.out.println("1. Find and reserve a room");
            System.out.println("2. See my reservations");
            System.out.println("3. Create an account");
            System.out.println("4. Admin");
            System.out.println("5. Exit");
            System.out.println("--------------------------------");
            System.out.println("Please select a number for the menu option");
            selection = sc.nextLine();
        }while(!selection.equals("1") && !selection.equals("2") && !selection.equals("3") && !selection.equals("4") && !selection.equals("5"));

        return selection;
    }
}
