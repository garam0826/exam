import React, { useState } from 'react';
import axios from 'axios';
import {useNavigate} from "react-router-dom";

function MemberInfo() {
    const [memId, setMemId] = useState('');
    const [memberInfo, setMemberInfo] = useState(null);
    const [error, setError] = useState(null);
    const [newMemberInfo, setNewMemberInfo] = useState({
        mem_id: '',
        mem_pw: '',
        mem_name: '',
        birth: '',
        phone: '',
        email: ''
    });
    const [updateMessage, setUpdateMessage] = useState('');
    const [message, setMessage] = useState('');

    const navigate = useNavigate();

    // 입력받은 id 처리
    const handleIdChange = (event) => {
        setMemId(event.target.value);
    };

    // 입력받은 수정 정보 처리
    const handleInputChange = (e) => {
        const { name, value } = e.target;
        // Update the new member info state based on input changes
        setNewMemberInfo(prevState => ({
            ...prevState,
            [name]: value
        }));
    };


    // api - 회원 정보 가져오기
    const handleSubmit = async (event) => {
        event.preventDefault();

        try {
            const response = await axios.get(`/member/mypage/myinfo/${memId}`);
            setMemberInfo(response.data);
            setNewMemberInfo(response.data);
            setError(null);
        } catch (error) {
            setError('사용자 정보를 가져오는 중에 오류가 발생했습니다.');
            setMemberInfo(null);
        }
    };

    // api - 회원 정보 수정
    const handleUpdateMember = async () => {
        try {
            const response = await axios.put('/member/mypage/myedit', newMemberInfo);
            setUpdateMessage(response.data);
            alert('회원정보 수정 성공!');
        } catch (error) {
            console.error('Error updating member info:', error);
            alert(error);
            setUpdateMessage('회원정보 수정 실패');
        }
    };

    // api - 회원 탈퇴
    const handleResign = async () => {
        try {
            const response = await axios.post('/member/mypage/resign', { mem_id: memId });
            if (response.status === 200) {
                alert('회원 탈퇴 성공!');
                setMemberInfo(null);
                setNewMemberInfo({
                    mem_id: '',
                    mem_pw: '',
                    mem_name: '',
                    birth: '',
                    phone: '',
                    email: ''
                });
            } else {
                setMessage('회원 탈퇴에 실패했습니다.');
            }
        } catch (error) {
            setMessage('회원 탈퇴에 실패했습니다.');
        }
    };

    return (
        <div>
            <form onSubmit={handleSubmit}>
                <label>
                    사용자 ID:
                    <input type="text" value={memId} onChange={handleIdChange}/>
                </label>
                <button type="submit">정보 가져오기</button>
            </form>

            {error && <p>{error}</p>}

            {memberInfo && (
                <div>
                    <h2>사용자 정보</h2>
                    <p>ID: {memberInfo.mem_id}</p>
                    <p>PW: {memberInfo.mem_pw}</p>
                    <p>Name: {memberInfo.mem_name}</p>
                    <p>Birth: {memberInfo.birth}</p>
                    <p>Phone: {memberInfo.phone}</p>
                    <p>Email: {memberInfo.email}</p>

                    <form>
                        <h2>회원 정보 수정</h2>
                        <label>
                            PW:
                            <input type="text" placeholder="새 비번" name="mem_pw" value={newMemberInfo.mem_pw}
                                   onChange={handleInputChange}/>
                        </label>
                        <label>
                            Name:
                            <input type="text" placeholder="새 이름" name="mem_name" value={newMemberInfo.mem_name}
                                   onChange={handleInputChange}/>
                        </label>
                        <label>
                            Birth:
                            <input type="text" placeholder="새 생일" name="birth" value={newMemberInfo.birth}
                                   onChange={handleInputChange}/>
                        </label>
                        <label>
                            Phone:
                            <input type="text" placeholder="새 핸드폰 번호" name="phone" value={newMemberInfo.phone}
                                   onChange={handleInputChange}/>
                        </label>
                        <label>
                            Email:
                            <input type="text" placeholder="새 이메일" name="email" value={newMemberInfo.email}
                                   onChange={handleInputChange}/>
                        </label>

                        <button onClick={handleUpdateMember}>수정</button>
                        {updateMessage && <p>{updateMessage}</p>}
                    </form>
                    <hr/>
                    <form>
                        <h2>회원 탈퇴</h2>
                        <button type="button" onClick={handleResign}>회원 탈퇴</button>
                        {message && <p>{message}</p>}
                        {error && <p>{error}</p>}
                    </form>
                    <hr/>
                    <button type="button" onClick={(e) => navigate("/")}>메인화면으로</button>
                </div>
            )}
        </div>
    );
}

export default MemberInfo;
