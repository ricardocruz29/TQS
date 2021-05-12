package ptua.airqualityAPI.Cache;

import ptua.airqualityAPI.Models.AirMetricsData;


public class ResultTTL {
    private AirMetricsData result;
    private long expireTime;

    public ResultTTL(AirMetricsData result, long expireTime){
        this.result = result;
        this.expireTime = expireTime;
    }

    public AirMetricsData getResult(){ return this.result; }
    public long getExpireTime(){ return this.expireTime; }

    public void setResult(AirMetricsData result) {this.result = result;}
    public void setExpireTime(long expireTime) {this.expireTime = expireTime;}
}
