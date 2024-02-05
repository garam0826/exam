package com.example.test.service;

import com.example.test.dao.BoardDAO;
import com.example.test.dto.BoardDTO;
import com.example.test.dto.ReplyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BoardServiceImple implements BoardService{
	

	
	private static String namespace= "com.toyproject.mappers.BoardMapper";
	
	@Autowired
	private BoardDAO boardDAO;
	
	//글 쓰기
	@Override
	public int writeBoard(BoardDTO boardDTO) throws Exception{
		int result = boardDAO.writeBoard(boardDTO);
		//System.out.println(BoardDTO.getReg_name()+ " 게시판 글 쓰기 Service");
		
		return result;
	}
	
	// 전체 글 갯수 계산
	@Override
	public int countBoard() throws Exception{
		int result = boardDAO.countBoard();
		System.out.println(result+ " : 게시판 글 갯수 조회 Service 완료!");
			
		return result;
	}
		
	// 글 보기
	@Override
	public BoardDTO readBoard(Integer idx) throws Exception{
		BoardDTO boardDTO = boardDAO.readBoard(idx);
		System.out.println(boardDTO.getReg_name()+ ", " +boardDTO.getIdx()+ " : 게시판 글 확인");
		
		return boardDTO;
	}
	
	// 글 수정
	@Override
	public int updateBoard(BoardDTO boardDTO) throws Exception{
		int result = boardDAO.updateBoard(boardDTO);
		System.out.println(boardDTO.getReg_name()+ ", " +boardDTO.getIdx()+ " : 게시판 글 수정 Service");
			
		return result;
	}
		
	// 글 삭제
	@Override
	public int deleteBoard(Integer idx) throws Exception{
		int result = boardDAO.deleteBoard(idx);
		System.out.println(idx+ " : 게시판 글 삭제 Service");
			
		return result;
	}
	
	//전체 글 목록
	 @Override
	 public List<BoardDTO> selectAllBoards() throws Exception {
	    return boardDAO.selectAllBoards();
	    }
	
	//글 내용 검색
	@Override
    public List<BoardDTO> selectBoardContent(String content) throws Exception {
        return boardDAO.selectBoardContent(content);
    }
	@Override
	public int insertReply(ReplyDTO replyDTO) throws Exception {
	    if (replyDTO.getSrc_idx() == replyDTO.getRef()) {
	        // 원글에 대한 첫 번째 댓글
	    } else {
	        // 대댓글 처리
	        ReplyDTO parentReply = boardDAO.selectReplyById(replyDTO.getSrc_idx());
	        replyDTO.setRef(parentReply.getRef());
	        replyDTO.setDepth(parentReply.getDepth() + 1);
	        int maxStep = boardDAO.selectMaxStepByRef(replyDTO.getRef());
	        replyDTO.setStep(maxStep + 1);
	    }
	    boardDAO.insertReply(replyDTO);
	    // 원글에 대한 첫 번째 댓글의 경우 ref update
	    if (replyDTO.getRef() == 0) {
	        replyDTO.setRef(replyDTO.getRep_idx());
	        boardDAO.updateReplyRef(replyDTO);
	    }
	    return replyDTO.getRep_idx();
	}

	/*
	@Override
	public int insertReply(ReplyDTO replyDTO) throws Exception {
	    if (replyDTO.getRef() <= 0) { 
	        boardDAO.insertReply(replyDTO); 
	        replyDTO.setRef(replyDTO.getRep_idx()); 
	        boardDAO.updateReplyRef(replyDTO); 
	    } else { // 대댓글
	        int maxStep = boardDAO.selectMaxStepByRef(replyDTO.getRef()); 
	        replyDTO.setStep(maxStep + 1); 
	        replyDTO.setDepth(replyDTO.getDepth() + 1); 
	        boardDAO.insertReply(replyDTO); 
	    }
	    return replyDTO.getRep_idx(); 
	}*/

	/*
	@Override
    public int insertReply(ReplyDTO replyDTO) throws Exception {
		
		if (replyDTO.getSrc_idx() == replyDTO.getRef()) { 
	        replyDTO.setRef(replyDTO.getRep_idx()); 
	        replyDTO.setStep(0);
	        replyDTO.setDepth(0);
	    } else { // 대댓글인 경우
	        // 같은 ref를 가진 댓글 중 가장 큰 step 값을 조회
	        int maxStep = sqlSession.selectOne(namespace + ".selectMaxStep", Map.of("ref", replyDTO.getRef()));
	        replyDTO.setStep(maxStep + 1); 
	        replyDTO.setDepth(replyDTO.getDepth() + 1); 
	    }
		
        return boardDAO.insertReply(replyDTO);
    }*/
	
	
	@Override
    public ReplyDTO selectReplyById(int rep_idx) throws Exception {
        return boardDAO.selectReplyById(rep_idx);
    }
    @Override
    public List<ReplyDTO> selectRepliesBySrcIdx(int src_idx) throws Exception {
        return boardDAO.selectRepliesBySrcIdx(src_idx);
    }

    @Override
    public int updateReply(ReplyDTO replyDTO) throws Exception {
        return boardDAO.updateReply(replyDTO);
    }

    @Override
    public int deleteReply(int rep_idx) throws Exception {
        return boardDAO.deleteReply(rep_idx);
    }
    
    
    @Override
    public void updateReplyRef(ReplyDTO replyDTO) throws Exception {
    	 boardDAO.updateReplyRef(replyDTO);
    }
}
