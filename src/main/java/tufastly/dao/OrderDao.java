package tufastly.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tufastly.model.OrderInfo;

import java.util.ArrayList;
import java.util.List;


@Repository
public class OrderDao {
    @Autowired
    private SessionFactory sessionFactory;

    public long getTotalNumOfOrder() {
        Session session = null;
        long count = 0;
        try {
            session = sessionFactory.openSession();
            Query query = session.createQuery(
                    "select count (*) from OrderInfo o where o.enableOrder= 1");

             count = (Long)query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return count;
    }

    public double getIncome() {
        Session session = null;
        double income = 0;
        try {
            session = sessionFactory.openSession();
            Query query = session.createQuery(
                    "select sum(o.price) from OrderInfo o where o.enableOrder= 1");

            income = (double)query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return income;
    }

    public List<OrderInfo> getAllOrder() {
        List<OrderInfo> orders = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            orders = session.createCriteria(OrderInfo.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return orders;
    }

}
