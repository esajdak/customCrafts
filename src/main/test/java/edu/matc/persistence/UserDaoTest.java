package edu.matc.persistence;

import edu.matc.entity.Product;
import edu.matc.entity.User;
import edu.matc.test.util.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoTest {
    GenericDao genericDao;

    /**
     * Creating the dao.
     */
    @BeforeEach
    void setUp() {
        genericDao = new GenericDao(User.class);
        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");

    }

    /**
     * Verifies the getAllUsers successfully.
     */
    @Test
    void getAllItemsSuccess() {
        List<User> users = genericDao.getAll();
        assertEquals(2, users.size());
    }

    /**
     * Verifies a user is returned correctly by id search
     */
    @Test
    void getByIdSuccess() {
        User retrievedUser = (User)genericDao.getById(1);
        assertNotNull(retrievedUser);
        assertEquals("Elizabeth", retrievedUser.getFirstName());
    }

    /**
     * Verify successful insert of a user
     */
    @Test
    void insertSuccess() {

        User newUser = new User("John", "Doe", "jdd@yahoo.com", "password");
        int id = genericDao.insert(newUser);
        assertNotEquals(0,id);
        User insertedUser = (User)genericDao.getById(id);
        assertTrue(newUser.equals(insertedUser));
    }

    /**
     * Verify successful insert of a user
     * todo test remove too
     */
    @Test
    void removeWithProductSuccess() {
        GenericDao productDao = new GenericDao(Product.class);
        User newUser = new User("John", "Doe", "jdd@yahoo.com", "password");
        Product productToRemove = (Product)productDao.getById(1);

        String productDescription = "Product 1";
        String image = "Image 1";
        String tags = "#FirstProduct";
        String productionCost = "2.00";
        String price = "15.00";
        int customizable = 1;
        String title = "First Product";

        Product product = new Product(productDescription, newUser, image, tags, productionCost, price, customizable, title);

        newUser.removeProduct(productToRemove);

        int id = genericDao.insert(newUser);

        assertNotEquals(0,id);
        User insertedUser = (User)genericDao.getById(id);
        assertNull(productToRemove.getUser());
        assertTrue(newUser.equals(insertedUser));
        assertEquals(0, insertedUser.getProducts().size());
    }

    /**
     * Verify successful insert of a user
     * todo test remove too
     */
    @Test
    void insertWithProductSuccess() {

        User newUser = new User("John", "Doe", "jdd@yahoo.com", "password");

        String productDescription = "Product 1";
        String image = "Image 1";
        String tags = "#FirstProduct";
        String productionCost = "2.00";
        String price = "15.00";
        int customizable = 1;
        String title = "First Product";

        Product product = new Product(productDescription, newUser, image, tags, productionCost, price, customizable, title);

        newUser.addProduct(product);

        int id = genericDao.insert(newUser);

        assertNotEquals(0,id);
        User insertedUser = (User)genericDao.getById(id);
        assertEquals(newUser, insertedUser);
        assertEquals(1, insertedUser.getProducts().size());
    }


    /**
     * Verify successful delete of user
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
        List<User> users = genericDao.getByPropertyEqual("lastName", "Kelley");
        assertEquals(1, users.size());
        assertEquals(2, users.get(0).getUserId());
    }

    /**
     * Verify successful get by property (like match)
     */
    @Test
    void getByPropertyLikeSuccess() {
        List<User> users = genericDao.getByPropertyLike("lastName", "a");
        assertEquals(1, users.size());
    }

    @Test
    void updateSuccess() {
        String newLastName = "Hewitt";
        User userToUpdate = (User)genericDao.getById(1);
        userToUpdate.setLastName(newLastName);
        genericDao.saveOrUpdate(userToUpdate);
        User retrievedUser = (User)genericDao.getById(1);
        assertEquals(userToUpdate, retrievedUser);
    }


}