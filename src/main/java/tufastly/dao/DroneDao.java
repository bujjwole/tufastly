package tufastly.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tufastly.model.Drone;

import java.util.ArrayList;
import java.util.List;

@Repository
public class DroneDao {

    @Autowired
    private SessionFactory sessionFactory;

    public void addDrone(Drone drone) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(drone);
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

    public List<Drone> getAllDrones() {
        List<Drone> droneList = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            droneList = session.createCriteria(Drone.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return droneList;
    }
}
