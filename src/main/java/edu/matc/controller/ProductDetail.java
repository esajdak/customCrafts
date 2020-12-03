package edu.matc.controller;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import edu.matc.entity.Product;
import edu.matc.persistence.GenericDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple servlet to welcome the user.
 * @author Elizabeth Sajdak
 */

@WebServlet(
        urlPatterns = {"/productDetail"}
)

public class ProductDetail extends HttpServlet {
    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GenericDao genericDao = new GenericDao(Product.class);
        String id = req.getParameter("id");
        if ((id != "") && (id != null)) {
            int newId = Integer.parseInt(id);

            Product productOnPage = (Product)genericDao.getById(newId);
            logger.info("productOnPage" + productOnPage);

            req.setAttribute("product", productOnPage);

            String productOnPageTags = productOnPage.getTags();
            req.setAttribute("productTags", productOnPageTags);
            logger.info("productOnPageTags" + productOnPageTags);

            String [] arrOfTags = productOnPageTags.split(" ");
            logger.info("arrOfTags" + arrOfTags);

            List<Product> relatedProducts = new ArrayList<>();
            for (String tag: arrOfTags) {
                List<Product> originalRelatedProducts = genericDao.getByPropertyLike("tags", tag);
                for (Product relatedProduct: originalRelatedProducts) {
                    if (!relatedProducts.contains(relatedProduct)) {
                        relatedProducts.add(relatedProduct);
                    }
                }
            }
            logger.info("relatedProducts" + relatedProducts);

            req.setAttribute("relatedProducts", relatedProducts);

            RequestDispatcher dispatcher = req.getRequestDispatcher("/productDetail.jsp");
            dispatcher.forward(req, resp);
        } else {
            req.setAttribute("products", genericDao.getAll());
            RequestDispatcher dispatcher = req.getRequestDispatcher("/index.jsp");
            dispatcher.forward(req, resp);
        }

    }
}