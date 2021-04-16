package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.ShoppingCartDaoMem;
import com.codecool.shop.model.Product;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;




@WebServlet(urlPatterns = {"/add-product-to-cart"})
public class AddProductInCart  extends HttpServlet{
    ProductDao productDataStore = ProductDaoMem.getInstance();
    private ShoppingCartDaoMem shoppingCart = ShoppingCartDaoMem.getInstance();


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        for (Product item : productDataStore.getAll()) {
            String id = String.valueOf(item.getId());
            if (id.equals(request.getParameter("product"))) {
                shoppingCart.add(item);
            }
        }
        response.sendRedirect(request.getContextPath() + "/");

    }
}
