import React, { useState } from 'react';
import axios from 'axios';
import {useNavigate} from "react-router-dom";

function Signup() {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [name, setName] = useState('');
    const [birth, setBirth] = useState('');
    const [phone, setPhone] = useState('');
    const [email, setEmail] = useState("");

    const navigate = useNavigate();

    const handleSubmit = (e) => {
        e.preventDefault();
        const memberInfo = {
            mem_id: username,
            mem_pw: password,
            mem_name: name,
            birth : birth,
            phone : phone,
            email : email


        };

        axios.post('/member/signup', memberInfo)
            .then(response => {
                alert('회원가입 성공!');
                navigate("/login");
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
                    <input type="text" value={name} onChange={(e) => setName(e.target.value)} />
                </label>
                <label>
                    birth:
                    <input type="text" value={birth} onChange={(e) => setBirth(e.target.value)} />
                </label>
                <label>
                    phone:
                    <input type="text" value={phone} onChange={(e) => setPhone(e.target.value)} />
                </label>
                <label>
                    email:
                    <input type="text" value={email} onChange={(e) => setEmail(e.target.value)} />
                </label>
                <button type="submit">Signup</button>
            </form>
        </div>
    );
}

export default Signup;