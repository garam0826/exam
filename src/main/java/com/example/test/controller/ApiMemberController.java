package com.example.test.controller;

import com.example.test.dto.MemberDTO;
import com.example.test.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member")
public class ApiMemberController {

    @Autowired
    private MemberService memberService; // 회원 정보를 조회하는 로직을 처리

    // 로그인 처리
    @PostMapping("/login")
    public ResponseEntity<MemberDTO> login(@RequestBody MemberDTO memberDTO) {
        try {
            MemberDTO loginUser = memberService.login(memberDTO);
            if (loginUser != null) {
                // 로그인 성공
                return new ResponseEntity<>(loginUser, HttpStatus.OK);
            } else {
                // 로그인 실패
                return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 회원가입
    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody MemberDTO memberDTO) {
        try {
            int result = memberService.insertMember(memberDTO);
            if (result > 0) {
                return new ResponseEntity<>("회원가입 성공", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("회원가입 실패", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //회원탈퇴
    @PostMapping("/mypage/resign")
    public ResponseEntity<String> deleteMember(@RequestBody MemberDTO memberDTO) {
        try {
            int result = memberService.deleteMember(memberDTO.getMem_id());
            if (result>0) {
                return new ResponseEntity<>("회원탈퇴 성공", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("회원탈퇴 실패", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //회원정보 조회
    @GetMapping("/mypage//myinfo/{mem_id}")
    public ResponseEntity<MemberDTO> getMemberInfo(@PathVariable String mem_id) {
        try {
            MemberDTO memberInfo = memberService.selectMember(mem_id);
            if (memberInfo != null) {
                return new ResponseEntity<>(memberInfo, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //회원정보 수정
    @PutMapping("/mypage/myedit")
    public ResponseEntity<String> updateMember(@RequestBody MemberDTO memberDTO) {
        try {
            int result = memberService.updateMember(memberDTO);
            if (result > 0) {
                return new ResponseEntity<>("회원정보 수정 성공", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("회원정보 수정 실패", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //아이디 찾기
    @GetMapping("/searchid")
    public ResponseEntity<?> findMemberId(@RequestParam String mem_name, @RequestParam String birth) {
        try {
            MemberDTO member = memberService.findMemberIdByNameAndBirth(mem_name, birth);
            if (member != null) {
                return new ResponseEntity<>(member.getMem_id(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("이름과 생년월일을 다시 확인해보세요", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //비밀번호 찾기(/searchpw)

    //찜(/mycart)





}

