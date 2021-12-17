package com.codecool.shop.dao.implementation.jdbc;

import com.codecool.shop.dao.UserDao;
import com.codecool.shop.model.User;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseManager {

    private static final String DB_URL = "db.url";
    private static final String DB_NAME = "db.database";
    private static final String DB_USERNAME = "db.user";
    private static final String DB_PASSWORD = "db.password";
//    private ProductDaoJdbc productDao = null;
//    private SupplierDaoJdbc supplierDao = null;
//    private ProductCategoryDaoJdbc categoryDao = null;
//    private UserOrderDaoJdbc userOrderDao = null;

    private UserDao userDao;
    private static DatabaseManager instance = null;

    private static Properties properties = null;

    public static DatabaseManager getInstance() {
        if (instance == null) {
            instance = new DatabaseManager();
        }
        return instance;
    }

    public void setup() throws SQLException, IOException {
        DataSource dataSource = connect();
//        productDao = new ProductDaoJdbc(dataSource);
//        supplierDao = new SupplierDaoJdbc(dataSource);
//        categoryDao = new ProductCategoryDaoJdbc(dataSource);
        userDao = new UserDaoJdbc(dataSource);
//        userOrderDao = new UserOrderDaoJdbc(dataSource);
    }

    public void addUser(User user) {
        userDao.addUser(user);
    }

//    public boolean doesNameExist(String name) {
//        return userDao.doesNameExist(name);
//    }
//
//    public boolean doesEmailExist(String email) {
//        return userDao.doesEmailExist(email);
//    }
//
//    public String getPasswordForEmail(String email) {
//        return userDao.getPasswordForEmail(email);    }

    public User getUserByEmail(String email) {
        return userDao.getUserByEmail(email);
    }

//    public ProductDaoJdbc getProductDao() {
//        return productDao;
//    }
//
//    public SupplierDaoJdbc getSupplierDao() {
//        return supplierDao;
//    }
//
//    public ProductCategoryDaoJdbc getCategoryDao() {
//        return categoryDao;
//    }
//
//    public UserOrderDaoJdbc getUserOrderDao() {
//        return userOrderDao;
//    }

    private DataSource connect()
            throws SQLException, IOException {

        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        properties = new Properties();
        properties.load(new FileInputStream("src/main/webapp/connection.properties"));
        String dbName = properties.getProperty(DB_NAME);
        String user = properties.getProperty(DB_USERNAME);
        String password = properties.getProperty(DB_PASSWORD);
        String url = properties.getProperty(DB_URL);


        dataSource.setDatabaseName(dbName);
        dataSource.setUser(user);
        dataSource.setPassword(password);
        dataSource.setUrl(url);

        System.out.println("Trying to connect");
        dataSource.getConnection().close();
        System.out.println("Connection ok.");

        return dataSource;
    }

}
