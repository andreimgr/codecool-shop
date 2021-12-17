package com.codecool.shop.model;

public class CreditCard {

    private String name;
    private long cardNumber;
    private String expiration;
    private int cvv;

    public CreditCard(String name, long cardNumber, String expiration, int cvv){
        this.name = name;
        this.cardNumber = cardNumber;
        this.expiration = expiration;
        this.cvv = cvv;
    }

    public String getName() {
        return name;
    }

    public long getCardNumber() {
        return cardNumber;
    }

    public String getExpiration() {
        return expiration;
    }

    public int getCvv() {
        return cvv;
    }

}
