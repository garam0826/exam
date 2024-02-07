// RegisterForm.js

import axios from 'axios';
import React, { useState } from 'react';

const SingUp = () => {
    const [formData, setFormData] = useState({
        mem_id: '',
        mem_pw: '',
        mem_name: '',
        birth: '',
        phone: '',
        email: ''
    });

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData({ ...formData, [name]: value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await axios.post('http://localhost:8080/api/member/Register_MyInfo.do', formData);
            const { data } = response;
            if (data.result > 0) {
                alert('회원가입이 완료되었습니다.');
                // 회원가입이 성공하면 홈페이지로 이동 또는 로그인 페이지로 이동 등의 처리
            } else {
                alert('회원가입에 실패했습니다.');
            }
        } catch (error) {
            console.error('회원가입 중 오류 발생:', error);
        }
    };

    return (
        <form onSubmit={handleSubmit}>
            <input type="text" name="mem_id" placeholder="아이디" value={formData.mem_id} onChange={handleChange} />
            <input type="password" name="mem_pw" placeholder="비밀번호" value={formData.mem_pw} onChange={handleChange} />
            <input type="text" name="mem_name" placeholder="이름" value={formData.mem_name} onChange={handleChange} />
            <input type="text" name="birth" placeholder="생년월일" value={formData.birth} onChange={handleChange} />
            <input type="text" name="phone" placeholder="전화번호" value={formData.phone} onChange={handleChange} />
            <input type="email" name="email" placeholder="이메일" value={formData.email} onChange={handleChange} />
            <button type="submit">회원가입</button>
        </form>
    );
};

export default SingUp;
