import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';

function AddContent() {
    const navigate = useNavigate();
    const [title, setTitle] = useState('');
    const [content, setContent] = useState('');

    const handleSubmit = (event) => {
        event.preventDefault();

        axios.post('/api/freeboard/write', {
            title: title,
            content: content,
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

export default AddContent;