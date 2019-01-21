package com.krushidj.utils;

import com.krushidj.modules.exception.GlobalException;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

@Component
@ComponentScan(basePackages = { "com.krushidj" })
public class MethodUtil<T> {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EntityManagerFactory entityManagerFactory;
    SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
    {
        System.out.println(sessionFactory);
    }

    public void save(T entity) throws Throwable {
        Session session = null;
        Transaction txn = null;
        try {
            session = sessionFactory.openSession();
            txn = session != null ? session.beginTransaction() : null;
            if (session != null && txn != null) {
                session.save(entity);
                txn.commit();
            } else {
                throw new GlobalException("An error occurred while saving . Please contact Support Team.");
            }

        } catch (Exception e) {
            if (txn != null) {
                txn.rollback();
            }
            log.error("An error occurred while saving Data", e);
            throw new GlobalException("An error occurred while saving Data. Please contact Support Team.");
        } finally {
            if (txn != null) {
                txn.rollback();
            }
            if (session != null) {
                session.flush();
                session.close();
            }
        }

    }

    public void update(T entity) throws Throwable {
        Session session = null;
        Transaction txn = null;
        try {
            session = sessionFactory.openSession();
            txn = session != null ? session.beginTransaction() : null;
            if (session != null && txn != null) {

                session.update(entity);
                txn.commit();
            } else {
                throw new GlobalException("An error occurred while saving . Please contact Support Team.");
            }

        } catch (Exception e) {
            log.error("An error occurred while saving Data", e);
            throw new GlobalException("An error occurred while saving Data. Please contact Support Team.");
        } finally {
            if (txn != null) {
                txn.rollback();
            }
            if (session != null) {
                session.flush();
                session.close();
            }
        }
    }

    public void delete(String tableName,Long id) throws Throwable {
        Session session = null;
        Transaction txn = null;
        try {

            session = sessionFactory.openSession();
            txn = session != null ? session.beginTransaction() : null;
            if (session != null && txn != null) {
                String hql = "UPDATE " + tableName + "set active = :active WHERE id = :id";
                Query query = session.createQuery(hql);
                query.setParameter("active", false);
                query.setParameter("id", id);
                query.executeUpdate();
                txn.commit();
            } else {
                throw new GlobalException("An error occurred while deleting . Please contact Support Team.");
            }

        } catch (Exception e) {
            log.error("An error occurred while saving Data", e);
            throw new GlobalException("An error occurred while deleteing Data. Please contact Support Team.");
        } finally {
            if (txn != null) {
                txn.rollback();
            }
            if (session != null) {
                session.flush();
                session.close();
            }
        }
    }

    @SuppressWarnings("unchecked")
    public List<T> getALlById(Long id, String fKeyColumnName) throws Throwable {
        System.out.println("factory " + sessionFactory);
        Session session = null;
        try {
            session = sessionFactory.openSession();
            if (session != null) {
                Criteria crit = session.createCriteria(Object.class);
                crit.add(Restrictions.eq(fKeyColumnName, id));
                crit.add(Restrictions.eq("active", true));
                return crit.list();

            } else {
                throw new GlobalException("An error occurred while getting Data. Please contact Support Team.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            log.error("An error occurred while getting  Data", e);
            throw new GlobalException("An error occurred while getting Data. Please contact Support Team.");
        } finally {
            if (session != null) {
                session.flush();
                session.close();
            }
        }

    }

}
