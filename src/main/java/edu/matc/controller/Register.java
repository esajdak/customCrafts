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
        urlPatterns = {"/signUp"}
)

public class Register extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/signUp.jsp");
        dispatcher.forward(request, response);
    }
//todo verify email doesnt exist already
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatch;
        int id = createNewUser(request.getParameter("firstName"), request.getParameter("lastName"),
                request.getParameter("email"), request.getParameter("password"));
        if(id != 0) {
            //success
            request.setAttribute("message", "nice job");
            response.sendRedirect(request.getContextPath() + "/home");
//            dispatch = request.getRequestDispatcher("/home").sendRedirect(request.getContextPath());
        }
        else {
            //failed
            request.setAttribute("message", "There was an erroring registering you, please try again");
            request.getRequestDispatcher("/signUp.jsp").forward(request, response);
        }

    }

    protected int createNewUser(String firstName, String lastName, String email, String password) {
        User newUser = new User();
        newUser.setFirstName(firstName);
        newUser.setLastName(lastName);
        newUser.setEmail(email);
        newUser.setPassword(password);
        Role role = new Role();
        role.setRoleName("all");
        newUser.addRole(role);

        GenericDao userDao = new GenericDao(User.class);
        int id = userDao.insert(newUser);
        return id;
    }
}