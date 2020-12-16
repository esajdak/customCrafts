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
 * A servlet to edit the product.
 *
 * @author Elizabeth Sajdak
 */
@WebServlet(
        urlPatterns = {"/editThisProduct"}
)

public class EditThisProduct extends HttpServlet {
    /**
     * The Product dao.
     */
    GenericDao productDao = new GenericDao(Product.class);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int itemId = Integer.parseInt(request.getParameter("productId"));
        Product productToEdit = (Product)productDao.getById(itemId);
        updateProduct(productToEdit, request.getParameter("title"), request.getParameter("description"),
                request.getParameter("tags"), request.getParameter("productionCost"),
                request.getParameter("price"), request.getParameter("customizable"), request.getParameter("image"));
        response.sendRedirect(request.getContextPath() + "/myAccount");
    }

    /**
     * Update product.
     *
     * @param productToEdit  the product to edit
     * @param title          the title
     * @param description    the description
     * @param tags           the tags
     * @param productionCost the production cost
     * @param price          the price
     * @param customizable   the customizable
     * @param image          the image
     */
    protected void updateProduct(Product productToEdit, String title, String description, String tags, String productionCost,
                                 String price, String customizable, String image) {
        productToEdit.setTitle(title);
        productToEdit.setDescription(description);
        productToEdit.setTags(tags);
        productToEdit.setProductionCost(productionCost);
        productToEdit.setPrice(price);
        productToEdit.setCustomizable(Boolean.parseBoolean(customizable));
        productToEdit.setImage(image);

        productDao.saveOrUpdate(productToEdit);
    }
}