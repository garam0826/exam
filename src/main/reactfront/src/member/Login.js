import axios from 'axios';
import React, { useState } from 'react';
import {useNavigate} from "react-router-dom";
function Login() {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    const navigate = useNavigate();

    const handleSubmit = (e) => {
        e.preventDefault();
        const memberInfo = {
            mem_id: username,
            mem_pw: password
        };

        axios.post('/member/login', memberInfo)
            .then(response => {
                console.log(response.data);
                alert('로그인 성공!');
                navigate("/");
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
                    <input type="text" value={username} onChange={(e) => setUsername(e.target.value)}/>
                </label>
                <label>
                    Password:
                    <input type="password" value={password} onChange={(e) => setPassword(e.target.value)}/>
                </label>
                <button type="submit">Login</button>
            </form>
            <hr/>
            <button onClick={() => navigate("/searchid")}> 아이디 찾기 >></button>
        </div>
    );
}

export default Login;