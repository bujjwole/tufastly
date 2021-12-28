package tufastly.model.admin;

import java.util.Date;
import java.util.List;

public class AdminOrderDetail {
    private Integer orderNumber;
    private Date date;
    private String name;
    private String pickupaddress;
    private String deliveryaddress;
    private List<String> type;
    private double revenue;

    public AdminOrderDetail() {

    }

    public AdminOrderDetail(Integer orderNumber, Date date, String name, String pickupaddress, String deliveryaddress, List<String> type, double revenue) {
        this.orderNumber = orderNumber;
        this.date = date;
        this.name = name;
        this.pickupaddress = pickupaddress;
        this.deliveryaddress = deliveryaddress;
        this.type = type;
        this.revenue = revenue;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPickupaddress() {
        return pickupaddress;
    }

    public void setPickupaddress(String pickupaddress) {
        this.pickupaddress = pickupaddress;
    }

    public String getDeliveryaddress() {
        return deliveryaddress;
    }

    public void setDeliveryaddress(String deliveryaddress) {
        this.deliveryaddress = deliveryaddress;
    }

    public List<String> getType() {
        return type;
    }

    public void setType(List<String> type) {
        this.type = type;
    }

    public double getRevenue() {
        return revenue;
    }

    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }
}
