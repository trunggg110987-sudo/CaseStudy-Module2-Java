package model;

public class Room {
    int id;
    String roomNumber;
    String type;
    double price;
    boolean isAvailable = true;

    public Room(int id, String roomNumber, String type, double price) {
        this.id = id;
        this.roomNumber = roomNumber;
        this.type = type;
        this.price = price;
    }

    public Room(){}


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", roomNumber='" + roomNumber + '\'' +
                ", type='" + type + '\'' +
                ", price=" + price +
                ", isAvailable=" + isAvailable +
                '}';
    }

}
