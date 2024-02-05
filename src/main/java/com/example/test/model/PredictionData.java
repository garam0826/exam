package com.example.test.model;

import java.util.List;


public class PredictionData {
    private String imageUrl;
    private List<Prediction> predictions;

    // 기본 생성자
    public PredictionData() {
    }

    // 매개변수를 가진 생성자
    public PredictionData(String imageUrl, List<Prediction> predictions) {
        this.imageUrl = imageUrl;
        this.predictions = predictions;
    }

    // Getter 메서드
    public String getImageUrl() {
        return imageUrl;
    }

    public List<Prediction> getPredictions() {
        return predictions;
    }

    // Setter 메서드
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setPredictions(List<Prediction> predictions) {
        this.predictions = predictions;
    }

    // Prediction 내부 클래스
    public static class Prediction {
        private String className;
        private double probability;

        // 기본 생성자
        public Prediction() {
        }

        // 매개변수를 가진 생성자
        public Prediction(String className, double probability) {
            this.className = className;
            this.probability = probability;
        }

        // Getter 메서드
        public String getClassName() {
            return className;
        }

        public double getProbability() {
            return probability;
        }

        // Setter 메서드
        public void setClassName(String className) {
            this.className = className;
        }

        public void setProbability(double probability) {
            this.probability = probability;
        }
    }
}