package tufastly.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import tufastly.dao.CenterAddressDao;
import tufastly.dao.DroneDao;
import tufastly.dao.RobotDao;
import tufastly.external.GoogleMapClient;
import tufastly.model.*;

import java.util.ArrayList;
import java.util.List;

@Service
public class PreviewService {

    @Autowired
    GoogleMapClient googleMapClient;

    @Autowired
    CenterAddressDao centerAddressDao;

    @Autowired
    DroneDao droneDao;

    @Autowired
    RobotDao robotDao;

    public PreviewResponseBody getRobotPreviewResponseBody(PreviewRequestBody requestBody) {

        // TODO: 1. Draw three station addresses from database and confirm the departure station
        List<CenterAddress> centerAddressList = centerAddressDao.getAllCenterAddress();
        List<String> centerList = new ArrayList<>();
        centerList.add(centerAddressList.get(0).getCenterAddress1());
        centerList.add(centerAddressList.get(0).getCenterAddress2());
        centerList.add(centerAddressList.get(0).getCenterAddress3());
        String departure_address = centerList.get(requestBody.getDeparture_station_address());
        Coordinate departure_coords = googleMapClient.getLocation(departure_address).getResults().get(0).getGeometry().getCoordinate();

        // TODO: 2. Call GeocodingAPI to convert pick_up_address and shipping_address to coordinates relatively
        String pick_up_address = requestBody.getPick_up_address();
        String shipping_address = requestBody.getShipping_address();
        Coordinate pick_up_coords = googleMapClient.getLocation(pick_up_address).getResults().get(0).getGeometry().getCoordinate();
        Coordinate shipping_coords = googleMapClient.getLocation(shipping_address).getResults().get(0).getGeometry().getCoordinate();

        // TODO: 3. Call DirectionAPI to fetch the preview polyline and distance
        List<String> waypoint = new ArrayList<>();
        waypoint.add(shipping_address);
        DirectionResponseItem directionResponseItem = googleMapClient.getRoute(departure_address, pick_up_address, waypoint);
        String polyline = directionResponseItem.getRoutes().get(0).getOverview_polyline().getPoints();
        double distance = directionResponseItem.getRoutes().get(0).getLegs().get(0).getDistance().getValue();

        // TODO: 4. Draw unit cost for robot to calculate the price
        List<Robot> robotList = robotDao.getAllRobots();
        double unit_cost = robotList.get(0).getUnitCost();
        // FIXME: Unknown price rule
        double price = distance * unit_cost;

        // TODO: 5. Construct the PreviewResponseBody
        List<String> waypoints_address = new ArrayList<>();
        List<Coordinate> waypoints_coords = new ArrayList<>();

        waypoints_address.add(departure_address);
        waypoints_address.add(pick_up_address);
        waypoints_address.add(shipping_address);

        waypoints_coords.add(departure_coords);
        waypoints_coords.add(pick_up_coords);
        waypoints_coords.add(shipping_coords);

        PreviewResponseBody responseBody = new PreviewResponseBody.Builder()
                .waypoints_address(waypoints_address)
                .waypoints_coordinates(waypoints_coords)
                .polyline(polyline)
                .distance(distance)
                .price(price)
                .build();
        return responseBody;
    }

    public PreviewResponseBody getDronePreviewResponseBody(PreviewRequestBody requestBody) {
        // TODO: 1. Draw three station addresses from database and confirm the departure station
        List<CenterAddress> centerAddressList = centerAddressDao.getAllCenterAddress();
        List<String> centerList = new ArrayList<>();
        centerList.add(centerAddressList.get(0).getCenterAddress1());
        centerList.add(centerAddressList.get(0).getCenterAddress2());
        centerList.add(centerAddressList.get(0).getCenterAddress3());
        String departure_address = centerList.get(requestBody.getDeparture_station_address());
        Coordinate departure_coords = googleMapClient.getLocation(departure_address).getResults().get(0).getGeometry().getCoordinate();

        // TODO: 2. Call GeocodingAPI to convert pick_up_address and shipping_address to coordinates relatively
        String pick_up_address = requestBody.getPick_up_address();
        String shipping_address = requestBody.getShipping_address();
        Coordinate pick_up_coords = googleMapClient.getLocation(pick_up_address).getResults().get(0).getGeometry().getCoordinate();
        Coordinate shipping_coords = googleMapClient.getLocation(shipping_address).getResults().get(0).getGeometry().getCoordinate();

        // TODO: 3. Calculate the distance between departure_coords and pick_up_coords
        double distance1 = getDistance(departure_coords, pick_up_coords);

        // TODO: 4. Calculate the distance between pick_up_coords and shipping_coords
        double distance2 = getDistance(pick_up_coords, shipping_coords);

        // TODO: 5. Draw unit cost for drone to calculate the price
        List<Drone> droneList = droneDao.getAllDrones();
        double unit_cost = droneList.get(0).getUnitCost();
        // FIXME: Unknown price rule
        double price = (distance1 + distance2) * unit_cost;

        // TODO: 6. Construct the PreviewResponseBody
        List<String> waypoints_address = new ArrayList<>();
        List<Coordinate> waypoints_coords = new ArrayList<>();

        waypoints_address.add(departure_address);
        waypoints_address.add(pick_up_address);
        waypoints_address.add(shipping_address);

        waypoints_coords.add(departure_coords);
        waypoints_coords.add(pick_up_coords);
        waypoints_coords.add(shipping_coords);

        PreviewResponseBody responseBody = new PreviewResponseBody.Builder()
                .waypoints_address(waypoints_address)
                .waypoints_coordinates(waypoints_coords)
                .distance(distance1 + distance2)
                .price(price)
                .build();

        return responseBody;
    }

    public double getDistance(Coordinate coordinate1, Coordinate coordinate2) {
        double R = 6378137;
        double d1 = coordinate1.getLatitude() * Math.PI / 180;
        double d2 = coordinate2.getLatitude() * Math.PI / 180;
        double dLat = (coordinate2.getLatitude() - coordinate1.getLatitude()) * Math.PI / 180;
        double dLng = (coordinate2.getLongitude() - coordinate1.getLongitude()) * Math.PI / 180;
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.cos(d1) * Math.cos(d2) * Math.sin(dLng / 2) * Math.sin(dLng / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double d = R * c;
        return Math.round(d);
    }

    public static void main(String[] args) {
        PreviewService service = new PreviewService();
        PreviewRequestBody requestBody = new PreviewRequestBody();
        requestBody.setDeparture_station_address(0);
        requestBody.setPick_up_address("Contemporary Jewith Museum");
        requestBody.setShipping_address("Ferry Building");
        PreviewResponseBody responseBody = service.getRobotPreviewResponseBody(requestBody);
        System.out.print(responseBody);
    }
}
