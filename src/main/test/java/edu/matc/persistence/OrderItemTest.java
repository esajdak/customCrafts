package edu.matc.persistence;

import edu.matc.entity.OrderItem;
import edu.matc.entity.Product;
import edu.matc.entity.User;
import edu.matc.entity.WholeOrder;
import edu.matc.test.util.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderItemTest {
    GenericDao genericDao;
    GenericDao productDao;
    GenericDao userDao;
    GenericDao orderDao;

    /**
     * Creating the dao.
     */
    @BeforeEach
    void setUp() {
        genericDao = new GenericDao(OrderItem.class);
        productDao = new GenericDao(Product.class);
        userDao = new GenericDao(User.class);
        orderDao = new GenericDao(WholeOrder.class);


        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");

    }

    /**
     * Gets by id success.
     */
    @Test
    void getByIdSuccess() {
        Product product = (Product)productDao.getById(5);
        OrderItem orderItem = (OrderItem)genericDao.getById(1);
        assertNotNull(orderItem);
        assertEquals(product, orderItem.getProduct());
    }

    @Test
    void insertSuccess() {
        Product product = (Product) productDao.getById(1);
        User user = (User) userDao.getById(1);
        WholeOrder newOrder = new WholeOrder(user);
        user.addWholeOrder(newOrder);

        OrderItem item = new OrderItem(product, newOrder, 2);
        assertNotNull(item.getId());
        assertTrue(newOrder.equals(item.getWholeOrder()));
    }

    @Test
    void deleteSuccess() {
        genericDao.delete(genericDao.getById(1));
        assertNull(genericDao.getById(1));
    }

//    @Test
//    void getByPropertyEqualSuccess() {
//        WholeOrder expectedOrder = (WholeOrder)orderDao.getById(1);
//        List<OrderItem> orderItems = genericDao.getByPropertyEqual("orders", expectedOrder);
//        assertEquals(1, orderItems.size());
//        assertEquals(expectedOrder, orderItems.get(0).getWholeOrder());
//    }
    @Test
    void updateSuccess() {
        OrderItem itemToEdit = (OrderItem)genericDao.getById(1);
        itemToEdit.setQuantity(5);
        genericDao.saveOrUpdate(itemToEdit);
        OrderItem itemRetrieved = (OrderItem)genericDao.getById(1);
        assertEquals(itemToEdit, itemRetrieved);
    }
}