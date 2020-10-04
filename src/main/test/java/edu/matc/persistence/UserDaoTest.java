package edu.matc.persistence;

import edu.matc.entity.Product;
import edu.matc.entity.User;
import edu.matc.test.util.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoTest {
    UserDao dao;

    /**
     * Creating the dao.
     */
    @BeforeEach
    void setUp() {
        dao = new UserDao();
        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");

    }

    /**
     * Verifies the getAllUsers successfully.
     */
    @Test
    void getAllItemsSuccess() {
        List<User> users = dao.getAllUsers();
        assertEquals(3, users.size());
    }

    /**
     * Verifies a user is returned correctly by id search
     */
    @Test
    void getUserByIdSuccess() {
        User retrievedUser = dao.getUserById(1);
        assertNotNull(retrievedUser);
        assertEquals("Elizabeth", retrievedUser.getFirstName());
    }

    /**
     * Verify successful insert of a user
     */
    @Test
    void insertSuccess() {

        User newUser = new User("John", "Doe", "jdd@yahoo.com", "password");
        int id = dao.insert(newUser);
        assertNotEquals(0,id);
        User insertedUser = dao.getUserById(id);
        assertEquals("John", insertedUser.getFirstName());
        // Could continue comparing all values, but
        // it may make sense to use .equals()
        // TODO review .equals recommendations http://docs.jboss.org/hibernate/orm/5.2/userguide/html_single/Hibernate_User_Guide.html#mapping-model-pojo-equalshashcode
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


        int id = dao.insert(newUser);

        assertNotEquals(0,id);
        User insertedUser = dao.getUserById(id);
        assertEquals("John", insertedUser.getFirstName());
        assertEquals(1, insertedUser.getProducts().size());
        // Could continue comparing all values, but
        // it may make sense to use .equals()
        // TODO review .equals recommendations http://docs.jboss.org/hibernate/orm/5.2/userguide/html_single/Hibernate_User_Guide.html#mapping-model-pojo-equalshashcode
    }


    /**
     * Verify successful delete of user
     */
    @Test
    void deleteSuccess() {
        dao.delete(dao.getUserById(2));
        assertNull(dao.getUserById(2));
    }

    /**
     * Verify successful get by property (equal match)
     */
    @Test
    void getByPropertyEqualSuccess() {
        List<User> users = dao.getByPropertyEqual("lastName", "Kelley");
        assertEquals(1, users.size());
        assertEquals(2, users.get(0).getUserId());
    }

    /**
     * Verify successful get by property (like match)
     */
    @Test
    void getByPropertyLikeSuccess() {
        List<User> users = dao.getByPropertyLike("lastName", "a");
        assertEquals(2, users.size());
    }

    @Test
    void updateSuccess() {
        String newLastName = "Hewitt";
        User userToUpdate = dao.getUserById(1);
        userToUpdate.setLastName(newLastName);
        dao.saveOrUpdate(userToUpdate);
        User retrievedUser = dao.getUserById(1);
        assertEquals(newLastName, retrievedUser.getLastName());
    }


}