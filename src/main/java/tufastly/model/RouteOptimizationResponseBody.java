package tufastly.model;

import java.util.List;

public class RouteOptimizationResponseBody {

    private List<String> waypoints_address;

    private List<Coordinate> waypoints_coordinates;

    private String polyline1;

    private String polyline2;

    public List<String> getWaypoints_address() {
        return waypoints_address;
    }

    public void setWaypoints_address(List<String> waypoints_address) {
        this.waypoints_address = waypoints_address;
    }

    public List<Coordinate> getWaypoints_coordinates() {
        return waypoints_coordinates;
    }

    public void setWaypoints_coordinates(List<Coordinate> waypoints_coordinates) {
        this.waypoints_coordinates = waypoints_coordinates;
    }

    public String getPolyline1() {
        return polyline1;
    }

    public void setPolyline1(String polyline1) {
        this.polyline1 = polyline1;
    }

    public String getPolyline2() {
        return polyline2;
    }

    public void setPolyline2(String polyline2) {
        this.polyline2 = polyline2;
    }

    public static class Builder {
        private List<String> waypoints_address;
        private List<Coordinate> waypoints_coordinates;
        private String polyline1;
        private String polyline2;

        public Builder waypoints_address(List<String> waypoints_address) {
            this.waypoints_address = waypoints_address;
            return this;
        }

        public Builder waypoints_coordinates(List<Coordinate> waypoints_coordinates) {
            this.waypoints_coordinates = waypoints_coordinates;
            return this;
        }

        public Builder polyline1(String polyline1) {
            this.polyline1 = polyline1;
            return this;
        }

        public Builder polyline2(String polyline2) {
            this.polyline2 = polyline2;
            return this;
        }

        public RouteOptimizationResponseBody build() {
            RouteOptimizationResponseBody routeOptimizationResponseBody = new RouteOptimizationResponseBody();
            routeOptimizationResponseBody.waypoints_address = waypoints_address;
            routeOptimizationResponseBody.waypoints_coordinates = waypoints_coordinates;
            routeOptimizationResponseBody.polyline1 = polyline1;
            routeOptimizationResponseBody.polyline2 = polyline2;
            return routeOptimizationResponseBody;
        }
    }
}
