package ptua.airqualityAPI.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import ptua.airqualityAPI.Cache.Cache;
import ptua.airqualityAPI.Models.AirMetricsData;
import ptua.airqualityAPI.Services.AirQualityService;

import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/airMetricsAPI")
public class AirMetricsController {

    @Autowired
    private AirQualityService service; //Autowire service

    //Creation of Logger
    private static final Logger logger = Logger.getLogger(AirMetricsController.class.getName());

    @CrossOrigin(origins = "*")
    @GetMapping("/getAirMetricsCity")
    public AirMetricsData getAirMetricsData(@RequestParam(value = "city", required = true) String city) {
        String message = "Received request to get Air Metrics in " + city;
        logger.log(Level.INFO, message);

        if (city.length() < 3 || checkIfContainsNumbers(city)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The city ( " + city + " ) requested is invalid");
        } else {
            return service.getAirMetricsCity(city);
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/getAirMetricsLatitudeLongitude")
    public AirMetricsData getAirMetricsData(@RequestParam(value = "latitude", required = true) Double latitude,
                                            @RequestParam(value = "longitude", required = true) Double longitude) {
        String message = "Received request to get Air Metrics in (" + latitude + " , " + longitude + ")";
        logger.log(Level.INFO, message);

        if (!checkRange(latitude,longitude)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The latitude and longitude are out of bounds. Latitude -> [-90,90] & Longitude -> [-180,180]");
        } else {
            return service.getAirMetricsLatLong(latitude,longitude);
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/getCacheStatistics")
        public Cache getCacheStatistics() {
        logger.log(Level.INFO, "API Cache Statistics Request");
        return service.getCacheStatistics();
    }

    // Complementary Functions

    //Checks if city contains numbers
    public Boolean checkIfContainsNumbers(String city){
        boolean containsNumber = false;

        if (city != null && !city.isEmpty()) {
            for (char c : city.toCharArray()) {
                if (Character.isDigit(c)) {
                    containsNumber = true;
                    break;
                }
            }
        }

        return containsNumber;
    }

    //Checks if latitude and longitude given are within bounds
    public Boolean checkRange(Double lat, Double lon){
        boolean InRange = false;

        Double minLat = -90.0;
        Double maxLat = 90.0;
        Double minLon = -180.0;
        Double maxLon = 180.0;

        if ( (minLat <= lat && lat <= maxLat) && (minLon <= lon && lon <= maxLon) ){
            InRange = true;
        }

        return InRange;
    }
}
