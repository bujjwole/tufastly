package tufastly.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "item")

public class Item implements Serializable {

    private static final long serialVersionUID = 5186013952828648626L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private double weight;
    private double size;
    private double distance;
    private boolean legal;

    @OneToOne(mappedBy = "itemInfo")
    private OrderInfo orderInfo;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    //weight
    public double getWeight() {
        return weight;
    }
    public void setWeight(double weight) {
        this.weight = weight;
    }
    //size
    public double getSize() {
        return size;
    }
    public void setSize(double size) {
        this.size = size;
    }
    //distance
    public double getDistance() {
        return distance;
    }
    public void setDistance(double distance) {
        this.distance = distance;
    }
    //legal
    public boolean getLegal() {
        return legal;
    }
    public void setLegal(boolean legal) {
        this.legal = legal;
    }

    public OrderInfo getOrder() {
        return orderInfo;
    }
    public void setOrder(OrderInfo orderInfo) {
        this.orderInfo = orderInfo;
    }

}
