package edu.matc.controller;

import edu.matc.entity.Product;
import edu.matc.persistence.GenericDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.*;
import java.io.IOException;

/**
 * A simple servlet to welcome the user.
 * @author Elizabeth Sajdak
 */

@WebServlet(
        urlPatterns = {"/productDetail"}
)

public class ProductDetail extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GenericDao genericDao = new GenericDao(Product.class);
        String id = req.getParameter("id");
        if ((id != "") && (id != null)) {
            int newId = Integer.parseInt(id);
            req.setAttribute("product", genericDao.getById(newId));
            RequestDispatcher dispatcher = req.getRequestDispatcher("/productDetail.jsp");
            dispatcher.forward(req, resp);
        } else {
            req.setAttribute("products", genericDao.getAll());
            RequestDispatcher dispatcher = req.getRequestDispatcher("/index.jsp");
            dispatcher.forward(req, resp);
        }

    }
}