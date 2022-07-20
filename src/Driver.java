import models.Customer;
import models.FreeRoom;
import models.Room;
import models.RoomType;

public class Driver {
    public static void main(String[] args) {
        Customer customer = new Customer("Tiia", "Kansa", "t@mail.com");
        System.out.println(customer);
        Room freeRoom = new FreeRoom("101", RoomType.DOUBLE);
        System.out.println(freeRoom);
    }
}
