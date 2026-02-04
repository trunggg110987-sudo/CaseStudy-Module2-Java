package model;

public class Customer {
    private int id;
    private String name;
    private String phone;
    private String email;
    private String idCard;

    public Customer(int id, String name, String phone, String email, String idCard) {
        this.setId(id);
        this.setName(name);
        this.setPhone(phone);
        this.setEmail(email);
        this.setIdCard(idCard);
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
                "email='" + getEmail() + '\'' +
                ", id=" + getId() +
                ", name='" + getName() + '\'' +
                ", phone='" + getPhone() + '\'' +
                ", idCard='" + getIdCard() + '\'' +
                '}';
    }
}
