package com.example.test.controller;

import com.example.test.dto.BoardDTO;
import com.example.test.dto.MemberDTO;
import com.example.test.dto.ReplyDTO;
import com.example.test.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/freeboard/")
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	//글 쓰기 page(성공)
	@GetMapping("/Write.do")
	public String writeBoard() throws Exception{
		System.out.println("BoardController 요청");
	
		return "freeboard/Write";
	}
	
	//글 쓰기 (성공), 댓글 까지 (성공)
		@PostMapping("/Write.do")
		public String writeBoard(HttpServletRequest request, @RequestParam String title, @RequestParam String content, Model model) throws Exception{
			System.out.println("BoardController 요청");
			HttpSession session = request.getSession();
			System.out.println(session);
			
			MemberDTO memberDTO = (MemberDTO)session.getAttribute("MemberInfo");
			System.out.println(memberDTO);
			BoardDTO boardDTO = new BoardDTO();
			boardDTO.setTitle(title);
			boardDTO.setReg_name(memberDTO.getMem_id());
			boardDTO.setContent(content);
			
			System.out.println(boardDTO);
			
			int result = boardService.writeBoard(boardDTO);
			
			
			model.addAttribute("result",result);
			
			return "/freeboard/List";
		}	
	
	// 글 보기 (성공)
	@RequestMapping("/Read.do")
	public String readBoard(@RequestParam("idx") int idx, Model model) throws Exception{
		System.out.println("BoardController 요청");
		//boardService.readBoard(idx);
		BoardDTO boardDTO = boardService.readBoard(idx);
		
		//ReplyDTO replyDTO = boardService.selectRepliesBySrcIdx(src_idx);
		
		System.out.println("조회된 BoardDTO: " + boardDTO);
		
		//idx-->src_idx로 변경하면 에러남 .
		List<ReplyDTO> replies = boardService.selectRepliesBySrcIdx(idx);
		
		System.out.println("조회된 List<ReplyDTO> " + replies);
		
	    model.addAttribute("freeboard", boardDTO);
	    model.addAttribute("replies", replies);
			
		return "/freeboard/Read";
	}
	
		
	//게시글 수정 page 이동(수정)
	@GetMapping("/Edit.do")
	public String editBoard(@RequestParam("idx") int idx, Model model) {
	    try {
	        BoardDTO boardDTO = boardService.readBoard(idx); // 게시글 정보 조회
	        if (boardDTO != null) {
	            model.addAttribute("freeboard", boardDTO); // 모델에 게시글 정보 추가
	            return "/freeboard/Edit"; // 수정 페이지로 이동
	        } else {
	            return "redirect:/freeboard/List.do"; // 게시글 정보가 없는 경우 목록으로 리다이렉트
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        return "redirect:/freeboard/List.do"; // 예외 발생 시 목록으로 리다이렉트
	    }
	}
	
	//게시글 수정 (성공) - reg_name을 값을 받아서 수정하지 않고 유지하려면, 컨트롤러에서 boardDTO를 업데이트하기 전에 기존 BoardDTO의 reg_name 값을 조회해서 새 boardDTO 객체에 설정해야 함.
	@PostMapping("/Update.do")
	public String updateBoard(@ModelAttribute("freeboard") BoardDTO boardDTO, RedirectAttributes redirectAttributes) {
	    try {
	        // 기존 게시글 정보를 조회합니다.
	        BoardDTO existingBoard = boardService.readBoard(boardDTO.getIdx());
	        
	        // 기존의 reg_name을 새로운 boardDTO에 설정합니다.
	        boardDTO.setReg_name(existingBoard.getReg_name());
	        
	        // 게시글을 업데이트합니다.
	        boardService.updateBoard(boardDTO);
	        
	        // 수정 완료 후 게시글 상세보기 페이지로 리다이렉트합니다.
	        redirectAttributes.addAttribute("idx", boardDTO.getIdx());
	        return "redirect:/freeboard/Read.do?idx=" + boardDTO.getIdx();

	    } catch (Exception e) {
	        e.printStackTrace();
	        // 오류 발생 시 게시글 목록 페이지로 리다이렉트합니다.
	        return "redirect:/freeboard/List"; 
	    }
	}

	//게시글 삭제 (성공) - mapper에서 select 할때 idx(나머지 정보도 같이 select 해야했음)
	@PostMapping("/Delete.do")
	public String deleteBoard(@RequestParam("idx") int idx,RedirectAttributes redirectAttributes) {
		 System.out.println("삭제하려고 받은 idx? " + idx);
		try {
            boardService.deleteBoard(idx);
            redirectAttributes.addFlashAttribute("message", "게시글이 성공적으로 삭제되었습니다.");
            return "redirect:/freeboard/List.do"; 
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("errorMessage", "게시글 삭제 중 오류가 발생했습니다.");
            return "redirect:/freeboard/Read.do?idx=" + idx; 
        }
	}
		
	
	
	//목록 조회 (성공)
	@GetMapping("/List.do")
	public String FreeBoardList(Model model) throws Exception{
		System.out.println("Info_BoardController 요청");
		List<BoardDTO> b_List= boardService.selectAllBoards();
		//System.out.println(b_List);
		
		model.addAttribute("FreeBoard",b_List);
		return "freeboard/List";
	}
	
	//게시글 댓글 갯수 
	


}
