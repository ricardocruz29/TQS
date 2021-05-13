class AirQualityService {
    async getAirMetricsByCity(BASE_URL, city) {
        var url = BASE_URL + "getAirMetricsCity?city=" + city;


        var result = await fetch(url);

        return result.json();
    }

    async getAirMetricsByLatLong(BASE_URL, latitude, longitude) {
        var url = BASE_URL + "getAirMetricsLatitudeLongitude?latitude=" + latitude + "&longitude=" + longitude;

        var result = await fetch(url);

        return result.json();
    }

    async getCacheStatistics(BASE_URL) {
        var url = BASE_URL + "getCacheStatistics";

        var result = await fetch(url);

        return await result.json();
    }

}

export default new AirQualityService();
