package com.dunghn.toeictest.model;

public class QuestionPart3_4 extends Question {
    public static String TABLE_NAME = "Question_Part3And4";
    public static String ANSWER_A = "ANSWERA";
    public static String ANSWER_B = "ANSWERB";
    public static String ANSWER_C = "ANSWERC";
    public static String ANSWER_D = "ANSWERD";
    public static String ID = "ID";
    private Integer id;
    private String answerA;
    private String answerB;
    private String answerC;
    private String answerD;

    public String getAnswerA() {
        return answerA;
    }

    public void setAnswerA(String answerA) {
        this.answerA = answerA;
    }

    public String getAnswerB() {
        return answerB;
    }

    public void setAnswerB(String answerB) {
        this.answerB = answerB;
    }

    public String getAnswerC() {
        return answerC;
    }

    public void setAnswerC(String answerC) {
        this.answerC  = answerC;
    }

    public String getAnswerD() {
        return answerD;
    }

    public void setAnswerD(String answerD) {
        this.answerD = answerD;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
