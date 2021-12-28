package tufastly.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Address {
    @JsonProperty("line")
    public String line;

    @JsonProperty("lat")
    public double lat;

    @JsonProperty("lon")
    public double lon;
}
