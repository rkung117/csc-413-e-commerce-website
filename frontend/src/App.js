import React from 'react';
import './App.css';
import { Switch, Route, Link } from 'react-router-dom';
import Home from './pages/Home';
import Login from './pages/Login';
import Post from './pages/Post';
// import Signup from './pages/Signup';
// import UserPage from './pages/UserPage';

const App = ({ws}) => {
  // todo, add more pages!
  return (
    <div>
      <nav>
        <Link to="/"> Home </Link>
        <Link to="/login"> Login </Link>
        <Link to="/post"> Post </Link>
      </nav>
      <Switch>
        <Route path="/post">
          <Post ws={ws}/>
        </Route>
        <Route path="/login">
          <Login ws={ws}/>
        </Route>
        <Route path="/">
          <Home />
        </Route>
      </Switch>
    </div>
  );
};

export default App;