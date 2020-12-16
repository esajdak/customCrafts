package edu.matc.persistence;

import edu.matc.controller.EditProduct;
import edu.matc.entity.Product;
import edu.matc.entity.User;
import edu.matc.test.util.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductDaoTest {
    GenericDao genericDao;

    /**
     * Creating the dao.
     */
    @BeforeEach
    void setUp() {
        genericDao = new GenericDao(Product.class);
        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");

    }

    /**
     * Verifies the getAllProducts successfully.
     */
    @Test
    void getAllItemsSuccess() {
        List<Product> products = genericDao.getAll();
        assertEquals(12, products.size());
    }

    /**
     * Verifies a product is returned correctly by id search
     */
    @Test
    void getByIdSuccess() {
        Product retrievedProduct = (Product)genericDao.getById(1);
        assertNotNull(retrievedProduct);
        assertEquals("2020 Wooden Ornaments", retrievedProduct.getTitle());
    }

    /**
     * Verify successful insert of a product
     */
    @Test
    void insertSuccess() {
        GenericDao userDao = new GenericDao(User.class);
        User user = (User)userDao.getById(1);

        String productDescription = "Product Test";
        String image = "Image Test";
        String tags = "#TestProduct";
        String productionCost = "2.00";
        String price = "15.00";
        int customizable = 1;
        String title = "Test Product";

        Product newProduct = new Product(productDescription, user, image, tags, productionCost, price, customizable, title);
        user.addProduct(newProduct);

        int id = genericDao.insert(newProduct);
        assertNotEquals(0,id);
        Product insertedProduct = (Product)genericDao.getById(id);
        assertEquals(newProduct, insertedProduct);
        assertNotNull(insertedProduct.getUser());
        assertEquals(user, insertedProduct.getUser());
    }

    /**
     * Verify successful delete of product
     */
    @Test
    void deleteSuccess() {
        genericDao.delete(genericDao.getById(2));
        assertNull(genericDao.getById(2));
    }

    /**
     * Verify successful get by property (equal match)
     */
    @Test
    void getByPropertyEqualSuccess() {
        List<Product> products = genericDao.getByPropertyEqual("title", "Custom Shirts");
        assertEquals(1, products.size());
        assertEquals(3, products.get(0).getItemId());
    }

    /**
     * Verify successful get by property (like match)
     */
    @Test
    void getByPropertyLikeSuccess() {
        List<Product> products = genericDao.getByPropertyLike("title", "Glass");
        assertEquals(4, products.size());
    }

    @Test
    void updateSuccess() {
        String newTitle = "Product One";
        Product productToUpdate = (Product)genericDao.getById(1);
        productToUpdate.setTitle(newTitle);
        genericDao.saveOrUpdate(productToUpdate);
        Product retrievedProduct = (Product)genericDao.getById(1);
        assertEquals(productToUpdate, retrievedProduct);
    }


}