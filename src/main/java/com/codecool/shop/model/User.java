package com.codecool.shop.model;

public class User {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private BillingAddress billingAddress;
    private ShippingAddress shippingAddress;
    private CreditCard creditCard;


    public User(int id,
                String firstName,
                String lastName,
                String email,
                BillingAddress billingAddress,
                ShippingAddress shippingAddress) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.billingAddress = billingAddress;
        this.shippingAddress = shippingAddress;
    }

    public User(String firstName,
                String lastName,
                String email,
                String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getName() {
        return firstName + " " + lastName;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public int getId() { return id; }

    public String getEmail() {
        return email;
    }

    public BillingAddress getBillingAddress() {
        return billingAddress;
    }

    public ShippingAddress getShippingAddress() {
        return shippingAddress;
    }

    public String getPassword() { return password; }

    public void setId(int userId) { this.id = userId; }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

}
