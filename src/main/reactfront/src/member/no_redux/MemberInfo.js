import React, { useState } from 'react';
import axios from 'axios';

function MemberInfo() {
    const [memId, setMemId] = useState('');
    const [memberInfo, setMemberInfo] = useState(null);
    const [newMemberInfo, setNewMemberInfo] = useState({
        mem_id: '',
        mem_pw: '',
        mem_name: '',
        birth: '',
        phone: '',
        email: ''
    });
    const [updateMessage, setUpdateMessage] = useState('');

    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null)

    const handleIdChange = (event) => {
        setMemId(event.target.value);
    };
    const handleGetMemberInfo = async () => {
        //alert(memId); //memId 입력 테스트
        try {
            const response = await axios.get(`/member/mypage/myinfo/${memId}`);
            if (response.data) {
                setMemberInfo(response.data);
                setNewMemberInfo(response.data);
            } else {
                setError('회원 정보를 찾을 수 없습니다.');
            }
        } catch (error) {
            alert(error);
            console.error('Error fetching member info:', error);
            setError('회원 정보를 가져오는 중 오류가 발생했습니다.');
            setMemberInfo(null);
        }
    };

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

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        // Update the new member info state based on input changes
        setNewMemberInfo(prevState => ({
            ...prevState,
            [name]: value
        }));
    };

    return (
        <div>
            <h2>회원 정보 조회</h2>
            <form onSubmit={handleGetMemberInfo}>
                <input type="text" placeholder="회원 아이디" value={memId} onChange={handleIdChange}/>
                <button type="submit">조회</button>
            </form>

            {memberInfo && (
                <div>
                    <li>ID: {memberInfo.mem_id}</li>
                    <li>Name: {memberInfo.mem_name}</li>
                    <li>Birth: {memberInfo.birth}</li>
                    <li>Phone: {memberInfo.phone}</li>
                    <li>Email: {memberInfo.email}</li>
                    {/* Display other member info fields as needed */}
                </div>
            )}

            <h2>회원 정보 수정</h2>
            <input type="text" placeholder="새 비번" name="mem_pw" value={newMemberInfo.mem_pw}
                   onChange={handleInputChange}/>
            <input type="text" placeholder="새 이름" name="mem_name" value={newMemberInfo.mem_name}
                   onChange={handleInputChange}/>
            <input type="text" placeholder="새 생일" name="birth" value={newMemberInfo.birth}
                   onChange={handleInputChange}/>
            <input type="text" placeholder="새 핸드폰 번호" name="phone" value={newMemberInfo.phone}
                   onChange={handleInputChange}/>
            <input type="text" placeholder="새 이메일" name="email" value={newMemberInfo.email}
                   onChange={handleInputChange}/>
            <button onClick={handleUpdateMember}>수정</button>
            {updateMessage && <p>{updateMessage}</p>}
        </div>
    );
}

export default MemberInfo;
