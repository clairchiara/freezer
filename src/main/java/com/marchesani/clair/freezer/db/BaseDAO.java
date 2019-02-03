package com.marchesani.clair.freezer.db;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.function.Supplier;

/**
 * Contains basic methods concerning Hibernate and session management as well as saving and updating entities
 */
public abstract class BaseDAO {

    public Session get;
    private SessionFactory sessionFactory;

    private Session session;

    protected BaseDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session getSession() {
        if (session == null) {
            session = sessionFactory.openSession();
        }
        return session;
    }

    private Transaction beginTransaction() {
        return getSession().beginTransaction();
    }

    private void close() {
        if (session != null) {
            session.flush();
            session.close();
            session = null;
        }
    }

    protected void saveOrUpdate(DBEntity dbEntity) {
        beginTransaction();
        session.saveOrUpdate(dbEntity);
        close();
    }

    protected <T extends DBEntity> Criteria getCriteriaForClass(Class<T> cls, String alias) {
        return getSession().createCriteria(cls, alias);
    }

    /**
     * Executes a criteria query transactionally
     * @param supplier: a {@link Supplier}
     * @param <R> the desired return class. The result of the supplier will be cast to this.
     */
    protected <R> R getTransactionally(Supplier<?> supplier) {
        beginTransaction();
        Object retVal = supplier.get();
        close();
        return (R) retVal;
    }

}
