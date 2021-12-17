package com.codecool.shop.dao;

import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;

import java.util.List;

public interface ProductDao {

    void add(Product product);
    Product find(int id);


    void remove(int id);

    List<Product> getAll();

    List<Product> getBySupplier(String supplier);

    List<Product> getByProductCategory(ProductCategory productCategory);

    List<Product> getBy(String supplier);
}
