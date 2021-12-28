package tufastly.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tufastly.model.OrderInfo;

@Repository
public class PaymentDao {

    @Autowired
    private SessionFactory sessionFactory;

    public void createOrder(OrderInfo orderInfo){
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(orderInfo);
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

    public OrderInfo getOrderById(int orderId){
        Session session = sessionFactory.openSession();
        return session.get(OrderInfo.class, orderId);
    }

}
