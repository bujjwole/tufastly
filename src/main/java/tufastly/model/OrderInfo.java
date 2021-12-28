package tufastly.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "orderInfo")
public class OrderInfo implements Serializable {

    private static final long serialVersionUID = 2652327633296064143L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String pickupTime;
    private String arriveTime;
    private double price;
    private boolean enableOrder;
    private String serviceMethod;

    @Column(length = 2048)
    private String polyline1;

    @Column(length = 2048)
    private String polyline2;

    @ElementCollection
    private List<String> points;

    @ElementCollection
    private List<Coordinate> coordinates;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    //@JoinColumn(unique = true)
    private PickUpAddress pickUpAddress;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    //@JoinColumn(unique = true)
    private ShippingAddress shippingAddress;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    //@JoinColumn(unique = true)
    private BillingAddress billingAddress;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Item itemInfo;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private Robot robot;

    @ManyToOne
    private Drone drone;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getPickupTime() {
        return pickupTime;
    }
    public void setPickupTime(String pickupTime) {
        this.pickupTime = pickupTime;
    }

    public String getArriveTime() {
        return arriveTime;
    }
    public void setArriveTime(String arriveTime) {
        this.arriveTime = arriveTime;
    }

    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }


    public boolean getEnableOrder() {
        return enableOrder;
    }
    public void setEnableOrder(boolean enableOrder) {
        this.enableOrder = enableOrder;
    }

    public String getServiceMethod() {
        return serviceMethod;
    }
    public void setServiceMethod(String serviceMethod) {
        this.serviceMethod = serviceMethod;
    }

    public Item getItemInfo() {
        return itemInfo;
    }
    public void setItemInfo(Item itemInfo) {
        this.itemInfo = itemInfo;
    }

    public String getPolyline1() {
        return polyline1;
    }
    public void setPolyline1(String polyline1) {
        this.polyline1 = polyline1;
    }

    public String getPolyline2() {
        return polyline2;
    }
    public void setPolyline2(String polyline2) {
        this.polyline2 = polyline2;
    }

    public List<String> getPoints() {
        return points;
    }
    public void setPoints(List<String> points) {
        this.points = points;
    }

    public PickUpAddress getPickUpAddress() {
        return pickUpAddress;
    }
    public void setPickUpAddress(PickUpAddress pickUpAddress) {
        this.pickUpAddress = pickUpAddress;
    }

    public ShippingAddress getShippingAddress() {
        return shippingAddress;
    }
    public void setShippingAddress(ShippingAddress shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public BillingAddress getBillingAddress() {
        return billingAddress;
    }
    public void setBillingAddress(BillingAddress billingAddress) {
        this.billingAddress = billingAddress;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    public Customer getCustomer() {
        return customer;
    }

    public void setRobot(Robot robot) {
        this.robot = robot;
    }
    public Robot getRobot() {
        return robot;
    }

    public void setDrone(Drone drone) {
        this.drone = drone;
    }
    public Drone getDrone() {
        return drone;
    }

    public List<Coordinate> getCoordinates() { return coordinates; }
    public void setCoordinates(List<Coordinate> coordinates) { this.coordinates = coordinates; }
}
