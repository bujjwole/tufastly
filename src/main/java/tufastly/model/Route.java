package tufastly.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Route {

    @JsonProperty("overview_polyline")
    private Overview_Polyline overview_polyline;

    @JsonProperty("waypoint_order")
    private int[] waypoint_order;

    @JsonProperty("legs")
    public List<Legs> legs;

    public Overview_Polyline getOverview_polyline() {
        return overview_polyline;
    }

    public void setOverview_polyline(Overview_Polyline overview_polyline) {
        this.overview_polyline = overview_polyline;
    }

    public int[] getWaypoint_order() {
        return waypoint_order;
    }

    public void setWaypoint_order(int[] waypoint_order) {
        this.waypoint_order = waypoint_order;
    }

    public List<Legs> getLegs() {
        return legs;
    }

    public void setLegs(List<Legs> legs) {
        this.legs = legs;
    }
}
