package edu.matc.controller;

import edu.matc.entity.OrderItem;
import edu.matc.entity.Product;
import edu.matc.entity.User;
import edu.matc.entity.WholeOrder;
import edu.matc.persistence.GenericDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@WebServlet(
        urlPatterns = {"/myAccount"}

)


public class UserPage extends HttpServlet {
    private final Logger logger = LogManager.getLogger(this.getClass());
    private GenericDao userDao = new GenericDao(User.class);
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userEmail = request.getRemoteUser();
        User currentUser = getCurrentUser(userEmail);

        request.setAttribute("currentUser", currentUser);

        logger.info("The logged in user: " + request.getRemoteUser() + " has role of : " + request.isUserInRole("admin"));

        RequestDispatcher dispatcher = request.getRequestDispatcher("/myAccount.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);

    }

    protected User getCurrentUser(String userEmail) {
//        String currentUserEmail = request.getRemoteUser();
        User currentUser = new User();
        List<User> currentUserList = userDao.getByPropertyEqual("email", userEmail);
        logger.info("currentUserList " + currentUserList);
        for (User thisUser: currentUserList) {
            currentUser = thisUser;
            logger.info("User being sent " + currentUser);
        }
        return currentUser;
    }

}
