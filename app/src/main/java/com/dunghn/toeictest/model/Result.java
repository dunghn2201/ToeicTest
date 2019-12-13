package com.dunghn.toeictest.model;

public class Result {
    public static String TABLE_NAME="Result";
    public static String KQ_SCORE="SCORE";
    public static String KQ_TIME="TIME";
    public static String KQ_NAME="NAME";
    public static String KQ_ID="ID";
    //----
    private int id;
    private String name;
    private int score;
    private String time;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
