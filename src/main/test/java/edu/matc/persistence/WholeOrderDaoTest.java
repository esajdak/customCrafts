package edu.matc.persistence;

import edu.matc.entity.OrderItem;
import edu.matc.entity.User;
import edu.matc.entity.WholeOrder;
import edu.matc.test.util.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WholeOrderDaoTest {
    GenericDao genericDao;

    /**
     * Creating the dao.
     */
    @BeforeEach
    void setUp() {
        genericDao = new GenericDao(WholeOrder.class);
        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");
    }

    /**
     * Verifies the getAllOrders successfully.
     */
    @Test
    void getAllItemsSuccess() {
        List<WholeOrder> orders = genericDao.getAll();
        assertEquals(1, orders.size());
    }

    /**
     * Verifies an order is returned correctly by id search
     */
    @Test
    void getByIdSuccess() {
        WholeOrder retrievedOrder = (WholeOrder)genericDao.getById(1);
        assertNotNull(retrievedOrder);
//        todo compare whole user?
        assertEquals(1, retrievedOrder.getUser().getUserId());
    }

    /**
     * Verify successful insert of an order
     */
    @Test
    void insertSuccess() {
        GenericDao userDao = new GenericDao(User.class);
        User user = (User)userDao.getById(1);

        WholeOrder newOrder = new WholeOrder(user);
        user.addWholeOrder(newOrder);

        int orderNumber = genericDao.insert(newOrder);
        assertNotEquals(0,orderNumber);
        WholeOrder insertedOrder = (WholeOrder) genericDao.getById(orderNumber);
        assertTrue(newOrder.equals(insertedOrder));
        assertNotNull(insertedOrder.getUser());
        assertTrue(user.equals(insertedOrder.getUser()));
    }

    /**
     * Verify successful delete of an order
     */
    @Test
    void deleteSuccess() {
        genericDao.delete(genericDao.getById(1));
        assertNull(genericDao.getById(1));
    }

    /**
     * Verify successful get by property (equal match)
     */
//    @Test
//    void getByPropertyEqualSuccess() {
//        List<WholeOrder> orders = genericDao.getByPropertyEqual("user", "1");
//        assertEquals(1, orders.size());
//        assertEquals(1, orders.get(0).getOrderNumber());
//    }

    @Test
    void updateSuccess() {
        GenericDao userDao = new GenericDao(User.class);
        User user = (User)userDao.getById(2);
        WholeOrder orderToUpdate = (WholeOrder)genericDao.getById(1);
        orderToUpdate.setUser(user);
        genericDao.saveOrUpdate(orderToUpdate);
        WholeOrder retrievedOrder = (WholeOrder)genericDao.getById(1);
        assertEquals(orderToUpdate, retrievedOrder);
    }






}