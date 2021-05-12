package ptua.airqualityAPI.Models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class IAQI implements Serializable {

    @JsonProperty("co")
    private MetricValue co;
    @JsonProperty("no2")
    private MetricValue no2;
    @JsonProperty("o3")
    private MetricValue o3;
    @JsonProperty("pm10")
    private MetricValue pm10;
    @JsonProperty("pm25")
    private MetricValue pm25;
    @JsonProperty("so2")
    private MetricValue so2;

    public MetricValue getCo() { return this.co; }
    public MetricValue getNo2() { return this.no2; }
    public MetricValue getO3() { return this.o3; }
    public MetricValue getPm10() { return this.pm10; }
    public MetricValue getPm25() { return this.pm25; }
    public MetricValue getSo2() { return this.so2; }

    public void setCo(MetricValue co) { this.co = co; }
    public void setNo2(MetricValue no2) { this.no2 = no2; }
    public void setO3(MetricValue o3) { this.o3 = o3; }
    public void setPm10(MetricValue pm10) { this.pm10 = pm10; }
    public void setPm25(MetricValue pm25) { this.pm25 = pm25; }
    public void setSo2(MetricValue so2) { this.so2 = so2; }

}
