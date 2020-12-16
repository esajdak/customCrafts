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
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * A servlet to register a user
 *
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
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatch;
        int id = createNewUser(request.getParameter("firstName"), request.getParameter("lastName"),
                request.getParameter("email"), request.getParameter("password"));
        if(id != 0) {
            //success
            HttpSession session = request.getSession(false);
            session.setAttribute("message", "You've successfully registered, please sign in to view your account.");
            response.sendRedirect(request.getContextPath() + "/home");
        }
        else {
            //failed
            request.setAttribute("message", "There was an error registering you, please try again");
            request.getRequestDispatcher("/signUp.jsp").forward(request, response);
        }

    }

    /**
     * Create new user int.
     *
     * @param firstName the first name
     * @param lastName  the last name
     * @param email     the email
     * @param password  the password
     * @return the int
     */
    protected int createNewUser(String firstName, String lastName, String email, String password) {
        User newUser = new User();
        newUser.setFirstName(firstName);
        newUser.setLastName(lastName);
        newUser.setEmail(email);
        newUser.setPassword(password);

        GenericDao userDao = new GenericDao(User.class);
        int id = userDao.insert(newUser);
        Role role = new Role();
        role.setRoleName("all");
        newUser.addRole(role);
        return id;
    }
}