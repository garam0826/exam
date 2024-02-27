package com.example.test.dao;

import com.example.test.dto.ImageAnalysisResult;

public interface ImageAnalysisDAO {
    ImageAnalysisResult findByDesertionNo(String desertionNo);
    void save(ImageAnalysisResult result);
}