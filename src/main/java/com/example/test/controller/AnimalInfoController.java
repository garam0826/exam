package com.example.test.controller;

import com.example.test.dto.AnimalInfo;
import com.example.test.service.AnimalInfoService;
import org.springframework.beans.factory.annotation.Autowired;
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
}
