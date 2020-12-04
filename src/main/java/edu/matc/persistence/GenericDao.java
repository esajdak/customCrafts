package edu.matc.persistence;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * A generic Dao
 */
public class GenericDao<T> {
    private Class<T> type;
    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * Instantiate a new Generic dao
     *
     * @param type the entity type, for example, User.
     */
    public GenericDao(Class<T> type) {
        this.type = type;
    }

    /**
     * Returns an open session from the SessionFactory
     * @return session
     */
    private Session getSession() {
        return SessionFactoryProvider.getSessionFactory().openSession();
    }

    /**
     *Gets an entity by id
     * @param id entity id to search by
     * @return an entity
     */
    public <T>T getById(int id) {
        Session session = getSession();
        T entity = (T)session.get(type, id);
        session.close();
        return entity;
    }

    public void delete(T entity) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        session.delete(entity);
        transaction.commit();
        session.close();
    }

    public List<T> getAll() {
        Session session = getSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(type);
        Root<T> root = query.from(type);
        List<T> list = session.createQuery(query).getResultList();
        session.close();
        logger.info(list);
        return list;

    }

    /**
     * update entity
     * @param entity  entity to be inserted or updated
     */
    public int insert(T entity) {
        int id = 0;
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        id = (int)session.save(entity);
        transaction.commit();
        session.close();
        return id;
    }

    /**
     * Get entity by property (exact match)
     * sample usage: getByPropertyEqual("lastname", "Sajdak")
     */
    public List<T> getByPropertyEqual(String propertyName, String value) {
        Session session = getSession();

//        logger.debug("Searching for product with " + propertyName + " = " + value);

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(type);
        Root<T> root = query.from(type);
        query.select(root).where(builder.equal(root.get(propertyName), value));
        List<T> list = session.createQuery( query ).getResultList();

        session.close();
        return list;
    }

    /**
     * Get entity by property (like)
     * sample usage: getByPropertyLike("lastname", "C")
     */
    public List<T> getByPropertyLike(String propertyName, String value) {
        Session session = getSession();

        logger.debug("Searching for product with {} = {}",  propertyName, value);

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(type);
        Root<T> root = query.from(type);
        Expression<String> propertyPath = root.get(propertyName);

        query.where(builder.like(propertyPath, "%" + value + "%"));

        List<T> list = session.createQuery( query ).getResultList();
        session.close();
        return list;
    }

    /**
     * update entity
     * @param entity  entity to be inserted or updated
     */
    public void saveOrUpdate(T entity) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(entity);
        transaction.commit();
        session.close();
    }

}
