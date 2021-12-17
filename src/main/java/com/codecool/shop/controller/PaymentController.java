package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.ShoppingCartDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.ShoppingCartDaoMem;
import com.codecool.shop.model.CreditCard;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.ShoppingCart;
import com.codecool.shop.model.User;
import com.codecool.shop.service.ProductService;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet(urlPatterns = {"/payment"})
public class PaymentController extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        engine.process("payment/payment.html", context, resp.getWriter());
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String nameOnCard = req.getParameter("nameOnCard");
        long cardNumber = Long.parseLong(req.getParameter("number"));
        String expiration = req.getParameter("expiration");
        int cvv = Integer.parseInt(req.getParameter("cvv"));
        CreditCard creditCard = new CreditCard(nameOnCard, cardNumber, expiration, cvv);

        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        ShoppingCartDao shoppingCartDao = ShoppingCartDaoMem.getInstance();
        ProductService productService = new ProductService(productDataStore,productCategoryDataStore, shoppingCartDao);
//        UserService userService = new UserService();

        HttpSession session = req.getSession();
        String userId = session.getId();

//        User user = userService.getUserById(Integer.parseInt(userId));
//        user.setCreditCard(creditCard);
//        CreditCard card = user.getCreditCard();

        ShoppingCart shoppingCart = productService.getShoppingCartByUserId(userId);
//        Order order = new Order(user, shoppingCart);
//        productService.addOrder(order);

        resp.sendRedirect(req.getContextPath() + "/success");

    }
}