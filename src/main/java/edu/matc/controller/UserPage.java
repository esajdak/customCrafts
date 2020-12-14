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
    private GenericDao orderDao = new GenericDao(WholeOrder.class);
    private GenericDao orderItemDao = new GenericDao(OrderItem.class);

    //    getOrdersForUser();
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userEmail = request.getRemoteUser();
        User currentUser = getCurrentUser(userEmail);

        Set<WholeOrder> orders = currentUser.getOrders();
        logger.info(orders);
        for (WholeOrder order: orders) {
            Set<OrderItem> items = order.getOrderItems();
            logger.info(items);
            for (OrderItem item: items) {
                Product thisProduct = item.getProduct();
                thisProduct.getTitle();
            }
        }


        List<WholeOrder> ordersForUser = getUsersOrders(userEmail);
        List<OrderItem> orderItemsPerOrder = getAllOrderItemsPerOrder(ordersForUser);

//        request.setAttribute("orders", ordersForUser);
        request.setAttribute("currentUser", currentUser);
//        request.setAttribute("orderItems", orderItemsPerOrder);

        for (WholeOrder order: ordersForUser) {
            for (OrderItem orderItem: orderItemsPerOrder) {
                WholeOrder tryThis = orderItem.getWholeOrder();
                int tryThisInt = tryThis.getOrderNumber();
                if (order.getOrderNumber() == tryThisInt) {
//                    logger.info(order.getOrderNumber());
//                    logger.info(orderItem.getProduct());
//                    logger.info(orderItem.getQuantity());
                }
            }
        }
//        for ()

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

    protected List<WholeOrder> getUsersOrders(String userEmail) {
        List<User> currentUserList = userDao.getByPropertyEqual("email", userEmail);
        int userId = 0;
        for (User currentUser: currentUserList) {
            userId = currentUser.getUserId();
        }
        List<WholeOrder> ordersForUser = orderDao.getByForeignKey("user", userId);
        return ordersForUser;
    }

    protected List<OrderItem> getAllOrderItemsPerOrder(List<WholeOrder> ordersForUser) {
        List<OrderItem> itemsPerOrder = new ArrayList<>();
        for (WholeOrder order: ordersForUser) {
            int orderNumber = order.getOrderNumber();
            List<OrderItem> itemsForOneOrder = orderItemDao.getByForeignKey("wholeOrder", orderNumber);
            for (OrderItem itemInOrder: itemsForOneOrder) {
                if (!itemsPerOrder.contains(itemInOrder)) {
                    itemsPerOrder.add(itemInOrder);
                }
            }
        }
        return itemsPerOrder;
    }
}
