package model;

public class Booking {
    private int id;
    private Customer customer;
    private Room room;
    private String checkInDate;
    private String checkOutDate;
    private String status;
    private double totalPrice;

    public  Booking(int id, Customer customer, Room room,  String checkInDate, String checkOutDate) {
        this.setId(id);
        this.setCustomer(customer);
        this.setRoom(room);
        this.setCheckInDate(checkInDate);
        this.setCheckOutDate(checkOutDate);
    }

    public Booking(){}

    public String getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(String checkInDate) {
        this.checkInDate = checkInDate;
    }

    public String getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(String checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getId(int id){
        return id;
    }

    public void setId(int id) {
        if(id < 0){
            throw new IllegalArgumentException("id không hợp lệ");
        }
        this.id = id;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "checkInDate='" + getCheckInDate() + '\'' +
                ", id=" + getId() +
                ", customer=" + getCustomer() +
                ", room=" + getRoom() +
                ", checkOutDate='" + getCheckOutDate() + '\'' +
                ", status='" + getStatus() + '\'' +
                ", totalPrice=" + getTotalPrice() +
                '}';
    }

    public int getId() {
        return id;
    }
}
