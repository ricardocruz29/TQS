package ptua.airqualityAPI.API;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner; // Import the Scanner class to read text files

import java.util.logging.Level; //Logger
import java.util.logging.Logger;

import org.springframework.web.client.RestTemplate; //RestTemplate for HTTP requests
import org.springframework.boot.web.client.RestTemplateBuilder;

import org.springframework.stereotype.Repository; //Annotation Repository

import ptua.airqualityAPI.Models.AirMetricsData; //AirMetris model

@Repository
public class AQICN_API {
    private static final Logger logger = Logger.getLogger(AQICN_API.class.getName()); //Creation of Logger
    private static final String [] info = getAPI_INFO(); //Stored in another file to be easier to change if needed

    private static final String BASE_API_URL = info[0];
    private static final String TOKEN = info[1];

    private final RestTemplate restTemplate = new RestTemplateBuilder().build(); //Creation of synchronous client to perform HTTP requests

    //Get AirMetrics by city
    public AirMetricsData getAirMetris_City(String city){
        AirMetricsData airMetrics_data = null;
        try {
            String api_url = BASE_API_URL + city + "/?token=" + TOKEN;
            airMetrics_data = DoRequest(api_url);
        } catch (Exception e){
            logErrors(e);
        }
        return airMetrics_data;
    }

    //Get AirMetrics by Latitude and Longitude
    public AirMetricsData getAirMetris_LatANDLong(Double latitude, Double longitude){
        AirMetricsData airMetrics_data = null;
        try {
            String api_url = BASE_API_URL + "geo:" + latitude + ";" + longitude + "/?token=" + TOKEN;
            airMetrics_data = DoRequest(api_url);
        } catch (Exception e){
            logErrors(e);
        }
        return airMetrics_data;
    }


    // ------------------------------------------------------------ Complementary Functions -----------------------------------------------------------

    //Function to make the request and Log Success
    private AirMetricsData DoRequest(String api_url){
        AirMetricsData airMetrics_data = restTemplate.getForObject(api_url, AirMetricsData.class);
        String message = "Request sent to " + api_url;
        logger.log(Level.INFO, message);
        return airMetrics_data;
    }

    //Function to Log Errors
    private void logErrors(Exception e) {
        String error = e.toString();
        logger.log(Level.WARNING, error);
        logger.log(Level.WARNING, "Error occurred when trying to get data from API");
    }

    //Function to extract base url and token from the API_Info.txt file. This way it is easier to change if needed.
    //If there's an error, it will be sent the default information.
    private static String[] getAPI_INFO() {
        //default value
        String information [] = new String[]{ "https://api.waqi.info/feed/", "70516169ef686d1ea7477f1715454d4638046301" };

        try {
            File api_info = new File("/home/ricardo/Desktop/TQS/TQS/Projeto/airqualityAPI/src/main/java/ptua/airqualityAPI/API_info.txt");
            Scanner scanner = new Scanner(api_info);
            String [] info = new String[2];
            for (int i = 0; i < 2; i++) {
                String data = scanner.nextLine();
                String [] data_split = data.split("-");
                info[i] = (String) data_split[1];
            }
            logger.log(Level.INFO, "API base url and token extrated succesfully");
            information = info;
        } catch (FileNotFoundException e) {
            logger.log(Level.WARNING, "Error occurred. Default token and api base url will be returned");
            String error = e.toString();
            logger.log(Level.WARNING, error);
        }

        return information;
    }

}
