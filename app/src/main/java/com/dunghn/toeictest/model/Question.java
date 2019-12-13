package com.dunghn.toeictest.model;

public class Question {
    public static String CONTENT = "CONTENT";
    public static String CORRECTANSWER = "CORRECTANSWER";
    public static String NUMBERANSWER = "NUMBERANSWER";
    private int numberAnswer;  //số lượng đáp án Correct
    private int correctAnswer; //đáp án dúng
    private int customerAnswer;    //đáp án của người làm
    private String content;  //nội dung câu hỏi
    private static boolean isCorrect; //người làm bài đã làm đúng hay sai

    public Question(int _numberAnswer, int _correctAnswer, String _content) {
        setNumberAnswer(_numberAnswer);
        setCorrectAnswer(_correctAnswer);
        setContent(_content);
        setCustomerAnswer(0);
        setIsCorrect(false);
    }

    public Question() {
        setNumberAnswer(4);
        setCorrectAnswer(1);
        setContent("");
        setCustomerAnswer(0);
        setIsCorrect(false);
    }

    public static boolean isCorrect() {
        return isCorrect;
    }

    public static void setIsCorrect(boolean isCorrect) {
        Question.isCorrect = isCorrect;
    }


    public int getCustomerAnswer() {
        return customerAnswer;
    }

    public void setCustomerAnswer(int customerAnswer) {
        this.customerAnswer = customerAnswer;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(int correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public int getNumberAnswer() {
        return numberAnswer;
    }

    public void setNumberAnswer(int numberAnswer) {
        this.numberAnswer = numberAnswer;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
