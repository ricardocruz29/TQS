import { useState, useEffect } from 'react';
import './CacheStatistics.css';

import * as ReactBootStrap from 'react-bootstrap';

/* Other components */
import AirQualityService from '../../Services/AirQualityService';

function CacheStatistics() {
    const [cacheStats, setCacheStats] = useState(null);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        const BASE_URL = "http://localhost:8080/airMetricsAPI/";

        async function search(){
            let result = await AirQualityService.getCacheStatistics(BASE_URL);
            setCacheStats(result);
            console.log(result);
        }
        
        search();
        console.log(cacheStats);
    },[])

    useEffect(() => {
        if(cacheStats){
            setLoading(false);
        }
    },[cacheStats])




    return(
        <div className="all-cache">
            <div className="container">
                <h1 className="air-title">Cache Statistics</h1>
                <div className="pre">
                    { loading &&
                        <ReactBootStrap.Spinner animation="border"/>
                    }

                    {!loading &&
                        <div>
                            
                            
                            {cacheStats &&
                                <div className="cache-results">
                                    <div className="result-card-cache">
                                        <h2>Number of Requests</h2>
                                        <h3>{cacheStats.numberOfRequests}</h3>
                                    </div>
                                    <div className="result-card-cache">
                                        <h2>Number of Hits</h2>
                                        <h3>{cacheStats.numberOfHits}</h3>
                                    </div>
                                    <div className="result-card-cache">
                                        <h2>Number of Misses</h2>
                                        <h3>{cacheStats.numberOfMisses}</h3>
                                    </div>
                                    
                                </div>
                            }
                            {!cacheStats &&
                                <h1>No cache statistics</h1>
                            }
                            
                        </div>
                        
                        
                    
                    
                    }
                </div>
                
                
            </div>
        </div>
        
        
    );
}

export default CacheStatistics;