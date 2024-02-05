package com.example.test;

import com.example.test.model.PredictionData;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PredictionController {

    private final List<PredictionData> predictions = new ArrayList<>();

    @PostMapping("/predictions")
    public ResponseEntity<?> createPrediction(@RequestBody PredictionData data) {
        predictions.add(data);  // 받은 데이터를 리스트에 추가
        return ResponseEntity.ok().body("Data received successfully");
    }

    @GetMapping("/predictions")
    public ResponseEntity<List<PredictionData>> getPredictions() {
        return ResponseEntity.ok().body(predictions);  // 저장된 데이터 반환
    }
}
