package model;

public class Customer {
    int id;
    String name;
    String phone;
    String email;
    String idCard;

    public Customer(int id, String name, String phone, String email, String idCard) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.idCard = idCard;
    }

    public Customer() {}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "email='" + email + '\'' +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", idCard='" + idCard + '\'' +
                '}';
    }
}
