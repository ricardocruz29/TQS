package ptua.airqualityAPI.Cache;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ptua.airqualityAPI.Models.AirMetricsData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Cache {
    private long TTL;
    private int n_requests;
    private int n_misses;
    private int n_hits;

    private static final Logger logger = Logger.getLogger(Cache.class.getName()); //Creation of logger

    //Contains the requests when searched from cities
    @JsonIgnore
    private Map<String, ResultTTL> requests_city;

    //Contains the requests when searched from latitude and longitude
    @JsonIgnore
    private Map<List<Double>, ResultTTL> requests_lat_long;

    public Cache(long TTL){
        this.TTL = TTL;
        this.requests_city = new HashMap<>();
        this.requests_lat_long = new HashMap<>();
    }

    //Stores a new Request from city
    public void storeRequest(String name, AirMetricsData airMetricsData){
        logger.log(Level.INFO, "Storing city results in Cache");
        ResultTTL resultTTL = new ResultTTL(airMetricsData, System.currentTimeMillis() + this.TTL * 1000);
        this.requests_city.put(name, resultTTL);
    }

    public void storeRequest(Double latitude, Double longitude, AirMetricsData airMetricsData){
        logger.log(Level.INFO, "Storing latitude and longitude results in Cache");
        List<Double> lat_long = createList(latitude,longitude);

        ResultTTL resultTTL = new ResultTTL(airMetricsData, System.currentTimeMillis() + this.TTL * 1000);
        this.requests_lat_long.put(lat_long, resultTTL);
    }

    //Returns the wanted request of city, checking if exists or if has expired
    public AirMetricsData getRequest(String name){
        this.n_requests++;
        AirMetricsData result = null;

        if (!this.requests_city.containsKey(name)){
            logger.log(Level.WARNING, "Cache doesn't contain information about this city");
            this.n_misses++;
        } else if (checkIfExpired(name)){
            logger.log(Level.WARNING, "Information about this city has expired");
            this.n_misses++;
            this.requests_city.remove(name);
        } else {
            logger.log(Level.INFO, "Cache contains information referring to this city");
            this.n_hits++;
            ResultTTL resultTTL = this.requests_city.get(name);
            result = resultTTL.getResult();
        }

        return result;
    }

    //Returns the wanted request of latitude and longitude, checking if exists or if has expired
    public AirMetricsData getRequest(Double latitude, Double longitude){
        this.n_requests++;
        AirMetricsData result = null;
        List<Double> lat_long = createList(latitude,longitude);

        if (!this.requests_lat_long.containsKey(lat_long)){
            logger.log(Level.WARNING, "Cache doesn't contain information about this latitude and longitude");
            this.n_misses++;
        } else if (checkIfExpired(lat_long)){
            logger.log(Level.WARNING, "Information about this latitude and longitude has expired");
            this.n_misses++;
            this.requests_lat_long.remove(lat_long);
        } else {
            logger.log(Level.INFO, "Cache contains information referring to this latitude and longitude");
            this.n_hits++;
            ResultTTL resultTTL = this.requests_lat_long.get(lat_long);
            result = resultTTL.getResult();
        }

        return result;
    }

    //Getters of numbers of requests, hits and misses
    public int getNumberOfRequests() {
        return n_requests;
    }

    public int getNumberOfHits() {
        return n_hits;
    }

    public int getNumberOfMisses() {
        return n_misses;
    }

    //Complementary function to check if the request is expired
    private Boolean checkIfExpired(String name){
        ResultTTL resultTTL = this.requests_city.get(name);
        Long expireTime = resultTTL.getExpireTime();
        return System.currentTimeMillis() > expireTime;
    }

    private Boolean checkIfExpired(List<Double> lat_long){
        ResultTTL resultTTL = this.requests_lat_long.get(lat_long);
        Long expireTime = resultTTL.getExpireTime();
        return System.currentTimeMillis() > expireTime;
    }

    private List<Double> createList(Double n1, Double n2){
        List<Double> n1_n2 = new ArrayList<>();
        n1_n2.add(n1);
        n1_n2.add(n2);
        return n1_n2;
    }
}
