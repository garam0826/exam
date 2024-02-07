package com.example.test.controller;

import com.example.test.dto.MemberDTO;
import com.example.test.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/member")
public class ApiMemberController {

    @Autowired
    private MemberService memberService; // 회원 정보를 조회하는 로직을 처리

    // 로그인 처리 API
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

    // 회원가입 API
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

}

