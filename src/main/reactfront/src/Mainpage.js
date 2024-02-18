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
            <hr/>
            <hr/>
            [[ 게시판으로 이동 >> ]]
            <button onClick={() => navigate("/board")}>게시판 >></button>
            <hr/>
            [[ 회원정보 테스트 ]]
            <button onClick={() => navigate("/myinfo")}> my page >> </button>
            <hr/>
            [[ 회원 탈퇴 (only 확인) ]]
            <button onClick={() => navigate("/resign")}> my page >> </button>
            <hr/>
            [[ animal api 확인 ]]
            <button onClick={() => navigate("/animal")}> my page >> </button>
        </div>
    );
}

export default Mainpage;