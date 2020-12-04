package edu.matc.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        urlPatterns = {"/signIn"}

)


public class SignIn extends HttpServlet {
    private final Logger logger = LogManager.getLogger(this.getClass());
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        logger.info("The logged in user: " + request.getRemoteUser() + " has role of : " + request.isUserInRole("admin"));
//        <c:if test="${pageContext.request.isUserInRole('admin)}">
//            only admin will see what's here/ this goes on some jsp that you want to forward to
//        </c:if>
        String url = "/index.jsp";

        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }
}
