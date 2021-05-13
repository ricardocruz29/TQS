package ptua.airqualityAPI.Cache;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ptua.airqualityAPI.Cache.Cache;
import ptua.airqualityAPI.Models.AirMetricsData;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.*;

/* Tests
       -Check that when we create the Cache, it comes with default values
       -Check that StoreRequest functions properly for city and latitude and longitude
       -Check that getRequest functions properly for city and latitude and longitude
       -Check that N_Requests, N_Misses and N_Hits functions properly
       -Check that method CheckIfExpired functions properly
       -Check if method CreateList functions properly
*/

public class CacheTest {
    private Cache cache;

    @BeforeEach
    private void setUp(){
        cache = new Cache(10);
    }

    //Check that when we create the Cache, it comes with default values
    @Test
    public void TestWhenCreateCacheValuesAreZero(){
        //Assert that this values are zero on the start
        assertThat(cache.getNumberOfRequests()).isZero();
        assertThat(cache.getNumberOfHits()).isZero();
        assertThat(cache.getNumberOfMisses()).isZero();
    }

    //Check that StoreRequest functions properly for city and latitude and longitude
    //Check that getRequest functions properly for city and latitude and longitude
    @Test
    public void TestWhenStoreRequestModeCityThenReturnsCorrect(){
        //Assert that when we store a request with a certain name, when we get it, it comes the correct one.
        AirMetricsData airMetricsData = new AirMetricsData();
        cache.storeRequest("Porto", airMetricsData);
        assertThat(cache.getRequest("Porto")).isEqualTo(airMetricsData);
    }

    //Check that StoreRequest functions properly for city and latitude and longitude
    //Check that getRequest functions properly for city and latitude and longitude
    //Check if method CreateList functions properly
    @Test
    public void TestWhenStoreRequestModeLatLongThenReturnsCorrect(){
        //Assert that when we store a request with a certain name, when we get it, it comes the correct one.
        AirMetricsData airMetricsData = new AirMetricsData();
        Double latitude = -10.0;
        Double longitude = 10.0;
        cache.storeRequest( latitude, longitude , airMetricsData);
        assertThat(cache.getRequest(latitude, longitude)).isEqualTo(airMetricsData);
    }

    //Check that N_Requests, N_Misses and N_Hits functions properly
    @Test
    public void TestWhenRequestInvalidNumberOfRequestsIncreases(){
        //Test that when requesting for a city and latitude and longitude that doesn't exist, they are null, and that the number of requests increased.
        assertThat(cache.getRequest("teste")).isNull();
        assertThat(cache.getRequest(0.0,0.0)).isNull();

        assertThat(cache.getNumberOfRequests()).isEqualTo(2);
    }

    //Check that N_Requests, N_Misses and N_Hits functions properly
    @Test
    public void TestWhenRequesValidNumberOfRequestsIncreases(){
        //Test that when requesting for a latitude and longitude that exist, the number of requests is increased.
        AirMetricsData airMetricsData = new AirMetricsData();
        Double latitude = -10.0;
        Double longitude = 10.0;
        cache.storeRequest( latitude, longitude , airMetricsData);
        AirMetricsData airMetricsData2 = new AirMetricsData();
        cache.storeRequest("Porto", airMetricsData2);

        assertThat(cache.getRequest(latitude, longitude)).isEqualTo(airMetricsData);
        assertThat(cache.getRequest("Porto")).isEqualTo(airMetricsData2);
        assertThat(cache.getRequest("test")).isNull();

        assertThat(cache.getNumberOfRequests()).isEqualTo(3);
    }

    //Check that N_Requests, N_Misses and N_Hits functions properly
    @Test
    public void TestWhenRequestExistsNumberOfHitsIncreases(){
        //Test that number of requests increases, and that number of hits increases.
        AirMetricsData airMetricsData = new AirMetricsData();
        Double latitude = -10.0;
        Double longitude = 10.0;
        cache.storeRequest( latitude, longitude , airMetricsData);

        assertThat(cache.getRequest(latitude, longitude)).isEqualTo(airMetricsData);
        assertThat(cache.getRequest("test")).isNull();
        assertThat(cache.getNumberOfRequests()).isEqualTo(2);
        assertThat(cache.getNumberOfHits()).isEqualTo(1);

        cache.storeRequest( "Porto", airMetricsData);
        cache.getRequest("Porto");
        assertThat(cache.getNumberOfHits()).isEqualTo(2);
    }

    //Check that N_Requests, N_Misses and N_Hits functions properly
    @Test
    public void TestWhenRequestDoesNotExistsNumberOfMissesIncreases(){
        //All of the tests were done before. This tests if number os misses increases
        assertThat(cache.getRequest("test")).isNull();
        assertThat(cache.getNumberOfMisses()).isEqualTo(1);
        assertThat(cache.getRequest(-10.0,20.0)).isNull();
        assertThat(cache.getNumberOfMisses()).isEqualTo(2);
    }

    //Check that N_Requests, N_Misses and N_Hits functions properly
    //Check that method CheckIfExpired functions properly
    @Test
    public void TestWhenRequestExpiredIsNullAndNumberOfMissesIncreases() throws InterruptedException{
        AirMetricsData airMetricsData = new AirMetricsData();
        cache.storeRequest( "Porto" , airMetricsData);

        assertThat(cache.getRequest("Porto")).isEqualTo(airMetricsData);
        assertThat(cache.getNumberOfRequests()).isEqualTo(1);
        assertThat(cache.getNumberOfHits()).isEqualTo(1);
        assertThat(cache.getNumberOfMisses()).isZero();

        //Small time of expiration due to tests
        TimeUnit.SECONDS.sleep(15);

        assertThat(cache.getRequest("Porto")).isNull();
        assertThat(cache.getNumberOfRequests()).isEqualTo(2);
        assertThat(cache.getNumberOfHits()).isEqualTo(1);
        assertThat(cache.getNumberOfMisses()).isEqualTo(1);
    }
}
