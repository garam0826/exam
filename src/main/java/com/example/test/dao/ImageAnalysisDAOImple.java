package com.example.test.dao;

import com.example.test.dto.ImageAnalysisResult;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ImageAnalysisDAOImple implements ImageAnalysisDAO {

    @Autowired
    private SqlSession sqlSession;
    private static final String namespace = "com.exam.mappers.ImageAnalysisMapper";

    @Override
    public ImageAnalysisResult findByDesertionNo(String desertionNo) {
        return sqlSession.selectOne(namespace + ".findByDesertionNo", desertionNo);
    }

    @Override
    public void save(ImageAnalysisResult result) {
        sqlSession.insert(namespace + ".save", result);
    }
}
