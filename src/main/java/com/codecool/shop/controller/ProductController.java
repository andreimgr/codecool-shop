package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.ShoppingCartDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.ShoppingCartDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.service.ProductService;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/"})
public class ProductController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String page = req.getParameter("category");
        String supplier = req.getParameter("supplier");
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDao = SupplierDaoMem.getInstance();
        ShoppingCartDao shoppingCartDao = ShoppingCartDaoMem.getInstance();
        ProductService productService = new ProductService(productDataStore,productCategoryDataStore, shoppingCartDao);

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        if (page != null) {
            for (ProductCategory pd : productCategoryDataStore.getAll()) {
                if (page.equals(pd.getName().toLowerCase())) {
                    System.out.println(pd.toString());
                    context.setVariable("category", productService.getProductCategory(pd.getId()));
                    context.setVariable("products", productService.getProductsForCategory(pd.getId()));
                } else {
                    context.setVariable("category", productService.getProductCategory(1));
                    context.setVariable("products", productService.getProductsForCategory(1));
                }
            }
        } else if (supplier != null){
            context.setVariable("products", productService.getSupplierDao(supplier));
            context.setVariable("currentSupplier", supplier);
        } else {
            context.setVariable("category", productService.getProductCategory(1));
            context.setVariable("products", productService.getProductsForCategory(1));
        }
        context.setVariable("supplierList", supplierDao.getAll());

        context.setVariable("categories", productCategoryDataStore.getAll());
        engine.process("product/index.html", context, resp.getWriter());
    }

}