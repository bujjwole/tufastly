package tufastly.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "robot")
public class Robot implements Serializable {

    private static final long serialVersionUID = -6717419372694197668L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private int robotId;
    private String arriveCenter;
    private String departureCenter;
    private String currentLocation;
    private double velocity;
    private double unitCost;
    private String status; //String or boolean

    @OneToMany
    private List<OrderInfo> orderInfoList;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getRobotId() {
        return robotId;
    }
    public void setRobotId(int robotId) {
        this.robotId = robotId;
    }

    public String getArriveCenter() {
        return arriveCenter;
    }
    public void setArriveCenter(String arriveCenter) {
        this.arriveCenter = arriveCenter;
    }

    public String getDepartureCenter() {
        return departureCenter;
    }
    public void setDepartureCenter(String departureCenter) {
        this.departureCenter = departureCenter;
    }

    public String getCurrentLocation() {
        return currentLocation;
    }
    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }

    public double getVelocity() {
        return velocity;
    }
    public void setVelocity(double velocity) {
        this.velocity = velocity;
    }

    public double getUnitCost() {
        return unitCost;
    }
    public void setUnitCost(double unitCost) {
        this.unitCost = unitCost;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public List<OrderInfo> getOrderInfoList() {
        return orderInfoList;
    }
    public void setOrderInfoList( List<OrderInfo> orderInfoList) {
        this.orderInfoList = orderInfoList;
    }
}
