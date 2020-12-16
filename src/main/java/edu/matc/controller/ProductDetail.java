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
 * A servlet to show product details
 *
 * @author Elizabeth Sajdak
 */
@WebServlet(
        urlPatterns = {"/productDetail"}
)

public class ProductDetail extends HttpServlet {
    private final Logger logger = LogManager.getLogger(this.getClass());
    /**
     * The Generic dao.
     */
    GenericDao genericDao = new GenericDao(Product.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if ((id != "") && (id != null)) {
            int newId = Integer.parseInt(id);

            Product productOnPage = (Product)genericDao.getById(newId);
            req.setAttribute("product", productOnPage);

            String [] arrOfTags = getProductTags(productOnPage);
            req.setAttribute("productTags", arrOfTags);

            List<Product> relatedProducts = getRelatedProducts(arrOfTags);
            req.setAttribute("relatedProducts", relatedProducts);

            RequestDispatcher dispatcher = req.getRequestDispatcher("/productDetail.jsp");
            dispatcher.forward(req, resp);
        } else {

            RequestDispatcher dispatcher = req.getRequestDispatcher("/home");
            dispatcher.forward(req, resp);
        }

    }

    /**
     * Get product tags string [ ].
     *
     * @param productOnPage the product on page
     * @return the string [ ]
     */
    protected String [] getProductTags(Product productOnPage) {
        String productOnPageTags = productOnPage.getTags();
        return productOnPageTags.split(" ");
    }

    /**
     * Gets related products.
     *
     * @param arrOfTags the arr of tags
     * @return the related products
     */
    protected List<Product> getRelatedProducts(String [] arrOfTags) {

        List<edu.matc.entity.Product> relatedProducts = new ArrayList<>();
        for (String tag: arrOfTags) {
            List<edu.matc.entity.Product> originalRelatedProducts = genericDao.getByPropertyLike("tags", tag);
            for (edu.matc.entity.Product relatedProduct: originalRelatedProducts) {
                if (!relatedProducts.contains(relatedProduct)) {
                    relatedProducts.add(relatedProduct);
                }
            }
        }
        return relatedProducts;
    }
}