package ptua.airqualityAPI.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ptua.airqualityAPI.AirqualityApiApplication;
import ptua.airqualityAPI.Cache.Cache;
import ptua.airqualityAPI.Models.AirMetricsData;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/* Tests
       -Check when requesting valid city, returns the correct results
       -Check if city has less than 2 letters then BadRequest is thrown
       -Check if city contains numbers then BadRequest is thrown
       -Check when requesting valid latitude and longitude, returns the correct results
       -Check if latitude or longitude are out of range then BadRequest is thrown
       -Check when requested cacheStatistics, the correct result is returned
*/

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = AirqualityApiApplication.class)
@AutoConfigureMockMvc
public class AirMetricsControllerIT {
    private AirMetricsData airMetricsData_Lisbon = null;
    private AirMetricsData airMetricsData_Paris = null;
    private AirMetricsData airMetricsData_Madrid= null;

    @Autowired
    private MockMvc mvc;

    @BeforeEach
    private void setUp() throws Exception{
        /* data */
        String json_response_Lisbon = "{\"data\":{\"city\":{\"geo\":[38.748611111111,-9.1488888888889],\"name\":\"Entrecampos, Lisboa, Portugal\"},\"iaqi\":{\"co\":null,\"no2\":{\"v\":10.0},\"o3\":{\"v\":24.8},\"pm10\":{\"v\":24.0},\"pm25\":{\"v\":25.0},\"so2\":{\"v\":0.8}}}}";
        String json_response_Paris = "{\"data\":{\"city\":{\"geo\":[48.856614,2.3522219],\"name\":\"Paris\"},\"iaqi\":{\"co\":{\"v\":0.1},\"no2\":{\"v\":21.2},\"o3\":{\"v\":30.9},\"pm10\":{\"v\":6.0},\"pm25\":{\"v\":33.0},\"so2\":{\"v\":0.6}}}}";
        String json_response_Beijing = "{\"data\":{\"city\":{\"geo\":[39.954592,116.468117],\"name\":\"Beijing (北京)\"},\"iaqi\":{\"co\":{\"v\":7.3},\"no2\":{\"v\":16.0},\"o3\":{\"v\":20.4},\"pm10\":{\"v\":32.0},\"pm25\":{\"v\":78.0},\"so2\":{\"v\":1.6}}}}";
        String json_response_Madrid = "{\"data\":{\"city\":{\"geo\":[40.4167754,-3.7037902],\"name\":\"Madrid\"},\"iaqi\":{\"co\":{\"v\":0.1},\"no2\":{\"v\":7.8},\"o3\":{\"v\":17.5},\"pm10\":{\"v\":10.0},\"pm25\":{\"v\":21.0},\"so2\":{\"v\":2.1}}}}";

        ObjectMapper mapper = new ObjectMapper();

        /* 2 of them will be used for the city method, and the other 2 for latitude and longitude method */
        airMetricsData_Lisbon = mapper.readValue(json_response_Lisbon, AirMetricsData.class);
        airMetricsData_Paris = mapper.readValue(json_response_Paris, AirMetricsData.class);
        airMetricsData_Madrid = mapper.readValue(json_response_Madrid, AirMetricsData.class);
    }

    //Using mockito it was possible to compare results of the values of air metrics. In this case we can't predefine values when creating the airMetricsData object, since these results are constantlyy changing

