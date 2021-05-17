import React from 'react';
import './App.css';
import { Switch, Route, Link } from 'react-router-dom';
import Home from './pages/Home';
import PostListings from './pages/PostListings';
import ViewListings from './pages/ViewListings';
// import Login from './pages/Login';
// import Signup from './pages/Signup';
// import UserPage from './pages/UserPage';

// {} means prop values are being passed in from parent element
const App = ({ ws }) => {
  // todo, add more pages!
  return (
    <div>
      <nav id="navbar">
        <Link class="link" to="/"> Home </Link>
        <Link class="link" to="/postListings"> Post </Link>
        <Link class="link" to="/viewListings"> View </Link>
        
      </nav>
      <Switch>
        <Route path="/viewListings">
          <ViewListings ws={ws} />
        </Route>
        <Route path="/postListings">
          <PostListings ws={ws} />
        </Route>
        <Route path="/">
          <Home />
        </Route>
      </Switch>
    </div>
  );
};

export default App;