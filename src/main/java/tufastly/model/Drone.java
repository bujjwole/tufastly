package tufastly.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "drone")
public class Drone implements Serializable {

    private static final long serialVersionUID = 3260875713744086996L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private int droneId;
    private String arriveCenter;
    private String departureCenter;
    private String currentLocation;
    private double velocity;
    private double unitCost;
    private String status;

    @OneToMany
    private List<OrderInfo> orderInfoList;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getDroneId() {
        return droneId;
    }
    public void setDroneId(int droneId) {
        this.droneId = droneId;
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
