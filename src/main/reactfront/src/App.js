import {RouterProvider} from "react-router-dom";
import root from './root';

function App(){
    return(
        <RouterProvider router={root}/>
    );
}

export  default App;
/*import logo from './logo.svg';
import './App.css';

import React, { useState } from 'react';
import axios from 'axios';

function Signup() {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [realname, setRealname] = useState('');
    const [userbirth, setUserbirth] = useState('');
    const [userphone, setUserphone] = useState('');
    const [useremail, setUseremail] = useState('');

    const handleSubmit = (e) => {
        e.preventDefault();
        const memberInfo = {
            mem_id: username,
            mem_pw: password,
            mem_name: realname,
            birth : userbirth,
            phone : userphone,
            email : useremail


        };

        axios.post('/api/member/signup', memberInfo)
            .then(response => {
                alert('회원가입 성공!');

            })
            .catch(error => {
                console.error("Signup failed: ", error);
                alert('회원가입 실패');

            });
    };

    return (
        <div>
            <h2>Signup</h2>
            <form onSubmit={handleSubmit}>
                <label>
                    Username:
                    <input type="text" value={username} onChange={(e) => setUsername(e.target.value)} />
                </label>
                <label>
                    Password:
                    <input type="password" value={password} onChange={(e) => setPassword(e.target.value)} />
                </label>

                <label>
                    name:
                    <input type="text" value={realname} onChange={(e) => setRealname(e.target.value)} />
                </label>
                <label>
                    birth:
                    <input type="text" value={userbirth} onChange={(e) => setUserbirth(e.target.value)} />
                </label>
                <label>
                    phone:
                    <input type="text" value={userphone} onChange={(e) => setUserphone(e.target.value)} />
                </label>
                <label>
                    email:
                    <input type="text" value={useremail} onChange={(e) => setUseremail(e.target.value)} />
                </label>
                <button type="submit">Signup</button>
            </form>
        </div>
    );
}

export default Signup;*/



/*import React, { useState } from 'react';
import axios from 'axios';

function Login() {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    const handleSubmit = (e) => {
        e.preventDefault();
        const memberInfo = {
            mem_id: username,
            mem_pw: password
        };

        axios.post('/api/member/login', memberInfo)
            .then(response => {
                console.log(response.data);
                alert('로그인 성공!');

            })
            .catch(error => {
                console.error("Login failed: ", error);
                alert('로그인 실패!');

            });
    };

    return (
        <div>
            <h2>Login</h2>
            <form onSubmit={handleSubmit}>
                <label>
                    Username:
                    <input type="text" value={username} onChange={(e) => setUsername(e.target.value)} />
                </label>
                <label>
                    Password:
                    <input type="password" value={password} onChange={(e) => setPassword(e.target.value)} />
                </label>
                <button type="submit">Login</button>
            </form>
        </div>
    );
}

export default Login;*/

/* 게시판 리스트 컨트롤러
import React, { useEffect, useState } from 'react';
import axios from 'axios';

function BoardList() {
    const [boards, setBoards] = useState([]);

    useEffect(() => {
        // API 호출
        axios.get('/api/freeboard/list')
            .then(response => {

                setBoards(response.data);
            })
            .catch(error => {
                console.error("There was an error retrieving the board list: ", error);
            });
    }, []);

    return (
        <div>
            <h2>게시글 목록</h2>
            <ul>
                {boards.map(board => (
                    <li key={board.idx}>{board.title} - {board.reg_name}</li>
                ))}
            </ul>
        </div>
    );
}

export default BoardList;*/



/*그냥 정보 넘겨주기
import React, {useState, useEffect} from 'react';

function App() {
  const [message, setMessage]=useState([]);
  useEffect(()=>{
    fetch("/hello")
        .then((res)=>{
          return res.json();
        })
        .then((data)=>{
          setMessage(data);
        });
  },[]);
  return (
      <div className="App">
        <header className="App-header">

          <ul>
            {message.map((v,idx)=><li key={`${idx}-${v}`}>{v}</li>)}
          </ul>
        </header>
      </div>
  );
}*/

/* 공공api 이용
import React, { useState, useEffect } from 'react';
import './App.css';

function App() {
    const [predictions, setPredictions] = useState([]);

    useEffect(() => {
        fetch("/api/predictions")
            .then(res => res.json())
            .then(data => {
                setPredictions(data);
            });
    }, []);

    return (
        <div className="App">
            <header className="App-header">
                {predictions.map((pred, idx) => (
                    <div key={idx}>
                        <p>Image URL: {pred.imageUrl}</p>
                        <ul>
                            {pred.predictions.map((p, index) => (
                                <li key={index}>{p.className}: {p.probability}</li>
                            ))}
                        </ul>
                    </div>
                ))}
            </header>
        </div>
    );
}
export default App;*/
