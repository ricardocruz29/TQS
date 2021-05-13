/* css */
import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';

/* icons */
import * as RiIcons from 'react-icons/ri';
import * as SiIcons from 'react-icons/si';

/* router */
import {Link, BrowserRouter, Route, Switch, withRouter} from 'react-router-dom';

/* other components */
import Home from './Components/Home/Home';
import AirStatistics from './Components/AirStatistics/AirStatistics';
import CacheStatistics from './Components/CacheStatistics/CacheStatistics';

function App() {

  function changeRoute(value_new_active){
    let items = ["home", "air", "about"];
    let active = document.getElementById(value_new_active);

    for (let i = 0; i<items.length; i++){
      if (items[i] === value_new_active){
        active.classList.add("active");
      } else {
        let non_active = document.getElementById(items[i]);
        if (non_active.classList.contains("active")){
          non_active.classList.remove("active");
        }
      }
    }

  }
  return (
    <BrowserRouter>
      <div className="col-md-12 all">
        <nav className="navbar">
          <div className="container">
            <div className="left-side">
              <Link to="/" onClick={() => changeRoute("home")}>
                <div className="navbar-brand"><i className="logo-icon"><RiIcons.RiWindyFill/></i><h2>AirQuality</h2></div>
              </Link>
            </div>
            <div className="center-side">
              <ul className="items">
                <li id="home" className="active" onClick={() => changeRoute("home")}>
                  <Link to="/">
                    <h6>Home</h6>
                  </Link>
                </li>
                <li id="air" onClick={() => changeRoute("air")}>
                  <Link to="/air">
                    <h6>Air statistics</h6>
                  </Link>
                </li>
                <li id="about" onClick={() => changeRoute("about")}>
                  <Link to="/cache">
                    <h6>Cache</h6>
                  </Link>
                </li>
              </ul>
            </div>
          </div>
        </nav>

        <div className="content">
          <Switch>
              <Route exact path='/' component={withRouter(Home)} />
              <Route path='/air/' component={withRouter(AirStatistics)} />
              <Route exact path='/cache' component={withRouter(CacheStatistics)} />
          </Switch>
        </div>

        <footer className="footer">
          <h6>2021 - Rights reserved to Ricardo Cruz</h6>
          <a href="https://www.linkedin.com/in/ricardo-cruz-139599208/" target="_blank" rel="noreferrer"><i><SiIcons.SiLinkedin/></i></a>
          <a href="https://github.com/ricardocruz29" target="_blank" rel="noreferrer"><i><SiIcons.SiGithub/></i></a>
        </footer>
      </div>
    </BrowserRouter>
    
  );
}

export default App;
