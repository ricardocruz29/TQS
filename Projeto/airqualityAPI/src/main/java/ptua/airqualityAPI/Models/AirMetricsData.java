package ptua.airqualityAPI.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AirMetricsData implements Serializable {
    @JsonProperty("data")
    private ParamData paramData;

    public ParamData getParamData(){ return paramData;}
    public void setParamData(ParamData pdata){ this.paramData = pdata;}
}
