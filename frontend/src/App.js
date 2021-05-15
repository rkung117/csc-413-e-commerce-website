import React from 'react';
import './App.css';
import { Switch, Route, Link } from 'react-router-dom';
import Home from './pages/Home';
import PostListings from './pages/PostListings';
import ViewListings from './pages/ViewListings';
// import Login from './pages/Login';
// import Signup from './pages/Signup';
// import UserPage from './pages/UserPage';

const App = () => {
  // todo, add more pages!
  return (
    <div>
      <nav>
        <Link to="/"> Home </Link>
        <Link to="/viewListings"> View </Link>
        <Link to="/postListings"> Post </Link>
      </nav>
      <Switch>
        <Route path="/viewListings">
          <ViewListings />
        </Route>
        <Route path="/postListings">
          <PostListings />
        </Route>
        <Route path="/">
          <Home />
        </Route>
      </Switch>
    </div>
  );
};

export default App;