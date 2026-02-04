package model;

public class Room {
    private int id;
    private String roomNumber;
    private String type;
    private double price;
    private boolean isAvailable = true;

    public Room(int id, String roomNumber, String type, double price) {
        this.setId(id);
        this.setRoomNumber(roomNumber);
        this.setType(type);
        this.setPrice(price);
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
                "id=" + getId() +
                ", roomNumber='" + getRoomNumber() + '\'' +
                ", type='" + getType() + '\'' +
                ", price=" + getPrice() +
                ", isAvailable=" + isAvailable() +
                '}';
    }

}
