package tufastly.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tufastly.model.CenterAddress;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CenterAddressDao {

    @Autowired
    private SessionFactory sessionFactory;

    public void addCenterAddress(CenterAddress centerAddress) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(centerAddress);
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

    public List<CenterAddress> getAllCenterAddress() {
        List<CenterAddress> centerAddressList = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            centerAddressList = session.createCriteria(CenterAddress.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return centerAddressList;
    }
}
