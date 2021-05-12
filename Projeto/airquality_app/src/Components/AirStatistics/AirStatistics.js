import {useEffect, useState} from 'react';

/* css */
import './AirStatistics.css';

/* Icons */
import * as FaIcons from 'react-icons/fa';

/* Other components */
import AirQualityService from '../../Services/AirQualityService';

function AirStatistics() {
    const [ModeCity, setModeCity] = useState(false);
    const [ModeLatLong, setModeLatLong] = useState(false);

    const [resultCity, setResultCity] = useState([]);
    const [resultLatLong, setResultLatLong] = useState([]);

    
    useEffect( () => {

        function setClass(result){
            let div_no2 = document.getElementById("results-no2");
            let div_co = document.getElementById("results-co");
            let div_o3 = document.getElementById("results-o3");
            let div_pm10 = document.getElementById("results-pm10");
            let div_pm25 = document.getElementById("results-pm25");
            let div_so2 = document.getElementById("results-so2");
            
            let counter = 0;

            
            if (result[0].data.iaqi.no2 === null){
                div_no2.classList.add("null");
                counter++;
            } else {
                if (div_no2.classList.contains("null")){
                    div_no2.classList.remove("null");
                }
                let no2 = result[0].data.iaqi.no2.v; 
                if (no2 <= 80.0){
                    div_no2.classList.add("good");
                } else if (no2 <= 180.0){
                    div_no2.classList.add("moderate");
                } else if (no2 <= 10000.0){
                    div_no2.classList.add("Danger");
                }
            }

            if (result[0].data.iaqi.co === null){
                div_co.classList.add("null");
                counter++;
            } else {
                if (div_co.classList.contains("null")){
                    div_co.classList.remove("null");
                }
                let co = result[0].data.iaqi.co.v; 
                if (co <= 2.0){
                    div_co.classList.add("good");
                } else if (co <= 10.0){
                    div_co.classList.add("moderate");
                } else if (co <= 10000.0){
                    div_co.classList.add("Danger");
                }
            }

            if (result[0].data.iaqi.o3 === null){
                div_o3.classList.add("null");
                counter++;
            } else {
                if (div_o3.classList.contains("null")){
                    div_o3.classList.remove("null");
                }
                let o3 = result[0].data.iaqi.o3.v; 
                if (o3 <= 100.0){
                    div_o3.classList.add("good");
                } else if (o3 <= 168.0){
                    div_o3.classList.add("moderate");
                } else if (o3 <= 10000.0){
                    div_o3.classList.add("Danger");
                }
            }

            if (result[0].data.iaqi.pm10 === null){
                div_pm10.classList.add("null");
                counter++;
            } else {
                if (div_pm10.classList.contains("null")){
                    div_pm10.classList.remove("null");
                }
                let pm10 = result[0].data.iaqi.pm10.v; 
                if (pm10 <= 100.0){
                    div_pm10.classList.add("good");
                } else if (pm10 <= 250.0){
                    div_pm10.classList.add("moderate");
                } else if (pm10 <= 10000.0){
                    div_pm10.classList.add("Danger");
                }
            }

            if (result[0].data.iaqi.pm25 === null){
                div_pm25.classList.add("null");
                counter++;
            } else {
                if (div_pm25.classList.contains("null")){
                    div_pm25.classList.remove("null");
                }
                let pm25 = result[0].data.iaqi.pm25.v; 
                if (pm25 <= 60.0){
                    div_pm25.classList.add("good");
                } else if (pm25 <= 90.0){
                    div_pm25.classList.add("moderate");
                } else if (pm25 <= 10000.0){
                    div_pm25.classList.add("Danger");
                }
            }

            if (result[0].data.iaqi.so2 === null){
                div_so2.classList.add("null");
                counter++;
            } else {
                if (div_so2.classList.contains("null")){
                    div_so2.classList.remove("null");
                }
                let so2 = result[0].data.iaqi.so2.v; 
                if (so2 <= 80.0){
                    div_so2.classList.add("good");
                } else if (so2 <= 380.0){
                    div_so2.classList.add("moderate");
                } else if (so2 <= 10000.0){
                    div_so2.classList.add("Danger");
                }
            }

            if (counter === 6){
                alert("Couldn't find results for " +  result[0].data.city.name + "! Try another one");
            }
        }

        console.log(resultLatLong)
        // Check which one was searched
        if(resultCity.length > 0 && ModeCity){
            console.log("entrei")
            setClass(resultCity);
        } else if (resultLatLong.length > 0 && ModeLatLong){
            console.log("entrei2")
            setClass(resultLatLong);
            console.log(resultLatLong);
        }

    }, [resultCity, resultLatLong])

    function appearInput(mode){
        let modes = ["city", "lat-long"];
        var card_focus, card_not_focus;

        //change the current mode to search
        if (mode === "city"){
            setModeCity(true);
            setModeLatLong(false);
        } else if(mode === "lat-long"){
            setModeCity(false);
            setModeLatLong(true);
        }

        //do the respective changes in css
        for (let i=0; i< modes.length; i++){
            if (modes[i] === mode) {
                card_focus = document.getElementById(mode);
                if(card_focus.classList.contains("mode-not-active")){
                    card_focus.classList.remove("mode-not-active");
                }
                card_focus.classList.add("mode-active");

                let appear_sel;
                if(mode === "city"){
                    appear_sel = document.getElementById("sel-city");
                } else{
                    appear_sel = document.getElementById("sel-lat-long");
                }

                if (appear_sel.classList.contains("hide")) {
                    appear_sel.classList.remove("hide");
                }
                appear_sel.classList.add("appear");
            } else {
                card_not_focus = document.getElementById(modes[i]);
                if(card_not_focus.classList.contains("mode-active")){
                    card_not_focus.classList.remove("mode-active");
                }
                card_not_focus.classList.add("mode-not-active");

                let hide_sel;
                if(mode === "city"){
                    hide_sel = document.getElementById("sel-lat-long");
                } else{
                    hide_sel = document.getElementById("sel-city");
                }

                if (hide_sel.classList.contains("appear")) {
                    hide_sel.classList.remove("appear");
                }
                hide_sel.classList.add("hide");
            }
        }

    }

    async function Search(){
        const BASE_URL = "http://localhost:8080/airMetricsAPI/";

        if (ModeCity){
            let city_select = document.getElementById("city-choose");
            let city = city_select.options[city_select.selectedIndex].value;

            let result = await AirQualityService.getAirMetricsByCity(BASE_URL, city);
            setResultCity([result]);
            

        } else if(ModeLatLong){
            let lat_inp = document.getElementById("lat");
            let long_inp = document.getElementById("long");
            let lat = lat_inp.value;
            let long = long_inp.value;

            if (lat === "" || long === ""){
                alert("When searching by latitude and longitude, you have to fill both fields !")
            } else {
                let result = await AirQualityService.getAirMetricsByLatLong(BASE_URL, lat, long);
                setResultLatLong([result]);
               
            }

        } else {
            alert("You have to search either by City or Latitude and Longitude. Choose one mode !")
        }

        
    }

    return(
        <div className="col-md-12 air">
            <div className="container">
                <h1 className="air-title">Search <span>AirQuality</span> by <span>City</span> or <span>Latitude&Longitude</span></h1>
                <div className="air-content">
                    <div className="mode-section">
                        <div className="by-city">
                            <div id="city" className="card city" onClick={() => appearInput("city")} >
                                <h5>Click to choose a city!</h5>
                                <i><FaIcons.FaCity/></i>
                                <div id="sel-city" className="select">
                                    <select id="city-choose" data-dropdown defaultValue="Choose a city" required>
                                        <option value="" disabled hidden>Choose a city</option>
                                        <option value="Afganistan">Afghanistan</option>
                                        <option value="Beijing">Portugal</option>
                                        <option value="Lisbon">Porto</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                        
                        <div className="by-lat-long">
                            <div id="lat-long" className="card lat-long" onClick={() => appearInput("lat-long")} >
                                <h5>Click to choose the latitude and Longitude!</h5>
                                <i><FaIcons.FaCompass/></i>
                                <div id="sel-lat-long" className="lat_long select">
                                    <input id="lat" type="number" step="0.1" placeholder="latitude"/>
                                    <input id="long" type="number" step="0.1" placeholder="longitude"/>
                                </div>
                            </div>
                        </div>

                        <button className="searc-button" onClick={() => Search()} >Search</button>
                    </div>
                    
                        {
                            (resultCity.length > 0 && ModeCity) &&
                            <div className="results-section">
    
                                <h6>Results for city {resultCity[0].data.city.name}</h6>
                                <div className="air-results">
                                    <div id="results-co" className="result-card">
                                        <h2>CO</h2>
                                        <h3>{resultCity[0].data.iaqi.co === null ? "" : resultCity[0].data.iaqi.co.v}</h3>
                                    </div>
                                    <div id="results-no2" className="result-card">
                                        <h2>NO2</h2>
                                        <h3>{resultCity[0].data.iaqi.no2 === null ? "" : resultCity[0].data.iaqi.no2.v}</h3>
                                    </div>
                                    <div id="results-o3" className="result-card">
                                        <h2>O3</h2>
                                        <h3>{resultCity[0].data.iaqi.o3 === null ? "" : resultCity[0].data.iaqi.o3.v}</h3>
                                    </div>
                                    <div id="results-pm10" className="result-card">
                                        <h2>PM10</h2>
                                        <h3>{resultCity[0].data.iaqi.pm10 === null ? "" : resultCity[0].data.iaqi.pm10.v}</h3>
                                    </div>
                                    <div id="results-pm25" className="result-card">
                                        <h2>PM25</h2>
                                        <h3>{resultCity[0].data.iaqi.pm25 === null ? "" : resultCity[0].data.iaqi.pm25.v}</h3>
                                    </div>
                                    <div id="results-so2" className="result-card">
                                        <h2>SO2</h2>
                                        <h3>{resultCity[0].data.iaqi.so2 === null ? "" : resultCity[0].data.iaqi.so2.v}</h3>
                                    </div>
                                </div>
                            </div>
                        }

                        {
                            (resultLatLong.length > 0 && ModeLatLong) &&

                            <div className="results-section">
                                <h6>Results for city {resultLatLong[0].data.city.name}</h6>
                                <div className="air-results">
                                    <div id="results-co" className="result-card">
                                        <h2>CO</h2>
                                        <h3>{resultLatLong[0].data.iaqi.co === null ? "" : resultLatLong[0].data.iaqi.co.v}</h3>
                                    </div>
                                    <div id="results-no2" className="result-card">
                                        <h2>NO2</h2>
                                        <h3>{resultLatLong[0].data.iaqi.no2 === null ? "" : resultLatLong[0].data.iaqi.no2.v}</h3>
                                    </div>
                                    <div id="results-o3" className="result-card">
                                        <h2>O3</h2>
                                        <h3>{resultLatLong[0].data.iaqi.o3 === null ? "" : resultLatLong[0].data.iaqi.o3.v}</h3>
                                    </div>
                                    <div id="results-pm10" className="result-card">
                                        <h2>PM10</h2>
                                        <h3>{resultLatLong[0].data.iaqi.pm10 === null ? "" : resultLatLong[0].data.iaqi.pm10.v}</h3>
                                    </div>
                                    <div id="results-pm25" className="result-card">
                                        <h2>PM25</h2>
                                        <h3>{resultLatLong[0].data.iaqi.pm25 === null ? "" : resultLatLong[0].data.iaqi.pm25.v}</h3>
                                    </div>
                                    <div id="results-so2" className="result-card">
                                        <h2>SO2</h2>
                                        <h3>{resultLatLong[0].data.iaqi.so2 === null ? "" : resultLatLong[0].data.iaqi.so2.v}</h3>
                                    </div>
                                </div>
                            </div>
                        }
                                
                                
                                        
                        {
                            (resultCity.length === 0 && resultLatLong.length === 0) &&
                            <h1>Search something to see results !</h1>
                        }
                        
                    
                </div>
            </div>
        </div>
    );
}

export default AirStatistics;