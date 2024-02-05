package com.example.test.dao;

import com.example.test.dto.MemberDTO;

import org.apache.ibatis.session.SqlSession;

//spring관련 annotation , repository, autoweired(의존성 주입)
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;


@Repository
public class MemberDAOImple implements MemberDAO{
	// SQLSession
	@Autowired
	private SqlSession sqlSession;
	
	// Mapper namespace 지정 (mapper 파일에서 그대로 가져와서 복붙)
	private static String namespace = "com.toyproject.mappers.MemberMapper";
	
	//회원가입
	@Override
	public int insertMember(MemberDTO memberDTO) throws Exception{
		//함수호출하듯이 집어넣으면 됨. (mapper파일에서 복사해오기)
		int result = sqlSession.insert(namespace + ".insertMember", memberDTO);
		
		return result;
	}
	
	// test
	@Override
	public String getTime() throws Exception{
		//단순하게 1개만 가져오는 selectOne 메서드, 컨트롤 스체이스에서 원하는거 알아서 찾기 selectList같이 찾기, String statement에 쿼리..
		String time = sqlSession.selectOne(namespace+ ".getTime");
		System.out.println(time);
		
		return time;
	}
	
	// 회원 탈퇴
	@Override
	public int deleteMember(String mem_id) throws Exception{
		int result = sqlSession.delete(namespace+ ".deleteMember", mem_id);
		System.out.println(mem_id+ "님 : 회원탈퇴 성공");
		
		return result;
	}
	
	// 비밀번호 확인
	@Override
	public String selectPw(String mem_id) throws Exception{
		String mem_pw = sqlSession.selectOne(namespace+ ".selectPw", mem_id);
		System.out.println(mem_id+ " : 비밀번호 확인 성공");
		
		return mem_pw;
	}
	
	// 회원정보 조회
	@Override
	public MemberDTO selectMember(String mem_id) throws Exception{
		MemberDTO memberDTO = sqlSession.selectOne(namespace+ ".selectMember", mem_id);
		
		System.out.println(mem_id+ "님 : 회원정보 조회 성공");
		
		return memberDTO;
	}
	
	// 회원정보 수정
	public int updateMember(MemberDTO memberDTO) throws Exception{
		int result = sqlSession.update(namespace+ ".updateMember", memberDTO);
		System.out.println(memberDTO.getMem_id()+ " : 회원정보 수정 성공");
		
		return result;
	}
	
}
