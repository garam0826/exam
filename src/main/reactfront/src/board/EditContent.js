import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useNavigate, useParams } from 'react-router-dom';

function EditContent() {
    const navigate = useNavigate();
    const { idx } = useParams();
    const [board, setBoard] = useState({});

    useEffect(() => {
        axios.get(`/api/freeboard/${idx}`)
            .then(response => {
                setBoard(response.data);
            })
            .catch(error => {
                console.error("There was an error retrieving the board: ", error);
            });
    }, [idx]);

    const handleEdit = async () => {
        await axios.put(`/api/freeboard/${idx}`, board)
            .then(response => {
                console.log('게시글 수정 성공:', response.data);
                navigate('/board');
            })
            .catch(error => {
                console.error('게시글 수정 실패:', error);
            });
    };

    return (
        <div>
            <h2>게시글 수정</h2>
            <div>
                <span>제목</span>
                <input
                    type="text"
                    value={board.title || ''}
                    onChange={(e) => setBoard({ ...board, title: e.target.value })}
                    required
                />
            </div>
            <br />
            <div>
                <span>내용</span>
                <textarea
                    name="content"
                    cols="30"
                    rows="20"
                    value={board.content || ''}
                    onChange={(e) => setBoard({ ...board, content: e.target.value })}
                ></textarea>
            </div>
            <br />
            <div>
                <button onClick={handleEdit}>수정 완료</button>
                <button onClick={() => navigate('/board')}>취소</button>
            </div>
        </div>
    );
}

export default EditContent;