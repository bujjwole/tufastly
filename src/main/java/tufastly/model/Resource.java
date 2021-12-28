package tufastly.model;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "resource")
public class Resource implements Serializable {

    private static final long serialVersionUID = 1825450025613736782L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int availableRobotC1;
    private int availableRobotC2;
    private int availableRobotC3;
    private int availableDroneC1;
    private int availableDroneC2;
    private int availableDroneC3;
    private int inUseRobot;
    private int inUseDrone;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getAvailableRobotC1() {
        return availableRobotC1;
    }
    public void setAvailableRobotC1(int availableRobotC1) {
        this.availableRobotC1 = availableRobotC1;
    }

    public int getAvailableRobotC2() {
        return availableRobotC2;
    }
    public void setAvailableRobotC2(int availableRobotC2) {
        this.availableRobotC2 = availableRobotC2;
    }

    public int getAvailableRobotC3() {
        return availableRobotC3;
    }
    public void setAvailableRobotC3(int availableRobotC3) {
        this.availableRobotC3 = availableRobotC3;
    }

    public int getAvailableDroneC1() {
        return availableDroneC1;
    }
    public void setAvailableDroneC1(int availableDroneC1) {
        this.availableDroneC1 = availableDroneC1;
    }

    public int getAvailableDroneC2() {
        return availableDroneC2;
    }
    public void setAvailableDroneC2(int availableDroneC2) {
        this.availableDroneC2 = availableDroneC2;
    }

    public int getAvailableDroneC3() {
        return availableDroneC3;
    }
    public void setAvailableDroneC3(int availableDroneC3) {
        this.availableDroneC3 = availableDroneC3;
    }

    public int getInUseRobot() {
        return inUseRobot;
    }
    public void setInUseRobot(int inUseRobot) {
        this.inUseRobot = inUseRobot;
    }

    public int getInUseDrone() {
        return inUseDrone;
    }
    public void setInUseDrone(int inUseDrone) {
        this.inUseDrone = inUseDrone;
    }
}
