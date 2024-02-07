import React from 'react';
import { Link } from "react-router-dom"

import './App.css';

const Mainpage = () => {
    return (
        /*<h1>Main page</h1>
        <div>
            <Link to={'/login'}>Login</Link>
        </div>*/
        <div>
            <Link to={'/signup'}>SignUp</Link>
        </div>
    );
}

export  default  Mainpage;