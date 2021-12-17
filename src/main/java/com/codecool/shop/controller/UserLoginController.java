package com.codecool.shop.controller;

import com.codecool.shop.dao.implementation.jdbc.DatabaseManager;
import com.codecool.shop.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = {"/login"})
public class UserLoginController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String email = req.getParameter("email");
        HttpSession session = req.getSession();

        User user = DatabaseManager.getInstance().getUserByEmail(email);
        session.setAttribute("user", user);
        session.setAttribute("id", user.getId());
        resp.sendRedirect(req.getContextPath());
    }


}
