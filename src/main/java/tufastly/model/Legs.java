package tufastly.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Legs {
    @JsonProperty("distance")
    public Distance distance;

    @JsonProperty("duration")
    public DurationLegs duration;

    public Distance getDistance() {
        return distance;
    }

    public void setDistance(Distance distance) {
        this.distance = distance;
    }

    public DurationLegs getDuration() {
        return duration;
    }

    public void setDuration(DurationLegs duration) {
        this.duration = duration;
    }
}
