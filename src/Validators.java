import models.Customer;

import java.util.Scanner;

public class Validators {

    public static Customer createAccount(){
        Scanner sc = new Scanner(System.in);
        /*try{
            System.out.println("Please enter your first name:");
            String firstName = sc.nextLine();
            System.out.println(firstName);
        } catch(Exception e){
            e.getLocalizedMessage();
        }
        finally {
            sc.close();
        }*/
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
        System.out.println("Thank you " + firstName + ". Your new account has been created!");

        return customer;

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
        //sc.close();
        return selection;
    }
}
