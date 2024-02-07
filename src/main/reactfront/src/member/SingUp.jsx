import './App.css';
import React, { useState } from 'react';
import axios from 'axios';

function Signup() {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    const handleSubmit = (e) => {
        e.preventDefault();
        const memberInfo = {
            mem_id: username,
            mem_pw: password
        };

        axios.post('/api/member/signup', memberInfo)
            .then(response => {
                alert('회원가입 성공!');
                // 회원가입 성공 후 로직, 예: 로그인 페이지로 리디렉션
            })
            .catch(error => {
                console.error("Signup failed: ", error);
                alert('회원가입 실패');
                // 회원가입 실패 처리, 예: 에러 메시지 표시
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
                <button type="submit">Signup</button>
            </form>
        </div>
    );
}

export default Signup;