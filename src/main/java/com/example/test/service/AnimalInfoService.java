package com.example.test.service;

import com.example.test.dto.AnimalInfo;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

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

}
