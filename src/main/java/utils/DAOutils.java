package utils;

import models.OrderHeader;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;

public class DAOutils {

    public <T> List<T> getListEntity(Class<T> entityType) {
        Session session = null;
        try {
            session = SessionHibernate.getSessionFactory().openSession();
            Query query = session.createQuery("from " + entityType.getName());
            List queryList = query.list();
            if (queryList != null && queryList.isEmpty()) {
                return null;
            } else {
                System.out.println("list " + queryList);
                return (List<T>) queryList;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    public <T> List<T> geListEntity(Class<T> type, String column, String value) {
        List<T> entities = new ArrayList<T>();
        Session session = null;
        try {
            session = SessionHibernate.getSessionFactory().openSession();
            Criteria userCriteria = session.createCriteria(type);
            userCriteria.add(Restrictions.eq(column, value));
            entities = (List<T>) userCriteria.list();
            if (entities.isEmpty()) {
                return null;
            } else {
                System.out.println("list " + entities);
                return entities;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    public <T> T addEntity(T entity) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = SessionHibernate.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
            return entity;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    public <T> void updateEntityHQL(Class<T> entity, String searchByColumn, Integer searchFor, String updateColumn, String newValue) {
        Session session = null;
        Transaction transaction = null;
        OrderHeader orderHeader = new OrderHeader();
        try {
            session = SessionHibernate.getSessionFactory().openSession();
            transaction = session.beginTransaction();
//            orderHeader = (OrderHeader) session.get(OrderHeader.class, 80);
            String hqlUpdate = "update " + entity.getName() + " set " + updateColumn + "= :updateColumn where " + searchByColumn + " = :searchByColumn";
            int updatedEntities = session.createQuery(hqlUpdate)
                    .setString("updateColumn", newValue)
                    .setInteger("searchByColumn", searchFor)
                    .executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public <T> void updateEntity(T entity) {
        Session session = null;
        Transaction transaction;
        try {
            session = SessionHibernate.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.update(entity);
//            session.saveOrUpdate(entity);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public <T> boolean deleteEntity(T entity) {
        Session session = null;
        Transaction transaction;
        try {
            session = SessionHibernate.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.delete(entity);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            session.close();
            return true;
        }
    }

    public <T> boolean deleteEntity(Class<T> entity, String column, String value) {
        Session session = null;
        Transaction transaction;
        try {
            session = SessionHibernate.getSessionFactory().openSession();
            session.beginTransaction();
            session.createQuery("delete " + entity.getName() + " where " + column + " = :value")
                    .setParameter("value",value).executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            session.close();
            return true;
        }
    }

    public <T> boolean deleteEntity(Class<T> entity, Integer id) {
        Session session = null;
        Transaction transaction;
        try {
            session = SessionHibernate.getSessionFactory().openSession();
            session.beginTransaction();
            T instance=session.load(entity, id);
            session.delete(instance);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Ups!!!!!!!!!!!!!!!!!!!!!!!!!1");
            return false;
        } finally {
            session.close();
            return true;
        }
    }

}
