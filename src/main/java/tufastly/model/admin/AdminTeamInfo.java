package tufastly.model.admin;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AdminTeamInfo {
    private Machine robots;
    private Machine drones;
    private List<AdminTeamDetail> details;

    public Machine getRobots() {
        return robots;
    }

    public void setRobots(Machine robots) {
        this.robots = robots;
    }

    public Machine getDrones() {
        return drones;
    }

    public void setDrones(Machine drones) {
        this.drones = drones;
    }

    public List<AdminTeamDetail> getDetails() {
        return details;
    }

    public void setDetails(List<AdminTeamDetail> details) {
        this.details = details;
    }
}
