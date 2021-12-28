package tufastly.service.admin;

import com.paypal.api.payments.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tufastly.dao.DroneDao;
import tufastly.dao.OrderDao;
import tufastly.dao.RobotDao;
import tufastly.model.Coordinate;
import tufastly.model.Drone;
import tufastly.model.OrderInfo;
import tufastly.model.Robot;
import tufastly.model.admin.*;
import tufastly.model.admin.AdminTeamPosition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class TeamManagementService {

    @Autowired
    private RobotDao robotDao;
    @Autowired
    private DroneDao droneDao;
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private AdminTeamInfo info;

    public AdminTeamInfo getGeneral() {
        List<Robot> robots = robotDao.getAllRobots();
        List<Drone> drones = droneDao.getAllDrones();
        List<AdminTeamDetail> details = new ArrayList();
        RobotAdminInfo robotInfo = new RobotAdminInfo();
        DroneAdminInfo droneInfo = new DroneAdminInfo();

        extractRobotInfo(robots, details, robotInfo);
        extractDroneInfo(drones, details, droneInfo);

        info.setRobots(robotInfo);
        info.setDrones(droneInfo);
        info.setDetails(details);
        return info;
    }

    public AdminTeamPosition getTeamPosition() {
      AdminTeamPosition positions = new AdminTeamPosition();
      List<OrderInfo> orders = orderDao.getAllOrder();
      List<Coordinate> coordinates = new ArrayList();
      List<String> polylines = new ArrayList();
      for (OrderInfo o : orders) {
          if (o.getEnableOrder()) {
              polylines.add(o.getPolyline1() + ":" + o.getPolyline2());
          }
      }
      return positions;
    }

    private void extractRobotInfo(List<Robot> robots, List<AdminTeamDetail> details, RobotAdminInfo robotInfo) {
        int away = 0;
        int charging = 0;
        for (Robot r : robots) {
            AdminTeamDetail detail = new AdminTeamDetail();
            detail.setId(r.getRobotId());
            detail.setCenter(Arrays.asList(r.getDepartureCenter()));
            detail.setType(Arrays.asList("Robot"));
            detail.setStatus(Arrays.asList(r.getStatus()));
            if (r.getStatus().equals("away")) {
                away++;
                List<OrderInfo> orders = r.getOrderInfoList();
                try {
                    detail.setOrdernumber(orders.get(orders.size() - 1).getId());
                } catch (Exception e){
                    System.out.print("Robot is away but there is no order number!");
                }
            } else {
                charging++;
            }
            robotInfo.setAway(away);
            robotInfo.setCharging(charging);
            details.add(detail);
        }
    }

    private void extractDroneInfo(List<Drone> drones, List<AdminTeamDetail> details, DroneAdminInfo droneInfo) {
        int away = 0;
        int charging = 0;
        for (Drone r : drones) {
            AdminTeamDetail detail = new AdminTeamDetail();
            detail.setId(r.getDroneId());
            detail.setCenter(Arrays.asList(r.getDepartureCenter()));
            detail.setType(Arrays.asList("Drone"));
            detail.setStatus(Arrays.asList(r.getStatus()));
            if (r.getStatus().equals("away")) {
                away++;
                List<OrderInfo> orders = r.getOrderInfoList();
                try {
                    detail.setOrdernumber(orders.get(orders.size() - 1).getId());
                } catch (Exception e){
                    System.out.print("Drone is away but there is no order number!");
                }
            } else {
                charging++;
            }
            droneInfo.setAway(away);
            droneInfo.setCharging(charging);
            details.add(detail);
        }
    }
}
