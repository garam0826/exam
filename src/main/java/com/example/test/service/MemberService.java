package com.example.test.service;

import com.example.test.dto.MemberDTO;

//추상메서드, 실제구현은 멤버서비스
public interface MemberService {
	
	//회원가입(insert 
	public int insertMember(MemberDTO memberDTO)throws Exception; 
	
	
	// 회원 탈퇴
	public int deleteMember(String mem_id) throws Exception;
	
	// 비밀번호 확인
	public boolean checkPW(String mem_id, String mem_pw) throws Exception;
	
	// 회원정보 조회
	public MemberDTO selectMember(String mem_id) throws Exception;
	
	// 회원정보 수정
	public int updateMember(MemberDTO memberDTO) throws Exception;
	
	/*//로그인
	public boolean login(String mem_id, String mem_pw) throws Exception;*/

	//별로의 파라미터로 안넘기고 MemberDTO 객체 하나로 전달, 위처럼 하려면 controller를 수정하면되긴함.
	public MemberDTO login(MemberDTO memberDTO) throws Exception;

	public MemberDTO findMemberIdByNameAndBirth(String mem_name, String birth) throws Exception;

}