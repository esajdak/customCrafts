package edu.matc.controller;

import edu.matc.entity.User;
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
import java.util.List;

@WebServlet(
        urlPatterns = {"/signIn"}

)


public class SignIn extends HttpServlet {
    private final Logger logger = LogManager.getLogger(this.getClass());
    private GenericDao genericDao = new GenericDao(User.class);

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        logger.info("The logged in user: " + request.getRemoteUser() + " has role of : " + request.isUserInRole("admin"));

        String currentUserEmail = request.getRemoteUser();
        List<User> currentUserList = genericDao.getByPropertyEqual("email", currentUserEmail);
        logger.info("currentUserList " + currentUserList);
        for (User thisUser: currentUserList) {
            User currentUser = thisUser;
            logger.info("User being sent " + currentUser);
            request.setAttribute("currentUser", currentUser);
        }


        String url = "/myAccount";

        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }
}
