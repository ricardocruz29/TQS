package ptua.airqualityAPI.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ptua.airqualityAPI.API.AQICN_API;
import ptua.airqualityAPI.Cache.Cache;
import ptua.airqualityAPI.Models.AirMetricsData;

import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class AirQualityService {

    @Autowired
    private AQICN_API aqicn_api;

    private final Cache cache = new Cache(10 * 60L);  //10 minutes of TTL

    private static final Logger logger = Logger.getLogger(AirQualityService.class.getName()); //Creation of logger

    public AirMetricsData getAirMetricsCity(String city) {
        logger.log(Level.INFO, "Trying to get data from cache");
        AirMetricsData data = cache.getRequest(city);

        if ( data == null ){
            logger.log(Level.INFO, "Cache didn't have the data. Requesting from the external API");
            data = this.aqicn_api.getAirMetris_City(city);
            cache.storeRequest(city, data);
        } else {
            logger.log(Level.INFO, "Data retrieved from the Cache");
        }

        return data;
    }

    public AirMetricsData getAirMetricsLatLong(Double latitude, Double longitude) {
        logger.log(Level.INFO, "Trying to get data from cache");
        AirMetricsData data = cache.getRequest(latitude,longitude);

        if ( data == null ){
            logger.log(Level.INFO, "Cache didn't have the data. Requesting from the external API");
            data = this.aqicn_api.getAirMetris_LatANDLong(latitude,longitude);
            cache.storeRequest(latitude, longitude, data);
        } else {
            logger.log(Level.INFO, "Data retrieved from the Cache");
        }

        return data;
    }

    public Cache getCacheStatistics(){
        return cache;
    }
}
