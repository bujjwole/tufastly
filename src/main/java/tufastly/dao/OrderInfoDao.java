package tufastly.dao;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tufastly.model.OrderInfo;

import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderInfoDao {

    @Autowired
    SessionFactory sessionFactory;

    public OrderInfo getOrderInfoById(int orderInfoId) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(OrderInfo.class, orderInfoId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateOrderInfo(OrderInfo orderInfo) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.saveOrUpdate(orderInfo);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public List<OrderInfo> getOrderInfoByCustomerId(int customerId) {
        List<OrderInfo> orderInfos = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            String sql = "SELECT * FROM orderInfo WHERE customer_id = :customer_id";
            SQLQuery query = session.createSQLQuery(sql);
            query.addEntity(OrderInfo.class);
            query.setParameter("customer_id", customerId);
            orderInfos = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderInfos;
    }

}