    //Check when requesting valid city, returns the correct results
    @Test
    public void TestCityRequestThenReturnCorrect() throws Exception{
        mvc.perform(get("/airMetricsAPI/getAirMetricsCity?city=Paris").contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.toJson(airMetricsData_Paris)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.city.name", is(airMetricsData_Paris.getParamData().getCity().getCity_name())))
                .andExpect(jsonPath("$.data.city.geo[0]", is(airMetricsData_Paris.getParamData().getCity().getLat_long().get(0))))
                .andExpect(jsonPath("$.data.city.geo[1]", is(airMetricsData_Paris.getParamData().getCity().getLat_long().get(1))));

        mvc.perform(get("/airMetricsAPI/getAirMetricsCity?city=Madrid").contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.toJson(airMetricsData_Paris)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.city.name", is(airMetricsData_Madrid.getParamData().getCity().getCity_name())))
                .andExpect(jsonPath("$.data.city.geo[0]", is(airMetricsData_Madrid.getParamData().getCity().getLat_long().get(0))))
                .andExpect(jsonPath("$.data.city.geo[1]", is(airMetricsData_Madrid.getParamData().getCity().getLat_long().get(1))));
    }

    //Check if city has less than 2 letters then BadRequest is thrown
    //Check if city contains numbers then BadRequest is thrown
    @Test
    public void TestInvalidCityLengthThenBadRequest() throws Exception{
        mvc.perform(get("/airMetricsAPI/getAirMetricsCity?city=Pa").contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.toJson("")))
                .andExpect(status().isBadRequest());

        mvc.perform(get("/airMetricsAPI/getAirMetricsCity?city=Paris1").contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.toJson("")))
                .andExpect(status().isBadRequest());
    }

    //Check when requesting valid latitude and longitude, returns the correct results
    @Test
    public void TestLatitudeAndLongitudeRequestThenReturnCorrect() throws Exception{
        mvc.perform(get("/airMetricsAPI/getAirMetricsLatitudeLongitude?latitude=38.748611111111&longitude=-9.1488888888889").contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.toJson(airMetricsData_Lisbon)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.city.name", is(airMetricsData_Lisbon.getParamData().getCity().getCity_name())))
                .andExpect(jsonPath("$.data.city.geo[0]", is(airMetricsData_Lisbon.getParamData().getCity().getLat_long().get(0))))
                .andExpect(jsonPath("$.data.city.geo[1]", is(airMetricsData_Lisbon.getParamData().getCity().getLat_long().get(1))));
    }

    //Check if latitude or longitude are out of range then BadRequest is thrown
    @Test
    public void TestInvalidLatitudeOrLongitudeThenBadRequest() throws Exception{
        //latitude out of bounds [-90,90]
        mvc.perform(get("/airMetricsAPI/getAirMetricsLatitudeLongitude?latitude=92.0&longitude=2.3522219").contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.toJson("")))
                .andExpect(status().isBadRequest());

        //latitude and longitude out of bounds [-90,90] [-180,180]
        mvc.perform(get("/airMetricsAPI/getAirMetricsLatitudeLongitude?latitude=-92.0&longitude=182.0").contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.toJson("")))
                .andExpect(status().isBadRequest());

        //longitude out of bounds [-180,180]
        mvc.perform(get("/airMetricsAPI/getAirMetricsLatitudeLongitude?latitude=75.0&longitude=-182.0").contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.toJson("")))
                .andExpect(status().isBadRequest());
    }

    //Check when requested cacheStatistics, the correct result is returned
    @Test
    public void TestRequestCacheStatisticsThenReturnCorrect() throws Exception{
        Cache cache = new Cache(2);
        AirMetricsData airMetricsData = new AirMetricsData();
        Double latitude = 38.748611111111;
        Double longitude = -9.1488888888889;
        cache.storeRequest( latitude,longitude , airMetricsData_Lisbon);

        //Storing lisbon in cache
        mvc.perform(get("/airMetricsAPI/getAirMetricsLatitudeLongitude?latitude=38.748611111111&longitude=-9.1488888888889").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mvc.perform(get("/airMetricsAPI/getAirMetricsLatitudeLongitude?latitude=38.748611111111&longitude=-9.1488888888889").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mvc.perform(get("/airMetricsAPI/getCacheStatistics").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.numberOfRequests", is(2)))
                .andExpect(jsonPath("$.numberOfMisses", is(1)))
                .andExpect(jsonPath("$.numberOfHits", is(1)));

        //With wrong latitude
        mvc.perform(get("/airMetricsAPI/getAirMetricsLatitudeLongitude?latitude=86&longitude=-20").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mvc.perform(get("/airMetricsAPI/getCacheStatistics").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.numberOfRequests", is(3)))
                .andExpect(jsonPath("$.numberOfMisses", is(2)))
                .andExpect(jsonPath("$.numberOfHits", is(1)));
    }
}
