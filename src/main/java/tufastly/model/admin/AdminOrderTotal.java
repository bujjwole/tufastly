package tufastly.model.admin;

public class AdminOrderTotal {
    private long totalorders;
    private double totalrevenues;

    public long getTotalorders() {
        return totalorders;
    }

    public void setTotalorders(long totalorders) {
        this.totalorders = totalorders;
    }

    public double getTotalrevenues() {
        return totalrevenues;
    }

    public void setTotalrevenues(double totalrevenues) {
        this.totalrevenues = totalrevenues;
    }
}
