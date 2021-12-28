package tufastly.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "billingAddress")
public class BillingAddress implements Serializable {

    private static final long serialVersionUID = 1028098616457762743L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String address;
    private String city;
    private String state;
    private String zipcode;
    private String country;

    @OneToOne(mappedBy = "billingAddress") //billingAddress
    private OrderInfo orderInfo;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }

    public String getZipcode() {
        return zipcode;
    }
    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }

    public OrderInfo getOrderInfo() {
        return orderInfo;
    }
    public void setOrderInfo(OrderInfo orderInfo) {
        this.orderInfo = orderInfo;
    }
}
