package edu.matc.persistence;

import edu.matc.entity.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductDaoTest {

    ProductDao dao;

    @BeforeEach
    void setUp() {
        dao = new ProductDao();
    }

    @Test
    void getAllItemsSuccess() {
        List<Product> products = dao.getAllItems();
        assertEquals(1, products.size());
    }

//    @Test
//    void getItemsByTitle() {
//    }
}