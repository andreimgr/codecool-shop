package com.codecool.shop.dao;

import com.codecool.shop.model.Product;

import java.util.List;
import java.util.Set;

public interface ShoppingCartDao {

    void add (Product product);
    Product find(int id);
    void remove (int id);
    Set<Product> getAll();

}
