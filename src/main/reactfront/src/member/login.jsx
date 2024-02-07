import './App.css';
import React, { useState } from 'react';
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

export default Login;