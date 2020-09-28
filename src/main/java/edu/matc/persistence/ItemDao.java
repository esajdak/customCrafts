package edu.matc.persistence;

import edu.matc.entity.Item;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import java.util.List;

public class ItemDao {
    private final Logger logger = LogManager.getLogger(this.getClass());

    /* Get all items */
    public List<Item> getAllItems() {
        SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Item> query = builder.createQuery(Item.class);
        Root<Item> root = query.from(Item.class);
        List<Item> items = session.createQuery(query).getResultList();
        session.close();
        return items;
    }

    /* Get all items */
    public List<Item> getItemsByTitle(String title) {
        SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Item> query = builder.createQuery(Item.class);
        Root<Item> root = query.from(Item.class);
//        TODO figure out how to search by given term and type
        Expression<String> propertyPath = root.get("title");
        query.where(builder.like(propertyPath, "%" + title + "%"));
        List<Item> items = session.createQuery(query).getResultList();
        session.close();
        return items;
    }
}
