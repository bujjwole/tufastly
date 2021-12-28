package tufastly.model;

import java.util.List;

public class PreviewResponseBody {

    private List<String> waypoints_address;

    private List<Coordinate> waypoints_coordinates;

    private String polyline;

    private double distance;

    private double price;

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

    public String getPolyline() {
        return polyline;
    }

    public void setPolyline(String polyline) {
        this.polyline = polyline;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public static class Builder {
        private List<String> waypoints_address;
        private List<Coordinate> waypoints_coordinates;
        private String polyline;
        private double distance;
        private double price;

        public Builder waypoints_address(List<String> waypoints_address) {
            this.waypoints_address = waypoints_address;
            return this;
        }

        public Builder waypoints_coordinates(List<Coordinate> waypoints_coordinates) {
            this.waypoints_coordinates = waypoints_coordinates;
            return this;
        }

        public Builder polyline(String polyline) {
            this.polyline = polyline;
            return this;
        }

        public Builder distance(double distance) {
            this.distance = distance;
            return this;
        }

        public Builder price(double price) {
            this.price = price;
            return this;
        }

        public PreviewResponseBody build() {
            PreviewResponseBody previewResponseBody = new PreviewResponseBody();
            previewResponseBody.waypoints_address = waypoints_address;
            previewResponseBody.waypoints_coordinates = waypoints_coordinates;
            previewResponseBody.polyline = polyline;
            previewResponseBody.distance = distance;
            previewResponseBody.price = price;
            return previewResponseBody;
        }
    }
}
