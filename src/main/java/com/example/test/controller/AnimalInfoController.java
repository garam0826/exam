package com.example.test.controller;

import com.example.test.dto.AnimalInfo;
import com.example.test.dto.ImageAnalysisResult;
import com.example.test.service.AnimalInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/animal")
public class AnimalInfoController {

    @Autowired
    private AnimalInfoService animalInfoService;

    @GetMapping("/info")
    public ResponseEntity<List<AnimalInfo>> getAnimalInfo(
            @RequestParam String startDate,
            @RequestParam String endDate,
            @RequestParam String kind,
            @RequestParam String uprCd,
            @RequestParam String orgCd) {

        List<AnimalInfo> animalInfos = animalInfoService.getAnimalInfo(startDate, endDate, kind, uprCd, orgCd);
        return ResponseEntity.ok(animalInfos);
    }


    @GetMapping("/analyze")
    public ResponseEntity<ImageAnalysisResult> analyzeImage(
            @RequestParam("desertionNo") String desertionNo,
            @RequestParam("popfile") String imageUrl) {

        // 데이터베이스에서 분석 결과 조회
        ImageAnalysisResult analysisResult = animalInfoService.getAnalysisResult(desertionNo);

        // 결과가 없으면 이미지 분석 수행
        if (analysisResult == null) {
            try {
                // 이미지 분석 및 결과 저장
                analysisResult = animalInfoService.analyzeAndSaveImage(desertionNo, imageUrl);
            } catch (Exception e) {
                // 오류 처리
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
            }
        }

        // 분석 결과를 클라이언트에 반환
        return ResponseEntity.ok(analysisResult);
    }
}
