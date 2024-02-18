import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useNavigate, useParams, Link } from 'react-router-dom';

function ReadContent() {
    const navigate = useNavigate();
    const { idx } = useParams();
    const [board, setBoard] = useState({});

    useEffect(() => {
        // 게시글 조회 API 호출
        axios.get(`/api/freeboard/${idx}`)
            .then(response => {
                setBoard(response.data);
            })
            .catch(error => {
                console.error("There was an error retrieving the board: ", error);
            });
    }, [idx]);

    return (
        <div>
            <h2>{board.title}</h2>
            <div>
                <span>내용: {board.content}</span>
            </div>
            <br />
            <Link to={`/board/modify/${idx}`}>수정</Link>
            <button onClick={() =>
                navigate("/board")}>
                목록
            </button>
        </div>
    );
}

export default ReadContent;
