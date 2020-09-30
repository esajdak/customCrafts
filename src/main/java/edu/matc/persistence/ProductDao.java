package edu.matc.persistence;

import edu.matc.entity.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import java.util.List;

public class ProductDao {
    private final Logger logger = LogManager.getLogger(this.getClass());
    SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();

    /* Get all items */
    public List<Product> getAllItems() {
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Product> query = builder.createQuery(Product.class);
        Root<Product> root = query.from(Product.class);
        List<Product> products = session.createQuery(query).getResultList();
        session.close();
        logger.info(products);
        return products;
    }

    /* Get all items */
//    public List<Product> getItemsByTitle(String title) {
//        SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();
//        Session session = sessionFactory.openSession();
//        CriteriaBuilder builder = session.getCriteriaBuilder();
//        CriteriaQuery<Product> query = builder.createQuery(Product.class);
//        Root<Product> root = query.from(Product.class);
////        TODO figure out how to search by given term and type
//        Expression<String> propertyPath = root.get("title");
//        query.where(builder.like(propertyPath, "%" + title + "%"));
//        List<Product> products = session.createQuery(query).getResultList();
//        session.close();
//        return products;
//    }
}
