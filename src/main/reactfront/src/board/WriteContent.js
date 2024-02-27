import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import { useSelector } from "react-redux";

function WriteContent() {
    const navigate = useNavigate();
    const [title, setTitle] = useState('');
    const [content, setContent] = useState('');
    const userData = useSelector((state) => state.session.userData);

    const handleSubmit = (event) => {
        event.preventDefault();

        const reg_name = userData ? userData.mem_name : '';
        const currentTime = new Date().toISOString();

        axios.post('/api/freeboard/write', {
            title: title,
            content: content,
            reg_name: reg_name,
            hit: 0,
            w_date: currentTime,
            u_date: currentTime,
        })
            .then(response => {
                console.log('게시글 작성 성공:', response.data);
                navigate('/board');
            })
            .catch(error => {
                console.error('게시글 작성 실패:', error);
            });
    };

    return (
        <div>
            <h2>게시글 작성</h2>
            <form onSubmit={handleSubmit}>
                <div>
                    <label>제목</label>
                    <input
                        type="text"
                        value={title}
                        onChange={(e) => setTitle(e.target.value)}
                        required
                    />
                </div>
                <br />
                <div>
                    <label>내용</label>
                    <textarea
                        cols="30"
                        rows="20"
                        value={content}
                        onChange={(e) => setContent(e.target.value)}
                        required
                    />
                </div>
                <br />
                <button onClick={handleSubmit}>저장</button>
            </form>
        </div>
    );
}

export default WriteContent;