package com.example.test.dao;

import com.example.test.dto.MemberDTO;

public interface MemberDAO {
	
	//회원가입(insert 
	//추상메서드로 선언 (뒤에 throws를 맞춰봐야함.
	public int insertMember(MemberDTO memberDTO)throws Exception; 
	
	// test
	public String getTime() throws Exception;
	
	
	//회원 탈퇴
	public int deleteMember(String mem_id) throws Exception;
	
	// 비밀번호 확인
	public String selectPw(String mem_id) throws Exception;
	
	// 회원정보 조회
	public MemberDTO selectMember(String mem_id) throws Exception;
	
	// 회원정보 수정
	public int updateMember(MemberDTO memberDTO) throws Exception;
	
	
	
}
