package com.codecool.shop.controller.endpoint;


import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.ShoppingCartDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.ShoppingCartDaoMem;
import com.codecool.shop.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet(urlPatterns = {"/add-to-cart"})
public class AddToCart extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        StringBuilder jsonString = new StringBuilder();
        String userID = req.getSession().getId();

        try {
            String line = "";
            BufferedReader reader = req.getReader();
            while ((line = reader.readLine()) != null)
                jsonString.append(line);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.print(jsonString);

        int productId = Integer.parseInt(String.valueOf(jsonString));
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        ShoppingCartDao shoppingCartDao = ShoppingCartDaoMem.getInstance();
        ProductService productService = new ProductService(productDataStore,productCategoryDataStore,shoppingCartDao);

        productService.addProductToCart(userID, productId);
    }

}