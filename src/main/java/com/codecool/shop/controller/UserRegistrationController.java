package com.codecool.shop.controller;

import com.codecool.shop.dao.implementation.jdbc.DatabaseManager;
import com.codecool.shop.model.User;
import org.mindrot.jbcrypt.BCrypt;
import org.thymeleaf.context.WebContext;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/register"})
public class UserRegistrationController extends HttpServlet {

    private static final String USER_CREATED_MESSAGE = "User %s was created successfully!";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        WebContext context = new WebContext(req, resp, req.getServletContext());

        String newUserFirstName = req.getParameter("firstname");
        String newUSerLastName = req.getParameter("lastname");
        String newUserEmail = req.getParameter("email");
        String newUserPassword = hashPassword(req.getParameter("password"));

        User newUser = new User(
                newUserFirstName,
                newUSerLastName,
                newUserEmail,
                newUserPassword);

        DatabaseManager databaseManager = DatabaseManager.getInstance();

        databaseManager.addUser(newUser);
        context.setVariable("message", String.format(USER_CREATED_MESSAGE, newUser.getName()));
        resp.sendRedirect("/");
    }

    private String hashPassword(String plainTextPassword){
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
    }

}
