package com.example.test.service;

import com.example.test.dto.MemberDTO;
import com.example.test.dao.MemberDAO;


import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;



//달아줘야함, 부가적인 기능을 넣을꺼면 길어질 것임, 이 result를 계쏙 front로 넘겨줄 것임. 
//어디서 오류났는지 알 고싶으면 sysout으로, 그냥 test용으로만 쓰기. 현업에서는 log로 확인을 할 것임. 
@Service
public class MemberServiceImple implements MemberService{
	
	//의존성주입(DAO)
	@Autowired
	private MemberDAO memberDAO;
	
	//회원가입 
	public int insertMember(MemberDTO memberDTO)throws Exception{
		int result = memberDAO.insertMember(memberDTO);
		
		System.out.println("회원가입완료!");
		
		return result;
		
	}
	
	// 회원 탈퇴
	@Override
	public int deleteMember(String mem_id) throws Exception{
		int result = memberDAO.deleteMember(mem_id);
			
		if(result > 0) {
			System.out.println(mem_id+ "님 : 회원탈퇴 성공");
		}
			
		return result;
	}
	
	// 비밀번호 확인
	@Override
	public boolean checkPW(String mem_id, String mem_pw) throws Exception{
		boolean result = false;
		String db_pw = memberDAO.selectPw(mem_id);
			
		if(mem_pw.equals(db_pw)) {
			System.out.println("비밀번호 일치");
			result = true;
		}else {
			System.out.println("비밀번호 불일치");
			result = false;
		}
			
		System.out.println(mem_id+"님 : 비밀번호 확인 성공");
			
		return result;
	}
	
	
	// 회원정보 조회
	@Override
	public MemberDTO selectMember(String mem_id) throws Exception{
		MemberDTO memberDTO = memberDAO.selectMember(mem_id);
		System.out.println(mem_id+" 님: 회원정보 조회 성공");
		
		return memberDTO;
	}
	
	// 회원정보 수정
	public int updateMember(MemberDTO memberDTO) throws Exception{
		int result = memberDAO.updateMember(memberDTO);
		System.out.println(memberDTO.getMem_id()+ " 님: 회원정보 수정 성공");
		
		return result;
	}
	
	/*@Override
	public boolean login(String mem_id, String mem_pw) throws Exception{
		boolean result = false;
		
		String db_pw= memberDAO.selectPw(mem_id);
		
		if(db_pw.equals(mem_pw)) {
			return true;
		}else {
			return false;
		}
		
		
	}*/

	@Override
	public MemberDTO login(MemberDTO memberDTO) throws Exception {

		String mem_id = memberDTO.getMem_id();
		String mem_pw = memberDTO.getMem_pw();

		// DB에서 해당 mem_id의 비밀번호 조회
		String db_pw = memberDAO.selectPw(mem_id);


		if (db_pw != null && db_pw.equals(mem_pw)) {

			return memberDTO;
		} else {

			return null;
		}
	}

	// 아이디 찾기
	public MemberDTO findMemberIdByNameAndBirth(String mem_name, String birth) throws Exception {
		return memberDAO.selectMemberByNameAndBirth(mem_name, birth);
	}
	

}
