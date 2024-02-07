package com.example.test.controller;

import com.example.test.dto.BoardDTO;
import com.example.test.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/api/freeboard")
public class ApiBoardController {

    @Autowired
    private BoardService boardService;

    // 게시글 목록 조회 API
    @GetMapping("/list")
    public ResponseEntity<List<BoardDTO>> getBoardList() {
        try {
            List<BoardDTO> boardList = boardService.selectAllBoards();
            return new ResponseEntity<>(boardList, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

