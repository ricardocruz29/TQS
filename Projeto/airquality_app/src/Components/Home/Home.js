import './Home.css';

function Home() {
    return(
        <div className="col-md-12 home">
            <div className="container">
                <h1 className="home-title">Welcome to <span>AirQuality</span></h1>
                <div className="home-content">
                    <div className="ti-div img">
                        <img src={process.env.PUBLIC_URL + "/images/air.png"} className="home-image"></img>
                    </div>
                    <div className="ti-div text">
                        <p className="text">Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.</p>
                    </div>
                </div>
                <div className="button-section">
                    <div className="button">
                        <h3>Check Air Statistics</h3>
                    </div>
                </div>
                
            </div>
        </div>
    );
}

export default Home;