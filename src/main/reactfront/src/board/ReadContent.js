import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useNavigate, useParams, Link } from 'react-router-dom';

function ReadContent() {
    const navigate = useNavigate();
    const { idx } = useParams();
    const [board, setBoard] = useState({});
    const [comments, setComments] = useState([]);
    const [newComment, setNewComment] = useState('');

    useEffect(() => {
        // 게시글 조회 API 호출
        axios.get(`/api/freeboard/${idx}`)
            .then(response => {
                setBoard(response.data);
            })
            .catch(error => {
                console.error("There was an error retrieving the board: ", error);
            });
        axios.get(`api/freeboard/comments/${idx}`)
            .then(response => {
                setComments(response.data);
            })
            .catch(error => {
                console.error("There was an error retrieving the board: ", error);
            });
    }, [idx]);

    const handleComment = async () => {
        await axios.post(`/api/freeboard/comments/${idx}`, {
            content: newComment,
        })
            .then(response => {
                console.log('댓글 작성 성공:', response.data);
                setComments(prevComments => [...prevComments, response.data]);
                setNewComment('');
            })
            .catch(error => {
                console.error('댓글 작성 실패:', error);
            });
    };

    const handleDelete = async () => {
        await axios.delete(`/api/freeboard/${idx}`)
            .then(response => {
                console.log('게시글 삭제 성공:', response.data);
                navigate('/delete');
            })
            .catch(error => {
                console.error('게시글 삭제 실패:', error);
            });
    };

    return (
        <div>
            <h2>{board.title}</h2>
            <div>
                <span>작성자: {board.reg_name}</span>
                <br />
                <span>조회수: {board.hit}</span>
                <br />
                <span>작성 시간: {board.w_date}</span>
                <br />
                <span>수정 시간: {board.u_date}</span>
            </div>
            <div>
                <span>내용: {board.content}</span>
            </div>
            <br />
            <Link to={`/edit/${idx}`}>
                <button>수정</button>
            </Link>
            <button onClick={() =>
                navigate("/board")}>
                목록
            </button>
            <button onClick={handleDelete}>삭제</button>
            <h3>댓글</h3>
            <ul>
                {comments.map(comment => (
                    <li key={comment.id}>
                        {comment.content} - 작성자: {comment.reg_name} - 작성 시간: {comment.w_date}
                    </li>
                ))}
            </ul>
            <div>
                <textarea
                    value={newComment}
                    onChange={(e) => setNewComment(e.target.value)}
                    placeholder={"댓글을 작성하세요"}
                ></textarea>
                <button onClick={handleComment}>댓글 작성</button>
            </div>
        </div>
    );
}

export default ReadContent;
