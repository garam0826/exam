package com.example.test.service;

import com.example.test.dao.ImageAnalysisDAO;
import com.example.test.dto.AnimalInfo;
import com.example.test.dto.ImageAnalysisResult;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class AnimalInfoService {

    public List<AnimalInfo> getAnimalInfo(String startDate, String endDate, String kind, String uprCd, String orgCd) {

        List<AnimalInfo> animalInfoList = new ArrayList<>();

        String url = "http://apis.data.go.kr/1543061/abandonmentPublicSrvc/abandonmentPublic";

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("serviceKey", "XoUKvRBvuJnQoblCd23mVs4%2F4%2FwBHivSh8CpK47qsUpJViqLxxEjo2BOEF18WpLUqBID%2FxnnJMNq8NdiDf83pw%3D%3D")
                .queryParam("bgnde", startDate)
                .queryParam("endde", endDate)
                .queryParam("upkind", "417000")  // 강이지 코드로 고정
                .queryParam("kind", kind) // 변수 오기입 수정
                .queryParam("upr_cd", uprCd)
                .queryParam("org_cd", orgCd)
                .queryParam("care_reg_no", "311322200900001") // 일단 아무 보호소 코드 입력
                .queryParam("pageNo", "1")
                .queryParam("numOfRows", "10")
                .queryParam("_type", "json");

        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(builder.toUriString(), String.class);

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode root = objectMapper.readTree(response);
            JsonNode items = root.path("response").path("body").path("items").path("item");

            if (items.isArray()) {
                for (JsonNode item : items) {
                    AnimalInfo animalInfo = objectMapper.treeToValue(item, AnimalInfo.class);
                    animalInfoList.add(animalInfo);
                }
            } else {
                // 단일 객체일때
                AnimalInfo animalInfo = objectMapper.treeToValue(items, AnimalInfo.class);
                animalInfoList.add(animalInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();

        }

        return animalInfoList;
    }

    @Autowired
    private ImageAnalysisDAO imageAnalysisDAO;


    public ImageAnalysisResult analyzeAndSaveImage(String desertionNo, String imageUrl) throws Exception {
        ProcessBuilder processBuilder = new ProcessBuilder("python", "scripts/analyze_image.py", imageUrl);
        Process process = processBuilder.start();

        // 스크립트의 출력 읽기
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream(), "UTF-8"));
        List<String> analysisData = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
            analysisData.add(line);
        }

        // 에러 스트림 처리
        BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream(), "UTF-8"));
        String errorLine;
        while ((errorLine = errorReader.readLine()) != null) {
            System.err.println(errorLine);
        }

        int exitCode = process.waitFor();
        if (exitCode != 0) {
            throw new RuntimeException("Image analysis failed with exit code " + exitCode);
        }

        ImageAnalysisResult analysisResult = new ImageAnalysisResult();
        analysisResult.setDesertionNo(desertionNo);
        analysisResult.setPopfile(imageUrl);
        // 파싱 로직 수정
        for (String data : analysisData) {
            String[] parts = data.split(",");
            if (parts.length == 2) { // 파싱된 데이터가 정확히 2개인 경우만 처리
                if (analysisResult.getClassName1() == null) {
                    analysisResult.setClassName1(parts[0]);
                    analysisResult.setProbability1(Double.parseDouble(parts[1]));
                } else if (analysisResult.getClassName2() == null) {
                    analysisResult.setClassName2(parts[0]);
                    analysisResult.setProbability2(Double.parseDouble(parts[1]));
                } else if (analysisResult.getClassName3() == null) {
                    analysisResult.setClassName3(parts[0]);
                    analysisResult.setProbability3(Double.parseDouble(parts[1]));
                }
            }
        }

        imageAnalysisDAO.save(analysisResult);

        return analysisResult;
    }




    // desertionNo에 해당하는 이미지 분석 결과를 DB에서 조회
    public ImageAnalysisResult getAnalysisResult(String desertionNo) {
        // ImageAnalysisDAO를 통해 데이터베이스에서 desertionNo에 해당하는 분석 결과 조회
        return imageAnalysisDAO.findByDesertionNo(desertionNo);
    }

}
