import React, { useState } from 'react';
import axios from 'axios';
import {useNavigate} from "react-router-dom";

function MemberResign() {
    const [memId, setMemId] = useState('');
    const [message, setMessage] = useState('');
    const [error, setError] = useState('');

    const navigate = useNavigate();
    const handleInputChange = (event) => {
        setMemId(event.target.value);
    };

    const handleResign = async () => {
        try {
            const response = await axios.post('/member/mypage/resign', { mem_id: memId });
            if (response.status === 200) {
                setMessage('회원 탈퇴가 성공적으로 처리되었습니다.');
                // 추가적인 작업 수행 (예: 로그아웃)
            } else {
                setError('회원 탈퇴에 실패했습니다.');
            }
        } catch (error) {
            setError('회원 탈퇴에 실패했습니다.');
        }
    };

    return (
        <div>
            <h2>회원 탈퇴</h2>
            <form>
                <label>
                    사용자 ID:
                    <input type="text" value={memId} onChange={handleInputChange}/>
                </label>
                <button type="button" onClick={handleResign}>회원 탈퇴</button>
            </form>

            {message && (
                <div>
                    <p>{message}</p>
                    <hr/>
                    <button onClick={() => navigate("/")}> 메인 화면 >></button>
                </div>
            )}
            {error && <p>{error}</p>}
        </div>
    );
}

export default MemberResign;
