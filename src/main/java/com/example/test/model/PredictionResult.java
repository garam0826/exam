package com.example.test.model;

public class PredictionResult {
    private String className;
    private double probability;

    public PredictionResult() {
    }

    public PredictionResult(String className, double probability) {
        this.className = className;
        this.probability = probability;
    }


    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public double getProbability() {
        return probability;
    }

    public void setProbability(double probability) {
        this.probability = probability;
    }
}

