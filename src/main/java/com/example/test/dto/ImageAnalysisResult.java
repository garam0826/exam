package com.example.test.dto;

public class ImageAnalysisResult {
    private String desertionNo;
    private String className1;
    private double probability1;
    private String className2;
    private double probability2;
    private String className3;
    private double probability3;

    // 기본 생성자
    public ImageAnalysisResult() {
    }

    // getter와 setter 메서드
    public String getDesertionNo() {
        return desertionNo;
    }

    public void setDesertionNo(String desertionNo) {
        this.desertionNo = desertionNo;
    }

    public String getClassName1() {
        return className1;
    }

    public void setClassName1(String className1) {
        this.className1 = className1;
    }

    public double getProbability1() {
        return probability1;
    }

    public void setProbability1(double probability1) {
        this.probability1 = probability1;
    }

    public String getClassName2() {
        return className2;
    }

    public void setClassName2(String className2) {
        this.className2 = className2;
    }

    public double getProbability2() {
        return probability2;
    }

    public void setProbability2(double probability2) {
        this.probability2 = probability2;
    }

    public String getClassName3() {
        return className3;
    }

    public void setClassName3(String className3) {
        this.className3 = className3;
    }

    public double getProbability3() {
        return probability3;
    }

    public void setProbability3(double probability3) {
        this.probability3 = probability3;
    }

    private String popfile;





    public String getPopfile() {
        return popfile;
    }

    public void setPopfile(String popfile) {
        this.popfile = popfile;
    }


    @Override
    public String toString() {
        return "ImageAnalysisResult{" +
                "desertionNo='" + desertionNo + '\'' +
                ", className1='" + className1 + '\'' +
                ", probability1=" + probability1 +
                ", className2='" + className2 + '\'' +
                ", probability2=" + probability2 +
                ", className3='" + className3 + '\'' +
                ", probability3=" + probability3 +
                ", popfile='" + popfile + '\'' +
                '}';
    }
}
