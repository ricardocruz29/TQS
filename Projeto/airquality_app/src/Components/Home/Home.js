import './Home.css';

import {Link} from 'react-router-dom';

function Home() {
    return(
        <div className="col-md-12 home">
            <div className="container">
                <h1 className="home-title">Welcome to <span>AirQuality</span></h1>
                <div className="home-content">
                    <div className="ti-div img">
                        <img src={process.env.PUBLIC_URL + "/images/air.png"} className="home-image" alt="home"></img>
                    </div>
                    <div className="ti-div text">
                        <p className="text">Hello there! . In this app you can see information about air quality and we have data about almost every city in the world. To search this, you have 2 modes. You can search by city name, or simply typing it's latitude and longitude. Hope you enjoy !!</p>
                    </div>
                </div>
                <div className="button-section">
                    <Link to="/air">
                        <div className="button">
                            <h3>Check Air Statistics</h3>
                        </div>
                    </Link>
                    
                </div>
                
            </div>
        </div>
    );
}

export default Home;