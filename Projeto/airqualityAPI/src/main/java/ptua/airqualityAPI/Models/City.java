package ptua.airqualityAPI.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class City implements Serializable {
    @JsonProperty("geo")
    private List<Double> lat_long;

    @JsonProperty("name")
    private String city_name;

    public List<Double> getLat_long() { return lat_long; }
    public void setLat_long(List<Double> lat_long) { this.lat_long = lat_long; }

    public String getCity_name() { return city_name; }
    public void setCity_name(String city_name) { this.city_name = city_name; }
}
