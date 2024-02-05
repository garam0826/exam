package com.example.test.service;

import com.example.test.dto.BoardDTO;
import com.example.test.dto.ReplyDTO;

import java.util.List;

public interface BoardService {
	
	//글 쓰기
	public int writeBoard(BoardDTO boardDTO) throws Exception;
	
	// 전체 글 갯수 계산
	public int countBoard() throws Exception;
		
	// 글 보기
	public BoardDTO readBoard(Integer idx) throws Exception;
	
	// 글 수정
	public int updateBoard(BoardDTO boardDTO) throws Exception;
		
	// 글 삭제
	public int deleteBoard(Integer idx) throws Exception;
	
	//전체 글 목록 조회
	public List<BoardDTO> selectAllBoards() throws Exception;
	
	//글내용 검색
	public List<BoardDTO> selectBoardContent(String content) throws Exception;
	
	
	public int insertReply(ReplyDTO replyDTO) throws Exception;
	
	ReplyDTO selectReplyById(int rep_idx) throws Exception;
	public List<ReplyDTO> selectRepliesBySrcIdx(int src_idx) throws Exception;
	
	public int updateReply(ReplyDTO replyDTO) throws Exception;
	
	public int deleteReply(int id) throws Exception;
	
	
	public void updateReplyRef(ReplyDTO replyDTO) throws Exception;

}
