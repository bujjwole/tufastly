package tufastly.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tufastly.dao.CenterAddressDao;
import tufastly.dao.OrderInfoDao;
import tufastly.external.GoogleMapClient;
import tufastly.external.RealtorClient;
import tufastly.model.*;

import java.util.*;

@Service
public class RouteOptimizationService {

    @Autowired
    RealtorClient realtorClient;

    @Autowired
    GoogleMapClient googleMapClient;

    @Autowired
    CenterAddressDao centerAddressDao;

    @Autowired
    OrderInfoDao orderInfoDao;

    public RouteOptimizationResponseBody getRouteResponseBody(RouteOptimizationRequestBody requestBody, int orderInfoId) {

        // TODO: 1. Call RealtorClient to fetch 8 addresses randomly
        RealtorResponseItem realtorResponseItem = realtorClient.searchProperty(20, null, null);
        Random random = new Random();
        List<Property> propertyList = new ArrayList<>();
        while (propertyList.size() != 8) {
            int index = random.nextInt(realtorResponseItem.properties.size());
            if (!propertyList.contains(realtorResponseItem.properties.get(index)) && realtorResponseItem.properties.get(index).address.lat != 0.0) {
                propertyList.add(realtorResponseItem.properties.get(index));
            }
        }

        // TODO: 2. Draw three stations addresses from database
        List<CenterAddress> centerAddressList = centerAddressDao.getAllCenterAddress();
        List<String> centerList = new ArrayList<>();
        List<Coordinate> centerCoords = new ArrayList<>();
        centerList.add(centerAddressList.get(0).getCenterAddress1());
        centerList.add(centerAddressList.get(0).getCenterAddress2());
        centerList.add(centerAddressList.get(0).getCenterAddress3());
        Coordinate center1_coords = googleMapClient.getLocation(centerAddressList.get(0).getCenterAddress1()).getResults().get(0).getGeometry().getCoordinate();
        Coordinate center2_coords = googleMapClient.getLocation(centerAddressList.get(0).getCenterAddress2()).getResults().get(0).getGeometry().getCoordinate();
        Coordinate center3_coords = googleMapClient.getLocation(centerAddressList.get(0).getCenterAddress3()).getResults().get(0).getGeometry().getCoordinate();
        centerCoords.add(center1_coords);
        centerCoords.add(center2_coords);
        centerCoords.add(center3_coords);

        // TODO: 3. Fetch the pick_up_address and shipping address from requestBody and convert them to coordinates using GeocodingAPI
        String pick_up_address = requestBody.getPick_up_address();
        String shipping_address = requestBody.getShipping_address();
        Coordinate pick_up_coords = googleMapClient.getLocation(pick_up_address).getResults().get(0).getGeometry().getCoordinate();
        Coordinate shipping_coords = googleMapClient.getLocation(shipping_address).getResults().get(0).getGeometry().getCoordinate();

        // TODO: 4. Call DirectionAPI with centerA, all start addresses and centerB to fetch polyline1 and waypoint_order1
        String centerA = centerList.get(requestBody.getDeparture_station_address());
        Coordinate centerA_coords = centerCoords.get(requestBody.getDeparture_station_address());
        String centerB = centerList.get((requestBody.getDeparture_station_address() + 1) % 3);
        Coordinate centerB_coords = centerCoords.get((requestBody.getDeparture_station_address() + 1) % 3);
        List<String> start_waypoints = new ArrayList<>();
        List<Coordinate> start_waypoints_coords = new ArrayList<>();
        for (int i = 0; i < propertyList.size() / 2; i++) {
            start_waypoints.add(propertyList.get(i).address.line + ", CA");
            Coordinate coordinate = new Coordinate();
            coordinate.setLatitude(propertyList.get(i).address.lat);
            coordinate.setLongitude(propertyList.get(i).address.lon);
            start_waypoints_coords.add(coordinate);
        }
        start_waypoints.add(pick_up_address);
        start_waypoints_coords.add(pick_up_coords);
        DirectionResponseItem googleMapResponseItem1 = googleMapClient.getRoute(centerA, centerB, start_waypoints);
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String polyline1 = googleMapResponseItem1.getRoutes().get(0).getOverview_polyline().getPoints();
        int[] waypoint_order1 = googleMapResponseItem1.getRoutes().get(0).getWaypoint_order();

        // TODO: 5. Call DirectionAPI with centerB, all end addresses and centerC to fetch polyline2 and waypoint_order2
        String centerC = centerList.get((requestBody.getDeparture_station_address() + 2) % 3);
        Coordinate centerC_coords = centerCoords.get((requestBody.getDeparture_station_address() + 2) % 3);
        List<String> end_waypoints = new ArrayList<>();
        List<Coordinate> end_waypoints_coords = new ArrayList<>();
        for (int i = 0; i < propertyList.size() / 2; i++) {
            end_waypoints.add(propertyList.get(i + 4).address.line + ", CA");
            Coordinate coordinate = new Coordinate();
            coordinate.setLatitude(propertyList.get(i + 4).address.lat);
            coordinate.setLongitude(propertyList.get(i + 4).address.lon);
            end_waypoints_coords.add(coordinate);
        }
        end_waypoints.add(shipping_address);
        end_waypoints_coords.add(shipping_coords);
        DirectionResponseItem googleMapResponseItem2 = googleMapClient.getRoute(centerB, centerC, end_waypoints);
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String polyline2 = googleMapResponseItem2.getRoutes().get(0).getOverview_polyline().getPoints();
        int[] waypoint_order2 = googleMapResponseItem2.getRoutes().get(0).getWaypoint_order();

        // TODO: 6. Construct the RouteOptimizationResponseBody
        List<String> waypoints_address = new ArrayList<>();
        List<Coordinate> waypoints_coords = new ArrayList<>();

        waypoints_address.add(centerA);
        waypoints_coords.add(centerA_coords);
        for (int i = 0; i < waypoint_order1.length; i++) {
            waypoints_address.add(start_waypoints.get(waypoint_order1[i]));
            waypoints_coords.add(start_waypoints_coords.get(waypoint_order1[i]));
        }
        waypoints_address.add(centerB);
        waypoints_coords.add(centerB_coords);
        for (int i = 0; i < waypoint_order2.length; i++) {
            waypoints_address.add(end_waypoints.get(waypoint_order2[i]));
            waypoints_coords.add(end_waypoints_coords.get(waypoint_order2[i]));
        }
        waypoints_address.add(centerC);
        waypoints_coords.add(centerC_coords);

        RouteOptimizationResponseBody responseBody = new RouteOptimizationResponseBody.Builder()
                .waypoints_address(waypoints_address)
                .waypoints_coordinates(waypoints_coords)
                .polyline1(polyline1)
                .polyline2(polyline2)
                .build();

        // TODO: 7. Find the corresponding OrderInfo using orderInfoId and store the rest information
        OrderInfo orderInfo = orderInfoDao.getOrderInfoById(orderInfoId);
        orderInfo.setPolyline1(polyline1);
        orderInfo.setPolyline2(polyline2);
        orderInfo.setPoints(waypoints_address);
        orderInfo.setCoordinates(waypoints_coords);
        orderInfoDao.updateOrderInfo(orderInfo);

        return responseBody;
    }

    public static void main(String[] args) {
        RouteOptimizationService service = new RouteOptimizationService();
        RouteOptimizationRequestBody requestBody = new RouteOptimizationRequestBody();
        requestBody.setDeparture_station_address(0);
        service.getRouteResponseBody(requestBody, 0);
    }
}
