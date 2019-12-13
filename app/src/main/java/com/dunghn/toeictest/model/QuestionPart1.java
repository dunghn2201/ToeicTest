package com.dunghn.toeictest.model;

public class QuestionPart1 extends Question {
    public static String TABLE_NAME = "Question_Part1";
    public static String ID = "ID";
    public static String IMAGENAME = "IMAGE_NAME";
    private int id;
    private String imageName;

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
