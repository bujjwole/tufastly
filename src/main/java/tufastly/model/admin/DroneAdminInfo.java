package tufastly.model.admin;

import org.springframework.stereotype.Component;

@Component
public class DroneAdminInfo extends Machine{
    @Override
    public int getAway() {
        return super.getAway();
    }

    @Override
    public void setAway(int away) {
        super.setAway(away);
    }

    @Override
    public int getCharging() {
        return super.getCharging();
    }

    @Override
    public void setCharging(int charging) {
        super.setCharging(charging);
    }
}
