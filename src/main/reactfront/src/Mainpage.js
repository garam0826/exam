import React from 'react';
import { Link } from "react-router-dom"
import {useNavigate} from "react-router-dom";

import './App.css';

const Mainpage = () => {
    const navigate = useNavigate();
    return (
        //<h1>Main page</h1>
        <div>
            [ Login ]
            <button onClick={() => navigate("/login")}>로그인</button>
            <hr/>
            [ SignUp ]
            <button onClick={() => navigate("/signup")}>회원가입</button>
        </div>


        /*<div>
            <Link to={'/login'}>Login</Link>
        </div>*!/
        <div>
            <Link to={'/signup'}>SignUp</Link>
        </div>*/
    );
}

export  default  Mainpage;