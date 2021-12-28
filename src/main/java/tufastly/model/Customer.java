package tufastly.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;


@Entity
@Table(name = "customer")
public class Customer implements Serializable {

    private static final long serialVersionUID = 2652327633296064143L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String firstName;
    private String lastName;
    private String customerPhone;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    //@JoinColumn(unique = true)
    private User user;

    @OneToMany
    private List<OrderInfo> orderInfoList;


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }
    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public List<OrderInfo> getOrderList() {
        return orderInfoList;
    }
    public void setOrderList(List<OrderInfo> orderInfoList) {
        this.orderInfoList = orderInfoList;
    }
}
