package ptua.airqualityAPI.API;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import ptua.airqualityAPI.Models.AirMetricsData;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

/* Tests
       -Check when getting data from valid city, the results are correct
       -Check when getting data from invalid city, there are no results
       -Check when getting data from valid latitude and longitude, the results are correct
       *Note: There was no test for invalid laitutde and longitude
*/

@ExtendWith(MockitoExtension.class)
public class AQICNTest {


    @InjectMocks
    private AQICN_API api_repository;

    @Test
    public void TestWhenGetAirMetricsByCityReturnCorrectResults(){
        assertThat(api_repository.getAirMetris_City("Paris")).isInstanceOf(AirMetricsData.class);
        assertThat("Paris").isEqualTo(api_repository.getAirMetris_City("Paris").getParamData().getCity().getCity_name());
        //Coordinates of Paris
        List<Double> lat_long = new ArrayList<>();
        Double latitude = 48.856614;
        Double longitude = 2.3522219;
        lat_long.add(latitude);
        lat_long.add(longitude);
        assertThat(lat_long).isEqualTo(api_repository.getAirMetris_City("Paris").getParamData().getCity().getLat_long());
    }

    @Test
    public void TestWhenGetAirMetricsByInvalidCityReturnNull(){
        assertThat(api_repository.getAirMetris_City("teste")).isNull();
    }

    @Test
    public void TestWhenGetAirMetricsByLatLongReturnCorrectResults(){
        assertThat(api_repository.getAirMetris_LatANDLong(48.856614,2.3522219)).isInstanceOf(AirMetricsData.class);
        assertThat("Paris").isEqualTo(api_repository.getAirMetris_LatANDLong(48.856614,2.3522219).getParamData().getCity().getCity_name());
        //Coordinates of Paris
        List<Double> lat_long = new ArrayList<>();
        Double latitude = 48.856614;
        Double longitude = 2.3522219;
        lat_long.add(latitude);
        lat_long.add(longitude);
        assertThat(lat_long).isEqualTo(api_repository.getAirMetris_City("Paris").getParamData().getCity().getLat_long());
    }
}
