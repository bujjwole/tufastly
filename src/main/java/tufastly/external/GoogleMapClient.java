package tufastly.external;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Component;
import tufastly.model.DirectionResponseItem;
import tufastly.model.GeocodingResponseItem;
import tufastly.model.RealtorResponseItem;
import tufastly.model.Legs;
import tufastly.model.Route;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class GoogleMapClient {
    private static final String DIRECTION_URL_TEMPLATE = "https://maps.googleapis.com/maps/api/directions/json?origin=%s&destination=%s&waypoints=%s&key=%s";
    private static final String GEOCODING_URL_TEMPLATE = "https://maps.googleapis.com/maps/api/geocode/json?address=%s&key=%s";
    private static final String KEY = "YOUR_GOOGLE_MAP_KEY";
    private static final String DIRECTION_URL_TEMPLATE_noWaypoints = "https://maps.googleapis.com/maps/api/directions/json?origin=%s&destination=%s&key=%s";
    public DirectionResponseItem getRoute(String origin, String destination, List<String> waypoints) {
        String WAY_POINTS = "optimize:true";
        for (String point : waypoints) {
            WAY_POINTS = WAY_POINTS + "|" + point + " CA";
        }
        try {
            origin = URLEncoder.encode(origin, "UTF-8");
            destination = URLEncoder.encode(destination, "UTF-8");
            WAY_POINTS = URLEncoder.encode(WAY_POINTS, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String url = new String();
        if (waypoints.size() == 0) {
            url = String.format(DIRECTION_URL_TEMPLATE_noWaypoints, origin, destination, KEY);
        } else {
            url = String.format(DIRECTION_URL_TEMPLATE, origin, destination, WAY_POINTS, KEY);

        }

        CloseableHttpClient httpClient = HttpClients.createDefault();

        ResponseHandler<DirectionResponseItem> responseHandler = response -> {
            if (response.getStatusLine().getStatusCode() != 200) {
                return null;
            }
            HttpEntity entity = response.getEntity();
            if (entity == null) {
                return null;
            }
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(entity.getContent(), DirectionResponseItem.class);
        };

        try {
            return httpClient.execute(new HttpGet(url), responseHandler);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
        return null;
    }

    public GeocodingResponseItem getLocation(String address) {
        try {
            address = URLEncoder.encode(address, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String url = String.format(GEOCODING_URL_TEMPLATE, address, KEY);

        CloseableHttpClient httpClient = HttpClients.createDefault();

        ResponseHandler<GeocodingResponseItem> responseHandler = response -> {
            if (response.getStatusLine().getStatusCode() != 200) {
                return null;
            }
            HttpEntity entity = response.getEntity();
            if (entity == null) {
                return null;
            }
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(entity.getContent(), GeocodingResponseItem.class);
        };

        try {
            return httpClient.execute(new HttpGet(url), responseHandler);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static void main(String[] args) throws IOException {

        RealtorClient client = new RealtorClient();

        RealtorResponseItem res = client.searchProperty(20, null, null);
        String origin = res.properties.get(0).address.line + " CA";
        String destination = res.properties.get(19).address.line + " CA";
        List<String> waypoints = new ArrayList<>();
        for (int i = 1; i < 20; i++) {
            waypoints.add(res.properties.get(i).address.line);
        }
        GoogleMapClient googleMapClient = new GoogleMapClient();
        DirectionResponseItem route = googleMapClient.getRoute(origin, destination, waypoints);
        List<Route> routes = route.getRoutes();
        int[] waypoint_order = routes.get(0).getWaypoint_order();
        System.out.println(Arrays.toString(waypoint_order));
        List<Legs> legs =  routes.get(0).legs;
        double totalDistance = 0;
        double totalDuration = 0;
        for (int i = 0; i < legs.size(); i++) {
            totalDistance = totalDistance + legs.get(i).distance.value;
            totalDuration = totalDuration + legs.get(i).duration.value;
        }
        System.out.println("totalDistance: " + totalDistance/1000 + " km");
        System.out.println("totalDuration: " + totalDuration/360 + " hours");
        origin = res.properties.get(0).address.line;

        totalDistance = 0;
        totalDuration = 0;
        waypoints = new ArrayList<>();
        for (int i = 1; i < 16; i++)   {
            destination = res.properties.get(i).address.line + " CA";
            route = googleMapClient.getRoute(origin, destination, waypoints);
            routes = route.getRoutes();
            legs =  routes.get(0).legs;
            totalDistance = totalDistance + legs.get(0).distance.value;
            totalDuration = totalDuration + legs.get(0).duration.value;
        }
        System.out.println("totalDistance: " + totalDistance/1000 + " km");
        System.out.println("totalDuration: " + totalDuration/360 + " hours");
    }
}
