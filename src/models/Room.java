package models;

public class Room implements IRoom {
    private String roomNumber;
    private Double roomPrice;
    private RoomType roomType;
    protected boolean isFree;

    public Room(String roomNumber, Double roomPrice, RoomType roomType) {
        this.roomNumber = roomNumber;
        this.roomPrice = roomPrice;
        this.roomType = roomType;
        this.isFree = false;
    }

    @Override
    public String getRoomNumber() {
        return this.roomNumber;
    }

    @Override
    public Double getRoomPrice() {
        return this.roomPrice;
    }

    @Override
    public RoomType getRoomType() {
        return this.roomType;
    }

    @Override
    public boolean isFree() {
        return this.isFree;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomNumber='" + roomNumber + '\'' +
                ", roomPrice=" + roomPrice +
                ", roomType=" + roomType +
                '}';
    }
}
