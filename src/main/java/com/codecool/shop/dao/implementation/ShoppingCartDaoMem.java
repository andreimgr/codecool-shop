package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ShoppingCartDao;
import com.codecool.shop.model.Product;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

public class ShoppingCartDaoMem implements ShoppingCartDao {

    private List<Product> productsInCart = new ArrayList<>();
    private float totalPrice = 0;
    private static ShoppingCartDaoMem instance = null;

    private ShoppingCartDaoMem() {}

    public static ShoppingCartDaoMem getInstance() {
        if (instance == null) { instance = new ShoppingCartDaoMem(); }
        return instance;
    }

    @Override
    public void add(Product product) {
        totalPrice += product.getDefaultPrice();
        productsInCart.add(product);
    }

    @Override
    public Product find(int id) {
        return productsInCart.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }

    @Override
    public void remove (int id) {
        totalPrice -= find(id).getDefaultPrice();
        productsInCart.remove(find(id));
    }

    @Override
    public LinkedHashSet<Product> getAll() {
        return new LinkedHashSet<>(productsInCart);
    }

    public float getTotalPrice() { return totalPrice; }
    public Integer getQuantityById (int id) {
        return (int) productsInCart.stream().filter(
                t -> t.getId() == id).count();
    }
}
