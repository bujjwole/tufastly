package tufastly.model.admin;

import java.util.List;

public class AdminOrderInfo {
    private AdminOrderTotal totals;
    private List<AdminOrderDetail> details;

    public AdminOrderTotal getTotals() {
        return totals;
    }

    public void setTotals(AdminOrderTotal totals) {
        this.totals = totals;
    }

    public List<AdminOrderDetail> getDetails() {
        return details;
    }

    public void setDetails(List<AdminOrderDetail> details) {
        this.details = details;
    }
}
