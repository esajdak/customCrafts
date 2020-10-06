package edu.matc.controller;

import edu.matc.entity.User;
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
        urlPatterns = {"/searchUser"}
)

public class SearchUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GenericDao genericDao = new GenericDao(User.class);

//        String searchTerm = req.getParameter("searchTerm");
//        if ((searchTerm != "") && (searchTerm != null)) {
//            TODO add option to pick what to search by and add term
//            req.setAttribute("items", productDao.getItemsByTag(searchTerm));
//            req.setAttribute("products", productDao.getAllItems());
//        } else {
//            req.setAttribute("products", productDao.getAllItems());
//        }
        req.setAttribute("users", genericDao.getAll());
        RequestDispatcher dispatcher = req.getRequestDispatcher("/results.jsp");
        dispatcher.forward(req, resp);
    }
}