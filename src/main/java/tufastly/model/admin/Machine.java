package tufastly.model.admin;

public abstract class Machine {
    private int away;
    private int charging;

    public int getAway() {
        return away;
    }

    public void setAway(int away) {
        this.away = away;
    }

    public int getCharging() {
        return charging;
    }

    public void setCharging(int charging) {
        this.charging = charging;
    }
}
