package tufastly.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Embeddable;
import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@Embeddable
public class Coordinate implements Serializable {

    @JsonProperty("lat")
    private double latitude;

    @JsonProperty("lng")
    private double longitude;

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
