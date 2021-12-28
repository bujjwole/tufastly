package tufastly.model.admin;

import java.util.List;

public class AdminTeamDetail {
    private int id;
    private List<String> center;
    private List<String> type;
    private List<String> status;
    private int ordernumber;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<String> getCenter() {
        return center;
    }

    public void setCenter(List<String> center) {
        this.center = center;
    }

    public List<String> getType() {
        return type;
    }

    public void setType(List<String> type) {
        this.type = type;
    }

    public List<String> getStatus() {
        return status;
    }

    public void setStatus(List<String> status) {
        this.status = status;
    }

    public int getOrdernumber() {
        return ordernumber;
    }

    public void setOrdernumber(int ordernumber) {
        this.ordernumber = ordernumber;
    }
}
