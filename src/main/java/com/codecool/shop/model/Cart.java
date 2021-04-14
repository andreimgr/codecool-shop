package com.codecool.shop.model;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    private static Cart cartInstance = null;
    private static List<Item> items = new ArrayList<>();

    public static Cart getInstance() {
        if (cartInstance == null) { cartInstance = new Cart(); }
        return cartInstance;
    }

    private Cart() {};

    public void add(Product product) {
        boolean notInCart = true;

        for (Item item : items) {
            if (item.getProduct().equals(product)) {
                int newQuantity = item.getQuantity() + 1;
                item.setQuantity(newQuantity);
                notInCart = false;
            }
        }

        if (notInCart) {
            Item newItem = new Item(product.getId(), product, product.getDefaultPrice());
            items.add(newItem);
        }
    }

    public int numberOfItems() {
        int numberOfItems = 0;
        for (Item item: items) { numberOfItems += item.getQuantity(); }
        return numberOfItems;
    }

    public void remove(int id) {
        for (Item item: items) {
            if (item.getId() == id) {
                items.remove(item);
                break;
            }
        }
    }

    public void removeAll() { items = new ArrayList<>(); }

    public float getTotalPrice() {
        float totalPrice = 0;
        for (Item item: items) { totalPrice += item.getPrice() * item.getQuantity(); }
        return totalPrice;
    }
}
