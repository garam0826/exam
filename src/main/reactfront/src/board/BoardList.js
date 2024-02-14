//게시판 리스트 컨트롤러
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

export default BoardList;