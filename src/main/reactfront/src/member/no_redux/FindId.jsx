import React, { useState } from 'react';
import axios from 'axios';
import {useNavigate} from "react-router-dom";

function FindId() {
    const [memName, setMemName] = useState('');
    const [birth, setBirth] = useState('');
    const [foundId, setFoundId] = useState('');
    const [error, setError] = useState('');

    const navigate = useNavigate();
    const handleFindId = async () => {
        try {
            const response = await axios.get(`/member/searchid?mem_name=${memName}&birth=${birth}`);
            if (response.status === 200) {
                setFoundId(response.data);
                setError('');
            } else {
                setFoundId('');
                setError('이름과 생년월일을 다시 확인해보세요');
            }
        } catch (error) {
            console.error('Error finding member id:', error);
            setFoundId('');
            setError('서버에 연결할 수 없습니다.');
        }
    };

    return (
        <div>
            <h2>아이디 찾기</h2>
            <input type="text" placeholder="이름" value={memName} onChange={(e) => setMemName(e.target.value)}/>
            <input type="text" placeholder="생년월일" value={birth} onChange={(e) => setBirth(e.target.value)}/>
            <button onClick={handleFindId}>조회</button>
            {foundId && (
                <div>
                    <p>찾은 아이디: {foundId}</p>
                    <hr/>
                    <button onClick={() => navigate("/login")}> 로그인 화면 >></button>
                </div>
            )}
            {error && <p>{error}</p>}
        </div>
    );
}

export default FindId;
