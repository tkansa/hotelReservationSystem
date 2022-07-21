import api.HotelResource;
import models.Customer;
import models.FreeRoom;
import models.Room;
import models.RoomType;

public class App {
    public static void main(String[] args) {

        HotelResource hotelResource = new HotelResource();
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
        }while(mainMenuSelection != 5);
        System.out.println("Thank you for visiting the Overlook!");
    }
}
