package com.example.test.dao;

import com.example.test.dto.BoardDTO;
import com.example.test.dto.ReplyDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BoardDAOImple implements BoardDAO{
	@Autowired
	private SqlSession sqlSession;
	
	private static String namespace= "com.exam.mappers.BoardMapper";
	
	//글 쓰기
	@Override
	public int writeBoard(BoardDTO boardDTO) throws Exception{
		int result = sqlSession.insert(namespace+".writeBoard",boardDTO);
		System.out.println(boardDTO.getReg_name()+ "게시판 글 쓰기");
		return result;
	}
	
	// 글 갯수
	@Override
	public int countBoard() throws Exception{
		int result = sqlSession.selectOne(namespace+ ".countBoard");
		System.out.println(result+ " : 개 입니다.");
		
		return result;
	}
	
	// 글 조회
	@Override
	public BoardDTO readBoard(int idx) throws Exception{
		BoardDTO boardDTO= sqlSession.selectOne(namespace+".readBoard",idx);
		
		System.out.println(boardDTO.getContent()+ " : 게시판 글 내용 조회");
		return boardDTO;
	}
	
	// 글 수정
	@Override
	public int updateBoard(BoardDTO boardDTO) throws Exception{
		int result = sqlSession.update(namespace+ ".updateBoard", boardDTO);
		System.out.println(boardDTO.getReg_name()+ "님 게시글, " + " : 게시판 글 수정 DAO");
			
		return result;
	}
		
	// 글 삭제
	@Override
	public int deleteBoard(Integer idx) throws Exception{
		int result = sqlSession.delete(namespace+ ".deleteBoard", idx);
		System.out.println(idx+ "번 게시판 글 삭제 DAO ");
			
		return result;
	}
	
	/*
	@Override
	public List<BoardDTO> selectBoardList() throws Exception{
		List<BoardDTO> boardList = sqlSession.selectList(namespace+ ".selectBoardList");
		System.out.println("게시판 글 목록 조회 DAO ");
		
		return boardList;
	}*/
	@Override
    public List<BoardDTO> selectAllBoards() throws Exception {
        return sqlSession.selectList(namespace + ".selectAllBoards");
    }
	
	//글 검색
	public List<BoardDTO> selectBoardContent(String content) throws Exception{
		 return sqlSession.selectList(namespace + ".selectBoardContent", content);
	}
	
	
	@Override
    public int insertReply(ReplyDTO replyDTO) throws Exception {
        return sqlSession.insert(namespace + ".insertReply", replyDTO);
    }
	
	
	@Override
    public ReplyDTO selectReplyById(int rep_idx) throws Exception {
        return sqlSession.selectOne(namespace + ".selectReplyById", rep_idx);
    }
	
    @Override
    public List<ReplyDTO> selectRepliesBySrcIdx(int src_idx) throws Exception {
        return sqlSession.selectList(namespace + ".selectRepliesBySrcIdx", src_idx);
    }

    @Override
    public int updateReply(ReplyDTO replyDTO) throws Exception {
        return sqlSession.update(namespace + ".updateReply", replyDTO);
    }

    @Override
    public int deleteReply(int rep_idx) throws Exception {
        return sqlSession.delete(namespace + ".deleteReply", rep_idx);
    }
    
    @Override
    public void updateReplyRef(ReplyDTO replyDTO) throws Exception {
        sqlSession.update(namespace + ".updateReplyRef", replyDTO);
    }
    
    @Override
    public int selectMaxStepByRef(int ref) {
        Integer maxStep = sqlSession.selectOne(namespace + ".selectMaxStepByRef", ref);
        return maxStep != null ? maxStep : 0;
    }
	
	
	

}
