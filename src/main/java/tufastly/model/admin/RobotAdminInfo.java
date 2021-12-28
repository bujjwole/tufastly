package tufastly.model.admin;

import org.springframework.stereotype.Component;
import tufastly.model.admin.Machine;

@Component
public class RobotAdminInfo extends Machine {
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
