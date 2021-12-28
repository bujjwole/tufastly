package tufastly.model;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "centerAddress")
public class CenterAddress implements Serializable {
    private static final long serialVersionUID = 596920592116460554L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String centerAddress1;
    private String centerAddress2;
    private String centerAddress3;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getCenterAddress1() {
        return centerAddress1;
    }
    public void setCenterAddress1(String centerAddress1) {
        this.centerAddress1 = centerAddress1;
    }

    public String getCenterAddress2() {
        return centerAddress2;
    }
    public void setCenterAddress2(String centerAddress2) {
        this.centerAddress2 = centerAddress2;
    }

    public String getCenterAddress3() {
        return centerAddress3;
    }
    public void setCenterAddress3(String centerAddress3) {
        this.centerAddress3 = centerAddress3;
    }

}