package edu.matc.controller;

import edu.matc.persistence.ItemDao;

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
        urlPatterns = {"/searchItem"}
)

public class SearchItem extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ItemDao itemDao = new ItemDao();
        String searchTerm = req.getParameter("searchTerm");
        if ((searchTerm != "") && (searchTerm != null)) {
//            TODO add option to pick what to search by and add term
//            req.setAttribute("items", itemDao.getItemsByTag(searchTerm));
        } else {
            req.setAttribute("items", itemDao.getAllItems());
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher("/results.jsp");
        dispatcher.forward(req, resp);
    }
}