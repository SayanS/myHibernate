package utils;

import models.OrderHeader;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.Serializable;
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
        OrderHeader orderHeader=new OrderHeader();
        try {
            session = SessionHibernate.getSessionFactory().openSession();
            transaction = session.beginTransaction();
//            orderHeader = (OrderHeader) session.get(OrderHeader.class, 80);
            String hqlUpdate = "update "+entity.getName()+ " set "+updateColumn +"= :updateColumn where "+searchByColumn+" = :searchByColumn";
            int updatedEntities = session.createQuery( hqlUpdate )
                    .setString( "updateColumn", newValue)
                    .setInteger( "searchByColumn", searchFor )
                    .executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public <T> void updateEntity(Class<T> entityType, Object id, T entity) {
        Session session = null;
        Transaction transaction = null;
        OrderHeader orderHeader=new OrderHeader();
        try {
            session = SessionHibernate.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            orderHeader = (OrderHeader) session.get(entityType, (Serializable) id);
            orderHeader.setCustomerName("OOOOOOOOOOOOO");
            orderHeader.setCustomerAddress("AAAAAAAAAAAAAAA");

//            session.update(o);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

}
