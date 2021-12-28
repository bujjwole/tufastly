package tufastly.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tufastly.model.Robot;

import java.util.ArrayList;
import java.util.List;

@Repository
public class RobotDao {

    @Autowired
    private SessionFactory sessionFactory;

    public void addRobot(Robot robot) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(robot);
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

    public List<Robot> getAllRobots() {
        List<Robot> robotList = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            robotList = session.createCriteria(Robot.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return robotList;
    }
}
