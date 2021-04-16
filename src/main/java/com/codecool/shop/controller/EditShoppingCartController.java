package com.codecool.shop.controller;

import com.codecool.shop.dao.implementation.ShoppingCartDaoMem;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

@WebServlet(urlPatterns = {"/edit-quantity"})
public class EditShoppingCartController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ShoppingCartDaoMem shoppingCart = ShoppingCartDaoMem.getInstance();
        Map<String, String> newData = new LinkedHashMap<>();

        newData.put("totalItemsInCart", Integer.toString(shoppingCart.getSize()));
        String json = new Gson().toJson(newData);

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(json);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ShoppingCartDaoMem shoppingCart = ShoppingCartDaoMem.getInstance();
        Map<String, String> newData = new LinkedHashMap<>();
        int productId = Integer.parseInt(req.getParameter("id"));

        if (req.getParameter("quantity").equals("decrease")) {
            shoppingCart.remove(productId);
        } else if (req.getParameter("quantity").equals("increase")) {
            shoppingCart.add(shoppingCart.find(productId));
        }

        String newQuantity = (shoppingCart.getQuantityById(productId) != null) ? Integer.toString(shoppingCart.getQuantityById(productId)) : "0";
        String newTotalItems = Integer.toString(shoppingCart.getSize());
        String newTotalPrice = Float.toString(shoppingCart.getTotalPrice());

        newData.put("productId", Integer.toString(productId));
        newData.put("newQuantity", newQuantity);
        newData.put("newTotalItems", newTotalItems);
        newData.put("newTotalPrice", newTotalPrice);
        String json = new Gson().toJson(newData);

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(json);
    }

}