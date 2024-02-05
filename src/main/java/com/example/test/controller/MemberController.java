package com.example.test.controller;

import com.example.test.dto.MemberDTO;
import com.example.test.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

//연결을 해야하니까, 필요한 것을 어노테이션으로 작성을 해야함. (이 클래스가 컨트롤러인지 인식을 함)
@Controller
//경로를 중간에 멤버가 들어오는게 있으면 membercontroller랑 연결해라.
@RequestMapping("/member/")
public class MemberController {
	//멤버 컨트롤러의 세부경로는 각 메서드마다 코딩을 해주면 됨.
	
	// MemberService
	@Autowired
	private MemberService memberService;
	
		
	// 회원가입 Page 이동
	@GetMapping("/RegisterPage.do")
	//단순페이지 이동이니까 get방식( 위에꺼랑 똑같은 의미가 됨)
	//@RequestMapping(value = "/RegisterPage.do", method=RequestMethod.GET)
	public String register_Page() throws Exception{
		System.out.println("MemberController 요청");
		
		//.html은 생략이 되어있는 것.
		return "/member/Register";
	}
	
	
	
	//아래꺼랑 똑같은 의미임.
	//@PostMapping(value = "/RegisterMyInfo.do", method=RequestMethod.POST)
	
	//
	
	
	
	// 회원가입 (post니까 뭘 처리 해 줘야 함)
	@RequestMapping("/Register_MyInfo.do")
	public String register_MyInfo(MemberDTO memberDTO, Model model) throws Exception{
	
		System.out.println("MemberController 요청");
		int result = memberService.insertMember(memberDTO);
		
		//모델에 데이터를 담아서 넘겨준다. if문 전이면 모든 페이지로 공통으로 들어갈 것이고, if문 안에 있으면 여기 페이지에서만...
		model.addAttribute("result",result);
		
		//result 값에 따라... 1명이 가입하면 1이 나오겠죠.
		if(result > 0) {
			
			System.out.println("회원가입 완료!");
			
			//리다이렉트(주소랑 뭐가 동일하게 나옴, 새로연결하는 느낌), 포워딩이랑 다음
			return "redirect:/";
		}else {
			System.out.println("회원가입 실패!");
			
			return "redirect:/";
		}
		
	
	
	}
	
	/*
	 * @RequestMapping(value = "/RegisterPage.do", method=RequestMethod.POST) public
	 * String register_Page(MemberDTO memberDTO) throws Exception{
	 * System.out.println("MemberController 요청");
	 * 
	 * return "/member/register"; }
	 */
	
	
	// 회원탈퇴
	@PostMapping("/deleteMember.do")
	public String resign_MyInfo(@RequestParam String mem_id, @RequestParam String mem_pw) throws Exception{
		
		boolean result = memberService.checkPW(mem_id, mem_pw);
		
		if(result) {
			System.out.println("비밀번호 확인 완료");
			
			return "redirect:/";
		}else {
			return "redirect:/member/MyInfo.do";
		}
		
			
	}
	
	// 회원정보 조회
	@GetMapping("/MyInfo.do")
	public String selectMember(HttpServletRequest request, Model model) throws Exception{
		System.out.println("MemberController 요청");
		HttpSession session = request.getSession();
		//String mem_id= (String)session.getAttribute("mem_id");
		//System.out.println("mem_id");
		System.out.println(session);
		
		MemberDTO memberDTO = (MemberDTO)session.getAttribute("MemberInfo");
		
		//System.out.println(memberDTO);
		//System.out.println(memberDTO.getMem_id());
		MemberDTO memberInfo = memberService.selectMember(memberDTO.getMem_id());
		
		//모델에 담아서 view에 뿌려주는.
		model.addAttribute("Info", memberInfo);
			
		return "/member/MyInfo";
	}
	
	// 회원정보 수정
	@PostMapping("/Update_MyInfo.do")
	public String update_MyInfo(HttpServletRequest request, @RequestParam("mem_id")String mem_id, @RequestParam("mem_pw")String mem_pw, @RequestParam("mem_name")String mem_name, @RequestParam("birth")String birth, @RequestParam("phone")String phone, @RequestParam("email")String email, HttpSession session) throws Exception{
		System.out.println("MemberController 요청");
			
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setMem_id(mem_id);
		memberDTO.setMem_pw(mem_pw);
		memberDTO.setMem_name(mem_name);
		memberDTO.setBirth(birth);
		memberDTO.setPhone(phone);
		memberDTO.setEmail(email);
		System.out.println(memberDTO);
			
		int result = memberService.updateMember(memberDTO);
			
		return "redirect:/member/MyInfo.do";
	}
	
	// 로그인(test 완료)
	
		@PostMapping("/Login.do")
		public String login_Member(@RequestParam String mem_id, @RequestParam String mem_pw, HttpSession session, Model model) throws Exception{
			System.out.println("MemberController 요청");
			boolean result = memberService.checkPW(mem_id, mem_pw);
			
			if(result) {
				System.out.println("비밀번호 확인 성공");
				
				MemberDTO memberDTO = memberService.selectMember(mem_id);
				
				session.setAttribute("MemberInfo", memberDTO);
				//session.setMaxInactiveInterval(10 * 60);
				//System.out.println("Session 설정");
				
				//System.out.println("Login 성공");
				
				//폴더이름,html이름
				return "main/Index";
			}else {
				System.out.println("비밀번호 확인 실패");
				System.out.println("Login 실패");
				
				return "redirect:/";
			}
		}
		
		
	//로그아웃
	@GetMapping("/Logout.do")
	public String logout(HttpServletRequest request) throws Exception{
		System.out.println("MemberController 요청");
		HttpSession session = request.getSession();
		
		System.out.println(session);
		
		session.invalidate();
		//null 값 뜨는지 확인 용으로 print
		System.out.println(session);
		return "redirect:/";
	}

}
