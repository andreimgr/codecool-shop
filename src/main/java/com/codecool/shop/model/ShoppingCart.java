package com.codecool.shop.model;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart extends BaseModel {
    public List<Product> productInShoppingCart = new ArrayList<>();
    public int numOfItemsInShoppingCart = 0;

    private int productId;
    private int amount;
    private float price;

    public ShoppingCart(int productId, int amount, float price, String name) {
        this.productId = productId;
        this.amount = amount;
        this.price = price;
        this.name = name;
    }

    public int getProductId() {
        return productId;
    }

    public float getPrice() {
        return price;
    }

    public int getAmount() {
        return amount;
    }

    public List<Product> getProductInShoppingCart() {
        return productInShoppingCart;
    }

    public void setProductInShoppingCart(List<Product> productInShoppingCart) {
        this.productInShoppingCart = productInShoppingCart;
    }

    public void addToShoppingCart(Product productToAdd) {
        //productToAdd.id = numOfItemsInShoppingCart;
        numOfItemsInShoppingCart += 1;
        productInShoppingCart.add(productToAdd);
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "productId=" + productId +
                ", amount=" + amount +
                ", price=" + price +
                '}';
    }
}
