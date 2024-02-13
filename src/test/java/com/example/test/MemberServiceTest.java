package com.example.test;

import com.example.test.dao.MemberDAO;
import com.example.test.dto.MemberDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Scanner;


@SpringBootTest
public class MemberServiceTest {
    @Autowired
    private MemberDAO memberDAO;

    Scanner sc = new Scanner(System.in);

    @Test
    public void test_Program() throws Exception {
        MemberDTO memberDTO = new MemberDTO();
        int menu = 0;

        while(true) {
            System.out.println("메뉴 : 1.회원가입, 2.회원정보 조회, 3.회원정보 수정, 4.회원탈퇴, 5.Login, 6.회원ID 찾기, 7.비밀번호 찾기, 8.종료");
            System.out.println("메뉴를 선택해주세요.");
            menu = sc.nextInt();

            if(menu == 1) {
                System.out.print("회원가입 정보 6개 입력하시오");
                memberDTO.setMem_id(sc.next());

                memberDTO.setMem_pw(sc.next());

                memberDTO.setMem_name(sc.next());

                memberDTO.setBirth(sc.next());

                memberDTO.setPhone(sc.next());

                memberDTO.setEmail(sc.next());

                /*
                 * String mem_id = sc.nextLine(); String mem_pw = sc.nextLine(); String mem_name
                 * = sc.nextLine(); String birth = sc.nextLine(); String phone = sc.nextLine();
                 * String email = sc.nextLine();
                 */

                memberDAO.insertMember(memberDTO);
                //memberDAO.getTime();
            }else if(menu == 2) {

                System.out.println("조회할 ID : ");
                String mem_id = sc.next();

                memberDTO = memberDAO.selectMember(mem_id);

                System.out.println(mem_id+ "님의 회원정보 : " +memberDTO);

                System.out.println("회원정보 조회 성공");



            }else if(menu == 3) {

                System.out.println("수정할 ID : ");
                memberDTO.setMem_id(sc.next());

                System.out.println("수정할 비밀번호 : ");
                memberDTO.setMem_pw(sc.next());

                System.out.println("수정할 이름 : ");
                memberDTO.setMem_name(sc.next());

                System.out.println("수정할 전화번호: ");
                memberDTO.setPhone(sc.next());

                System.out.println("수정할 이메일: ");
                memberDTO.setEmail(sc.next());

                memberDAO.updateMember(memberDTO);

                System.out.println("회원정보 수정 완료");


            }else if(menu == 4) {

                System.out.println("탈퇴할 ID : ");
                String mem_id = sc.next();

                memberDAO.deleteMember(mem_id);

                System.out.println("회원탈퇴 성공");


            }else if(menu == 5) {


                boolean result = false;

                System.out.println("로그인할 ID : ");
                String mem_id = sc.next();

                System.out.println("비밀번호를 입력 : ");
                String mem_pw = sc.next();

                String db_pw = memberDAO.selectPw(mem_id);

                if(mem_pw.equals(db_pw)) {
                    result = true;
                    System.out.println("비밀번호 확인 : " +result);

                    System.out.println("로그인 성공");
                }else {
                    result = false;
                    System.out.println("비밀번호 확인 : " +result);

                    System.out.println("로그인 실패");
                }
            }else if(menu == 6) {

                System.out.println("아이디를 찾을 회원의 이름을 입력하세요: ");
                String mem_name = sc.next();

                System.out.println("아이디를 찾을 회원의 생년월일을 입력하세요 ");
                String birth = sc.next();

                try {
                    MemberDTO memberInfo = memberDAO.selectMemberByNameAndBirth(mem_name, birth);
                    if (memberInfo != null) {
                        System.out.println(mem_name + "님의 아이디는 " + memberInfo.getMem_id() + "입니다.");
                    } else {
                        System.out.println("입력하신 정보와 일치하는 회원 정보가 없습니다.");
                    }
                } catch (Exception e) {
                    System.out.println("오류가 발생");
                    e.printStackTrace();
                }
            }else if(menu == 7) {

            }else {

            }
        }
    }
}

