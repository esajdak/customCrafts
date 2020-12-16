package edu.matc.controller;

import edu.matc.entity.Product;
import edu.matc.entity.Role;
import edu.matc.entity.User;
import edu.matc.persistence.GenericDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * A simple servlet to welcome the user.
 * @author Elizabeth Sajdak
 */

@WebServlet(
        urlPatterns = {"/editProduct"}
)

public class EditProduct extends HttpServlet {
    GenericDao productDao = new GenericDao(Product.class);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int itemId = Integer.parseInt(request.getParameter("productId"));
        Product productSelected = (Product)productDao.getById(itemId);

        String buttonAction = request.getParameter("button");
        if (buttonAction.equals("Edit")) {
            //edit page shows item from itemId
            request.setAttribute("selectedProduct", productSelected);
            request.getRequestDispatcher("editProduct.jsp").forward(request, response);
        } else if (buttonAction.equals("Delete")) {
            //delete prod by itemId
            deleteProduct(productSelected);
            response.sendRedirect(request.getContextPath() + "/myAccount");
        }
    }

    protected void deleteProduct(Product productSelected) {
        productDao.delete(productSelected);
    }
}