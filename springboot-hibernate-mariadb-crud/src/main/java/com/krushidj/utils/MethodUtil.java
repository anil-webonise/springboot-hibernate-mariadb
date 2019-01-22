package com.krushidj.utils;

import com.krushidj.constants.CommonConstatnts;
import com.krushidj.constants.PackageConstatnt;
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

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

@Component
@ComponentScan(basePackages = PackageConstatnt.basePackages)
public class MethodUtil<T> {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    private SessionFactory sessionFactory;
    private EntityManager entityManager;

    @PostConstruct
    public void init() {
        sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        entityManager = entityManagerFactory.createEntityManager();
    }


    public void save(T entity) throws Throwable {
        Session session = sessionFactory.openSession();
        try {
            entityManager.getTransaction().begin();
            if (session != null) {
                session.save(entity);
                entityManager.getTransaction().commit();
            } else {
                throw new GlobalException(CommonConstatnts.errorMsg + CommonConstatnts.savingData + CommonConstatnts.constactSupportTeamMsg);
            }
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            log.error(CommonConstatnts.errorMsg + CommonConstatnts.savingData, e);
            throw new GlobalException(CommonConstatnts.errorMsg + CommonConstatnts.savingData + CommonConstatnts.constactSupportTeamMsg);
        } finally {
            if (session != null) {
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

    public void delete(String tableName, Long id) throws Throwable {
        Session session = null;
        try {

            session = sessionFactory.openSession();
            entityManager.getTransaction().begin();
            if (session != null) {
                String hql = "UPDATE " + tableName + "set active =:active" + " WHERE id =:id";
                System.out.println(hql);
                Query query = session.createQuery(hql);
                query.setParameter("active", 0);
                query.setParameter("id", id);
                query.executeUpdate();
                entityManager.getTransaction().commit();
            } else {
                throw new GlobalException(CommonConstatnts.errorMsg + CommonConstatnts.deletingData + CommonConstatnts.constactSupportTeamMsg);
            }
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
            log.error(CommonConstatnts.errorMsg + CommonConstatnts.deletingData, e);
            throw new GlobalException(CommonConstatnts.errorMsg + CommonConstatnts.deletingData + CommonConstatnts.constactSupportTeamMsg);
        } finally {
            if (session != null) {
                // session.flush();
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
