package edu.matc.persistence;

import edu.matc.entity.Product;
import edu.matc.entity.User;
import edu.matc.test.util.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductDaoTest {
    ProductDao dao;

    /**
     * Creating the dao.
     */
    @BeforeEach
    void setUp() {
        dao = new ProductDao();
        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");

    }

    /**
     * Verifies the getAllProducts successfully.
     */
    @Test
    void getAllItemsSuccess() {
        List<Product> products = dao.getAllProducts();
        assertEquals(2, products.size());
    }

    /**
     * Verifies a product is returned correctly by id search
     */
    @Test
    void getProductByIdSuccess() {
        Product retrievedProduct = dao.getProductById(1);
        assertNotNull(retrievedProduct);
        assertEquals("Product 1", retrievedProduct.getTitle());
    }

    /**
     * Verify successful insert of a product
     */
    @Test
    void insertSuccess() {
        UserDao userDao = new UserDao();
        User user = userDao.getUserById(1);

        String productDescription = "Product Test";
        String image = "Image Test";
        String tags = "#TestProduct";
        String productionCost = "2.00";
        String price = "15.00";
        int customizable = 1;
        String title = "Test Product";

        Product newProduct = new Product(productDescription, user, image, tags, productionCost, price, customizable, title);
        user.addProduct(newProduct);

        int id = dao.insert(newProduct);
        assertNotEquals(0,id);
        Product insertedProduct = dao.getProductById(id);
        assertEquals("Test Product", insertedProduct.getTitle());
        assertNotNull(insertedProduct.getUser());
        assertEquals("Elizabeth", insertedProduct.getUser().getFirstName());
        // Could continue comparing all values, but
        // it may make sense to use .equals()
        // TODO review .equals recommendations http://docs.jboss.org/hibernate/orm/5.2/productguide/html_single/Hibernate_Product_Guide.html#mapping-model-pojo-equalshashcode
    }

    /**
     * Verify successful delete of product
     */
    @Test
    void deleteSuccess() {
        dao.delete(dao.getProductById(2));
        assertNull(dao.getProductById(2));
    }

    /**
     * Verify successful get by property (equal match)
     */
    @Test
    void getByPropertyEqualSuccess() {
        List<Product> products = dao.getByPropertyEqual("title", "Product 1");
        assertEquals(1, products.size());
        assertEquals(1, products.get(0).getItemId());
    }

    /**
     * Verify successful get by property (like match)
     */
    @Test
    void getByPropertyLikeSuccess() {
        List<Product> products = dao.getByPropertyLike("title", "p");
        assertEquals(2, products.size());
    }

    @Test
    void updateSuccess() {
        String newTitle = "Product One";
        Product productToUpdate = dao.getProductById(1);
        productToUpdate.setTitle(newTitle);
        dao.saveOrUpdate(productToUpdate);
        Product retrievedProduct = dao.getProductById(1);
        assertEquals(newTitle, retrievedProduct.getTitle());
    }


}