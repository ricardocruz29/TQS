package ptua.airqualityAPI.Models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class MetricValue implements Serializable {

    @JsonProperty("v")
    private Double metricValue;

    public Double getMetricValue(){ return this.metricValue; }
    public void setMetricValue(Double metricValue){ this.metricValue = metricValue; }
}
