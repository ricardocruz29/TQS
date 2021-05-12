package ptua.airqualityAPI.Models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class ParamData implements Serializable {

    @JsonProperty("city")
    private City city;

    @JsonProperty("iaqi")
    private IAQI iaqi;

    public City getCity(){ return city;}
    public void setCity(City city){ this.city = city;}

    public IAQI getIAQI(){ return iaqi;}
    public void setIAQI(IAQI iaqi){ this.iaqi = iaqi;}
}
